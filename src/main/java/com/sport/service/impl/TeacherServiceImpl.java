package com.sport.service.impl;

import com.sport.entity.Teacher;
import com.sport.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sport.mapper.TeacherMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    public IPage<Teacher> selectTeacher(Integer page, Integer size, String name) {

     IPage<Teacher>  teacherIPage = this.baseMapper.selectTeacher(new Page<Teacher>(page,size), name);
        return  teacherIPage;
    }
    @Override
    public void createTeacher(Teacher teacher) {
        baseMapper.insert(teacher);

    }

    @Override
    public void updateTeacher(Teacher teacher) {
        baseMapper.updateById(teacher);

    }

    @Override
    public Teacher getTeacherById(String id) {
        Teacher teacher =  baseMapper.selectById(id);
        return teacher;
    }

    @Override
    public void deleteTeacherById(String id) {


        baseMapper.deleteById(id);
    }

    @Override
    public IPage<Teacher> selectMyPage(Integer page, Integer size, String name) {
        IPage<Teacher> teacherIPage = baseMapper.selectPage(new Page<Teacher>(page,size),new QueryWrapper<Teacher>().like(!StringUtils.isEmpty(name), Teacher.NAME, name));
        return teacherIPage;
    }


    @Override
    public List<Teacher> getTeacher(QueryWrapper queryWrapper) {

        List<Teacher> teachers =  baseMapper.selectList(queryWrapper);
        return teachers;
    }

    @Override
    public Teacher validateUser(String name, String password){
        Teacher teacher = baseMapper.selectOne(new QueryWrapper<Teacher>().like(!StringUtils.isEmpty(name), Teacher.NAME, name));
        System.out.println(teacher.getPassword()+"aoto"+password);
        if ( teacher.getPassword().equals(password)){
                return teacher;
        }
        return  null;

    }

}