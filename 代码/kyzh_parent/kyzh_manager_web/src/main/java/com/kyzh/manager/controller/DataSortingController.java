package com.kyzh.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kyzh.article.service.DataSortingService;
import com.kyzh.pojo.TbDataSorting;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dataSorting")
public class DataSortingController {

    @Reference
    private DataSortingService dataSortingService;

    @RequestMapping("/findAll")
    public List<TbDataSorting> findAll(){
        return dataSortingService.findAll();
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int pageNum, int pageSize,@RequestBody TbDataSorting dataSorting) {
        return dataSortingService.findPage(pageNum, pageSize,dataSorting);
    }

    /**
     * 新增方法
     *
     * @param dataSorting
     */
    @RequestMapping("/insert")
    public Result insert(@RequestBody TbDataSorting dataSorting) {
        try {
            dataSortingService.insert(dataSorting);
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
    public TbDataSorting findOne(Long id) {
        return dataSortingService.findOne(id);
    }

    /**
     * 品牌修改方法
     * @param dataSorting
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbDataSorting dataSorting) {
        try {
            dataSortingService.update(dataSorting);
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
            dataSortingService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 文章新增中类目下拉列表
     * @return
     */
    @RequestMapping("/selectOptions")
    public List<Map> selectOptions(){
        return dataSortingService.selectOptions();
    }

    /**
     * 根据id查询该分类下的所有分类列表
     * @param id
     * @return
     */
    @RequestMapping("/findByParentId")
    public List<TbDataSorting> findByParentId(Long id){
        return dataSortingService.findByParentId(id);
    }
}
