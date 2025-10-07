package com.andershow.springbootmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {
	//o método abaixo é apenas para decodificar a url
	//explicando: quando a requisição de pesquisa é feita acontece algo asssim:
	//pesquisa?"estou%20aqui" o espaço é substituido, essa função vai decodificar esse espaço
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	public static Date convertDate(String textDate, Date defaultDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return sdf.parse(textDate);
		} catch (ParseException e) {
			return defaultDate;
		}
	}
}
