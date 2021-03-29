package com.sport.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sport.entity.Result.SelectDTO;
import com.sport.entity.Type;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeService  extends IService<Type> {

    public void createType(Type type);
    public void updateType(Type type);
    public Type getTypeById(String id);
    public void deleteTypeById(String id);
    IPage<Type> selectMyPage(Integer page, Integer size, String name);
    List<SelectDTO> getAllType();
}
