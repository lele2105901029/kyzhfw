package com.kyzh.manager.controller;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.kyzh.pojo.TbArticleContent;
import com.kyzh.article.service.ArticleContentService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/articleContent")
public class ArticleContentController {

	@Reference
	private ArticleContentService articleContentService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbArticleContent> findAll(){			
		return articleContentService.findAll();
	}
	
	
	/**
	 * 查询+分页
	 * 参数一 查询对象
	 * @param pageNo 页码
	 * @param pageSize 每页记录数
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(@RequestBody TbArticleContent articleContent,int pageNo,int pageSize){			
		return articleContentService.findAll(articleContent, pageNo, pageSize);
	}
	

	/**
	 * 增加
	 * @param articleContent
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbArticleContent articleContent){
		try {
			articleContentService.add(articleContent);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param articleContent
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbArticleContent articleContent){
		try {
			articleContentService.update(articleContent);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbArticleContent findOne(Long id){
		return articleContentService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			articleContentService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
}
