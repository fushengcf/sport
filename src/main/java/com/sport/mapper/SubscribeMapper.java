package com.sport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sport.entity.Result.SelectDTO;
import com.sport.entity.Result.SportDTO;
import com.sport.entity.Result.SubscribeDTO;
import com.sport.entity.Result.SubscribePageDTO;
import com.sport.entity.Subscribe;
import com.sport.entity.Type;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
public interface SubscribeMapper extends BaseMapper<Subscribe> {
    List<SubscribeDTO> selectAllSubscribe( String sportId, String startTime,String id);

    IPage<SubscribePageDTO> selectSubscribe(IPage<SubscribePageDTO> subscribePageDTOIPage, String name, String venueId, String typeId, String sportId);
}
