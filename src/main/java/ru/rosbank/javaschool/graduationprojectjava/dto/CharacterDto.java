package ru.rosbank.javaschool.graduationprojectjava.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.graduationprojectjava.entity.CharacterEntity;

import java.util.UUID;

@ApiModel(value = "Character DTO", description = "Сокращенное представление персонажа, без вывода комиксов.")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDto {
    @ApiModelProperty(position = 1)
    private UUID id;

    @ApiModelProperty(position = 2)
    private String name;

    @ApiModelProperty(position = 3)
    private String description;

    @ApiModelProperty(position = 4, notes = "id + расширение изобржения")
    private String image;

    @ApiModelProperty(position = 5, notes = "id + расширение звука")
    private String sound;

    @ApiModelProperty(position = 6, notes = "id + расширение видео")
    private String video;

    public static CharacterDto from(CharacterEntity entity) {
        return new CharacterDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImage(),
                entity.getSound(),
                entity.getVideo()
        );
    }

    public static CharacterDto from(CharacterDtoWithComics dto) {
        return new CharacterDto(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getImage(),
                dto.getSound(),
                dto.getVideo()
        );
    }
}
