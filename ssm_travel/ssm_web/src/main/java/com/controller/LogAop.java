package com.controller;

import com.domain.SysLog;
import com.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;
    private Class clazz;
    private Method method;

    /**
     * 前置通知，主要是获取的是开始执行的时间，然后什么类中的什么方法
     * @param jp
     */
    @Before("execution(* com.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        System.out.println("执行到1");
        visitTime = new Date();
        clazz = jp.getTarget().getClass();

        System.out.println("执行到2");
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0){
            System.out.println("执行到3");
            method = clazz.getMethod(methodName);
        }else {
            System.out.println("执行到4");
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            System.out.println("执行到5");
            method = clazz.getMethod(methodName,classArgs);
        }
    }

    @After("execution(* com.controller.*.*(..))")
    public void doAfter(JoinPoint jp){
        System.out.println("执行到6");
        long time = new Date().getTime() - visitTime.getTime();

        if (method!=null&&clazz!=null&&clazz!=SysLog.class){
            System.out.println("执行到7");
            RequestMapping classAnnotation = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null){
                System.out.println("执行到8");
                String[] classValue = classAnnotation.value();
                // 方法上的注解
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null){
                    System.out.println("执行到9");
                    String[] methodValue = methodAnnotation.value();
                    String url = classValue[0] + methodValue[0];

                    String ip = request.getRemoteAddr();

                    // 获取用户名
                    SecurityContext context = SecurityContextHolder.getContext();
                    String username = ((User) (context.getAuthentication().getPrincipal())).getUsername();

                    System.out.println("执行到10");
                    // 封装日志对象
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名] "+clazz.getName()+"[方法名] "+method.getName());
                    sysLog.setUsername(username);
                    sysLog.setUrl(url);
                    sysLog.setVisitTime(visitTime);
                    sysLogService.saveLog(sysLog);
                }
            }
        }
    }
}
