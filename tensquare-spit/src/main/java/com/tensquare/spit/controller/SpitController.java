package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/spit")
@CrossOrigin
@RestController
public class SpitController {
    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping(method= RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());

    }
    @RequestMapping(value = "/{spitId}",method= RequestMethod.GET)
    public Result findById(@PathVariable String spitId){
        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(spitId));

    }
    @RequestMapping(value = "/{spitId}",method= RequestMethod.PUT)
    public Result update(@PathVariable String spitId,@RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "修改成功");

    }
    @RequestMapping(method= RequestMethod.POST)
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true, StatusCode.OK, "保存成功");

    }
    @RequestMapping(value = "/{spitId}",method= RequestMethod.DELETE)
    public Result deleteById(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK, "删除成功");

    }
    @RequestMapping(value = "/comment/{parentid}/{page}/{size}",method =RequestMethod.GET)
    public Result findByParentid(@PathVariable String parentid,@PathVariable int page,@PathVariable int size){
        return new Result(true, StatusCode.OK, "查询成功",spitService.findByParentid(parentid,page,size));
    }


    /**
     * 点赞
     * @param id
     * @return
     */
    @RequestMapping(value="/thumbup/{id}",method=RequestMethod.PUT)
    public Result updateThumbup(@PathVariable String id){
        //判断用户是否点过赞
        String userid="2023";// 后边我们会修改为当前登陆的用户
        if(redisTemplate.opsForValue().get("thumbup_"+userid+"_"+id)!=null){
            return new Result(false,StatusCode.REPERROR,"你已经点过赞了");
        }
        spitService.updateThumbup(id);
        redisTemplate.opsForValue().set( "thumbup_"+userid+"_"+ id,"1");
        return new Result(true,StatusCode.OK,"点赞成功");

    }




}
