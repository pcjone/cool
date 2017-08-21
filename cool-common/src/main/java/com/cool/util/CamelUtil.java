package com.cool.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 骆驼命名工具类转换
 * @author wangjun
 * @date 2014-08-17
 */
public class CamelUtil {

	/**下划线*/
    public static final char UNDERLINE='_';
    /**
     * 骆驼式转下划线
     * @param param
     * @return
     */
    public static String camelToUnderline(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    /**
     * 下划线转骆驼式
     * @param param
     * @return
     */
    public static String underlineToCamel(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (c==UNDERLINE){
               if (++i<len){
                   sb.append(Character.toUpperCase(param.charAt(i)));
               }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    /**
     * 下划线转骆驼式
     * @param param
     * @return
     */
    public static String underlineToCamel2(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        StringBuilder sb=new StringBuilder(param);
        Matcher mc= Pattern.compile("_").matcher(param);
        int i=0;
        while (mc.find()){
            int position=mc.end()-(i++);
            //String.valueOf(Character.toUpperCase(sb.charAt(position)));
            sb.replace(position-1,position+1,sb.substring(position,position+1).toUpperCase());
        }
        return sb.toString();
    }
    
	public static void main(String[] args) {
		System.out.println(camelToUnderline("codeName"));
		System.out.println(camelToUnderline("codename"));
		System.out.println(underlineToCamel("codename"));
		System.out.println(underlineToCamel("code_name"));
	}


}
