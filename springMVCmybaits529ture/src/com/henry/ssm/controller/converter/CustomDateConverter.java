package com.henry.ssm.controller.converter;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/** 
 * 参数绑定转换器
* @author  作者 E-mail: 
* @date 创建时间：2016年5月27日 下午1:59:46 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public class CustomDateConverter implements Converter<String, Date> {

	
	
	@Override
	public Date convert(String arg0) {
		//日期string转换成日期类型
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			//如果转换成功
			return format.parse(arg0);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//参数绑定失败。返回null
		return null;
	}

}
