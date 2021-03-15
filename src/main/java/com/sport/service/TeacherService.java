package com.sport.service;

import com.sport.entity.Teacher;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TeacherService extends IService<Teacher> {
    IPage<Teacher> selectTeacher(Integer page, Integer size, String name);
    public void createTeacher(Teacher teacher);
    public void updateTeacher(Teacher teacher);
    public Teacher getTeacherById(String id);
    public void deleteTeacherById(String id);
    IPage<Teacher> selectMyPage(Integer page, Integer size, String name);

    public List<Teacher> getTeacher(QueryWrapper queryWrapper);

    public Teacher validateUser(String name, String password);
}