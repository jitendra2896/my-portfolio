package com.jitendra.myportfolio.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document("article")
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    private String id;

    private String heading;

    private String mainImageUrl;

    private String mainImageAltText;

    private String shortDesc;

    private String subtitle;

    private List<String> labels;

    private List<ArticleContent> content;

    private Date date;
}
