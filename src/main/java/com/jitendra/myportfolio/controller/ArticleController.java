package com.jitendra.myportfolio.controller;

import java.util.List;

import com.jitendra.myportfolio.dto.ArticleListDTO;
import com.jitendra.myportfolio.model.Article;
import com.jitendra.myportfolio.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        articleService.saveArticle(article);
        return ResponseEntity.ok(article);
    }

    @GetMapping
    public ResponseEntity<List<ArticleListDTO>> getArticles() {
        List<ArticleListDTO> article = articleService.getAllArticles();
        return ResponseEntity.ok(article);
    }

    @GetMapping("{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable String id) {
        Article article = articleService.getArticleById(id);
        return ResponseEntity.ok(article);
    }
}
