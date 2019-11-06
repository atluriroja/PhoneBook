package com.phonebook.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tbl_contact_details")
public class ContactDetails implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -230585902894048089L;
	private Long id;
	private Contact contact;
	private String type;
	
	
	@Column(name = "contact_value")
	private String value;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "contact_details_id")
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(name = "contact_id")
	public Contact getContact() {
		return contact;
	}


	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@Column(name = "contact_type")
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
		return "ContactDetails [id=" + id + ", contact=" + contact + ", type=" + type + ", value=" + value + "]";
	}

	

}
