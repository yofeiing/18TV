package com.shafa.tv.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	
	// 毫秒时间转普通时间
	public static String timeSimple(long time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss ");
		String date = dateFormat.format(new Date(time));
		return date;
	}


}
