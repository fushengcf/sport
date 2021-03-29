package com.sport.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sport.config.Result;
import com.sport.config.StatusCode;
import com.sport.entity.Result.SelectDTO;
import com.sport.entity.Result.SportDTO;
import com.sport.entity.Result.StatusDTO;
import com.sport.entity.Sport;
import com.sport.service.SportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "场地")
@ApiSupport(order = 4)
@RestController
@RequestMapping("/sport")
public class SportController {
    @Autowired
    private SportService sportService;

    @ApiImplicitParams({
                @ApiImplicitParam(name = "name",value = "姓名",required = false),
    @ApiImplicitParam(name = "venueId",value = "场馆ID",required = false),
    @ApiImplicitParam(name = "typeId",value = "类型ID",required = false)
    })

    @ApiOperation(value = "分页列表")
    @GetMapping(value = "/list")
//    @TokenCheck
    public Result getInfoListSQL(Integer page, Integer size, @RequestParam(value = "name",required = false) String name, @RequestParam(value = "venueId",required = false) String venueId, @RequestParam(value = "typeId",required = false) String typeId, @RequestParam(value = "status",required = false) Integer status){
        IPage<SportDTO> data = sportService.selectSport(page,size,name,venueId,typeId,status);
        return  new Result(true, StatusCode.OK,"查询成功",data);
    }
    @ApiOperation(value = "新增场地")
    @PostMapping(value = "/add")
    public Result createSport(@RequestBody Sport sport) {
        sport.setStatus(1);
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


    @ApiOperation(value = "修改场地状态")
    @PostMapping(value = "/status")
    public Result handleStatus(@RequestBody StatusDTO statusDTO) {
        String id = statusDTO.getId();
        Integer status = statusDTO.getStatus();
        Sport sport = sportService.getSportById(id);
        sport.setStatus(status);
        sportService.updateSport(sport);
        return  new Result(true, StatusCode.OK,"操作成功",sport);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "venueId",value = "场馆ID",required = false),
            @ApiImplicitParam(name = "typeId",value = "类型ID",required = false)
    })
    @ApiOperation(value = "所有场地")
    @GetMapping(value = "/allSport")
    public Result getAll(@RequestParam(value = "venueId",required = false) String venueId, @RequestParam(value = "typeId",required = false) String typeId){
        List<SelectDTO> data = sportService.getAllSport(venueId,typeId);
        return  new Result(true, StatusCode.OK,"查询成功",data);
    }
}
