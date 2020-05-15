package com.xinlang.zly_xyx.cat_common.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.xml.XMLSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtil {
	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		try {
			ObjectMapper om = new ObjectMapper();
			return om.readValue(jsonString, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param arr
	 * @return
	 */
	public static String arrayToJson(Object[] arr) {
		JSONArray jsonArray = JSONArray.fromObject(arr);
		return jsonArray.toString();
	}

	/**
	 * 
	 * 
	 * @param list
	 * @return
	 */
	public static String list2json(@SuppressWarnings("rawtypes") List list) {

		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();

	}

	/**
	 * 
	 * 
	 * @param map
	 * @return
	 */
	public static String map2json(@SuppressWarnings("rawtypes") Map map) {

		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();

	}

	/**
	 * 
	 * 
	 * @param object
	 * @return
	 */
	public static String object2json(Object object) {

		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();

	}

	/**
	 * 
	 * 
	 * @param xml
	 * @return
	 */
	public static String xml2json(String xml) {

		JSONArray jsonArray = (JSONArray) new XMLSerializer().read(xml);
		return jsonArray.toString();

	}

	/**
	 *
	 *
	 * @param excludes
	 * @return
	 */
	public static JsonConfig configJson(String[] excludes) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		jsonConfig.setIgnoreDefaultExcludes(true);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return jsonConfig;
	}

	/**
	 * json转换为List集合
	 * 
	 * @param str
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> strToList(String str, Class<T> clazz) {
		JSONArray json = JSONArray.fromObject(str);
		JSONObject object = null;
		T t = null;
		List<T> list = new ArrayList<T>();
		for (int i = 0; i < json.size(); i++) {
			object = JSONObject.fromObject(json.get(i));
			t = (T) JSONObject.toBean(object, clazz);
			list.add(t);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> strToList2(String str, Class<T> clazz, @SuppressWarnings("rawtypes") Map<String, Class> map) {
		JSONArray json = JSONArray.fromObject(str);
		JSONObject object = null;
		T t = null;
		List<T> list = new ArrayList<T>();
		for (int i = 0; i < json.size(); i++) {
			object = JSONObject.fromObject(json.get(i));
			t = (T) JSONObject.toBean(object, clazz, map);
			list.add(t);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public static <T> T strToObject(String str, Class<T> clazz) {
		JSONObject object = null;
		T t = null;
		object = JSONObject.fromObject(str);
		t = (T) JSONObject.toBean(object, clazz);
		return t;
	}

}
