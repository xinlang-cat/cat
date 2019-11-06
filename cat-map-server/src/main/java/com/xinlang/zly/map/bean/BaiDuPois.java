package com.xinlang.zly.map.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaiDuPois implements Serializable {

	private static final long serialVersionUID = 8934342017888918014L;
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
