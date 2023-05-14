package com.example.commons.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/*@Autor Juan carlos
 * @Descriptino common utils
 * 
 * */
public class CommonUtil {
	
	/*Method that convert an onject in json
	 * @param Object
	 * @retunr string in json format
	 * */
	public static String convertOjectToJson(Object object) {

		ObjectMapper objectMapper = new ObjectMapper();
		String json = "";
		try {
			json = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

}
