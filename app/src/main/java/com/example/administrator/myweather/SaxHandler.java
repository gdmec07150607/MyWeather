package com.example.administrator.myweather;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/12.
 */

public class SaxHandler extends DefaultHandler{
    // 保存省份和城市， key是省份名称， value是省份下所有城市名的列表
    private Map<String, List<String>> cityMap = new HashMap<String, List<String>>();
    // 临时保存读取到的省份和城市名称
    String cityName = "";
    String provinceName = "";
    public Map<String, List<String>> getCityMap(){
        return cityMap;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if ("Province".equals(qName)) {   // 读取省份信息
            provinceName = attributes.getValue("name");
            // 每读取到一个省份节点， 就将省份名字存入map
            cityMap.put(provinceName, new ArrayList<String>());
        } else if ("City".equals(qName)) {  //读取城市信息
            cityName = attributes.getValue("name");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if ("City".equals(qName)) {
            // 读取到城市节点的结束标签后， 将读到的城市所在省份存在对应的list里面
            cityMap.get(provinceName).add(cityName);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}