package com.kyzh.mapper;

import com.kyzh.pojo.TbDataSorting;
import com.kyzh.pojo.TbDataSortingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TbDataSortingMapper {
    int countByExample(TbDataSortingExample example);

    int deleteByExample(TbDataSortingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbDataSorting record);

    int insertSelective(TbDataSorting record);

    List<TbDataSorting> selectByExample(TbDataSortingExample example);

    TbDataSorting selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbDataSorting record, @Param("example") TbDataSortingExample example);

    int updateByExample(@Param("record") TbDataSorting record, @Param("example") TbDataSortingExample example);

    int updateByPrimaryKeySelective(TbDataSorting record);

    int updateByPrimaryKey(TbDataSorting record);

    List<Map> selectOptions();
}