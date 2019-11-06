package com.xinlang.zly.map.bean;

import lombok.Data;

@Data
public class BaiDuPois {

	private String addr;
	private String  cp;
	private String  direction;
	private String  distance;
	private String  name;
	private String  poiType;
	private BaiDuPoint  point;
	private String  tag;
	private String  tel;
	private String  uid;
	private String  zip;
	private BaiDuParent_poi  parent_poi;

	
}
