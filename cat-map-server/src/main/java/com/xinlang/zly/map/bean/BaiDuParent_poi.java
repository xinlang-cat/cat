package com.xinlang.zly.map.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaiDuParent_poi implements Serializable {

	private static final long serialVersionUID = 6662450149968341237L;
	private String name;
	private String tag;
	private String addr;
	private BaiDuPoint point;
	private String direction;
	private String distance;
	private String uid;

	
}
