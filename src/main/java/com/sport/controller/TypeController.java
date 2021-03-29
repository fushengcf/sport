package com.sport.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sport.config.Result;
import com.sport.config.StatusCode;
import com.sport.entity.Result.SelectDTO;
import com.sport.entity.Type;
import com.sport.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "类型")
@ApiSupport(order = 4)
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @ApiImplicitParam(name = "name",value = "姓名",required = false)
    @ApiOperation(value = "分页列表")
    @GetMapping(value = "/list")
//    @TokenCheck
    public Result getInfoListSQL(Integer page, Integer size, @RequestParam(value = "name",required = false) String name){
        IPage<Type> data = typeService.selectMyPage(page,size,name);
        return  new Result(true, StatusCode.OK,"查询成功",data);
    }
    @ApiOperation(value = "新增类型")
    @PostMapping(value = "/add")
    public Result createType(@RequestBody Type type) {
        typeService.createType(type);
        return  new Result(true, StatusCode.OK,"新增成功",type);
    }

    @ApiOperation(value = "修改类型")
    @PutMapping(value = "/update")
    public Result updateType(@RequestBody Type type) {
        typeService.updateType(type);
        return  new Result(true, StatusCode.OK,"修改成功",type);
    }

    @ApiOperation(value = "类型详情")
    @GetMapping(value = "/detail/{id}")
    public Result getTypeById(@PathVariable("id") String id) {
        Type type =  typeService.getTypeById(id);
        return  new Result(true, StatusCode.OK,"详情成功",type);
    }

    @ApiOperation(value = "删除类型")
    @DeleteMapping(value = "/delete/{id}")
    public Result deleteTypeById(@PathVariable("id") String id) {

        typeService.deleteTypeById(id);
        return  new Result(true, StatusCode.OK,"删除成功","");
    }

    @ApiOperation(value = "所有类型")
    @GetMapping(value = "/allType")
    public Result getAll(){
        List<SelectDTO> data = typeService.getAllType();
        return  new Result(true, StatusCode.OK,"查询成功",data);
    }
}
