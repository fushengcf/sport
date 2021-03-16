package com.sport.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sport.config.JwtUtil;
import com.sport.config.Result;
import com.sport.config.StatusCode;
import com.sport.entity.Admin;
import com.sport.entity.Major;
import com.sport.entity.Result.UserDTO;
import com.sport.service.AdminService;
import com.sport.service.MajorService;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "管理员")
@ApiSupport(order = 4)
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    static final String SECRET = "ThisIsASecret";
    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "电话号码",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true)
    })
    @PostMapping(value = "/login" )
    public Result login(@RequestParam(value = "phone",required = true) String phone,@RequestParam(value = "password",required = true) String password)  {
        UserDTO userDTO = adminService.validateAdmin(phone,password);
        if (userDTO !=null){
            String jwt = JwtUtil.generateAdmintToken(userDTO);
            return new Result(true, StatusCode.OK, "登录成功", jwt);
        }
        return new Result(true, StatusCode.ERROR, "账号或密码错误",null);
    }

    @ApiOperation(value = "用户信息")
//    @TokenCheck
    @GetMapping(value = "/info" )
    public Result getInfo(@RequestParam(value = "token",required = true) String token)  {

        Map<String, Object> body = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace("Bearer ",""))
                .getBody();

        String id = body.get("id").toString();
        Admin admin = adminService.getAdminById(id);
        Integer time = Integer.parseInt(body.get("exp").toString())*1000;
        System.out.println(id);
        System.out.println(System.currentTimeMillis()/1000+",,,,"+body.get("exp"));
        if (System.currentTimeMillis()/1000>Integer.parseInt(body.get("exp").toString())){
            return new Result(true, StatusCode.ERROR, "token过期",null);
        }

        if(admin ==null){
            return new Result(true, StatusCode.ERROR, "token无效",null);
        }
        return new Result(true, StatusCode.OK, "获取成功", admin);
    }


}
