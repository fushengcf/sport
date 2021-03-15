package com.sport.mapper;

import com.sport.entity.Gradle;

public interface GradleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Gradle record);

    int insertSelective(Gradle record);

    Gradle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Gradle record);

    int updateByPrimaryKey(Gradle record);
}