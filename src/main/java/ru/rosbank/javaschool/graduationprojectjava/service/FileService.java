package ru.rosbank.javaschool.graduationprojectjava.service;

import org.springframework.web.multipart.MultipartFile;
import ru.rosbank.javaschool.graduationprojectjava.domain.UploadInfo;
import ru.rosbank.javaschool.graduationprojectjava.dto.UploadFileDto;

public interface FileService {
    UploadFileDto save(MultipartFile multipartFile);
    UploadInfo get(String id);
}
