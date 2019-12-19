package ru.rosbank.javaschool.graduationprojectjava.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Upload response DTO", description = "Возвращается при сохранении файла")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileDto {
    private String name;
}
