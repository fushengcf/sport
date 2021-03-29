package com.sport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sport.entity.Result.SelectDTO;
import com.sport.entity.Type;
import com.sport.mapper.TypeMapper;
import com.sport.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
//    public IPage<Type> selectType(Integer page, Integer size, String name) {
//        IPage<Type> majarDTOIPage = this.baseMapper.selectPage(new Page<Type>(page,size), name);
//        return majarDTOIPage;
//    }
    @Override
    public void createType(Type type) {
        baseMapper.insert(type);

    }

    @Override
    public void updateType(Type type) {
        baseMapper.updateById(type);

    }

    @Override
    public Type getTypeById(String id) {
        Type type =  baseMapper.selectById(id);
        return type;
    }

    @Override
    public void deleteTypeById(String id) {
        baseMapper.deleteById(id);
    }

    @Override
    public IPage<Type> selectMyPage(Integer page, Integer size, String name) {
        IPage<Type> typeIPage = baseMapper.selectPage(new Page<Type>(page,size),new QueryWrapper<Type>().like(!StringUtils.isEmpty(name), Type.NAME, name));
        return typeIPage;
    }

    @Override
    public List<SelectDTO> getAllType() {
        List<SelectDTO> selectDTO =  typeMapper.selectAllType();
        return selectDTO;
    }
}