package com.xinlang.zly.map.controller;

import com.xinlang.zly.map.bean.BaiDuData;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-06
 */
@RestController
@RequestMapping("/baiDuMap")
public class BaiDuMapController {


    @Value("${baidu.map.ak}")
    private String baiDuMapAK;


    /**
     * @param lat 百度地图通过经纬度来获取地址,传入参数纬度lat、经度lng该方法数据解析并未完善，BaiDuRoads实体需要添加属性
     * @param lng 百度地图通过经纬度来获取地址,传入参数纬度lat、经度lng该方法数据解析并未完善，BaiDuRoads实体需要添加属性
     * @return 地址
     */
    @GetMapping(value = "/address",params = {"lat","lng"})
    public BaiDuData getLocationInfo(String lat, String lng) {
        // String url =
        // "http://api.map.baidu.com/geocoder?ak=1RCUrYA1VEnxozLjRGIL84uBBZT48GtS"
        // + "&callback=renderReverse&location=30.68093376455154,104.06552381979525" +
        // "&output=json";
        String url1 = "http://api.map.baidu.com/geocoder/v2/?" + "callback=renderReverse&location=" + lat + "," + lng
                + "&output=json&pois=1&ak=" + baiDuMapAK;
        String str = doGetString(url1).substring(29);
        JSONObject result = JSONObject.fromObject(str.substring(0, str.length() - 1)).getJSONObject("result");
        System.out.println(result);
        BaiDuData baiduDATA = (BaiDuData) JSONObject.toBean(result, BaiDuData.class);
        return baiduDATA;
    }

    /**
     * 百度地图通过地址来获取经纬度，传入参数address
     * @param address 地址
     * @return 经纬度
     */
    @GetMapping("/lng_lat/{address}")
    public Map<String, Double> findLngAndLat(@PathVariable String address) {
        Map<String, Double> map = new HashMap<>();
        String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address + "&output=json&ak="+baiDuMapAK;
        String json = loadJSON(url);
        JSONObject obj = JSONObject.fromObject(json);
        if (obj.get("status").toString().equals("0")) {
            double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
            double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
            map.put("lng", lng);
            map.put("lat", lat);
        } else {
            throw new RuntimeException("未找到匹配的数据！");
        }
        return map;
    }

    private String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            throw new IllegalArgumentException("获取位置信息失败!");
        }
        return json.toString();
    }

    private String doGetString(String url) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        try{
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, "UTF-8");
            }
        }catch (Exception e){
            throw new IllegalArgumentException("获取位置信息失败!");
        }finally {
            httpGet.releaseConnection();
        }
        return null;
    }

}
