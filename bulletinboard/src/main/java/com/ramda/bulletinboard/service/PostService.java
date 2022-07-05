package com.ramda.bulletinboard.service;

import com.ramda.bulletinboard.dto.PostDTO;
import com.ramda.bulletinboard.entity.PostEntity;
import com.ramda.bulletinboard.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.metadata.HsqlTableMetaDataProvider;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Iterable<PostEntity> index() {
        return postRepository.findAll();
    }

    public PostEntity show(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public PostEntity create(PostDTO dto) {
        PostEntity post = dto.toEntity();
        if (post.getId() != null) {
            return null;
        }
        return postRepository.save(post);
    }

    public PostEntity update(Long id, PostDTO dto) {
        PostEntity post = dto.toEntity();
        log.info(" id : {}, post : {}", id, post.toString());

        PostEntity target = postRepository.findById(id).orElse(null);

        if (target == null || id != post.getId()) {
            log.info("Error id : {}, post : {}", id, post.toString());
            return null;
        }

        target.patch(post);
        PostEntity updated = postRepository.save(target);
        return updated;
    }

    public PostEntity delete(Long id) {
        PostEntity target = postRepository.findById(id).orElse(null);

        if (target == null) {
            return null;
        }
        postRepository.delete(target);
        return target;
    }
}
