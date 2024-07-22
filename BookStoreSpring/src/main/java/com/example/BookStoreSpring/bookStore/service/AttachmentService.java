package com.example.BookStoreSpring.bookStore.service;

import com.example.BookStoreSpring.bookStore.dto.ApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface AttachmentService {

    ApiResponse upload(MultipartFile multipartFile);
    HttpEntity<?> download(Long attachmentId);
}
