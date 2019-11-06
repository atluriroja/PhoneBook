package com.phonebook.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Contact")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContactModel implements Serializable  {
  
     /**
	 * 
	 */
	private static final long serialVersionUID = -4036210623387434374L;
	
	@XmlElement(name = "Name")
	private String name;
	
	@XmlElement(name = "ContactType")
	private List<ContactTypeModel> contactType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ContactTypeModel> getContactType() {
		return contactType;
	}

	public void setContactType(List<ContactTypeModel> contactType) {
		this.contactType = contactType;
	}

	@Override
	public String toString() {
		return "ContactModel [name=" + name + ", contactType=" + contactType + "]";
	}
	
}
