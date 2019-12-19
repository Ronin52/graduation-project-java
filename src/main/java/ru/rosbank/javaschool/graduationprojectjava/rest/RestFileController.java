package ru.rosbank.javaschool.graduationprojectjava.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.rosbank.javaschool.graduationprojectjava.domain.UploadInfo;
import ru.rosbank.javaschool.graduationprojectjava.dto.UploadFileDto;
import ru.rosbank.javaschool.graduationprojectjava.service.FileService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class RestFileController {
    private final FileService service;

    @PostMapping("/upload")
    public UploadFileDto upload(@RequestParam MultipartFile file) {
        return service.save(file);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Resource> get(@PathVariable String name) {
        UploadInfo uploadInfo = service.get(name);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(uploadInfo.getMimeType()))
                .body(uploadInfo.getResource());
    }
}
