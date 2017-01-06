package com.sojson.common.model;

/**
 * 共用枚举
 * @author hao.gao
 *
 */
public enum CommonEnum   {
 
	    Invalid(0,"无效"),
	    Valid(1,"有效");
	 
	    private int value;
	    
	    private String name;

	    private CommonEnum(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
	    
	    public int getValue() {
	        return value;
	    }

	    public void setValue(int value) {
	        this.value = value;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
}
