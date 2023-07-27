package com.jitendra.myportfolio.controller;

import com.jitendra.myportfolio.exception.ErrorSavingEntityException;
import com.jitendra.myportfolio.model.ContactMe;
import com.jitendra.myportfolio.service.ContactMeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact-me")
public class ContactMeController {

    @Autowired
    ContactMeService contactMeService;

    @PostMapping
    public ResponseEntity saveContactMeInfo(@RequestBody ContactMe contactMe) {
        if(contactMeService.saveContactMe(contactMe)) {
            return ResponseEntity.noContent().build();
        }
        throw new ErrorSavingEntityException("Couldn't send the message");
    }
}
