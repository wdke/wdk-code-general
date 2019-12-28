package com.wdk.general.core.common.constant;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author wdke
 * @date 2019/9/11
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicConst {

    public static final String EXECUTOR_TOPIC = "executorTopic";

    public static final String SEND_BATCH_SMS = "sendBatchSms";

    public static final String COMMON_SEND_MSG = "common_send_msg";

    public static final String RESPONSE_VO = "RESPONSE_VO";
}
