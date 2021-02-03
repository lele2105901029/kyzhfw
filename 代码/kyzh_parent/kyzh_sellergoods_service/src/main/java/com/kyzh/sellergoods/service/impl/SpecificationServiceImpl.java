package com.kyzh.sellergoods.service.impl;
import java.util.List;
import java.util.Map;

import com.kyzh.mapper.TbSpecificationOptionMapper;
import com.kyzh.pojo.TbSpecificationOption;
import com.kyzh.pojo.TbSpecificationOptionExample;
import entity.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import com.kyzh.mapper.TbSpecificationMapper;
import com.kyzh.pojo.TbSpecification;
import com.kyzh.pojo.TbSpecificationExample;
import com.kyzh.pojo.TbSpecificationExample.Criteria;
import com.kyzh.sellergoods.service.SpecificationService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;

	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {

		//将页面传入的封装对象中的规格对象保存到数据库
		TbSpecification spec = specification.getSpec();
		specificationMapper.insert(spec);

		//将页面传入的封装对象中的规格选项保存到数据库
		List<TbSpecificationOption> options = specification.getSpecOptions();
		for (TbSpecificationOption option : options) {
			option.setSpecId(spec.getId()); //维护规格选项和规格的多对一关系
			specificationOptionMapper.insert(option);
		}

	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification){
		//修改规格封装对象
		TbSpecification spec = specification.getSpec();
		specificationMapper.updateByPrimaryKey(spec);

		//修改规格选项:分解成两部 一、删除掉该规格下的所有规格选项  二、本次规格选项集合改成insert操作
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		example.createCriteria().andSpecIdEqualTo(spec.getId()); //当前规格下的所有规格选项
		specificationOptionMapper.deleteByExample(example);

		//改成新增操作
		List<TbSpecificationOption> options = specification.getSpecOptions();
		for (TbSpecificationOption option : options) {
			option.setSpecId(spec.getId()); //维护规格选项和规格的多对一关系
			specificationOptionMapper.insert(option);
		}
	}
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
		Specification specification = new Specification();
		//查询规格对象
		specification.setSpec(specificationMapper.selectByPrimaryKey(id));
		//查询该规格对象对应的规格选项集合
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		example.createCriteria().andSpecIdEqualTo(id);
		List<TbSpecificationOption> options = specificationOptionMapper.selectByExample(example);
		specification.setSpecOptions(options);

		return specification;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			//根据规格id删除规格对象
			specificationMapper.deleteByPrimaryKey(id);

			//删除对象的规格选项
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			example.createCriteria().andSpecIdEqualTo(id); //当前规格下的所有规格选项
			specificationOptionMapper.deleteByExample(example);
		}		
	}
	
	/**
	 * 分页查询+模糊搜索
	 */
	@Override
	public PageResult findAll(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
			if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		List<TbSpecification> lists= specificationMapper.selectByExample(example);		

		PageInfo<TbSpecification> page = new PageInfo<>(lists);

		return new PageResult(page.getTotal(), page.getList());
	}

	@Override
	public List<Map> selectOptions() {
		return specificationMapper.selectOptions();
	}

}
