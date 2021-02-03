package com.kyzh.article.service;
import java.util.List;
import com.kyzh.pojo.TbArticleContent;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface ArticleContentService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbArticleContent> findAll();
	
	
	/**
	 * 增加
	*/
	public void add(TbArticleContent articleContent);
	
	
	/**
	 * 修改
	 */
	public void update(TbArticleContent articleContent);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbArticleContent findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long[] ids);

	/**
	 * 分页+模糊搜索
	 * 参数一模糊搜索条件
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findAll(TbArticleContent articleContent, int pageNum, int pageSize);
	
}
