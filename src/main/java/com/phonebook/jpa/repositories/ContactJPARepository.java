package com.phonebook.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phonebook.domain.Contact;

@Repository
public interface ContactJPARepository extends JpaRepository<Contact, Long>{

}





