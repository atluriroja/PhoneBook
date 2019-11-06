package com.phonebook.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "tbl_contact")
public class Contact implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1871745067258223568L;
	
	private Long id;
	private String name;
	private List<ContactDetails> details;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Column(name = "contact_id")
 	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "contact_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY, mappedBy = "contact")
	@JsonManagedReference(value="contact_details")
	public List<ContactDetails> getDetails() {
		return details;
	}
	
	public void setDetails(List<ContactDetails> details) {
		this.details = details;
	}

}
