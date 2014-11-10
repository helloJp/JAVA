package com.jp.annotation;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class SuppressWarningTest {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		
		Map map = new TreeMap();
		map.put("hello", new Date());
		System.out.println(map.get("hello"));
		
	}
}
