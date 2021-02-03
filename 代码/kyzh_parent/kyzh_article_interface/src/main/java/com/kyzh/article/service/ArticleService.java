package com.kyzh.article.service;
import java.util.List;
import com.kyzh.pojo.TbArticle;

import entity.Article;
import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface ArticleService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbArticle> findAll();
	
	
	/**
	 * 增加
	*/
	public void add(Article article);
	
	
	/**
	 * 修改
	 */
	public void update(Article article);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Article findOne(Long id);
	
	
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
	public PageResult findAll(TbArticle article, int pageNum, int pageSize);
	
}
