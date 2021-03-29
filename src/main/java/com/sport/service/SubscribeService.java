package com.sport.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sport.entity.Result.SportDTO;
import com.sport.entity.Result.SubscribeDTO;
import com.sport.entity.Result.SubscribePageDTO;
import com.sport.entity.Subscribe;
import com.sport.entity.Subscribe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public interface SubscribeService extends IService<Subscribe> {

    public void createSubscribe(Subscribe subscribe);
    public void updateSubscribe(Subscribe subscribe);
    public Subscribe getSubscribeById(String id);
    public void deleteSubscribeById(String id);
    IPage<Subscribe> selectMyPage(Integer page, Integer size, String username);
    List<Integer> getAllSubscribe(String sportId, String startTime,String id);
    IPage<SubscribePageDTO> selectSubscribe(Integer page, Integer size, String name, String venueId, String typeId, String sportId);
}
