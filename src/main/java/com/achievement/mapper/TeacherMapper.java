package com.achievement.mapper;

import com.achievement.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.achievement.entity.Teacher;
import org.springframework.stereotype.Component;


@Component
public interface TeacherMapper extends BaseMapper<Teacher> {
    IPage<Teacher> selectTeacher(IPage<Teacher> page, String name);

}
