package ru.rosbank.javaschool.graduationprojectjava.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import ru.rosbank.javaschool.graduationprojectjava.dto.UploadFileDto;
import ru.rosbank.javaschool.graduationprojectjava.exception.ContentTypeIsNullException;
import ru.rosbank.javaschool.graduationprojectjava.exception.FileStorageException;
import ru.rosbank.javaschool.graduationprojectjava.exception.UnsupportedFileTypeException;
import ru.rosbank.javaschool.graduationprojectjava.service.FileService;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileServiceImplTest {
    private FileService service;
    private MultipartFile multipartFile;

    @BeforeEach
    void init() {
        service = new FileServiceImpl("");
        multipartFile = mock(MultipartFile.class);
    }

    @Test
    void saveMultipartJpeg() {
        when(multipartFile.getContentType()).thenReturn("image/jpeg");

        assertTrue(service.save(multipartFile).getName().endsWith(".jpg"));
    }

    @Test
    void saveMultipartPng() {
        when(multipartFile.getContentType()).thenReturn("image/png");
        UploadFileDto dto = service.save(multipartFile);

        assertTrue(dto.getName().endsWith(".png"));
    }

    @Test
    void saveMultipartWebm() {
        when(multipartFile.getContentType()).thenReturn("video/webm");
        UploadFileDto dto = service.save(multipartFile);

        assertTrue(dto.getName().endsWith(".webm"));
    }

    @Test
    void saveMultipartMpeg() {
        when(multipartFile.getContentType()).thenReturn("audio/mpeg");
        UploadFileDto dto = service.save(multipartFile);

        assertTrue(dto.getName().endsWith(".mp3"));
    }

    @Test
    void saveMultipartThrowUnsupportedFileTypeException() {
        when(multipartFile.getContentType()).thenReturn("");

        assertThrows(UnsupportedFileTypeException.class, () -> service.save(multipartFile));
    }

    @Test
    void saveMultipartThrowContentTypeIsNullException() {
        when(multipartFile.getContentType()).thenReturn(null);

        assertThrows(ContentTypeIsNullException.class, () -> service.save(multipartFile));
    }

    @Test
    void saveMultipartThrowFileStorageException() throws IOException {
        when(multipartFile.getContentType()).thenReturn("image/jpeg");
        doThrow(IOException.class).when(multipartFile).transferTo((Path) any());

        assertThrows(FileStorageException.class, () -> service.save(multipartFile));
    }
}