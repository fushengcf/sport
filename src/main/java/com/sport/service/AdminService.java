package com.sport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sport.entity.Admin;

public interface AdminService extends IService<Admin> {
    public Admin getAdminById(String id);


    public Admin validateAdmin(String phone, String password);
}