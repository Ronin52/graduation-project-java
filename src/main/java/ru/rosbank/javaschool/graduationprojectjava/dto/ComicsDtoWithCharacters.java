package ru.rosbank.javaschool.graduationprojectjava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.graduationprojectjava.constants.Errors;
import ru.rosbank.javaschool.graduationprojectjava.entity.ComicsEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComicsDtoWithCharacters {
    private UUID id;
    @NotNull(message = Errors.VALIDATION_IS_NULL)
    @Size(min = 2, message = Errors.VALIDATION_MIN_SIZE)
    @Size(max = 40, message = Errors.VALIDATION_MAX_SIZE)
    private String title;
    @Size(min = 10, message = Errors.VALIDATION_MIN_SIZE)
    @Size(max = 2000, message = Errors.VALIDATION_MAX_SIZE)
    private String description;
    private String image;
    private Collection<CharacterDto> characters;

    public static ComicsDtoWithCharacters from(ComicsEntity entity) {
        return new ComicsDtoWithCharacters(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getImage(),
                entity.getCharacters().stream()
                        .map(CharacterDto::from)
                        .collect(Collectors.toList())
        );
    }
}
