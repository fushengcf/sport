package com.sport.service.impl;

import com.sport.entity.Major;
import com.sport.mapper.MajorMapper;
import com.sport.service.MajorService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {
    @Autowired
    private MajorMapper majorMapper;
//    public IPage<Major> selectMajor(Integer page, Integer size, String name) {
//        IPage<Major> majarDTOIPage = this.baseMapper.selectPage(new Page<Major>(page,size), name);
//        return majarDTOIPage;
//    }
    @Override
    public void createMajor(Major major) {
        baseMapper.insert(major);

    }

    @Override
    public void updateMajor(Major major) {
        baseMapper.updateById(major);

    }

    @Override
    public Major getMajorById(String id) {
        Major major =  baseMapper.selectById(id);
        return major;
    }

    @Override
    public void deleteMajorById(String id) {
        baseMapper.deleteById(id);
    }

    @Override
    public IPage<Major> selectMyPage(Integer page, Integer size, String name) {
        IPage<Major> majorIPage = baseMapper.selectPage(new Page<Major>(page,size),new QueryWrapper<Major>().like(!StringUtils.isEmpty(name), Major.NAME, name));
        return majorIPage;
    }
//    @Override
//    public List<SelectDTO> getAllMajor() {
//        List<SelectDTO> selectDTO =  majorMapper.selectAll();
//        return selectDTO;
//    }
}