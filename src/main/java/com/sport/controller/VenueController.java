package com.sport.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sport.config.JwtUtil;
import com.sport.config.Result;
import com.sport.config.StatusCode;
import com.sport.entity.Result.SelectDTO;
import com.sport.entity.Venue;
import com.sport.entity.Venue;
import com.sport.service.VenueService;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "场馆")
@ApiSupport(order = 4)
@RestController
@RequestMapping("/venue")
public class VenueController {
    @Autowired
    private VenueService venueService;
    static final String SECRET = "ThisIsASecret";

    @ApiImplicitParam(name = "name",value = "姓名",required = false)
    @ApiOperation(value = "分页列表")
    @GetMapping(value = "/list")
//    @TokenCheck
    public Result getInfoListSQL(Integer page, Integer size, @RequestParam(value = "name",required = false) String name){
        IPage<Venue> data = venueService.selectMyPage(page,size,name);
        return  new Result(true, StatusCode.OK,"查询成功",data);
    }
    @ApiOperation(value = "新增场馆")
    @PostMapping(value = "/add")
    public Result createVenue(@RequestBody Venue venue) {
        venue.setPassword("123456");
        venueService.createVenue(venue);
        return  new Result(true, StatusCode.OK,"新增成功",venue);
    }

    @ApiOperation(value = "修改场馆")
    @PutMapping(value = "/update")
    public Result updateVenue(@RequestBody Venue venue) {
        venueService.updateVenue(venue);
        return  new Result(true, StatusCode.OK,"修改成功",venue);
    }

    @ApiOperation(value = "场馆详情")
    @GetMapping(value = "/detail/{id}")
    public Result getVenueById(@PathVariable("id") String id) {
        Venue venue =  venueService.getVenueById(id);
        return  new Result(true, StatusCode.OK,"详情成功",venue);
    }

    @ApiOperation(value = "删除场馆")
    @DeleteMapping(value = "/delete/{id}")
    public Result deleteVenueById(@PathVariable("id") String id) {

        venueService.deleteVenueById(id);
        return  new Result(true, StatusCode.OK,"删除成功","");
    }
    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "电话号码",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true)
    })
    @PostMapping(value = "/login" )
    public Result login(@RequestParam(value = "phone",required = true) String phone,@RequestParam(value = "password",required = true) String password)  {
        Venue venue = venueService.validateVenue(phone,password);
        if (venue !=null){
            String jwt = JwtUtil.generateVenueToken(venue);
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
        Venue venue = venueService.getVenueById(id);
        Integer time = Integer.parseInt(body.get("exp").toString())*1000;
        if (System.currentTimeMillis()/1000>Integer.parseInt(body.get("exp").toString())){
            return new Result(true, StatusCode.LOGINERROR, "token过期",null);
        }

        if(venue ==null){
            return new Result(true, StatusCode.LOGINERROR, "token无效",null);
        }
        return new Result(true, StatusCode.OK, "获取成功", venue);
    }


    @ApiOperation(value = "重置密码")
    @GetMapping(value = "/reset/{id}")
    public Result resetPw(@PathVariable("id") String id) {
        Venue venue =  venueService.getVenueById(id);
        venue.setPassword("123456");
        venueService.updateVenue(venue);
        return  new Result(true, StatusCode.OK,"修改成功","");
    }


//    @ApiOperation(value = "修改密码")
//    @GetMapping(value = "/updatePw/{id}")
//    public Result updatePw() {
//        Venue venue =  venueService.getVenueById(id);
//        venue.setPassword("123456");
//        venueService.updateVenue(venue);
//        return  new Result(true, StatusCode.OK,"修改成功","");
//    }

    @ApiOperation(value = "所有场馆")
    @GetMapping(value = "/allVenue")
    public Result getAll(){
        List<SelectDTO> data = venueService.getAllVenue();
        return  new Result(true, StatusCode.OK,"查询成功",data);
    }
}
