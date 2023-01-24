package com.uniyaz.application.data.service;


import com.uniyaz.application.data.entity.Company;
import com.uniyaz.application.data.entity.Contact;
import com.uniyaz.application.data.entity.Status;
import com.uniyaz.application.data.repository.CompanyRepository;
import com.uniyaz.application.data.repository.ContactRepository;
import com.uniyaz.application.data.repository.StatusRepository;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {
    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;

    public CrmService(ContactRepository contactRepository, CompanyRepository companyRepository, StatusRepository statusRepository) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
        this.statusRepository = statusRepository;
    }

    public List<Contact> findAllContact(String fiterText){

        if (fiterText == null) return contactRepository.findAll();
        else return contactRepository.search(fiterText);
    }

    public long countContact(){
        return  contactRepository.count();
    }

    public void deleteContact(Contact contact){
        contactRepository.delete(contact);
    }
    public void saveContact(Contact contact){
        if (contact==null) System.err.println("Contact is null");
        //return;
    }
    public List<Company> findAllCompany(){
        return  companyRepository.findAll();
    }
    public List<Status> findAllStatus(){
        return statusRepository.findAll();
    }
}
