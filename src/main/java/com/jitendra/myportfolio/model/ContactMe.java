package com.jitendra.myportfolio.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class ContactMe {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String message;

    private Date date = new Date();
}
