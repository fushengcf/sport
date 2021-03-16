package com.sport.config;


import com.auth0.jwt.exceptions.TokenExpiredException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sun.org.apache.bcel.internal.classfile.CodeException;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.Map;

@RestController
public class GlobalExceptionController  extends AbstractErrorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionController.class);
    private static final String ERROR_PATH = "/error";

    public GlobalExceptionController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(value = ERROR_PATH)
    public Result error(HttpServletRequest request) {
        WebRequest webRequest = new ServletWebRequest(request);
        Throwable e = getError(webRequest);
        if (e == null) {
            Map<String, Object> attributes = getErrorAttributes(request, false);
            Object timestamp = attributes.get("timestamp");
            Object status = attributes.get("status");
            String error = attributes.get("error").toString();
            Object path = attributes.get("path");
            return  new Result(true, StatusCode.ERROR,"系统错误请联系管理员", "");
        }
        if (e instanceof TokenExpiredException) {
            TokenExpiredException tokenExpiredException = (TokenExpiredException) e;
            return  new Result(true, StatusCode.ERROR,"系统错误请联系管理员", "");
        } else {
            return  new Result(true, StatusCode.ERROR,"系统错误请联系管理员", "");
        }
    }

    private Throwable getError(WebRequest webRequest) {
        return (Throwable) this.getAttribute(webRequest, "javax.servlet.error.exception");
    }

    private Object getAttribute(RequestAttributes requestAttributes, String name) {
        return requestAttributes.getAttribute(name, 0);
    }
}