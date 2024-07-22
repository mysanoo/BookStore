package com.example.BookStoreSpring.bookStore.service.impl;

import com.example.BookStoreSpring.bookStore.dto.BookDto;
import com.example.BookStoreSpring.bookStore.entity.Attachment;
import com.example.BookStoreSpring.bookStore.repository.AttachmentRepository;
import com.example.BookStoreSpring.bookStore.repository.BookRepository;
import com.example.BookStoreSpring.bookStore.service.AttachmentService;
import com.example.BookStoreSpring.bookStore.utils.AppConstants;
import com.example.BookStoreSpring.bookStore.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;

    @Override
    public ApiResponse upload(MultipartFile multipartFile) {
        assert multipartFile != null;
        try{
            Attachment attachment = Attachment.builder()
                    .contentType(multipartFile.getContentType())
                    .size(multipartFile.getSize())
                    .name(multipartFile.getOriginalFilename())
                    .build();

            attachmentRepository.saveAndFlush(attachment);

//            String path = new File(".").getCanonicalPath() + "/books/" + attachment.getId() + "_" +multipartFile.getOriginalFilename();
            String path = "C:\\Users\\User\\Desktop\\G19\\PracticeNapa\\BookStore\\BookStoreFrontend\\book-store\\assert\\" + multipartFile.getOriginalFilename();
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(multipartFile.getBytes());
            fileOutputStream.close();

            attachment.setPath(path);
            attachmentRepository.save(attachment);
            return ApiResponse.builder()
                    .data(attachment)
                    .success(true)
                    .message("successfully")
                    .build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ApiResponse.builder().success(false).build();
    }

    @Override
    public HttpEntity<?> download(Long attachmentId) {
        Attachment attachment = attachmentRepository.findById(attachmentId).orElseThrow(() -> new NullPointerException("Null pointer Exception!"));

        try {
            FileInputStream fio = new FileInputStream(attachment.getPath());
            byte[] bytes = FileCopyUtils.copyToByteArray(fio);
            fio.close();
            return ResponseEntity.ok().contentType(MediaType.valueOf(attachment.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment, filename=\"" + attachment.getName() + "\"")
                    .body(bytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
