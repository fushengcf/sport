package com.sport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sport.entity.Admin;
import com.sport.entity.Result.SelectDTO;
import com.sport.entity.Venue;
import com.sport.mapper.VenueMapper;
import com.sport.service.TypeService;
import com.sport.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class VenueServiceImpl extends ServiceImpl<VenueMapper, Venue>implements VenueService {
    @Autowired
    private VenueMapper venueMapper;
//    public IPage<Venue> selectVenue(Integer page, Integer size, String name) {
//        IPage<Venue> majarDTOIPage = this.baseMapper.selectPage(new Page<Venue>(page,size), name);
//        return majarDTOIPage;
//    }
    @Override
    public void createVenue(Venue venue) {
        baseMapper.insert(venue);

    }

    @Override
    public void updateVenue(Venue venue) {
        baseMapper.updateById(venue);

    }

    @Override
    public Venue getVenueById(String id) {
        Venue venue =  baseMapper.selectById(id);
        return venue;
    }

    @Override
    public void deleteVenueById(String id) {
        baseMapper.deleteById(id);
    }

    @Override
    public IPage<Venue> selectMyPage(Integer page, Integer size, String name) {
        IPage<Venue> venueIPage = baseMapper.selectPage(new Page<Venue>(page,size),new QueryWrapper<Venue>().like(!StringUtils.isEmpty(name), Venue.NAME, name));
        return venueIPage;
    }

//    @Override
//    public Venue getVenueById(String id) {
//        Venue venue =  baseMapper.selectById(id);
//        return venue;
//    }



    @Override
    public Venue validateVenue(String phone, String password){


        Venue venue = baseMapper.selectOne(new QueryWrapper<Venue>().like(!StringUtils.isEmpty(phone), Venue.PHONE, phone));
        if ( venue.getPassword().equals(password)){
            return venue;
        }
        return  null;

    }


    @Override
    public List<SelectDTO> getAllVenue() {
        List<SelectDTO> selectDTO =  venueMapper.selectAllVenue();
        return selectDTO;
    }
}