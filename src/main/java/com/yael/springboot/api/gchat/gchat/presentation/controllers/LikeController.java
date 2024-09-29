package com.yael.springboot.api.gchat.gchat.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yael.springboot.api.gchat.gchat.application.dtos.PaginationDto;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.ILikeProjection;
import com.yael.springboot.api.gchat.gchat.application.services.LikeService;
import com.yael.springboot.api.gchat.gchat.application.services.ResponseService;
import com.yael.springboot.api.gchat.gchat.application.services.ResponseServicePagination;



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

    @GetMapping("/get/{imageId}")
    public ResponseEntity<ResponseServicePagination<List<ILikeProjection>>> getLikesbyImage( @PathVariable Long imageId, @ModelAttribute PaginationDto paginationDto ){
        var res = likeService.getLikesByImageId(imageId, paginationDto);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

}
