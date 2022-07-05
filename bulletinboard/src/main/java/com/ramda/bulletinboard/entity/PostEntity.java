package com.ramda.bulletinboard.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String title;


    @Column
    private String content;

    public void patch(PostEntity post) {
        if (post.title != null)
            this.title = post.title;
        if (post.content != null)
            this.content = post.content;
    }
}
