package com.kyzh.mapper;

import com.kyzh.pojo.TbArticleContent;
import com.kyzh.pojo.TbArticleContentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbArticleContentMapper {
    int countByExample(TbArticleContentExample example);

    int deleteByExample(TbArticleContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbArticleContent record);

    int insertSelective(TbArticleContent record);

    List<TbArticleContent> selectByExampleWithBLOBs(TbArticleContentExample example);

    List<TbArticleContent> selectByExample(TbArticleContentExample example);

    TbArticleContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbArticleContent record, @Param("example") TbArticleContentExample example);

    int updateByExampleWithBLOBs(@Param("record") TbArticleContent record, @Param("example") TbArticleContentExample example);

    int updateByExample(@Param("record") TbArticleContent record, @Param("example") TbArticleContentExample example);

    int updateByPrimaryKeySelective(TbArticleContent record);

    int updateByPrimaryKeyWithBLOBs(TbArticleContent record);
}