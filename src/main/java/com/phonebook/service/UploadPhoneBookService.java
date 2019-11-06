package com.phonebook.service;

import org.springframework.web.multipart.MultipartFile;

import com.phonebook.domain.Contact;
import com.phonebook.model.PhoneBookResponse;

public interface UploadPhoneBookService {

	public  PhoneBookResponse uploadXMLFile(MultipartFile file);

	public	Contact saveContact(Contact contact);
	
	public	PhoneBookResponse getAllContacts();

}
