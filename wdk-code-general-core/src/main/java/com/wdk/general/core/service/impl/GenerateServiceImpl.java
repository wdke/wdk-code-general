package com.wdk.general.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.wdk.general.core.common.model.UserContext;
import com.wdk.general.core.dao.SchemaColumnsDao;
import com.wdk.general.core.dao.SchemaTablesDao;
import com.wdk.general.core.model.*;
import com.wdk.general.core.service.*;
import com.wdk.general.core.utils.CommonFileUtils;
import com.wdk.general.core.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * created by wdk on 2019/12/12
 */
@Service
public class GenerateServiceImpl implements GenerateService {

    private Logger logger = LoggerFactory.getLogger(GenerateService.class);


    @Value("${staticFile}")
    private String staticFile;

    @Autowired
    private SchemaColumnsDao schemaColumnsDao;

    @Autowired
    private SchemaTablesDao schemaTablesDao;


    @Autowired
    private ModelService modelService;

    @Autowired
    private MapperDaoService mapperDaoService;

    @Autowired
    private MapperXmlService mapperXmlService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private MvcControllerService mvcControllerService;

    @Autowired
    private HtmlService htmlService;

    @Autowired
    private RestControllerService restControllerService;


    /**
     * 开始生成文件
     *
     * @param tableName
     */
    @Override
    public void init(String[] tableName) {

        logger.info("init start param【tableName->{}】", JSON.toJSONString(tableName));

        //获取数据源
        DbMessage dbMessage = UserContext.current().getDbMessage();
        logger.info("获取数据源:{}", JSON.toJSONString(dbMessage));
        if (null == dbMessage) {
            logger.info("获取数据源失败，生成结束。");
            return;
        }

        //项目创建信息
        ProjectMetadata projectMetadata = UserContext.current().getProjectMetadata();
        logger.info("项目创建信息:{}", JSON.toJSONString(projectMetadata));
        if (null == projectMetadata) {
            logger.info("获取项目信息失败，生成结束。");
            return;
        }

        //获取相关表信息
        List<Tables> list1 = schemaTablesDao.list(tableName);
        logger.info("获取相关表信息Tables size={}", list1.size());
        if (list1.size() == 0) {
            logger.info("数据表为空，生成结束。");
            return;
        }
        //生成菜单主页
        String menus = htmlService.menus(list1);
        //生成主页
        String rootFile = UserContext.current().getProjectServerRoot() + "/src/main/resources/templates/menus.html";
        try {
            logger.info("生成 menus.html文件");
            FileUtil.write(rootFile, menus);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("生成菜单主页成功。");

        //表由list转map
        Map<String, Tables> collect = list1.stream().collect(Collectors.toMap(Tables::getTableName, tables -> tables));
        logger.info("表由list转map成功！");

        //获取相关表字段信息
        List<SchemaColumns> list = schemaColumnsDao.list(tableName);
        logger.info("获取相关表字段信息 size={}", list.size());
        if (null == list || list.size() == 0) {
            logger.info("获取表字段为空，生成结束。");
            return;
        }
        //生成mybatis插件
//        GeneratorConfigUtil.init(list1, list);

//        FileUtil.runMybatisCoreJar(UserContext.current().getMybatisCorePath(), FilePathConstant.generatorConfig());

        //项目生成路径
        String rootPath = UserContext.current().getProjectServerRoot() + "/src/main/java/" + projectMetadata.getPackages().replaceAll("\\.", "/");
        logger.info("项目生成路径:{}", JSON.toJSONString(rootPath));

        //生成通用的文件
        CommonFileUtils.all(rootPath, projectMetadata.getPackages());
        logger.info("生成通用的文件结束！");

        //copy静态文件
        FileUtil.fileCopyByCmd(staticFile, UserContext.current().getProjectRoot() + "/" + projectMetadata.getName() + "-server/src/main/resources");
        logger.info("copy静态文件结束！");

        //根据表名分组
        Map<String, List<SchemaColumns>> map = list.stream().collect(Collectors.groupingBy(SchemaColumns::getTableName));
        logger.info("根据表名分组结束！");

        //设置BaseParam参数数据
        BaseParam baseParam = null;
        //循环生成各个表对应的文件
        logger.info("循环生成各个表对应的文件开始 size::{}", map.size());
        for (Map.Entry<String, List<SchemaColumns>> entry : map.entrySet()) {
            logger.info("开始生成tableName={}相关文件。", entry.getKey());
            if (null == entry.getValue() || entry.getValue().size() == 0) {
                logger.info("tableName={}生成失败，无表字段属性，生成结束。", entry.getKey());
                continue;
            }
            baseParam = new BaseParam();
            //参数初始化
            baseParam.setTableSchema(dbMessage.getDbname());
            baseParam.setTableName(entry.getKey());
            baseParam.setColumns(entry.getValue());
            baseParam.setKeys(entry.getValue().stream().filter(obj -> "PRI".equals(obj.getColumnKey())).collect(Collectors.toList()));
            baseParam.setModelName(collect.get(entry.getKey()).getModelName());
            baseParam.setModelObjName(collect.get(entry.getKey()).getModelObjName());
            baseParam.setTableComment(collect.get(entry.getKey()).getTableComment());
            baseParam.setLogger(false);
            baseParam.setMybatisCore(false);

            //生成实体
//            modelService.init(baseParam, projectMetadata.getPackages());

            if (!baseParam.isMybatisCore()) {
                //生成dao
                mapperDaoService.initDao(baseParam, projectMetadata.getPackages());

                //生成xml
                mapperXmlService.mapperXml(baseParam, projectMetadata.getPackages());
            }

            //生成service
            serviceService.init(baseParam, projectMetadata.getPackages());

            //生成 PageController
            mvcControllerService.createFile(baseParam, projectMetadata.getPackages());

            //生成 APIController
            restControllerService.init(baseParam, projectMetadata.getPackages());

            //生成相关的HTML文件
            htmlService.all(baseParam);

            logger.info("生成tableName={}相关文件结束。", entry.getKey());

        }
        logger.info("循环生成各个表对应的文件结束");

    }

    /**
     * 生成dao层次
     *
     * @param tableName
     */
    @Override
    public void autoMybatis(String[] tableName) {

    }
}
