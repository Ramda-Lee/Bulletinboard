package com.ramda.bulletinboard.dto;

import com.ramda.bulletinboard.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
public class PostDTO {

    private Long id;
    private String title;
    private String content;

    public PostEntity toEntity() {
        return new PostEntity(id, title, content);
    }
}
