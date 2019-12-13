package com.wdk.general.core.service.impl;

import com.wdk.general.core.dao.SchemaColumnsDao;
import com.wdk.general.core.model.BaseParam;
import com.wdk.general.core.model.DbMessage;
import com.wdk.general.core.model.SchemaColumns;
import com.wdk.general.core.service.CommonFileService;
import com.wdk.general.core.service.GenerateService;
import com.wdk.general.core.service.ModelService;
import com.wdk.general.core.service.PomService;
import com.wdk.general.core.utills.ColumnsUtil;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * created by wdk on 2019/12/12
 */
@Service
public class GenerateServiceImpl implements GenerateService {

    private Logger logger = LoggerFactory.getLogger(GenerateService.class);

    @Autowired
    private SchemaColumnsDao schemaColumnsDao;


    @Autowired
    private ModelService modelService;

    @Autowired
    private PomService pomService;

    @Autowired
    private CommonFileService commonFileService;


    /**
     * 开始生成文件
     *
     * @param tableName
     */
    @Override
    public void init(String[] tableName) {



        List<SchemaColumns> list = schemaColumnsDao.list(tableName);
        DbMessage dbMessage = UserContext.current().getDbMessage();

        Map<String, List<SchemaColumns>> map = list.stream().collect(Collectors.groupingBy(SchemaColumns::getTableName));


        BaseParam baseParam = new BaseParam();
        baseParam.setTableSchema(dbMessage.getDbname());
        for (Map.Entry<String, List<SchemaColumns>> entry : map.entrySet()) {
            if (null == entry.getValue() || entry.getValue().size() == 0) {
                continue;
            }
            baseParam.setTableName(entry.getKey());
            baseParam.setColumns(entry.getValue());
            baseParam.setTableName(ColumnsUtil.columns(entry.getKey(), "objName"));
//            baseParam.setTableComment(entry.getValue().get(0));
            baseParam.setLogger(true);
            modelService.init(baseParam, "com.wdk.test");

        }

    }
}
