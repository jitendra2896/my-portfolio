package com.jitendra.myportfolio.service;

import com.jitendra.myportfolio.model.ContactMe;
import com.jitendra.myportfolio.repository.ContactMeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactMeService {

    @Autowired
    private ContactMeRepository contactMeRepository;

    public boolean saveContactMe(ContactMe contactMe) {
        contactMeRepository.save(contactMe);
        return true;
    }
}
