package com.kyzh.article.service;

import com.kyzh.pojo.TbDataSorting;
import entity.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 资料分类
 */
public interface DataSortingService {

    List<TbDataSorting> findAll();

    PageResult findPage(int pageNum, int pageSize,TbDataSorting dataSorting);

    void insert(TbDataSorting dataSorting);

    TbDataSorting findOne(Long id);

    void update(TbDataSorting dataSorting);

    void delete(Long[] ids);

    List<Map> selectOptions();

    /**
     * 根据id查询该分类下的所有分类
     * @param id
     * @return
     */
    List<TbDataSorting> findByParentId(Long id);
}
