package com.xinlang.zly.map.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaiDuAddressComponent implements Serializable {

	private static final long serialVersionUID = -511658382118719818L;
	private String country;
	private Integer country_code;
	private String country_code_iso;
	private String country_code_iso2;
	private String province;
	private String city;
	private	Integer city_level;
	private String district;
	private String town;
	private String adcode;
	private String street;
	private String street_number;
	private String direction;
	private String distance;
}
