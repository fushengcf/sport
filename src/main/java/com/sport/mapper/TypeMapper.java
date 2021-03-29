package com.sport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sport.entity.Result.SelectDTO;
import com.sport.entity.Type;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface TypeMapper extends BaseMapper<Type> {
    List<SelectDTO> selectAllType();
}
