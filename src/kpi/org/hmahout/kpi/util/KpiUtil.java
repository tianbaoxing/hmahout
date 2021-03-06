package org.hmahout.kpi.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hmahout.kpi.entity.Kpi;

public class KpiUtil {
	/***
	 * line记录转化成kpi对象
	 * 
	 * @param line
	 *            日志的一条记录
	 * @author tianbx
	 * */
	public static Kpi transformLineKpi(String line) {
		String[] elementList = line.split(" ");
		if(elementList.length<=10){
			System.out.println(line);
			return null;
		}
		Kpi kpi = new Kpi();
		kpi.setRemote_addr(elementList[0]);
		kpi.setRemote_user(elementList[1]);
		kpi.setTime_local(elementList[3].substring(1));
		kpi.setMethod(elementList[5].substring(1));
		kpi.setRequest(elementList[6]);
		kpi.setHttp_version(elementList[7]);
		kpi.setStatus(elementList[8]);
		kpi.setBody_bytes_sent(elementList[9]);
		kpi.setHttp_referer(elementList[10]);

		 if (elementList.length > 11) {
	            kpi.setHttp_user_agent(elementList[11] );
	            kpi.setHttP_user_agent_info(line.substring(line.indexOf(elementList[11])));
	     } 
        if (elementList.length > 12) {
            kpi.setHttp_user_agent(elementList[11] + " " + elementList[12]);
         }
        
		return kpi;
	}
	
	public static String getDomain(String url){
		Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)"
				,Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(url);
		if(matcher.find()){
			return matcher.group();
		}else{
			return url;
		}
		
	}
	
	public static void main(String[] args) {
		getDomain(null);
	}

	public static boolean isNull(Kpi kpi){
		return kpi==null?true:false;
	}
	public static boolean isValid(Kpi kpi) {
		if (kpi == null) {
			return false;
		}
		Set<String> pages = new HashSet<String>();
		pages.add("/about");
		pages.add("/black-ip-list/");
		pages.add("/cassandra-clustor/");
		pages.add("/finance-rhive-repurchase/");
		pages.add("/hadoop-family-roadmap/");
		pages.add("/hadoop-hive-intro/");
		pages.add("/hadoop-zookeeper-intro/");
		pages.add("/hadoop-mahout-roadmap/");

		if (pages.contains(kpi.getRequest())) {
			return true;
		}
		return false;
	}
}
