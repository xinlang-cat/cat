package com.xinlang.zly.map.bean;


import lombok.Data;

import java.io.Serializable;

@Data
public class BaiDuData implements Serializable {

	private static final long serialVersionUID = 5220799671604233344L;
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
