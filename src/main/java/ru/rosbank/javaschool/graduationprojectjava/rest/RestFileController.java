package ru.rosbank.javaschool.graduationprojectjava.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.rosbank.javaschool.graduationprojectjava.domain.UploadInfo;
import ru.rosbank.javaschool.graduationprojectjava.dto.UploadResponseDto;
import ru.rosbank.javaschool.graduationprojectjava.service.FileService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class RestFileController {
    private final FileService service;

    @PostMapping("/upload")
    public UploadResponseDto upload(@RequestParam MultipartFile file) {
        return service.save(file);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> get(@PathVariable String id) {
        UploadInfo uploadInfo = service.get(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(uploadInfo.getMimeType()))
                .body(uploadInfo.getResource());
    }
}
