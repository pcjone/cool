package com.cool.model.enums;

public enum TriggerTypeEnum {
	TRIGGERTYPE_AUTO("01", "定时触发"),
	TRIGGERTYPE_HAND("16","人工触发");
	public String key;
	public String value;

	private TriggerTypeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public static TriggerTypeEnum get(String key) {
		TriggerTypeEnum[] values = TriggerTypeEnum.values();
		for (TriggerTypeEnum object : values) {
			if (object.key == key) {
				return object;
			}
		}
		return null;
	}

}
