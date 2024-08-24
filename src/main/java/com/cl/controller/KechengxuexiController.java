package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.KechengxuexiEntity;
import com.cl.entity.view.KechengxuexiView;

import com.cl.service.KechengxuexiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 课程学习
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-06 14:47:47
 */
@RestController
@RequestMapping("/kechengxuexi")
public class KechengxuexiController {
    @Autowired
    private KechengxuexiService kechengxuexiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KechengxuexiEntity kechengxuexi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			kechengxuexi.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			kechengxuexi.setXuehao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<KechengxuexiEntity> ew = new EntityWrapper<KechengxuexiEntity>();

		PageUtils page = kechengxuexiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengxuexi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,KechengxuexiEntity kechengxuexi, 
		HttpServletRequest request){
        EntityWrapper<KechengxuexiEntity> ew = new EntityWrapper<KechengxuexiEntity>();

		PageUtils page = kechengxuexiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengxuexi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KechengxuexiEntity kechengxuexi){
       	EntityWrapper<KechengxuexiEntity> ew = new EntityWrapper<KechengxuexiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kechengxuexi, "kechengxuexi")); 
        return R.ok().put("data", kechengxuexiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KechengxuexiEntity kechengxuexi){
        EntityWrapper< KechengxuexiEntity> ew = new EntityWrapper< KechengxuexiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kechengxuexi, "kechengxuexi")); 
		KechengxuexiView kechengxuexiView =  kechengxuexiService.selectView(ew);
		return R.ok("查询课程学习成功").put("data", kechengxuexiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KechengxuexiEntity kechengxuexi = kechengxuexiService.selectById(id);
		kechengxuexi = kechengxuexiService.selectView(new EntityWrapper<KechengxuexiEntity>().eq("id", id));
        return R.ok().put("data", kechengxuexi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KechengxuexiEntity kechengxuexi = kechengxuexiService.selectById(id);
		kechengxuexi = kechengxuexiService.selectView(new EntityWrapper<KechengxuexiEntity>().eq("id", id));
        return R.ok().put("data", kechengxuexi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KechengxuexiEntity kechengxuexi, HttpServletRequest request){
    	kechengxuexi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(kechengxuexi);
        kechengxuexiService.insert(kechengxuexi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KechengxuexiEntity kechengxuexi, HttpServletRequest request){
    	kechengxuexi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(kechengxuexi);
        kechengxuexiService.insert(kechengxuexi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KechengxuexiEntity kechengxuexi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kechengxuexi);
        kechengxuexiService.updateById(kechengxuexi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kechengxuexiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
