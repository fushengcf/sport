package com.sport.controller;

import com.sport.config.Result;
import com.sport.config.StatusCode;
import com.sport.entity.Major;
import com.sport.service.MajorService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "专业")
@ApiSupport(order = 4)
@RestController
@RequestMapping("/major")
public class MajorController {
    @Autowired
    private MajorService majorService;
    @ApiImplicitParam(name = "name",value = "姓名",required = false)
    @ApiOperation(value = "分页列表")
    @GetMapping(value = "/list")
//    @TokenCheck
    public Result getInfoListSQL(Integer page, Integer size, @RequestParam(value = "name",required = false) String name){
        IPage<Major> data = majorService.selectMyPage(page,size,name);
        return  new Result(true, StatusCode.OK,"查询成功",data);
    }
    @ApiOperation(value = "新增专业")
    @PostMapping(value = "/add")
    public Result createUser(@RequestBody Major major) {
        majorService.createMajor(major);
        return  new Result(true, StatusCode.OK,"新增成功",major);
    }

    @ApiOperation(value = "修改专业")
    @PutMapping(value = "/update")
    public Result updateUser(@RequestBody Major major) {
        majorService.updateMajor(major);
        return  new Result(true, StatusCode.OK,"修改成功",major);
    }

    @ApiOperation(value = "专业详情")
    @GetMapping(value = "/detail/{id}")
    public Result getUserById(@PathVariable("id") String id) {
        Major major =  majorService.getMajorById(id);
        return  new Result(true, StatusCode.OK,"详情成功",major);
    }

    @ApiOperation(value = "删除专业")
    @DeleteMapping(value = "/delete/{id}")
    public Result deleteUserById(@PathVariable("id") String id) {

        majorService.deleteMajorById(id);
        return  new Result(true, StatusCode.OK,"删除成功","");
    }

//    @ApiOperation(value = "所有专业")
//    @GetMapping(value = "/allMajor")
//    public Result getAll(){
//        List<SelectDTO> data = majorService.getAllMajor();
//        return  new Result(true, StatusCode.OK,"查询成功",data);
//    }
}
