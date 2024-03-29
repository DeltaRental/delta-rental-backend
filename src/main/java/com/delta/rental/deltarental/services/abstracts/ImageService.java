package com.delta.rental.deltarental.services.abstracts;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String uploadImage(MultipartFile file,String plate) throws IOException;

    byte[] downloadImage(String fileName);
}
