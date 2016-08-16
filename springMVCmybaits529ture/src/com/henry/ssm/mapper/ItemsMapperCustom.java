package com.henry.ssm.mapper;

import com.henry.ssm.po.Items;
import com.henry.ssm.po.ItemsCustom;
import com.henry.ssm.po.ItemsExample;
import com.henry.ssm.po.ItemsQueryVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemsMapperCustom {
   
	/**
	 *<p>description:商品查询列表 </P>
	 *@return 
	 *@param 
	 *@date 2016年5月25日下午2:35:15
	 */
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}