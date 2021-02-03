package com.kyzh.article.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import com.kyzh.mapper.TbArticleContentMapper;
import com.kyzh.pojo.TbArticleContent;
import com.kyzh.pojo.TbArticleContentExample;
import com.kyzh.pojo.TbArticleContentExample.Criteria;
import com.kyzh.article.service.ArticleContentService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class ArticleContentServiceImpl implements ArticleContentService {

	@Autowired
	private TbArticleContentMapper articleContentMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbArticleContent> findAll() {
		return articleContentMapper.selectByExample(null);
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbArticleContent articleContent) {
		articleContentMapper.insert(articleContent);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbArticleContent articleContent){
		articleContentMapper.updateByPrimaryKeyWithBLOBs(articleContent);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbArticleContent findOne(Long id){
		return articleContentMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			articleContentMapper.deleteByPrimaryKey(id);
		}		
	}
	
	/**
	 * 分页查询+模糊搜索
	 */
	@Override
	public PageResult findAll(TbArticleContent articleContent, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbArticleContentExample example=new TbArticleContentExample();
		Criteria criteria = example.createCriteria();
		
		if(articleContent!=null){			
				
		}
		
		List<TbArticleContent> lists= articleContentMapper.selectByExampleWithBLOBs(example);

		PageInfo<TbArticleContent> page = new PageInfo<>(lists);

		return new PageResult(page.getTotal(), page.getList());
	}
	
}
