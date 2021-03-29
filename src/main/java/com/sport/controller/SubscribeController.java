package com.sport.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sport.config.Result;
import com.sport.config.StatusCode;
import com.sport.entity.Result.SubscribeDTO;
import com.sport.entity.Result.SubscribePageDTO;
import com.sport.entity.Sport;
import com.sport.entity.Subscribe;
import com.sport.service.SportService;
import com.sport.service.SubscribeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "订购")
@ApiSupport(order = 4)
@RestController
@RequestMapping("/subscribe")
public class SubscribeController {
    @Autowired
    private SubscribeService subscribeService;
    @Autowired
    private SportService sportService;
    @ApiImplicitParam(name = "username",value = "姓名",required = false)
    @ApiOperation(value = "分页列表")
    @GetMapping(value = "/list")
//    @TokenCheck
    public Result getInfoListSQL(Integer page, Integer size, @RequestParam(value = "name",required = false) String name, @RequestParam(value = "venueId",required = false) String venueId, @RequestParam(value = "typeId",required = false) String typeId, @RequestParam(value = "sportId",required = false) String sportId){
        IPage<SubscribePageDTO> data = subscribeService.selectSubscribe(page,size,name,venueId,typeId,sportId);
        return  new Result(true, StatusCode.OK,"查询成功",data);
    }
    @ApiOperation(value = "新增订购")
    @PostMapping(value = "/add")
    public Result createSubscribe(@RequestBody Subscribe subscribe) {
        String sportId = subscribe.getSportId();
        Sport sport = sportService.getSportById(sportId);
        String price = sport.getPrice();
        String startTime = subscribe.getStartTime().toString().substring(11,13);
        String finishTime = subscribe.getFinishTime().toString().substring(11,13);
        Integer a = Integer.parseInt(price)*(Integer.parseInt(finishTime)-Integer.parseInt(startTime));
        subscribe.setPrice(a.toString());
        subscribeService.createSubscribe(subscribe);

        return  new Result(true, StatusCode.OK,"新增成功",subscribe);
    }

    @ApiOperation(value = "修改订购")
    @PutMapping(value = "/update")
    public Result updateSubscribe(@RequestBody Subscribe subscribe) {
        String sportId = subscribe.getSportId();
        Sport sport = sportService.getSportById(sportId);
        String price = sport.getPrice();
        String startTime = subscribe.getStartTime().toString().substring(11,13);
        String finishTime = subscribe.getFinishTime().toString().substring(11,13);
        Integer a = Integer.parseInt(price)*(Integer.parseInt(finishTime)-Integer.parseInt(startTime));
        subscribe.setPrice(a.toString());
        subscribeService.updateSubscribe(subscribe);

        return  new Result(true, StatusCode.OK,"修改成功",subscribe);
    }

    @ApiOperation(value = "订购详情")
    @GetMapping(value = "/detail/{id}")
    public Result getSubscribeById(@PathVariable("id") String id) {
        Subscribe subscribe =  subscribeService.getSubscribeById(id);
        return  new Result(true, StatusCode.OK,"详情成功",subscribe);
    }

    @ApiOperation(value = "删除订购")
    @DeleteMapping(value = "/delete/{id}")
    public Result deleteSubscribeById(@PathVariable("id") String id) {
        subscribeService.deleteSubscribeById(id);
        return  new Result(true, StatusCode.OK,"删除成功","");
    }

    @ApiOperation(value = "所有订购")
    @GetMapping(value = "/allSubscribe")
    public Result getAll( @RequestParam(value = "sportId",required = false) String sportId, @RequestParam(value = "startTime",required = false) String startTime, @RequestParam(value = "id",required = false) String id){
        List<Integer> data = subscribeService.getAllSubscribe(sportId,startTime,id);
        return  new Result(true, StatusCode.OK,"查询成功",data);
    }
}
