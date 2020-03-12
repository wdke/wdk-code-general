package com.wdk.code.general.server.web.pages;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.wdk.general.core.common.enums.ResponseStatusEnum;
import com.wdk.general.core.common.model.ResponseVo;
import com.wdk.general.core.service.CommonFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created by wdk on 2019/12/19
 */
@Controller
@RequestMapping("operation/cmd")
public class OperationCmdController {

    private static Logger logger = LoggerFactory.getLogger(OperationCmdController.class);


    @Autowired
    private CommonFileService commonFileService;

    /**
     * 命令行输入输出
     *
     * @param cmd
     * @param model
     * @return
     */
    @RequestMapping(value = "")
    public String index(String cmd, Model model, HttpServletRequest request) {
        logger.info("index cmd:{}", cmd);
        if (StringUtils.isEmpty(cmd)) {
            if (StringUtils.isEmpty(request.getParameter("cmd"))) {

                model.addAttribute("msg", "");
                model.addAttribute("cmd", cmd);
                return "operationCmd/index";
            } else {
                cmd = request.getParameter("cmd");
            }
        }

        String[] cmds = cmd.split(" ");
        logger.info("cmds->{}", JSON.toJSONString(cmds));
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(cmds);
        try {
            Process start = builder.start();
            String msg = succesMsgLog(start);
            int i = start.waitFor();
            // 这里线程阻塞，将等待外部转换进程运行成功运行结束后，才往下执行
            if (i == 0) {
                model.addAttribute("msg", "操作成功");
                model.addAttribute("result", msg);
                model.addAttribute("cmd", cmd);
            }else{
                model.addAttribute("msg", "失败操作");
                model.addAttribute("result", msg);
                model.addAttribute("cmd", cmd);
            }
        } catch (Exception e) {
            logger.info("Exception msg->{}", e.getMessage());
            model.addAttribute("msg", "失败操作");
            model.addAttribute("result", e.getMessage());
            model.addAttribute("cmd", cmd);
            e.printStackTrace();
        }

        return "operationCmd/index";
    }


    /**
     * 命令行输入输出
     *
     * @return
     */
    @RequestMapping(value = "run")
    @ResponseBody
    public ResponseVo<String> run() {
        commonFileService.run();
        return new ResponseVo<>(ResponseStatusEnum.SUCCESS);
    }

    public String errorMsgLog(Process process) throws IOException {

        String line = null;
        BufferedReader buf = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while ((line = buf.readLine()) != null) {
            sb.append(line).append("\n");
            continue;
        }
        logger.error("error:{}", sb.toString());
        return sb.toString();
    }


    public String succesMsgLog(Process process) throws IOException {

        String line = null;
        BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while ((line = buf.readLine()) != null) {
            sb.append(line).append("\n");
            continue;
        }
        if (StringUtils.isEmpty(sb.toString())) {
            return errorMsgLog(process);
        }
        logger.info("result:{}", sb.toString());
        return sb.toString();

    }
}
