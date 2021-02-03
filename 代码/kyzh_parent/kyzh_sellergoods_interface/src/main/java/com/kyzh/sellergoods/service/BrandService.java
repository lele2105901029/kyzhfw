package com.kyzh.sellergoods.service;

import com.kyzh.pojo.TbBrand;
import entity.PageResult;

import java.util.List;
import java.util.Map;

public interface BrandService {

    List<TbBrand> findAll();

    PageResult findPage(int pageNum, int pageSize, TbBrand brand);

    void insert(TbBrand brand);

    TbBrand findOne(Long id);

    void update(TbBrand brand);

    void delete(Long[] ids);

    List<Map> selectOptions();
}
