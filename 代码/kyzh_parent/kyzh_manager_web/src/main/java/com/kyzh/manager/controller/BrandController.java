package com.kyzh.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kyzh.pojo.TbBrand;
import com.kyzh.sellergoods.service.BrandService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    /**
     * 查询全部
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int pageNum, int pageSize,@RequestBody TbBrand brand) {
        return brandService.findPage(pageNum, pageSize,brand);
    }

    /**
     * 新增方法
     *
     * @param brand
     */
    @RequestMapping("/insert")
    public Result insert(@RequestBody TbBrand brand) {
        try {
            brandService.insert(brand);
            return new Result(true, "新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "新增失败");
        }
    }

    /**
     * 品牌对象数据回显
     */
    @RequestMapping("/findOne")
    public TbBrand findOne(Long id) {
        return brandService.findOne(id);
    }

    /**
     * 品牌修改方法
     * @param brand
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand brand) {
        try {
            brandService.update(brand);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 品牌删除方法
     * @param
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            brandService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 模版新增中品牌下拉列表
     * @return
     */
    @RequestMapping("/selectOptions")
    public List<Map> selectOptions(){
        return brandService.selectOptions();
    }
}
