package com.sport.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sport.config.Result;
import com.sport.config.StatusCode;
import com.sport.entity.Sport;
import com.sport.service.SportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "场地")
@ApiSupport(order = 4)
@RestController
@RequestMapping("/sport")
public class SportController {
    @Autowired
    private SportService sportService;
    @ApiImplicitParam(name = "name",value = "姓名",required = false)
    @ApiOperation(value = "分页列表")
    @GetMapping(value = "/list")
//    @TokenCheck
    public Result getInfoListSQL(Integer page, Integer size, @RequestParam(value = "name",required = false) String name){
        IPage<Sport> data = sportService.selectMyPage(page,size,name);
        return  new Result(true, StatusCode.OK,"查询成功",data);
    }
    @ApiOperation(value = "新增场地")
    @PostMapping(value = "/add")
    public Result createSport(@RequestBody Sport sport) {
        sportService.createSport(sport);
        return  new Result(true, StatusCode.OK,"新增成功",sport);
    }

    @ApiOperation(value = "修改场地")
    @PutMapping(value = "/update")
    public Result updateSport(@RequestBody Sport sport) {
        sportService.updateSport(sport);
        return  new Result(true, StatusCode.OK,"修改成功",sport);
    }

    @ApiOperation(value = "场地详情")
    @GetMapping(value = "/detail/{id}")
    public Result getSportById(@PathVariable("id") String id) {
        Sport sport =  sportService.getSportById(id);
        return  new Result(true, StatusCode.OK,"详情成功",sport);
    }

    @ApiOperation(value = "删除场地")
    @DeleteMapping(value = "/delete/{id}")
    public Result deleteSportById(@PathVariable("id") String id) {

        sportService.deleteSportById(id);
        return  new Result(true, StatusCode.OK,"删除成功","");
    }

//    @ApiOperation(value = "所有场地")
//    @GetMapping(value = "/allSport")
//    public Result getAll(){
//        List<SelectDTO> data = sportService.getAllSport();
//        return  new Result(true, StatusCode.OK,"查询成功",data);
//    }
}
