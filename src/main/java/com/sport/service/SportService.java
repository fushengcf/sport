package com.sport.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sport.entity.Result.SelectDTO;
import com.sport.entity.Result.SportDTO;
import com.sport.entity.Sport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SportService extends IService<Sport> {

     void createSport(Sport sport);
     void updateSport(Sport sport);
     Sport getSportById(String id);
     void deleteSportById(String id);
    IPage<Sport> selectMyPage(Integer page, Integer size, String name);
    IPage<SportDTO> selectSport(Integer page, Integer size, String name,String venueId,String typeId,Integer status);
    List<SelectDTO> getAllSport(String venueId,String typeId);
}
