package com.henry.ssm.po;

import java.util.List;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2016年5月25日 下午2:27:32 
* @version 1.0 
* @parameter  
* @since  
* @return  
* 查询条件包装类
*/
public class ItemsQueryVo {
	
	
	private Items items;
	
	private ItemsCustom itemsCustom;
	
	
	private List<ItemsCustom> itemsList;
	
	
	

	public List<ItemsCustom> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<ItemsCustom> itemsList) {
		this.itemsList = itemsList;
	}

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}

	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}
	
	

}
