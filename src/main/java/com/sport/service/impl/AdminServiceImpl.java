package com.sport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sport.entity.Admin;
import com.sport.entity.Result.UserDTO;
import com.sport.mapper.AdminMapper;
import com.sport.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {





    @Override
    public Admin getAdminById(String id) {
        Admin admin =  baseMapper.selectById(id);
        return admin;
    }



    @Override
    public UserDTO validateAdmin(String phone, String password){
        UserDTO userDTO = baseMapper.selectOne(new QueryWrapper<userDTO>().like(!StringUtils.isEmpty(phone), Admin.PHONE, phone));
        if ( userDTO.getPassword().equals(password)){
                return userDTO;
        }
        return  null;

    }
}