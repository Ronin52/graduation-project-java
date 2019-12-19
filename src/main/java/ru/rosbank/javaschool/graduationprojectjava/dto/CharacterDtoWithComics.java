package ru.rosbank.javaschool.graduationprojectjava.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.graduationprojectjava.constants.Errors;
import ru.rosbank.javaschool.graduationprojectjava.constants.Extensions;
import ru.rosbank.javaschool.graduationprojectjava.constrain.FileType;
import ru.rosbank.javaschool.graduationprojectjava.entity.CharacterEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@ApiModel(value = "Character DTO with comics", description = "Детальное представление персонажа, с комиксами.")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDtoWithComics {
    @ApiModelProperty(position = 1)
    private UUID id;

    @ApiModelProperty(position = 2)
    @NotNull(message = Errors.VALIDATION_IS_NULL)
    @Size(min = 2, message = Errors.VALIDATION_MIN_SIZE)
    @Size(max = 20, message = Errors.VALIDATION_MAX_SIZE)
    private String name;

    @ApiModelProperty(position = 3)
    @Size(min = 10, message = Errors.VALIDATION_MIN_SIZE)
    @Size(max = 1000, message = Errors.VALIDATION_MAX_SIZE)
    private String description;

    @ApiModelProperty(position = 4, notes = "id + расширение изобржения")
    @FileType(value = {Extensions.JPEG, Extensions.PNG}, message = Errors.FILE_BAD_TYPE)
    private String image;

    @ApiModelProperty(position = 5, notes = "id + расширение звука")
    @FileType(value = {Extensions.MPEG}, message = Errors.FILE_BAD_TYPE)
    private String sound;

    @ApiModelProperty(position = 6, notes = "id + расширение видео")
    @FileType(value = {Extensions.WEBM}, message = Errors.FILE_BAD_TYPE)
    private String video;

    @ApiModelProperty(position = 7)
    private Collection<ComicsDto> comics;

    public static CharacterDtoWithComics from(CharacterEntity entity) {
        return new CharacterDtoWithComics(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImage(),
                entity.getSound(),
                entity.getVideo(),
                entity.getComics().stream()
                        .map(ComicsDto::from)
                        .collect(Collectors.toList())
        );
    }
}
