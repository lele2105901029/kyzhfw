package com.kyzh.sellergoods.service.impl;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.kyzh.mapper.TbSpecificationOptionMapper;
import com.kyzh.pojo.TbSpecificationOption;
import com.kyzh.pojo.TbSpecificationOptionExample;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import com.kyzh.mapper.TbTypeTemplateMapper;
import com.kyzh.pojo.TbTypeTemplate;
import com.kyzh.pojo.TbTypeTemplateExample;
import com.kyzh.pojo.TbTypeTemplateExample.Criteria;
import com.kyzh.sellergoods.service.TypeTemplateService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

	@Autowired
	private TbTypeTemplateMapper typeTemplateMapper;

	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbTypeTemplate> findAll() {
		return typeTemplateMapper.selectByExample(null);
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbTypeTemplate typeTemplate) {
		typeTemplateMapper.insert(typeTemplate);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbTypeTemplate typeTemplate){
		typeTemplateMapper.updateByPrimaryKey(typeTemplate);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbTypeTemplate findOne(Long id){
		return typeTemplateMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			typeTemplateMapper.deleteByPrimaryKey(id);
		}		
	}
	
	/**
	 * 分页查询+模糊搜索
	 */
	@Override
	public PageResult findAll(TbTypeTemplate typeTemplate, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbTypeTemplateExample example=new TbTypeTemplateExample();
		Criteria criteria = example.createCriteria();
		
		if(typeTemplate!=null){			
			if(typeTemplate.getName()!=null && typeTemplate.getName().length()>0){
				criteria.andNameLike("%"+typeTemplate.getName()+"%");
			}
		}
		
		List<TbTypeTemplate> lists= typeTemplateMapper.selectByExample(example);		

		PageInfo<TbTypeTemplate> page = new PageInfo<>(lists);

		return new PageResult(page.getTotal(), page.getList());
	}

	@Override
	public List<Map> findSpecList(Long id) {
		//模版id获取模版对象
		TbTypeTemplate template = typeTemplateMapper.selectByPrimaryKey(id);

		List<Map> maps = JSON.parseArray(template.getSpecIds(), Map.class);

		//循环specIds，
		for (Map map : maps) {
			//根据规格id获取该规格先的规格选项
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			example.createCriteria().andSpecIdEqualTo(Long.parseLong(map.get("id").toString()));
			List<TbSpecificationOption> options = specificationOptionMapper.selectByExample(example);
			//给循环的规格增加规格选项{id:xxx,text:xxxx,options:[]}
			map.put("options", options);
		}
		return maps;
	}

}
