package com.sport.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sport.entity.Sport;
import org.springframework.stereotype.Service;

@Service
public interface SportService extends IService<Sport> {

    public void createSport(Sport sport);
    public void updateSport(Sport sport);
    public Sport getSportById(String id);
    public void deleteSportById(String id);
    IPage<Sport> selectMyPage(Integer page, Integer size, String name);
//    List<SelectDTO> getAllSport();
}
