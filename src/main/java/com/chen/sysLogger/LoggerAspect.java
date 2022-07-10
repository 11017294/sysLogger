package com.chen.sysLogger;

import com.alibaba.fastjson.JSONObject;
import com.chen.util.AspectUtil;
import com.chen.util.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author: MaybeBin
 * @createTime: 2022-07-10
 */
@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;
    /**
     * 开始时间
     */
    Date startTime;

    @Pointcut(value = "@annotation(operationLogger)")
    public void pointcut(OperationLogger operationLogger) {

    }

    @Around(value = "pointcut(operationLogger)")
    public Object doAround(ProceedingJoinPoint joinPoint, OperationLogger operationLogger) throws Throwable {
        startTime = new Date();
        //先执行业务
        Object result = joinPoint.proceed();

        try {
            // 日志收集
            handle(joinPoint);

        } catch (Exception e) {
            log.error("日志记录出错!", e);
        }

        return result;
    }

    /**
     * 日志收集
     *
     * @param point
     * @throws Exception
     */
    private void handle(ProceedingJoinPoint point) throws Exception {
        Method currentMethod = AspectUtil.INSTANCE.getMethod(point);

        //获取操作名称
        OperationLogger annotation = currentMethod.getAnnotation(OperationLogger.class);
        String operationName = annotation.value().getBehavior();

        boolean save = annotation.save();

        if (!save) {
            return;
        }
        HttpServletRequest request = RequestHolder.getRequest();
        String userId = request.getAttribute(SysConf.USER_ID) == null ? "" : request.getAttribute(SysConf.USER_ID).toString();
        String type = request.getMethod();
        String url = request.getRequestURI();
        Map nameAndArgsMap = AspectUtil.getFieldsName(point);
        String paramsJson = JSONObject.toJSONString(nameAndArgsMap);

        // 异步存储日志
        threadPoolTaskExecutor.execute(
                new SysLogHandle(userId, type, url,
                        paramsJson, operationName,
                        point.getTarget().getClass().getName(),
                        point.getSignature().getName(),
                        startTime));
    }
}
