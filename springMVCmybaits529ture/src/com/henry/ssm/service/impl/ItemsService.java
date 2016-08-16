package com.henry.ssm.service.impl;

import java.util.List;

import com.henry.ssm.po.Items;
import com.henry.ssm.po.ItemsCustom;
import com.henry.ssm.po.ItemsQueryVo;

/** 
* @author  作者 henry: 
* @date 创建时间：2016年5月25日 下午2:37:48 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public interface ItemsService {

	/**
	 * 
	 *<p>description:商品查询列表 </P>
	 *@return 
	 *@param 
	 *@date 2016年5月25日下午2:39:09
	 *
	 */
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
	/**
	 * 
	 *<p>description:根据id查找商品 </P>
	 *@return 
	 *@param 
	 *@date 2016年5月26日上午11:44:29
	 *
	 */
	public ItemsCustom findItemsById(Integer id) throws Exception;
	/**
	 * 
	 *<p>description:根据id更新商品 </P>
	 *@return 
	 *@param 
	 *@date 2016年5月26日上午11:46:45
	 *
	 */
	public void updateItemsById(Integer id,ItemsCustom itemsCustom) throws Exception;
}
