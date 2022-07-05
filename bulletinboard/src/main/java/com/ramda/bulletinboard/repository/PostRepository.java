package com.ramda.bulletinboard.repository;

import com.ramda.bulletinboard.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {

}
