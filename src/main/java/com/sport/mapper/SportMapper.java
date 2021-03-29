package com.sport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sport.entity.Result.SelectDTO;
import com.sport.entity.Result.SportDTO;
import com.sport.entity.Sport;
import com.sport.entity.Type;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface SportMapper extends BaseMapper<Sport> {
    IPage<SportDTO> selectSport(IPage<SportDTO> goodDTOIPage, String name, String venueId, String typeId,Integer status);
    List<SelectDTO> selectAllSport(String venueId,String typeId);
}
