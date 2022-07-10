package com.chen.sysLogger;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author: MaybeBin
 * @createTime: 2022-07-10
 */
@Data
//@TableName("t_sys_log")
public class SysLog {

    private static final long serialVersionUID = -1;

    /**
     * 操作人id
     */
    private String userId;

    /**
     * 请求IP
     */
    private String ip;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求方式 GET POST
     */
    private String type;

    /**
     * 请求类路径
     */
    private String classPath;

    /**
     * 方法名
     */
    private String method;

    /**
     * 参数
     */
    private String params;

    /**
     * 描述
     */
    private String operation;

    /**
     * 方法请求花费的时间，单位毫秒
     */
    private Long spendTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
