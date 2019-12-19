package ru.rosbank.javaschool.graduationprojectjava.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.graduationprojectjava.entity.ComicsEntity;

import java.util.UUID;

@ApiModel(value = "Comics DTO", description = "Сокращенное представление комикса, без персонажей.")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComicsDto {
    @ApiModelProperty(position = 1)
    private UUID id;

    @ApiModelProperty(position = 2)
    private String title;

    @ApiModelProperty(position = 3)
    private String description;

    @ApiModelProperty(position = 4, notes = "id + расширение изобржения")
    private String image;

    @ApiModelProperty(position = 5, notes = "id + расширение звука")
    private String sound;

    @ApiModelProperty(position = 6, notes = "id + расширение видео")
    private String video;

    public static ComicsDto from(ComicsEntity entity) {
        return new ComicsDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getImage(),
                entity.getSound(),
                entity.getVideo()
        );
    }

    public static ComicsDto from(ComicsDtoWithCharacters dto) {
        return new ComicsDto(
                dto.getId(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getImage(),
                dto.getSound(),
                dto.getVideo()
        );
    }
}


