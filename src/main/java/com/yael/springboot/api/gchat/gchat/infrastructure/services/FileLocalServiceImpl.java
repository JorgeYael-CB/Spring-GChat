package com.yael.springboot.api.gchat.gchat.infrastructure.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
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
    @Autowired
    ResourceLoader resourceLoader;


    @Override
    public String saveImage(MultipartFile image, String path) {
        try {
            String originalFilename = image.getOriginalFilename();
            if( originalFilename == null ) throw CustomException.badRequestException("File required extension valid.");

            // Si no existe lo creamos
            this.getPath(path);

            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + extension;

            Path pathFile = Paths.get(STATIC_FILES + path + fileName);

            Files.write(pathFile, image.getBytes());

            String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
            String fileUrl = pathFile.toString().substring( (pathFile.toString().lastIndexOf('\\') + 1) );

            var url = baseUrl + "/" + path + fileUrl;
            return url;
        } catch (IOException e) {
            throw CustomException.internalServerException("No se logro guardar la imagen: " + e.getMessage());
        }
    }

    @Override
    public Boolean deleteImage(String url, String path) {
        try {
            Path directory = this.getPath(path);
            String fileUrl = url.replace('\\', '/').substring( url.lastIndexOf('/') );

            var resource = resourceLoader.getResource("classpath:static/" + path + fileUrl);

            if( resource.exists() && resource.getFile().isFile() ){
                Files.delete( Paths.get(directory + fileUrl) );
            }

            return true;
        } catch (Exception e) {
            throw CustomException.internalServerException("No se pudo eliminar la imagen: " + e.getMessage());
        }
    }


    private Path getPath( String path ) throws IOException{
        Path directory = Paths.get(STATIC_FILES + path);
        if( Files.notExists(directory) ) Files.createDirectories(directory);
        return directory;
    }

}
