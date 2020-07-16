package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    /**
     * 查询全部标签
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Label> list=labelService.findAll();
        return new Result(true, StatusCode.OK, "查询成功",list);
    }

    /**
     * 根据id查询标签
     * @param labelId
     * @return
     */
    @RequestMapping("/{labelId}")
    public Result findById(@PathVariable String labelId){
        labelService.findById(labelId);
        return new Result(true, StatusCode.OK, "查询成功");
    }

    /**
     * 新增标签
     * @param label
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改标签
     * @param labelId
     * @param label
     * @return
     */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
    public Result update(@PathVariable String labelId,@RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除标签
     * @param labelId
     * @return
     */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String labelId){
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 标签条件查询
     * @param label
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label){
        List<Label> list=labelService.findSearch(label);
        return new Result(true, StatusCode.OK, "查询成功",list);
    }
    /**
     * 标签分页
     * @param label
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result pageQuery(@RequestBody Label label,@PathVariable int page,@PathVariable int size){
        Page pageData=labelService.pageQuery(label,page,size);
        return new Result(true, StatusCode.OK, "查询成功",new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }
}
