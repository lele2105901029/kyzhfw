package com.kyzh.article.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kyzh.article.service.DataSortingService;
import com.kyzh.mapper.TbDataSortingMapper;
import com.kyzh.pojo.TbDataSorting;
import com.kyzh.pojo.TbDataSortingExample;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class DataSortingServiceImpl implements DataSortingService {

    @Autowired
    private TbDataSortingMapper tbDataSortingMapper;
    //查询全部资料分类名称
    @Override
    public List<TbDataSorting> findAll() {
        return tbDataSortingMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize,TbDataSorting dataSorting) {
        PageHelper.startPage(pageNum, pageSize);

        TbDataSortingExample example = new TbDataSortingExample();
        if(dataSorting != null){
            TbDataSortingExample.Criteria criteria = example.createCriteria();

            //名称模糊搜索
            if(dataSorting.getName()!=null && dataSorting.getName().length() > 0){
                criteria.andNameLike("%"+dataSorting.getName()+"%");
            }
            //首字母匹配查询
            if(dataSorting.getFirstChar()!=null && dataSorting.getFirstChar().length() > 0){
                criteria.andFirstCharEqualTo(dataSorting.getFirstChar());
            }
        }
        List<TbDataSorting> dataSortings = tbDataSortingMapper.selectByExample(example);

        PageInfo<TbDataSorting> page = new PageInfo<>(dataSortings);

        return new PageResult(page.getTotal(), page.getList());
    }

    @Override
    public void insert(TbDataSorting dataSorting) {
        tbDataSortingMapper.insert(dataSorting);
    }

    @Override
    public TbDataSorting findOne(Long id) {
        return tbDataSortingMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TbDataSorting dataSorting) {
        tbDataSortingMapper.updateByPrimaryKey(dataSorting);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            tbDataSortingMapper.deleteByPrimaryKey(id); //根据主键删除品牌对象
        }
    }

    @Override
    public List<Map> selectOptions() {
        return tbDataSortingMapper.selectOptions();
    }

    @Override
    public List<TbDataSorting> findByParentId(Long id) {
        //以当前的id作为其他分类的parentId
        TbDataSortingExample example = new TbDataSortingExample();
        example.createCriteria().andParentIdEqualTo(id);
        return tbDataSortingMapper.selectByExample(example);
    }


}
