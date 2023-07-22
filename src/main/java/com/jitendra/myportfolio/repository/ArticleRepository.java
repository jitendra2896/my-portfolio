package com.jitendra.myportfolio.repository;

import java.util.List;
import java.util.UUID;

import com.jitendra.myportfolio.model.Article;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {

    @Query("{heading: '?0'}")
    Article findArticleByTitle(String title);
}
