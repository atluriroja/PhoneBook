package com.phonebook.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="ContactType")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContactTypeModel implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5070976824127867243L;
	
	@XmlElement(name = "Type")
	private String type;
	
	@XmlElement(name = "Value")
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ContactTypeModel [type=" + type + ", value=" + value + "]";
	}

}
