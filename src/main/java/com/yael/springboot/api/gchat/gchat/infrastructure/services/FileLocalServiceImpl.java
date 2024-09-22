package com.yael.springboot.api.gchat.gchat.infrastructure.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IFilesService;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;




@Service
public class FileLocalServiceImpl implements IFilesService {

    private final String STATIC_FILES = "src/main/resources/static/";


    @Override
    public String saveImage(MultipartFile image, String path) {
        String originalFilename = image.getOriginalFilename();
        if( originalFilename == null ) throw CustomException.badRequestException("File required extension valid.");

        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + extension;

        Path pathFile = Paths.get(STATIC_FILES + path + fileName);

        try {
            Files.write(pathFile, image.getBytes());
        } catch (IOException e) {
            throw CustomException.internalServerException("No se logro guardar la imagen");
        }

        String fileUrl = "/static" + path + fileName;
        return fileUrl;
    }

    @Override
    public Boolean deleteImage(String url, String path) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteImage'");
    }

}
