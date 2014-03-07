package org.hmahout.util;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class StringUtils {

	/**
	 * 生成sql语句的?号
	 * 
	 * @param count
	 * @return
	 */
	public static String generateQuestionMark(int count) {
		StringBuffer sb = new StringBuffer("");
		if (count > 0) {
			sb.append("?");
			for (int i = 1; i < count; i++) {
				sb.append(",");
				sb.append("?");
			}
		}
		return sb.toString();
	}

	public static boolean isBlank(String s) {
		return s == null || s.trim().length() < 1;
	}

	public static boolean isNull(Object o) {
		return o == null;
	}

	public static String collectionToString(Collection<String> coll) {
		StringBuffer sb = new StringBuffer();
		if (coll != null && coll.size() > 0) {
			for (String item : coll) {
				sb.append(item + ",");
			}
			return sb.substring(0, sb.length() - 1);
		}
		return null;
	}

	/**
	 * 以一种简单的方式格式化字符串 如 String s = StringHelper.format("{0} is {1}", "apple",
	 * "fruit"); System.out.println(s); //输出 apple is fruit.
	 * 
	 * @param pattern
	 * @param args
	 * @return
	 */
	public static String format(String pattern, Object... args) {
		for (int i = 0; i < args.length; i++) {
			pattern = pattern.replace("{" + i + "}", args[i].toString());
		}
		return pattern;
	}

	/**
	 * 校验邮箱是否合法
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String check = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		return matcher.matches();
	}

	public static boolean isPassword(String pwd) {
		String check = "^[a-zA-Z0-9.!@#$%^&*+-_]{6,16}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(pwd);
		return matcher.matches();
	}

	public static boolean isBlank(Integer obj) {
		return null == obj;
	}

	/**
	 * 判断字符串short是否是字符串long的开始字符串
	 * 
	 * @return
	 * @throws ActionException
	 */
	public static boolean isInclude(String shortStr, String longStr) {
		int position = longStr.indexOf(shortStr);
		if (position == 0)
			return true;

		return false;

	}

	public static Object getBlankNull(Object o) {
		return (o == null) ? "" : o;
	}

	// 是否是子网机
	public static boolean isSubPhoneid(String subPhoneid) {
		if (subPhoneid == null) {
			return false;
		}

		String check = "^[1-9]\\d{4,10}#[1-9]\\d{0,3}|#[1-9]\\d{4,10}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(subPhoneid.trim());
		return matcher.matches();
	}

	/**
	 * 取系统的换行
	 * 
	 * @return
	 */
	public static String otherLine() {
		return System.getProperty("line.separator");
	}

	public static boolean isDecimal(String str) {
		if (str == null || "".equals(str))
			return false;
		java.util.regex.Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 去空格、回车、制表符
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
	}
	
	public static String replaceXML(String str){
		if(isBlank(str))
			return "";
		str = str.replaceAll("&amp;", "&");
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("&apos;", "'");
		str = str.replaceAll("&quot;", "\"");
		
		return str;
	}
	
}
