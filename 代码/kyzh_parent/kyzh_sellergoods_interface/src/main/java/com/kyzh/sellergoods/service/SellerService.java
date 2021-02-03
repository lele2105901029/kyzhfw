package com.kyzh.sellergoods.service;
import java.util.List;
import com.kyzh.pojo.TbSeller;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface SellerService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbSeller> findAll();
	
	
	/**
	 * 增加
	*/
	public void add(TbSeller seller);
	
	
	/**
	 * 修改
	 */
	public void update(TbSeller seller);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbSeller findOne(String id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(String[] ids);

	/**
	 * 分页+模糊搜索
	 * 参数一模糊搜索条件
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findAll(TbSeller seller, int pageNum, int pageSize);
	/**
	 * 修改企业状态
	 * @param status
	 */
	public void updateStatus(String sellerId, String status);
}
