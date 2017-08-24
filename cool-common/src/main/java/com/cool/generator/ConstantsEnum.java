/**
 * Innotek.com.cn Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.cool.generator;

/**
 * 
* @ClassName: ConstantsEnum 
* @Description: 全局定义
* @author panlei
* @date 2017年8月16日 下午2:35:00 
*
 */
public class ConstantsEnum {
	/**
	 * YesOrNo
	 */
	public static enum YesOrNo {
		NO(0, "否"), YES(1, "是");
		public int key;
		public String value;

		private YesOrNo(int key, String value) {
			this.key = key;
			this.value = value;
		}

		public static YesOrNo get(int key) {
			YesOrNo[] values = YesOrNo.values();
			for (YesOrNo object : values) {
				if (object.key == key) {
					return object;
				}
			}
			return null;
		}
	}
	
	public static enum UserType{
		RegisterUser("1","普通用户"),ManagerUser("2","管理员"),SystemUser("3","系统用户");
		public String key;
		public String value;
		private UserType(String key, String value) {
			this.key = key;
			this.value = value;
		}
		public static UserType get(String key) {
			UserType[] values = UserType.values();
			for (UserType object : values) {
				if (object.key == key) {
					return object;
				}
			}
			return null;
		}
	}

	/**
	 * 删除枚举
	 *
	 */
	public static enum DELETED {
		NO(0, "未删除"), YES(1, "已删除");
		public int key;
		public String value;

		private DELETED(int key, String value) {
			this.key = key;
			this.value = value;
		}

		public static DELETED get(int key) {
			DELETED[] values = DELETED.values();
			for (DELETED object : values) {
				if (object.key == key) {
					return object;
				}
			}
			return null;
		}
	}

	/**
	 * 区域等级
	 */
	public static enum AreaLevel {
		CITY(1, "市"), REGION(2, "区"), STREET(3, "街道"), ROAD(4, "道路");
		public int key;
		public String value;

		private AreaLevel(int key, String value) {
			this.key = key;
			this.value = value;
		}

		public static YesOrNo get(int key) {
			YesOrNo[] values = YesOrNo.values();
			for (YesOrNo object : values) {
				if (object.key == key) {
					return object;
				}
			}
			return null;
		}
	}
	
	public enum IsValid {
		YES(0, "有效"), NO(1,"无效");
		public int key;
		public String value;
		private IsValid(int key, String value) {
			this.key = key;
			this.value = value;
		}
		public static IsValid get(int key) {
			IsValid[] values = IsValid.values();
			for (IsValid object : values) {
				if (object.key == key) {
					return object;
				}
			}
			return null;
		}
	}
}
