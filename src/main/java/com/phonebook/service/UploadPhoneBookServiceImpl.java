package com.phonebook.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.phonebook.domain.Contact;
import com.phonebook.domain.ContactDetails;
import com.phonebook.jpa.repositories.ContactJPARepository;
import com.phonebook.model.ContactModel;
import com.phonebook.model.ContactTypeModel;
import com.phonebook.model.PhoneBook;
import com.phonebook.model.PhoneBookResponse;

@Service
public class UploadPhoneBookServiceImpl implements UploadPhoneBookService {
	
private static final Logger LOGGER = LoggerFactory.getLogger(UploadPhoneBookServiceImpl.class);

	public UploadPhoneBookServiceImpl() {
		super();
	}
	
	@Autowired
	private ContactJPARepository repository;
	
	@Override
	public PhoneBookResponse uploadXMLFile(MultipartFile file) {
		LOGGER.info("UploadPhoneBookServiceImpl >> uploadXMLFile: Process initiated");
		PhoneBookResponse entity = new PhoneBookResponse();
		String fileName = file.getOriginalFilename();
		int lastIndex = fileName.lastIndexOf('.');
		String fileExt = fileName.substring(lastIndex, fileName.length());
		
		try (InputStream inputStream = file.getInputStream()) {	
			if(file.getContentType().equals("text/xml") && fileExt.equalsIgnoreCase(fileExt)){
				JAXBContext jaxbContext   = JAXBContext.newInstance(PhoneBook.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();				
				PhoneBook phoneBook = (PhoneBook) jaxbUnmarshaller.unmarshal(inputStream);
				Contact contact = null;
			
				if(phoneBook != null) {
					for(ContactModel contactModel:phoneBook.getContacts()) {
			
						contact = new Contact();
						contact.setName(contactModel.getName());
						List<ContactDetails> contactDetails = new ArrayList<>();
					
						if (contactModel.getContactType() != null) {
							for(ContactTypeModel contactType:contactModel.getContactType()) {
								ContactDetails detail = new ContactDetails();
								detail.setContact(contact);
								detail.setType(contactType.getType());
								detail.setValue(contactType.getValue());
								contactDetails.add(detail);
							}
						}
					
						contact.setDetails(contactDetails);
						saveContact(contact);								
					}
				
					entity.setEntity(phoneBook);
					LOGGER.info("UploadPhoneBookServiceImpl >> uploadXMLFile: Successfully uploaded");
				
				}
				entity.setStatus("Success");
				entity.setMessage("Successfully uploaded the contacts");
				
			}else {
				entity.setStatus("Failure");
				entity.setMessage("Failed to upload the file: Unsupported FileType/ContentType ");}
			
	         
		}catch (UnmarshalException e) {
			LOGGER.error("Exception in UploadPhoneBookService uploadXMLFile method", e);
			e.printStackTrace();
			entity.setStatus("Failure");
			entity.setMessage("Failed to upload the file: Unsupported format");
			
			
		}
		catch (Exception e) {
			LOGGER.error("Exception in UploadPhoneBookService uploadXMLFile method", e);
			e.printStackTrace();
			entity.setStatus("Failure");
			entity.setMessage("Failed to upload the file");
			
			
		}
		return entity;
		
	}
	
	@Transactional
	@Override
	public Contact saveContact(Contact contact) {
		LOGGER.info("UploadPhoneBookServiceImpl >> saveContact: Process initiated");
		return repository.saveAndFlush(contact);
		
	}
	
	@Transactional
	@Override
	public PhoneBookResponse getAllContacts() {
		LOGGER.info("UploadPhoneBookServiceImpl >> getAllContacts: Process initiated");
		PhoneBookResponse entity = new PhoneBookResponse();
		
		try {
			entity.setEntity( repository.findAll());
			entity.setStatus("Success");
			entity.setMessage("Successfully fetched the contacts");
			LOGGER.info("UploadPhoneBookServiceImpl >> getAllContacts: Successfull");
		} catch (Exception e) {
			LOGGER.error("Exception in UploadPhoneBookService getAllContacts method", e);
			e.printStackTrace();
			entity.setEntity(null);
			entity.setStatus("Failure");
			entity.setMessage("Failed to get the contacts");
			
		}
		return entity;
		
	}
		
}
