package com.supershen.pro.core;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;


/**
 * 解决springmvc默认的jackson时间转换不支持yyyy-MM-dd HH:mm:ss问题；
 * 需要在转换的Date元素上增加注解@JsonDeserialize(using = DateJsonDeserializer.class)  
 * @author gshen
 *
 */
public class DateJsonDeserializer extends JsonDeserializer<Date> {
	/**
	 * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser,
	 *      com.fasterxml.jackson.databind.DeserializationContext)
	 */
	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(parser.getValueAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
