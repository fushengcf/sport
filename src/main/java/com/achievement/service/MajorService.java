package com.achievement.service;

import com.achievement.entity.Major;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.List;

public interface MajorService extends IService<Major> {
//    IPage<MajorDTO> selectMajor(Integer page, Integer size, String name);
    public void createMajor(Major major);
    public void updateMajor(Major major);
    public Major getMajorById(String id);
    public void deleteMajorById(String id);
    IPage<Major> selectMyPage(Integer page, Integer size, String name);
//    List<SelectDTO> getAllMajor();
}