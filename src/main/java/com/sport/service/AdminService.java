package com.sport.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sport.entity.Admin;
import com.sport.entity.Result.UserDTO;

import java.util.List;

public interface AdminService extends IService<Admin> {
    public Admin getAdminById(String id);


    public UserDTO validateAdmin(String phone, String password);
}