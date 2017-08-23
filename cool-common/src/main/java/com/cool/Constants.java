package com.cool;

/**
 * 常量表
 * 
 * @author ShenHuaJie
 * @version $Id: Constants.java, v 0.1 2014-2-28 上午11:18:28 ShenHuaJie Exp $
 */
public interface Constants {
	/**
	 * 异常信息统一头信息<br>
	 * 非常遗憾的通知您,程序发生了异常
	 */
	public static final String Exception_Head = "OH,MY GOD! SOME ERRORS OCCURED! AS FOLLOWS :";
	/** 客户端语言 */
	public static final String USERLANGUAGE = "userLanguage";
	/** 客户端主题 */
	public static final String WEBTHEME = "webTheme";
	/** 当前用户信息 */
	public static final String CURRENT_USER = "CURRENT_USER";
	/** 在线用户数量 */
	public static final String ALLUSER_NUMBER = "ALLUSER_NUMBER";
	/** 登录用户数量 */
	public static final String USER_NUMBER = "USER_NUMBER";
	/** 上次请求地址 */
	public static final String PREREQUEST = "PREREQUEST";
	/** 上次请求时间 */
	public static final String PREREQUEST_TIME = "PREREQUEST_TIME";
	/** 非法请求次数 */
	public static final String MALICIOUS_REQUEST_TIMES = "MALICIOUS_REQUEST_TIMES";
	/** 缓存命名空间 */
	public static final String CACHE_NAMESPACE = "Cool:";
	/**收费员CODE*/
	public final static String CODE_TYPE_COLL_CODE = "COLL_CODE";

	/**是否锁定*/
	public static final Integer ENABLE_YES = 1;//锁定
	public static final Integer ENABLE_NO = 0;//未锁定
	/**启用状态*/
	public static final Integer STATUS_ENABLE = 1;
	public static final Integer STATUS_DISABLE = 0;

	/**
	 * 编码类型
	 * */
	public final static String CODE_TYPE_CITY_CODE = "CITY_CODE";
	public final static String CODE_TYPE_AREA_CODE = "AREA_CODE";
	public final static String CODE_TYPE_STREET_CODE = "STREET_CODE";

	/**
	 * 以分为单位的因子
	 * */
	public final static int TIME_FACTORY = 100;
	public final static double DIVISION_FACTORY = 100.00;
	public final static int TIME_FACTORY_HUN = 10000;

	// 常量
	public final static Integer INT_ZERO = 0;
	public final static Integer INT_ONE = 1;
	public final static Integer INT_TWO = 2;
	public final static Integer ONEWEEK_MILLISECOND = 7 * 24 * 60 * 60 * 1000; //7天毫秒数
	public final static Integer ONEDAY_MILLISECOND = 24 * 60 * 60 * 1000; //1天毫秒数
	public final static Integer ONEHOUR_MILLISECOND = 60 * 60 * 1000; //1小时毫秒数
	public final static String STRING_ZERO = "0";
	public final static String STRING_ONE = "1";
	public final static String STRING_TWO = "2";

	//权限顶级parentId
	public final static Long PERMISSION_ZERO = 0L;

	public final static Long LONG_ZERO = (long) 0;

	/**
	 * 是否通用
	 */
	public final static Integer ISNOTCOMMON = 0;
	public final static Integer ISCOMMON = 1;

	//是否包含下级
	public final static Integer CONTAINS_NO = 0;//不包含
	public final static Integer CONTAINS_YES = 1;//包含

	//区域等级
	public final static int AREA_LEVEL_CITY = 1; //城市
	public final static int AREA_LEVEL_REGION = 2;//区域
	public final static int AREA_LEVEL_STREET = 3;//街道
	public final static int AREA_LEVEL_ROAD = 4;//道路

	public final static String AREA_LEVEL_CITY_STR = "CITY_CODE"; //城市
	public final static String AREA_LEVEL_REGION_STR = "REGION_ID";//区域
	public final static String AREA_LEVEL_STREET_STR = "STREET_ID";//街道
	public final static String AREA_LEVEL_ROAD_STR = "ROAD_ID";//道路

	public final static String DATA_PERMISSION_DEPT_ID = "DEPT_ID";//部门数据权限拼装ID
	//统计形成时间，00=
	public final static String STAT_TIME_AREA_PARM = "00:00:00";//统计时间区间，时间点，小时开始

	/*是否超级管理员*/
	public final static Integer SUPER_ADMIN_YES = 1;//超级管理员
	public final static Integer SUPER_ADMIN_NO = 0;//非超级管理员

	/**
	 * 缓存key值前缀
	 */
	//数据字典
	public final static String DICTIOINARY_CACHE = "DICTIOINARY_CACHE_";
	//区域
	public final static String AREA_CACHE = "AREA_CACHE_";
	//部门
	public final static String DEPT_CACHE = "DEPT_CACHE_";
	//参数
	public final static String PARAMTER_CACHE = "PARAMTER_CACHE_";
	//用户
	public final static String USER_CACHE = "USER_CACHE_";
	
	public final static String TARGET = "target";
	public final static String OUT_TAG_NAME = "outTagName";
	
	/*
	 * 登录异常
	 */
	public static final String USER_NOT_EXIST = "账户不存在！";
	public static final String USER_WRONG = "用户名或密码错误！";
	public static final String ACCOUNT_IS_NULL = "账号不能为空！";
	public static final String PASSWORD_IS_NULL = "请输入密码！";
	public static final String SUCCESS  ="登录成功";
	public static final String FAILURE  ="登录失败";
	public static final String USER_ENABLED ="账号被锁定，请联系管理员";

}
