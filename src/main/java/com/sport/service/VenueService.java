package com.sport.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sport.entity.Admin;
import com.sport.entity.Result.SelectDTO;
import com.sport.entity.Venue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VenueService extends IService<Venue> {

    public void createVenue(Venue venue);
    public void updateVenue(Venue venue);
    public Venue getVenueById(String id);
    public void deleteVenueById(String id);
    IPage<Venue> selectMyPage(Integer page, Integer size, String name);


    public Venue validateVenue(String phone, String password);
    List<SelectDTO> getAllVenue();
}
