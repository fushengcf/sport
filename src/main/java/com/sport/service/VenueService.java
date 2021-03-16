package com.sport.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.sport.mapper.VenueMapper;
import com.sport.entity.Venue;
@Service
public class VenueService{

    @Resource
    private VenueMapper venueMapper;

    
    public int deleteByPrimaryKey(String id) {
        return venueMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Venue record) {
        return venueMapper.insert(record);
    }

    
    public int insertSelective(Venue record) {
        return venueMapper.insertSelective(record);
    }

    
    public Venue selectByPrimaryKey(String id) {
        return venueMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Venue record) {
        return venueMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Venue record) {
        return venueMapper.updateByPrimaryKey(record);
    }

}
