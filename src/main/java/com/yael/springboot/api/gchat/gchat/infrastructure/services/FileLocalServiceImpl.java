package com.yael.springboot.api.gchat.gchat.infrastructure.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IFilesService;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;

import jakarta.servlet.http.HttpServletRequest;




@Service
public class FileLocalServiceImpl implements IFilesService {

    private final String STATIC_FILES = "src/main/resources/static/";

    @Autowired
    HttpServletRequest request;

    @Override
    public String saveImage(MultipartFile image, String path) {
        try {
            String originalFilename = image.getOriginalFilename();
            if( originalFilename == null ) throw CustomException.badRequestException("File required extension valid.");

            Path directory = Paths.get(STATIC_FILES + path);

            if( Files.notExists(directory) ) Files.createDirectories(directory);

            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + extension;

            Path pathFile = Paths.get(STATIC_FILES + path + fileName);

            Files.write(pathFile, image.getBytes());

            String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
            String fileUrl = pathFile.toString().substring( (pathFile.toString().lastIndexOf('\\') + 1) );

            var url = baseUrl + "/" + fileUrl;
            return url;
        } catch (IOException e) {
            throw CustomException.internalServerException("No se logro guardar la imagen");
        }
    }

    @Override
    public Boolean deleteImage(String url, String path) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteImage'");
    }

}
