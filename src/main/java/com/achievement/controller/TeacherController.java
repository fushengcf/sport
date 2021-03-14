package com.achievement.controller;

import com.achievement.entity.Teacher;
import com.achievement.service.TeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.achievement.config.JwtUtil;
import com.achievement.config.Result;
import com.achievement.config.StatusCode;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "管理员")
@ApiSupport(order = 1)
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    static final String SECRET = "ThisIsASecret";
    @ApiImplicitParam(name = "name",value = "姓名",required = false)
    @ApiOperation(value = "分页列表")
    @GetMapping(value = "/list")
//    @TokenCheck
    public Result getInfoListSQL(Integer page, Integer size, @RequestParam(value = "name",required = false) String name){
        System.out.println(name);
        IPage<Teacher> data = teacherService.selectMyPage(page,size,name);
        return  new Result(true, StatusCode.OK,"查询成功",data);
    }
    @ApiOperation(value = "新增管理员")
    @PostMapping(value = "/add")
    public Result createUser(@RequestBody Teacher teacher) {
        teacher.setPassword("123456");
        teacherService.createTeacher(teacher);
        return  new Result(true, StatusCode.OK,"新增成功", teacher);
    }

    @ApiOperation(value = "修改管理员")
    @PutMapping(value = "/update")
    public Result updateUser(@RequestBody Teacher teacher) {
        teacherService.updateTeacher(teacher);
        return  new Result(true, StatusCode.OK,"修改成功", teacher);
    }

    @ApiOperation(value = "管理员详情")
    @GetMapping(value = "/detail/{id}")
    public Result getUserById(@PathVariable("id") String id) {
        Teacher teacher =  teacherService.getTeacherById(id);
        teacher.setPassword("");
        return  new Result(true, StatusCode.OK,"详情成功", teacher);
    }

    @ApiOperation(value = "删除管理员")
    @DeleteMapping(value = "/delete/{id}")
    public Result deleteUserById(@PathVariable("id") String id) {

        teacherService.deleteTeacherById(id);
        return  new Result(true, StatusCode.OK,"删除成功","");
    }

    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "姓名",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true)
    })
    @PostMapping(value = "/login" )
    public Result login(@RequestParam(value = "name",required = true) String name,@RequestParam(value = "password",required = true) String password)  {
        Teacher teacher = teacherService.validateUser(name,password);
            if (teacher !=null){
                            String jwt = JwtUtil.generateToken(teacher);
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
        Teacher teacher = teacherService.getTeacherById(id);
        Integer time = Integer.parseInt(body.get("exp").toString())*1000;
        System.out.println(id);
        System.out.println(System.currentTimeMillis()/1000+",,,,"+body.get("exp"));
        if (System.currentTimeMillis()/1000>Integer.parseInt(body.get("exp").toString())){
            return new Result(true, StatusCode.ERROR, "token过期",null);
        }

        if(teacher ==null){
            return new Result(true, StatusCode.ERROR, "token无效",null);
        }
        return new Result(true, StatusCode.OK, "获取成功", teacher);
    }
}
