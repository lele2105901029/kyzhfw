package com.kyzh.article.service.impl;
import java.util.List;

import com.kyzh.mapper.TbArticleContentMapper;
import com.kyzh.pojo.TbArticleContent;
import com.kyzh.pojo.TbArticleContentExample;
import entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import com.kyzh.mapper.TbArticleMapper;
import com.kyzh.pojo.TbArticle;
import com.kyzh.pojo.TbArticleExample;
import com.kyzh.pojo.TbArticleExample.Criteria;
import com.kyzh.article.service.ArticleService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private TbArticleMapper articleMapper;

	@Autowired
	private TbArticleContentMapper articleContentMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbArticle> findAll() {
		return articleMapper.selectByExample(null);
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Article article) {
		//新增文章对象
		TbArticle article1 = article.getArticle();
		articleMapper.insert(article1);
		//新增文章内容对象
		TbArticleContent articleContent = article.getArticleContent();
		//手动维护文章和文章内容表关系
		articleContent.setId(article1.getId());
		articleContentMapper.insert(articleContent);


	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Article article){
		//修改文章
		articleMapper.updateByPrimaryKey(article.getArticle());
		//修改文章内容
		articleContentMapper.updateByPrimaryKeyWithBLOBs(article.getArticleContent());
	}
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Article findOne(Long id){
		//返回封装对象
		Article article = new Article();
		article.setArticle(articleMapper.selectByPrimaryKey(id));
		TbArticleContent tbArticleContent = articleContentMapper.selectByPrimaryKey(id);
		article.setArticleContent(tbArticleContent);
		return article;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			//删除文章
			articleMapper.deleteByPrimaryKey(id);
			//删除文章内容
			articleContentMapper.deleteByPrimaryKey(id);
		}		
	}
	
	/**
	 * 分页查询+模糊搜索
	 */
	@Override
	public PageResult findAll(TbArticle article, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbArticleExample example=new TbArticleExample();
		Criteria criteria = example.createCriteria();
		
		if(article!=null){			
			if(article.getName()!=null && article.getName().length()>0){
				criteria.andNameLike("%"+article.getName()+"%");
			}
			if(article.getSource()!=null && article.getSource().length()>0){
				criteria.andSourceLike("%"+article.getSource()+"%");
			}
			if(article.getAuthor()!=null && article.getAuthor().length()>0){
				criteria.andAuthorLike("%"+article.getAuthor()+"%");
			}
	
		}
		
		List<TbArticle> lists= articleMapper.selectByExample(example);		

		PageInfo<TbArticle> page = new PageInfo<>(lists);

		return new PageResult(page.getTotal(), page.getList());
	}
	
}
