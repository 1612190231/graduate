package com.luck.graduate.utils.aspect;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.luck.graduate.entity.UserModel;
import com.luck.graduate.utils.MyException;
import com.luck.graduate.utils.JWTToken.PassToken;
import com.luck.graduate.utils.JWTToken.UserLoginToken;
import com.luck.graduate.service.UserService;
import com.luck.graduate.utils.result.Result;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


@Component
@Aspect
public class AspectVerify {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    //定义切面，所有的controller层都会监控
    @Pointcut("execution(* com.luck.graduate.controller..*.*(..))")
    public void doHander() {

    }

    @Around("doHander()")
    public Object exception(ProceedingJoinPoint joinPoint) {
        try {
            //进入controller层前
            beforePoint(joinPoint);
            //放行
            Object result = joinPoint.proceed();
            //返回数据前
            afterPoint(joinPoint, result);
            return result;
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Throwable e) {
            return Result.error(9999, "系统异常");
        }

    }

    private Boolean beforePoint(ProceedingJoinPoint joinPoint) throws Exception {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        // 从 http 请求头中取出 token
        String token = request.getHeader("Authorization");

        //得到要进入的是哪个controller方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //检查是否有passtoken注释，有则跳过认证，所以在controller层加了@Passtoken注解，这里我就不校验
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        // 执行认证
        if (token == null) {
            throw new RuntimeException("无token，请重新登录");
        }
        // 获取 token 中的 user id
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        // 验证 token  这里的 iiiii 要和上面生成token的密钥一致才能解析成功
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("iiiii")).build();
        try {
            //验证token
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("401");
        }
        //得到这个方法控制器的所有形参
        Object[] args = joinPoint.getArgs();
        for (Object argItem : args) {
            //如果这个控制器方法中有用户这个形参，说明这个控制器需要用户的信息，那么我就把我这里解析出来的userId 赛进这个形参中，那样在控制器那边就能得到我的userId了
            if (argItem instanceof UserModel) {
                UserModel paramVO = (UserModel) argItem;
                //paramVO.setPassword("000000");
                paramVO.setUserId(Integer.valueOf(userId));
            }
        }
        return true;
    }


    private void afterPoint(ProceedingJoinPoint joinPoint, Object result) throws Exception {

    }

}

