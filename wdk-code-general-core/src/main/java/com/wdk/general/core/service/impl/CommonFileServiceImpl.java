package com.wdk.general.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.wdk.general.core.model.ProjectMetadata;
import com.wdk.general.core.service.CommonFileService;
import com.wdk.general.core.utils.FileUtil;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * created by wdk on 2019/12/13
 */
@Service
public class CommonFileServiceImpl implements CommonFileService {

    private static Logger logger = LoggerFactory.getLogger(CommonFileService.class);


    /**
     * 生成所有文件
     *
     * @param name
     * @param packages
     */
    @Override
    public void all(String name, String packages) {
        ProjectMetadata projectMetadata = UserContext.current().getProjectMetadata();


        //生成 LoginController.java 文件
        String filePath = UserContext.current().getProjectServerRoot() + "/src/main/java/" + packages.replaceAll("\\.", "/") + "/" + name + "ServerApplication.java";
        //获取文本内容
        String content = mainApplication(projectMetadata.getProJectJavaName(), projectMetadata.getPackages());


        try {
            FileUtil.write(filePath, content);
            logger.info("all write success .filename = LoginController.java");
        } catch (IOException e) {
            logger.info("all write error .filename = LoginController.java");
            e.printStackTrace();
        }

        //生成 LoginController.java 文件
        filePath = UserContext.current().getProjectRoot() + "/" + projectMetadata.getName() + "-server/src/main/java/" + projectMetadata.getPackages().replaceAll("\\.", "/") + "/web/controller/LoginController.java";
        //获取文本内容
        content = loginController(packages);

        try {
            FileUtil.write(filePath, content);
            logger.info("all write success .filename = LoginController.java");
        } catch (IOException e) {
            logger.info("all write error .filename = LoginController.java");
            e.printStackTrace();
        }

        //生成 LoginService.java 文件
        filePath = UserContext.current().getProjectRoot() + "/" + projectMetadata.getName() + "-server/src/main/java/" + projectMetadata.getPackages().replaceAll("\\.", "/") + "/service/LoginService.java";
        //获取文本内容
        content = loginService(packages);

        try {
            FileUtil.write(filePath, content);
            logger.info("all write success .filename = LoginService.java");
        } catch (IOException e) {
            logger.info("all write error .filename = LoginService.java");
            e.printStackTrace();
        }

        //生成 LoginServiceImpl.java 文件
        filePath = UserContext.current().getProjectRoot() + "/" + projectMetadata.getName() + "-server/src/main/java/" + projectMetadata.getPackages().replaceAll("\\.", "/") + "/service/impl/LoginServiceImpl.java";
        //获取文本内容
        content = loginServiceImpl(packages);

        try {
            FileUtil.write(filePath, content);
            logger.info("all write success .filename = LoginServiceImpl.java");
        } catch (IOException e) {
            logger.info("all write error .filename = LoginServiceImpl.java");
            e.printStackTrace();
        }


        //生成 RedisConstant.java 文件
        filePath = UserContext.current().getProjectRoot() + "/" + projectMetadata.getName() + "-server/src/main/java/" + projectMetadata.getPackages().replaceAll("\\.", "/") + "/common/constant/RedisConstant.java";
        //获取文本内容
        content = redisConstant(packages + ".common.constant");

        try {
            FileUtil.write(filePath, content);
            logger.info("all write success .filename = RedisConstant.java");
        } catch (IOException e) {
            logger.info("all write error .filename = RedisConstant.java");
            e.printStackTrace();
        }


        //生成 IndexPages.java 文件
        filePath = UserContext.current().getProjectRoot() + "/" + projectMetadata.getName() + "-server/src/main/java/" + projectMetadata.getPackages().replaceAll("\\.", "/") + "/web/pages/IndexPages.java";
        //获取文本内容
        content = indexPages(packages + ".web.pages");

        try {
            FileUtil.write(filePath, content);
            logger.info("all write success .filename = IndexPages.java");
        } catch (IOException e) {
            logger.info("all write error .filename = IndexPages.java");
            e.printStackTrace();
        }
    }

    /**
     * 生成运行文件
     */
    @Override
    public void run() {


        dockerManager();

        dockerfile();

        dockerCompose();


        shellFile();
    }

    /**
     * 启动函数配置
     *
     * @param name
     * @param packages
     */
    @Override
    public String mainApplication(String name, String packages) {

        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(packages).append(";\n\n")
                .append("import org.springframework.boot.SpringApplication;\n")
                .append("import org.springframework.boot.autoconfigure.SpringBootApplication;\n")
                .append("import org.mybatis.spring.annotation.MapperScan;\n")
                .append("import org.springframework.cloud.client.discovery.EnableDiscoveryClient;\n")
                .append("import org.springframework.transaction.annotation.EnableTransactionManagement;\n")
                .append("\n")
                .append("@SpringBootApplication\n")
                .append("@EnableDiscoveryClient\n")
                .append("@EnableTransactionManagement\n")
                .append("@MapperScan(\"").append(packages).append(".storage.dao\")\n")
                .append("public class ").append(name).append("ServerApplication {\n")
                .append("\n")
                .append("\tpublic static void main(String[] args) {\n")
                .append("\t\tSpringApplication.run(").append(name).append("ServerApplication.class, args);\n")
                .append("\t}\n")
                .append("}\n");


        return sb.toString();

    }

    /**
     * 生成dockerfile文件
     */
    @Override
    public void dockerfile() {
        //项目创建信息
        ProjectMetadata projectMetadata = UserContext.current().getProjectMetadata();
        logger.info("项目创建信息:{}", JSON.toJSONString(projectMetadata));
        if (null == projectMetadata) {
            logger.info("获取项目信息失败，生成结束。");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("FROM java:8-jre-alpine\n")
                .append("ADD ").append(projectMetadata.getName()).append("-").append(projectMetadata.getVersion()).append(".jar app.jar\n")
                .append("EXPOSE ").append(projectMetadata.getPoint()).append("\n")
                .append("ENV JAVA_OPTS=\"-Xms128m -Xmx128m\"\n")
                .append("ENTRYPOINT [ \"sh\", \"-c\", \"java $JAVA_OPTS -Dspring.profiles.active=dev -Djava.security.egd=file:/dev/./urandom -jar app.jar\"]\n");

        String rootPath = System.getProperty("user.dir");
        String file = rootPath + "/shell/" + projectMetadata.getName() + "/Dockerfile";

        try {
            FileUtil.write(file, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成docker-manager.sh
     */
    @Override
    public void dockerManager() {
        //项目创建信息
        ProjectMetadata projectMetadata = UserContext.current().getProjectMetadata();
        logger.info("项目创建信息:{}", JSON.toJSONString(projectMetadata));
        if (null == projectMetadata) {
            logger.info("获取项目信息失败，生成结束。");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("#!/bin/bash\n")
                .append("echo \"-------删除容器---------\"\n")
                .append("docker stop ").append(projectMetadata.getName()).append("\n")
                .append("docker rm ").append(projectMetadata.getName()).append("\n")
                .append("docker rmi ").append(projectMetadata.getName()).append(":0.0.1\n")
                .append("echo $(docker build -t ").append(projectMetadata.getName()).append(":0.0.1 .)\n")
                .append("#echo \"------请输入新的ContainerId------\"\n")
                .append("#read containerId --net=host\n")
                .append("docker run --privileged  -d -p ").append(projectMetadata.getPoint()).append(":").append(projectMetadata.getPoint()).append(" --name ").append(projectMetadata.getName()).append(" $(docker images | grep ").append(projectMetadata.getName()).append(" |grep 0.0.1| awk '{print $3}')");

        String rootPath = System.getProperty("user.dir");
        String file = rootPath + "/shell/" + projectMetadata.getName() + "/docker-manager.sh";

        try {
            FileUtil.write(file, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成docker-compose.yml文件
     */
    @Override
    public void dockerCompose() {

        //项目创建信息
        ProjectMetadata projectMetadata = UserContext.current().getProjectMetadata();
        logger.info("项目创建信息:{}", JSON.toJSONString(projectMetadata));
        if (null == projectMetadata) {
            logger.info("获取项目信息失败，生成结束。");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("version: \"2\" #版本号\n")
                .append("services:\n")
                .append("  ").append(projectMetadata.getName()).append(": #服务名称（不是容器名）\n")
                .append("\timage: \"ascdc/jdk8\"  #使用的镜像\n")
                .append("\tports:\n")
                .append("\t  - \"").append(projectMetadata.getPoint()).append(":").append(projectMetadata.getPoint()).append("\"  #暴露的端口信息和docker run -d -p 80:80 一样\n")
                .append("\trestart: \"always\" #重启策略，能够使服务保持始终运行，生产环境推荐使用\n")
                .append("\tcontainer_name: ").append(projectMetadata.getName()).append(" #容器名称\n")
                .append("\t#挂载文件ps:如果是挂载文件必须在容器中也指定名称，推荐是挂载文件夹，下面只是做个文件挂载的案例也可以\n")
                .append("\t#挂载文件夹/root/compose_test/jdk/:/data\n")
                .append("\tvolumes:  \n")
                .append("\t  - /opt/app/auto-code/").append(projectMetadata.getName()).append("/").append(projectMetadata.getName()).append("-").append(projectMetadata.getVersion()).append(".jar:/app.jar\n")
                .append("\t  #- /opt/app/wdk-code-general/auto-code:/script/auto-code\n")
                .append("\tentrypoint: java -Xms128m -Xmx128m -Dspring.profiles.active=dev -Djava.security.egd=file:/dev/./urandom -jar /app.jar #启动容器后执行的命令\n");

        String rootPath = System.getProperty("user.dir");
        String file = rootPath + "/shell/" + projectMetadata.getName() + "/docker-compose.yml";

        try {
            FileUtil.write(file, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建shell 执行脚本
     */
    @Override
    public void shellFile() {

        //项目创建信息
        ProjectMetadata projectMetadata = UserContext.current().getProjectMetadata();
        logger.info("项目创建信息:{}", JSON.toJSONString(projectMetadata));
        if (null == projectMetadata) {
            logger.info("获取项目信息失败，生成结束。");
            return;
        }

        String rootPath = System.getProperty("user.dir");
        StringBuilder sb = new StringBuilder();
        sb.append("#!/bin/bash\n")
                .append("echo \"-------开始复制文件到指定文件目录---------\"\n")
                .append("cd auto-code/").append(projectMetadata.getName()).append("/\n")
                .append("echo $(mvn clean)\n")
                .append("echo $(mvn package)\n")
                .append("rm -rf /opt/app/auto-code/").append(projectMetadata.getName()).append("/\n")
                .append("mkdir /opt/app/auto-code/").append(projectMetadata.getName()).append("\n")
                .append("cp ./target/").append(projectMetadata.getName()).append("-").append(projectMetadata.getVersion()).append(".jar /opt/app/auto-code/").append(projectMetadata.getName()).append("/\n")
                .append("cp ").append(rootPath).append("/shell/").append(projectMetadata.getName()).append("/* ").append("/opt/app/auto-code/").append(projectMetadata.getName()).append("/\n")
                .append("cd /opt/app/auto-code/").append(projectMetadata.getName()).append("/\n")
                .append("echo \"-------开始部署docker容器---------\"\n")
                .append("echo $(docker-compose up -d)\n");
        String file = rootPath + "/shell/" + projectMetadata.getName() + ".sh";

        try {
            FileUtil.write(file, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成登陆API
     */
    @Override
    public String loginController(String packages) {

        StringBuffer sb = new StringBuffer();
        sb.append("package ").append(packages).append(".web.controller;\n")
                .append("\n")
                .append("import ").append(packages).append(".common.enums.ResponseStatusEnum;\n")
                .append("import ").append(packages).append(".common.model.ResponseVo;\n")
                .append("import ").append(packages).append(".service.LoginService;\n")
                .append("import ").append(packages).append(".web.args.LoginArgs;\n")
                .append("import org.springframework.beans.factory.annotation.Autowired;\n")
                .append("import org.springframework.web.bind.annotation.*;\n")
                .append("import org.springframework.web.bind.annotation.RequestParam;\n")
                .append("import org.springframework.web.bind.annotation.RestController;\n")
                .append("\n")
                .append("/**\n")
                .append(" * created by wdk on 2019/12/23\n")
                .append(" */\n")
                .append("@RestController\n")
                .append("@RequestMapping(\"api\")\n")
                .append("public class LoginController {\n")
                .append("\n")
                .append("\t@Autowired\n")
                .append("\tprivate LoginService loginService;\n")
                .append("\n")
                .append("\n")
                .append("\t@PostMapping(value = \"login\")\n")
                .append("\tpublic ResponseVo<String> login(@RequestBody LoginArgs loginArgs){\n")
                .append("\n")
                .append("\t\tResponseVo<String> result=loginService.login(loginArgs.getUsername(),loginArgs.getPassord());\n")
                .append("\n")
                .append("\n")
                .append("\t\treturn result;\n")
                .append("\t}\n\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 校验token是否存在\n")
                .append("\t *\n")
                .append("\t * @param tokenValue\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\t@GetMapping(value = \"token/check\")\n")
                .append("\tpublic ResponseVo<Boolean> tokenCheck(@RequestParam(\"tokenValue\") String tokenValue) {\n")
                .append("\n")
                .append("\t\tResponseVo<Boolean> result = loginService.tokenCheck(tokenValue);\n")
                .append("\n")
                .append("\n")
                .append("\t\treturn result;\n")
                .append("\t}\n\n")
                .append("}\n");

        return sb.toString();
    }

    /**
     * 生成 loginService文件
     *
     * @param packages
     * @return
     */
    @Override
    public String loginService(String packages) {

        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packages).append(".service;\n")
                .append("\n")
                .append("import ").append(packages).append(".common.model.ResponseVo;\n")
                .append("\n")
                .append("/**\n")
                .append(" * created by wdk on 2019/12/23\n")
                .append(" */\n")
                .append("public interface LoginService {\n")
                .append("\t/**\n")
                .append("\t * 登陆接口\n")
                .append("\t *\n")
                .append("\t * @param username\n")
                .append("\t * @param password\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\tResponseVo<String> login(String username, String password);\n\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 校验token\n")
                .append("\t *\n")
                .append("\t * @param tokenValue\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\tResponseVo<Boolean> tokenCheck(String tokenValue);\n\n")
                .append("}\n");

        return sb.toString();
    }

    /**
     * 生成 loginServiceImpl
     *
     * @param packages
     * @return
     */
    @Override
    public String loginServiceImpl(String packages) {

        //项目创建信息
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packages).append(".service.impl;\n")
                .append("\n")
                .append("import ").append(packages).append(".common.constant.RedisConstant;\n")
                .append("import ").append(packages).append(".redis.RedisStringDao;\n")
                .append("import ").append(packages).append(".utils.JwtUtils;\n")
                .append("import ").append(packages).append(".common.enums.ResponseStatusEnum;\n")
                .append("import ").append(packages).append(".common.model.ResponseVo;\n")
                .append("//import ").append(packages).append(".model.SysUser;\n")
                .append("import ").append(packages).append(".service.LoginService;\n")
                .append("//import ").append(packages).append(".service.SysUserService;\n")
                .append("import ").append(packages).append(".utils.PasswordUtil;\n")
                .append("import org.slf4j.Logger;\n")
                .append("import com.alibaba.druid.util.StringUtils;\n")
                .append("import org.slf4j.LoggerFactory;\n")
                .append("import org.springframework.beans.BeanUtils;\n")
                .append("import org.springframework.beans.factory.annotation.Autowired;\n")
                .append("import org.springframework.stereotype.Service;\n")
                .append("\n")
                .append("import java.util.Map;\n")
                .append("import java.util.HashMap;\n")
                .append("\n")
                .append("/**\n")
                .append(" * created by wdk on 2019/12/23\n")
                .append(" */\n")
                .append("@Service\n")
                .append("public class LoginServiceImpl implements LoginService {\n")
                .append("\n")
                .append("\n")
                .append("\tprivate static Logger logger = LoggerFactory.getLogger(LoginService.class);\n")
                .append("\n\n")
                .append("\t@Autowired\n")
                .append("\tRedisStringDao redisStringDao;\n\n")
                .append("\t//@Autowired\n")
                .append("\t//private SysUserService sysUserService;\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 登陆接口\n")
                .append("\t *\n")
                .append("\t * @param username\n")
                .append("\t * @param password\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\t@Override\n")
                .append("\tpublic ResponseVo<String> login(String username, String password) {\n")
                .append("\n")
                .append("\t\t//查询用户信息\n")
                .append("\t\t//SysUser sysUser = new SysUser();\n")
                .append("\t\t//sysUser.setUsername(username);\n")
                .append("\t\t//List<SysUser> list = sysUserService.list(sysUser);\n")
                .append("\n")
                .append("\n")
                .append("\t\t//if (list.isEmpty()) {\n")
                .append("\t\t\t//logger.info(\"login  用户不存在 .username={}\", username);\n")
                .append("\t\t\t//return new ResponseVo(ResponseStatusEnum.PARRAM_ERROR.getCode(), \"用户不存在\");\n")
                .append("\t\t//} else {\n")
                .append("\t\t\t//for (SysUser user1 : list) {\n")
                .append("\t\t\t\t//if (PasswordUtil.encrypt(password, \"1qaz1qaz\").equals(user1.getPassword())){\n")
                .append("\t\t\t\t\t//return new ResponseVo(ResponseStatusEnum.SUCCESS.getCode(), \"登录成功\");\n")
                .append("\t\t\t\t//}\n")
                .append("\t\t\t//}\n")
                .append("\t\t//}\n\n")
                .append("\t\t//生成 token\n")
                .append("\t\tMap<String, Object> map = new HashMap<>();\n")
                .append("\t\tmap.put(\"username\", username);\n")
                .append("\t\tString token = JwtUtils.createJWT(map);\n\n")
                .append("\t\t//保存到 redis\n")
                .append("\t\tredisStringDao.set(\"token_\" + username, token, RedisConstant.TOKEN_TIME);\n")
                .append("\t\tlogger.info(\"login 登录失败。username={}\", username);\n")
                .append("\t\treturn new ResponseVo(ResponseStatusEnum.SUCCESS, token);\n\n")
                .append("\t}\n\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 校验token\n")
                .append("\t *\n")
                .append("\t * @param tokenValue\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\t@Override\n")
                .append("\tpublic ResponseVo<Boolean> tokenCheck(String tokenValue) {\n")
                .append("\t\tString check = redisStringDao.get(tokenValue);\n")
                .append("\t\tif (StringUtils.isEmpty(check)) {\n")
                .append("\t\t\treturn new ResponseVo(ResponseStatusEnum.SUCCESS, false);\n")
                .append("\t\t}\n")
                .append("\t\treturn new ResponseVo(ResponseStatusEnum.SUCCESS, true);\n")
                .append("\t}\n\n")
                .append("}\n");


        return sb.toString();
    }

    /**
     * 生成 redis 常量
     *
     * @param packages
     * @return
     */
    @Override
    public String redisConstant(String packages) {


        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packages).append(";\n")
                .append("\n")
                .append("/**\n")
                .append(" * created by wdk on 2019/12/24\n")
                .append(" */\n")
                .append("public class RedisConstant {\n")
                .append("\n")
                .append("\t//token 的过期时间\n")
                .append("\tpublic static final Integer TOKEN_TIME = 60 * 60;\n")
                .append("\n")
                .append("\t//字符串的默认过期时间\n")
                .append("\tpublic static final Integer _TIME = 5 * 60;\n")
                .append("}\n")
                .append("\n");

        return sb.toString();

    }

    /**
     * 生成页面主控制类
     *
     * @param packages
     * @return
     */
    @Override
    public String indexPages(String packages) {

        //项目创建信息

        StringBuffer sb = new StringBuffer();
        sb.append("package ").append(packages).append(";\n")
                .append("\n")
                .append("import org.springframework.stereotype.Controller;\n")
                .append("import org.springframework.web.bind.annotation.RequestMapping;\n")
                .append("\n")
                .append("@Controller\n")
                .append("@RequestMapping(\"pages\")\n")
                .append("public class IndexPages {\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 进入后台管理菜单\n")
                .append("\t *\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\t@RequestMapping(value = \"menus\",name = \"进入后台管理菜单\")\n")
                .append("\tpublic String menus() {\n")
                .append("\n")
                .append("\n")
                .append("\t\treturn \"menus\";\n")
                .append("\t}\n")
                .append("}\n");

        return sb.toString();

    }
}
