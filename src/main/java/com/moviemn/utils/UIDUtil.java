package com.moviemn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UIDUtil {
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

	public static synchronized String next() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

	public static synchronized String saveClildPathname() {
		return formatter.format(new Date());
	}
}
