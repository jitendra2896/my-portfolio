package com.jitendra.myportfolio.repository;

import com.jitendra.myportfolio.model.ContactMe;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMeRepository extends MongoRepository<ContactMe, String> {

}
