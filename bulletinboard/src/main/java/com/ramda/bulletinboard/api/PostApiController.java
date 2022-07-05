package com.ramda.bulletinboard.api;

import com.ramda.bulletinboard.dto.PostDTO;
import com.ramda.bulletinboard.entity.PostEntity;
import com.ramda.bulletinboard.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PostApiController {

    @Autowired
    private PostService postService;

    //get
    @GetMapping("/api/post")
    public Iterable<PostEntity> index() {
        return postService.index();
    }

    @GetMapping("/api/post/{id}")
    public PostEntity show(@PathVariable Long id) {
        return postService.show(id);
    }

    //post
    @PostMapping("/api/post")
    public ResponseEntity create(@RequestBody PostDTO dto) {
        PostEntity created = postService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //patch
    @PatchMapping("/api/post/{id}")
    public ResponseEntity<PostEntity> update(@PathVariable Long id, @RequestBody PostDTO dto) {

        PostEntity updated = postService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //delete
    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<PostEntity> delete(@PathVariable Long id) {
       PostEntity deleted = postService.delete(id);
       return (deleted != null) ?
               ResponseEntity.status(HttpStatus.OK).body(deleted) :
               ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
