package com.yael.springboot.api.gchat.gchat.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yael.springboot.api.gchat.gchat.application.services.LikeService;
import com.yael.springboot.api.gchat.gchat.application.services.ResponseService;


@RestController
@RequestMapping("/api/like")
public class LikeController {

    @Autowired
    LikeService likeService;


    @PostMapping("/image/{id}")
    public ResponseEntity<ResponseService<Boolean>> addLikeImage(@PathVariable Long id) {
        var res = likeService.likeImage(id);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

}
