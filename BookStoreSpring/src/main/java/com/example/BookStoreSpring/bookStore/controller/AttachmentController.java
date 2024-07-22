package com.example.BookStoreSpring.bookStore.controller;

import com.example.BookStoreSpring.bookStore.dto.ApiResponse;
import com.example.BookStoreSpring.bookStore.service.impl.AttachmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentServiceImpl attachmentService;

    @PostMapping("/upload")
    public HttpEntity<?> upload(@RequestParam("file") MultipartFile multipartFile){
        ApiResponse apiResponse = attachmentService.upload(multipartFile);
        System.out.println(apiResponse);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/download/{id}")
    public HttpEntity<?> download(@PathVariable Long id){
        return ResponseEntity.ok(attachmentService.download(id));
    }
}
