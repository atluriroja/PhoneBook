package com.phonebook.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phonebook.domain.ContactDetails;

@Repository
public interface ContactDetailsJPARepository extends JpaRepository<ContactDetails, Long>{

}


