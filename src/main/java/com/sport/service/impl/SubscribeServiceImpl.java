package com.sport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sport.entity.Result.SportDTO;
import com.sport.entity.Result.SubscribeDTO;
import com.sport.entity.Result.SubscribePageDTO;
import com.sport.entity.Subscribe;
import com.sport.mapper.SubscribeMapper;
import com.sport.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SubscribeServiceImpl extends ServiceImpl<SubscribeMapper, Subscribe> implements SubscribeService {
    @Autowired
    private SubscribeMapper subscribeMapper;
    public IPage<SubscribePageDTO> selectSubscribe(Integer page, Integer size, String name,String venueId,String typeId,String sportId) {
        IPage<SubscribePageDTO> subscribeDTOIPage = this.baseMapper.selectSubscribe(new Page<SubscribePageDTO>(page,size),name, venueId,typeId,sportId);
        return subscribeDTOIPage;
    }
    @Override
    public void createSubscribe(Subscribe subscribe) {
        baseMapper.insert(subscribe);

    }

    @Override
    public void updateSubscribe(Subscribe subscribe) {
        baseMapper.updateById(subscribe);

    }

    @Override
    public Subscribe getSubscribeById(String id) {
        Subscribe subscribe =  baseMapper.selectById(id);
        return subscribe;
    }

    @Override
    public void deleteSubscribeById(String id) {
        baseMapper.deleteById(id);
    }

    @Override
    public IPage<Subscribe> selectMyPage(Integer page, Integer size, String username) {
        IPage<Subscribe> subscribeIPage = baseMapper.selectPage(new Page<Subscribe>(page,size),new QueryWrapper<Subscribe>().like(!StringUtils.isEmpty(username), Subscribe.USERNAME, username));
        return subscribeIPage;
    }
    @Override
    public List<Integer> getAllSubscribe(String sportId, String startTime,String id) {
        List<SubscribeDTO> subscribeDTOS =  subscribeMapper.selectAllSubscribe(sportId,startTime,id);
        List array = new ArrayList();
        for(int i=0;i<subscribeDTOS.size();i++){
            SubscribeDTO item = subscribeDTOS.get(i);
            Integer start = item.getStartTime().getHours();
            Integer end = item.getFinishTime().getHours();
            for(int j=start;j<end;j++){
                array.add(j);
            }
        }

        return array;
    }
}