package com.yael.springboot.api.gchat.gchat.application.interfaces.services;

import org.springframework.web.multipart.MultipartFile;

import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;



public interface IFilesService {

    public String saveImage( MultipartFile image, String path );
    public Boolean deleteImage( String url, String path );

    public default String update( MultipartFile newImage, String url, String path ){
        Boolean isDeleted = this.deleteImage(url, path);
        if( isDeleted ){
            return this.saveImage(newImage, path);
        }

        throw CustomException.internalServerException("No se logro guardar la imagen en el servicio");
    }

}
