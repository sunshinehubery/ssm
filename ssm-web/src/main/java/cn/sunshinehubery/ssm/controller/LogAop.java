package cn.sunshinehubery.ssm.controller;

import cn.sunshinehubery.ssm.pojo.SysLog;
import cn.sunshinehubery.ssm.service.ISysLogService;
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
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;
    private Date visitTime;
    private Class executionClass;
    private Method executionMethod;
    //操作前，需要获取访问的时间,访问的类和访问的方法
    @Before("execution(* cn.sunshinehubery.ssm.controller.I*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //获取访问时间
        visitTime = new Date();
        //获取访问的类
        executionClass = jp.getTarget().getClass();
        //获取访问的方法
        String methodName = jp.getSignature().getName(); //获取访问方法的名称
        Object[] args = jp.getArgs(); //获取访问方法的参数数组
        if(args == null || args.length == 0){
            executionMethod = executionClass.getMethod(methodName);
        }else{
            Class[] classArgs = new Class[args.length];
            for(int i = 0;i < args.length;i++){
                classArgs[i] = args[i].getClass();
            }
            executionMethod = executionClass.getMethod(methodName,classArgs);
        }
    }

    //主要是获取日志中的其他信息，时长，ip，url
    @After("execution(* cn.sunshinehubery.ssm.controller.I*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        //利用反射获取类上的@RequestMapping("/orders")
        if(executionClass != SysLogController.class){
            //类上的@RequestMapping
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if(classAnnotation != null){
                //方法上的@RequestMapping
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                //获取url
                if(methodAnnotation != null) {
                    String url = classAnnotation.value()[0] + methodAnnotation.value()[0];
                    //获取时长
                    Long executionTime = new Date().getTime() - visitTime.getTime();
                    //获取ip地址
                    String ip = request.getRemoteAddr();
                    //获取username
                    SecurityContext context = SecurityContextHolder.getContext();
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(executionTime);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名]" + executionClass.getName().substring(executionClass.getName().lastIndexOf(".")+1) + "[方法名]"+ executionMethod.getName());
                    sysLog.setUrl(url);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setUsername(username);
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
