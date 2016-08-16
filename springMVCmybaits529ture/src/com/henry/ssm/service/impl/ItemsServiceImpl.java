package com.henry.ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.henry.ssm.mapper.ItemsMapper;
import com.henry.ssm.mapper.ItemsMapperCustom;
import com.henry.ssm.po.Items;
import com.henry.ssm.po.ItemsCustom;
import com.henry.ssm.po.ItemsQueryVo;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2016年5月25日 下午2:41:56 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private ItemsMapperCustom itemsMapperCustom;
	
	@Autowired
	private ItemsMapper itemsMapper;
	
	
	
	
	
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		
	
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}

	
	
	
	
	
	
	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		
		Items items = itemsMapper.selectByPrimaryKey(id);
		
		//各种操作
		//返回2次处理的对象
		ItemsCustom itemsCustom = new ItemsCustom();
		
		BeanUtils.copyProperties(items, itemsCustom);
		
		return itemsCustom;
	}
	
	
	
	
	

	@Override
	public void updateItemsById(Integer id, ItemsCustom itemsCustom) throws Exception {
		
		//id 的各种校验
		
		
		itemsCustom.setId(id);
		
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
		
		
	}

	
}
