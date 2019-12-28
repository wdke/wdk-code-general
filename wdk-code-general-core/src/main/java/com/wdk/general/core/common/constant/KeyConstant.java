/*
 * Copyright 2012-2014 Wanda.cn All right reserved. This software is the
 * confidential and proprietary information of Wanda.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Wanda.cn.
 */
package com.wdk.general.core.common.constant;

/**
 * Created by wdk on
 */
public class KeyConstant {
	
	/** 返回结果类KEY定义 status:状态 */
	public static final String KEY_RESULT_STATUS = "code";
	/** 返回结果类KEY定义 message:消息内容 */
	public static final String KEY_RESULT_MESSAGE = "msg";
	/** 返回结果类KEY定义 data:数据内容 */
	public static final String KEY_RESULT_DATA = "data";
	/** 统一编码*/
	public static final String KEY_UTF8 = "UTF-8";
	/** 统一contentType*/
    public static final String KEY_CONTENT_TYPE = "application/json;charset=UTF-8";
	/** 设置MDC磐石日志KEY */
	public static final String KEY_API_LOGS = "apiLogs";
	/** 设置MDC磐石日志API调用序列号 */
	public static final String KEY_API_NO = "apiNo";
	
	public static final String KEY_TRACE_ID = "__trace_id";
	
}
