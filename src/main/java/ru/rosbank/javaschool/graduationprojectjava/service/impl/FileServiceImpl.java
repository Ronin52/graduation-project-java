package ru.rosbank.javaschool.graduationprojectjava.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.rosbank.javaschool.graduationprojectjava.constants.Extensions;
import ru.rosbank.javaschool.graduationprojectjava.domain.UploadInfo;
import ru.rosbank.javaschool.graduationprojectjava.dto.UploadFileDto;
import ru.rosbank.javaschool.graduationprojectjava.exception.ContentTypeIsNullException;
import ru.rosbank.javaschool.graduationprojectjava.exception.FileStorageException;
import ru.rosbank.javaschool.graduationprojectjava.exception.UnsupportedFileTypeException;
import ru.rosbank.javaschool.graduationprojectjava.service.FileService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Transactional
public class FileServiceImpl implements FileService {
    private final String uploadPath;

    private enum FileContentType {
        JPEG("image/jpeg"),
        PNG("image/png"),
        MPEG("audio/mpeg"),
        VWEBM("video/webm")
        ;

        private String name;

        FileContentType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static FileContentType getType(String typeName) {
            for (FileContentType value : FileContentType.values()) {
                if(value.getName().equals(typeName)){
                    return value;
                }
            }
            throw new UnsupportedFileTypeException(typeName);
        }
    }


    public FileServiceImpl(@Value("${app.upload-path}") String uploadPath) {
        this.uploadPath = uploadPath;
    }

    @PostConstruct
    public void init() throws IOException {
        Path path = Paths.get(uploadPath);
        if (Files.notExists(path)) {
            Files.createDirectories(path);
        }
    }

    public UploadFileDto save(MultipartFile multipartFile) {
        final String name;
        String contentType = multipartFile.getContentType();
        if (contentType == null) {
            throw new ContentTypeIsNullException();
        }
        switch (FileContentType.getType(contentType)) {
            case JPEG:
                name = UUID.randomUUID() + Extensions.JPEG;
                break;
            case PNG:
                name = UUID.randomUUID() + Extensions.PNG;
                break;
            case MPEG:
                name = UUID.randomUUID() + Extensions.MPEG;
                break;
            case VWEBM:
                name = UUID.randomUUID() + Extensions.WEBM;
                break;
            default:
                throw new UnsupportedFileTypeException(contentType);
        }
        try {
            multipartFile.transferTo(Paths.get(uploadPath).resolve(name));
            return new UploadFileDto(name);
        } catch (IOException e) {
            throw new FileStorageException(e);
        }
    }

    public UploadInfo get(String id) {
        try {
            return new UploadInfo(
                    new FileSystemResource(Paths.get(uploadPath).resolve(id)),
                    Files.probeContentType(Paths.get(uploadPath).resolve(id))
            );
        } catch (IOException e) {
            throw new FileStorageException(e);
        }
    }

}
