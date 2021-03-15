package com.sport.mapper;

import com.sport.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Component;


@Component
public interface TeacherMapper extends BaseMapper<Teacher> {
    IPage<Teacher> selectTeacher(IPage<Teacher> page, String name);

}
