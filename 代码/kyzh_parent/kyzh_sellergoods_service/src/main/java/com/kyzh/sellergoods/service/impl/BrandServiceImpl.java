package com.kyzh.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kyzh.mapper.TbBrandMapper;
import com.kyzh.pojo.TbBrand;
import com.kyzh.pojo.TbBrandExample;
import com.kyzh.sellergoods.service.BrandService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper brandMapper;

    @Override
    public List<TbBrand> findAll() {
        return brandMapper.selectByExample(null);
    }

    //ORM
    @Override
    public PageResult findPage(int pageNum, int pageSize,TbBrand brand) {
        PageHelper.startPage(pageNum, pageSize);

        TbBrandExample example = new TbBrandExample();
        if(brand != null){
            TbBrandExample.Criteria criteria = example.createCriteria();

            //名称模糊搜索
            if(brand.getName()!=null && brand.getName().length() > 0){
                criteria.andNameLike("%"+brand.getName()+"%");
            }
            //首字母匹配查询
            if(brand.getFirstChar()!=null && brand.getFirstChar().length() > 0){
                criteria.andFirstCharEqualTo(brand.getFirstChar());
            }
        }

        List<TbBrand> brands = brandMapper.selectByExample(example);

        PageInfo<TbBrand> page = new PageInfo<>(brands);

        return new PageResult(page.getTotal(), page.getList());
    }

    @Override
    public void insert(TbBrand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public TbBrand findOne(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TbBrand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            brandMapper.deleteByPrimaryKey(id); //根据主键删除品牌对象
        }
    }

    @Override
    public List<Map> selectOptions() {
        return brandMapper.selectOptions();
    }


}
