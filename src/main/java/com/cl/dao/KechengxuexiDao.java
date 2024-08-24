package com.cl.dao;

import com.cl.entity.KechengxuexiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KechengxuexiView;


/**
 * 课程学习
 * 
 * @author 
 * @email 
 * @date 2024-03-06 14:47:47
 */
public interface KechengxuexiDao extends BaseMapper<KechengxuexiEntity> {
	
	List<KechengxuexiView> selectListView(@Param("ew") Wrapper<KechengxuexiEntity> wrapper);

	List<KechengxuexiView> selectListView(Pagination page,@Param("ew") Wrapper<KechengxuexiEntity> wrapper);
	
	KechengxuexiView selectView(@Param("ew") Wrapper<KechengxuexiEntity> wrapper);
	

}
