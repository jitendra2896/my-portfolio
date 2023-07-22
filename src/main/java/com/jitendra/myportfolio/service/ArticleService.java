package com.jitendra.myportfolio.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.jitendra.myportfolio.dto.ArticleListDTO;
import com.jitendra.myportfolio.exception.ResourceNotFoundException;
import com.jitendra.myportfolio.imagekit.ImageHandler;
import com.jitendra.myportfolio.model.Article;
import com.jitendra.myportfolio.model.ContentType;
import com.jitendra.myportfolio.repository.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.imagekit.sdk.models.results.Result;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public void saveArticle(Article article) {
        String mainImageBase64 = article.getMainImageUrl();
        ImageHandler.initializeImageKit();
        try {
            Result result = ImageHandler.uploadBase64Image(mainImageBase64, article.getHeading() + ".png");
            article.setMainImageUrl(result.getUrl());
            AtomicInteger contentCounter = new AtomicInteger(1);
            article.getContent()
                   .stream()
                   .filter(articleContent -> articleContent.getKey().equals(ContentType.IMAGE))
                   .forEach(articleContent -> {
                       try {
                           Result resultContent = ImageHandler.uploadBase64Image(articleContent.getValue(),
                                                                           article.getHeading() + (contentCounter.getAndIncrement()) + ".png");
                           articleContent.setValue(resultContent.getUrl());
                       } catch (Exception e) {
                           throw new RuntimeException(e);
                       }
                   });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        articleRepository.save(article);
    }

    public Article getArticleByTitle(String title) {
        return articleRepository.findArticleByTitle(title);
    }

    public List<ArticleListDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(article -> {
            ArticleListDTO articleListDTO = new ArticleListDTO();
            articleListDTO.setId(article.getId());
            articleListDTO.setHeading(article.getHeading());
            articleListDTO.setMainImageUrl(article.getMainImageUrl());
            articleListDTO.setMainImageAltText(article.getMainImageAltText());
            articleListDTO.setShortDesc(article.getShortDesc());
            articleListDTO.setSubtitle(article.getSubtitle());
            return articleListDTO;
        }).collect(Collectors.toList());
    }

    public Article getArticleById(String id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if (articleOptional.isEmpty()) {
            throw new ResourceNotFoundException("No article present with id: " + id);
        }
        return articleOptional.get();
    }
}
