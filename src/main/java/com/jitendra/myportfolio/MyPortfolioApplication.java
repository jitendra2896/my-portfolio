package com.jitendra.myportfolio;

import com.jitendra.myportfolio.model.Article;
import com.jitendra.myportfolio.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import static java.util.UUID.randomUUID;

@SpringBootApplication
public class MyPortfolioApplication{

	@Autowired
	ArticleService articleService;

	public static void main(String[] args) {
		SpringApplication.run(MyPortfolioApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Article article = new Article(randomUUID(), "test-title");
//		articleService.saveArticle(article);
//	}
}
