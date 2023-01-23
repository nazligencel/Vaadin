package com.uniyaz.application.data.repository;

import com.uniyaz.application.data.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
