package com.kyzh.mapper;

import com.kyzh.pojo.TbExhibitType;
import com.kyzh.pojo.TbExhibitTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbExhibitTypeMapper {
    int countByExample(TbExhibitTypeExample example);

    int deleteByExample(TbExhibitTypeExample example);

    int deleteByPrimaryKey(String typeId);

    int insert(TbExhibitType record);

    int insertSelective(TbExhibitType record);

    List<TbExhibitType> selectByExample(TbExhibitTypeExample example);

    TbExhibitType selectByPrimaryKey(String typeId);

    int updateByExampleSelective(@Param("record") TbExhibitType record, @Param("example") TbExhibitTypeExample example);

    int updateByExample(@Param("record") TbExhibitType record, @Param("example") TbExhibitTypeExample example);

    int updateByPrimaryKeySelective(TbExhibitType record);

    int updateByPrimaryKey(TbExhibitType record);
}