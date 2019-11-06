package com.xinlang.zly.map.bean;


import lombok.Data;

@Data
public class BaiDuData {

	private BaiDuLocation location;
	private String formatted_address;
	private String business;
	private BaiDuAddressComponent addressComponent;
	private BaiDuPois[] pois;
	private BaiDuRoads[] roads;
	private BaiDuPoiRegions[] poiRegions;
	private String sematic_description;
	private Integer cityCode;

	
}
