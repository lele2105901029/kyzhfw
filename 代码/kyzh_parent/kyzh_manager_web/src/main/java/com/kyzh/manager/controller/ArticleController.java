package com.kyzh.manager.controller;
import java.util.List;

import entity.Article;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.kyzh.pojo.TbArticle;
import com.kyzh.article.service.ArticleService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

	@Reference
	private ArticleService articleService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbArticle> findAll(){			
		return articleService.findAll();
	}
	
	
	/**
	 * 查询+分页
	 * 参数一 查询对象
	 * @param pageNo 页码
	 * @param pageSize 每页记录数
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(@RequestBody TbArticle article,int pageNo,int pageSize){
		return articleService.findAll(article, pageNo, pageSize);
	}
	

	/**
	 * 增加
	 * @param article
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody Article article){
		try {
			articleService.add(article);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param article
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody Article article){
		try {
			articleService.update(article);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体(封装对象)
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public Article findOne(Long id){
		return articleService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			articleService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
}
