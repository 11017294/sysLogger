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
public class SysLogHandle extends AbstractRequestAwareRunnable {

    /**
     * 主键
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 参数列表
     */
    private String paramsJson;

    /**
     * 类路径
     */
    private String classPath;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 方法请求时间
     */
    private Date startTime;

    /**
     * 操作名称
     */
    private String operationName;

    /**
     * 请求IP
     */
    private String ip;

    /**
     * 请求类型
     */
    private String type;

    /**
     * 请求URL
     */
    private String requestUrl;


    public SysLogHandle(String userId, String type, String requestUrl,
                        String paramsJson,  String operationName,
                        String classPath, String methodName,
                        Date startTime) {
        this.userId = userId;
        this.type = type;
        this.requestUrl = requestUrl;
        this.paramsJson = paramsJson;
        this.operationName = operationName;
        this.classPath = classPath;
        this.methodName = methodName;
        this.startTime = startTime;
    }

    @Override
    protected void onRun() {
        SysLog sysLog = new SysLog();
        //设置用户id
        sysLog.setUserId(userId);

        //设置请求信息
        sysLog.setIp(ip);

        //设置调用的类
        sysLog.setClassPath(classPath);

        //设置调用的方法
        sysLog.setMethod(methodName);

        //设置Request的请求方式 GET POST
        sysLog.setType(type);
        sysLog.setUrl(requestUrl);

        sysLog.setOperation(operationName);
        sysLog.setCreateTime(new Date());
        sysLog.setUpdateTime(new Date());

        sysLog.setParams(paramsJson);
        Long spendTime = new Date().getTime() - startTime.getTime();
        // 计算请求接口花费的时间，单位毫秒
        sysLog.setSpendTime(spendTime);

        //ystem.out.println(sysLog);
        //sysLog.insert();
    }

}
