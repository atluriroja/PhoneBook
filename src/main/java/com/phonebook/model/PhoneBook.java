package com.phonebook.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

	@XmlRootElement(name="PhoneBook")
	@XmlAccessorType (XmlAccessType.FIELD)
	public class PhoneBook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -959867024115707236L;
	
	@XmlElement(name = "Contact")
	private List<ContactModel> contacts;

	public List<ContactModel> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactModel> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "PhoneBook [contacts=" + contacts + "]";
	}
	
}
