package com.kyzh.sellergoods.service;
import java.util.List;
import java.util.Map;

import com.kyzh.pojo.TbSpecification;

import entity.PageResult;
import entity.Specification;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface SpecificationService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbSpecification> findAll();
	
	
	/**
	 * 增加
	*/
	public void add(Specification specification);
	
	
	/**
	 * 修改
	 */
	public void update(Specification specification);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Specification findOne(Long id);
	
	
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
	public PageResult findAll(TbSpecification specification, int pageNum, int pageSize);


	List<Map> selectOptions();
}
