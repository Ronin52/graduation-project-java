package ru.rosbank.javaschool.graduationprojectjava.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@ApiModel(value = "Error DTO", description = "Представление информации об ошибке")
@Data
@AllArgsConstructor
public class ErrorDto {
    @ApiModelProperty(position = 1)
    private final int status;

    @ApiModelProperty(position = 2, notes = "Код ошибки, согласно разработанной кодировке")
    private final String message;

    @ApiModelProperty(position = 3, notes = "Используется при валидации, чтобы указать множество ошибок при заполнении полей")
    private final Map<String, List<String>> errors;
}
