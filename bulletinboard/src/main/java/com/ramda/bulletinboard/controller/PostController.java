package com.ramda.bulletinboard.controller;

import com.ramda.bulletinboard.dto.PostDTO;
import com.ramda.bulletinboard.entity.PostEntity;
import com.ramda.bulletinboard.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/post/newpost")
    public String newPostForm() {
        return "post/newpost";
    }

    @PostMapping("/post/create")
    public String createPost(PostDTO dto) {

        PostEntity postEntity = dto.toEntity();

        PostEntity save = postRepository.save(postEntity);

        return "redirect:/post/" + save.getId();
    }

    @GetMapping("/post/{id}")
    public String show(@PathVariable Long id, Model model) {

        PostEntity postEntity = postRepository.findById(id).orElse(null);

        model.addAttribute("post", postEntity);

        return "post/show";
    }

    @GetMapping("/post")
    public String index(Model model) {

        Iterable<PostEntity> postEntityList = postRepository.findAll();

        model.addAttribute("postList", postEntityList);

        return "post/index";
    }

    @GetMapping("/post/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {

        PostEntity postEntity = postRepository.findById(id).orElseThrow();

        model.addAttribute("post", postEntity);

        return "post/edit";
    }

    @PostMapping("/post/update")
    public String update(PostDTO dto) {

        PostEntity postEntity = dto.toEntity();
        PostEntity repository = postRepository.findById(postEntity.getId()).orElseThrow();
        if (repository != null) {
            postRepository.save(postEntity);
        }
        return "redirect:/post/" + postEntity.getId();
    }

    @GetMapping("/post/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {

        PostEntity postEntity = postRepository.findById(id).orElseThrow();

        if (postEntity != null) {
            postRepository.delete(postEntity);
            ra.addFlashAttribute("notice", "삭제가 완료되었습니다.");
        }
        return "redirect:/post";

    }
}
