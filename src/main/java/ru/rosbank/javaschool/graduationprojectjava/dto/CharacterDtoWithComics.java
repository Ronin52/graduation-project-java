package ru.rosbank.javaschool.graduationprojectjava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.graduationprojectjava.constants.Errors;
import ru.rosbank.javaschool.graduationprojectjava.constrain.FileType;
import ru.rosbank.javaschool.graduationprojectjava.entity.CharacterEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDtoWithComics {
    private UUID id;
    @NotNull(message = Errors.VALIDATION_IS_NULL)
    @Size(min = 2, message = Errors.VALIDATION_MIN_SIZE)
    @Size(max = 20, message = Errors.VALIDATION_MAX_SIZE)
    private String name;
    @Size(min = 10, message = Errors.VALIDATION_MIN_SIZE)
    @Size(max = 1000, message = Errors.VALIDATION_MAX_SIZE)
    private String description;
    @FileType(value = {".jpg", ".png"}, message = Errors.FILE_BAD_TYPE)
    private String image;
    @FileType(value = {".mp3"}, message = Errors.FILE_BAD_TYPE)
    private String sound;
    @FileType(value = {".webm"}, message = Errors.FILE_BAD_TYPE)
    private String video;
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
