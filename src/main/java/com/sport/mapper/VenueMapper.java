package com.sport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sport.entity.Result.SelectDTO;
import com.sport.entity.Type;
import com.sport.entity.Venue;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface VenueMapper extends BaseMapper<Venue> {
    List<SelectDTO> selectAllVenue();
}
