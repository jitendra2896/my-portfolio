package com.jitendra.myportfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListDTO {
    private String id;
    private String heading;
    private String mainImageUrl;
    private String mainImageAltText;
    private String shortDesc;
    private String subtitle;
}
