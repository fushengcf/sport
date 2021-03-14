package com.achievement.config;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.achievement.controller.TokenCheck;
import com.achievement.entity.Teacher;
import com.achievement.service.TeacherService;
import io.jsonwebtoken.Jwts;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

public class MyInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    static final String SECRET = "ThisIsASecret";


    @Autowired
    TeacherService teacherService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                             Object object) throws Exception {

        String token = httpServletRequest.getHeader("Authorization");
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        System.out.println(method.isAnnotationPresent(TokenCheck.class));
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(TokenCheck.class)) {
            TokenCheck tokenCheck = method.getAnnotation(TokenCheck.class);
            System.out.println(tokenCheck);
            if (tokenCheck.required()) {
                // 执行认证
                System.out.println("执行认证");
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                System.out.println("获取id");
                String userId;
                System.out.println(token);
                try {
                    Map<String, Object> body = Jwts.parser()
                            .setSigningKey(SECRET)
                            .parseClaimsJws(token.replace("Bearer ",""))
                            .getBody();

                    String id = body.get("id").toString();
                    Integer time = Integer.parseInt(body.get("exp").toString())*1000;
                    System.out.println(id);
                    System.out.println(System.currentTimeMillis()+",,,,"+body.get("exp"));
                    if (System.currentTimeMillis()>time){
                        throw new RuntimeException("401555");
                    }

                    Teacher teacher = teacherService.getTeacherById(id);
                    if(teacher !=null){
                        throw new RuntimeException("401555");
                    }

                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401555");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // logger.info("执行完方法之后进执行(Controller方法调用之后)，但是此时还没进行视图渲染");
        System.out.println();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // logger.info("整个请求都处理完咯，DispatcherServlet也渲染了对应的视图咯，此时我可以做一些清理的工作了");
    }
}