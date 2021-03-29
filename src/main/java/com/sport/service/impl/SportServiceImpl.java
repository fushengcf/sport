package com.sport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sport.entity.Result.SelectDTO;
import com.sport.entity.Result.SportDTO;
import com.sport.entity.Sport;
import com.sport.mapper.SportMapper;
import com.sport.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SportServiceImpl extends ServiceImpl<SportMapper, Sport> implements SportService {
    @Autowired
    private SportMapper sportMapper;
    public IPage<SportDTO> selectSport(Integer page, Integer size, String name,String venueId,String typeId,Integer status) {
        IPage<SportDTO> sportDTOIPage = this.baseMapper.selectSport(new Page<SportDTO>(page,size),name, venueId,typeId,status);
        return sportDTOIPage;
    }
    @Override
    public void createSport(Sport sport) {
        baseMapper.insert(sport);

    }

    @Override
    public void updateSport(Sport sport) {
        baseMapper.updateById(sport);

    }

    @Override
    public Sport getSportById(String id) {
        Sport sport =  baseMapper.selectById(id);
        return sport;
    }

    @Override
    public void deleteSportById(String id) {
        baseMapper.deleteById(id);
    }

    @Override
    public IPage<Sport> selectMyPage(Integer page, Integer size, String name) {
        IPage<Sport> sportIPage = baseMapper.selectPage(new Page<Sport>(page,size),new QueryWrapper<Sport>().like(!StringUtils.isEmpty(name), Sport.NAME, name));
        return sportIPage;
    }
    @Override
    public List<SelectDTO> getAllSport(String venueId,String typeId) {
        List<SelectDTO> selectDTO =  sportMapper.selectAllSport(venueId,typeId);
        return selectDTO;
    }
}