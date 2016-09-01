package org.iwinter.framework.type;

import java.io.Serializable;
import java.lang.reflect.Field;

public class ObjFieldId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object obj;
	private Field field;
	private String id;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
