package com.phonebook.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonebook.domain.Contact;
import com.phonebook.model.PhoneBookResponse;
import com.phonebook.service.UploadPhoneBookService;

@RestController
public class UploadPhoneBookController {
	
	@Autowired
	private UploadPhoneBookService uploadPhoneBookService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadPhoneBookController.class);
	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/api/phonebook/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		LOGGER.info("UploadPhoneBookController >> handleFileUpload: Process initiated");	
		String message = "";
		
		try {
			PhoneBookResponse responseEntity = uploadPhoneBookService.uploadXMLFile(file);
			return ResponseEntity.ok(new ObjectMapper().writeValueAsString(responseEntity));
			
		} catch (Exception e) {
			message = "Fail to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/api/phonebook/getallcontacts")
	public ResponseEntity<String> retrieveContacts(Long id) throws IOException {
		LOGGER.info("UploadPhoneBookController >> retrieveContacts: Process initiated");
		
		PhoneBookResponse responseEntity = uploadPhoneBookService.getAllContacts();	
		
		if(responseEntity.getStatus().equals("Success")) {
			LOGGER.info("UploadPhoneBookController >> retrieveContacts: Response from DB: {}", responseEntity.getEntity());
			List<Contact> contacts = (List<Contact>) responseEntity.getEntity();	
			return ResponseEntity.ok(new ObjectMapper().writeValueAsString(contacts));
		}
		
		return ResponseEntity.ok(new ObjectMapper().writeValueAsString(responseEntity));
		
	}
}
