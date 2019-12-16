package ru.rosbank.javaschool.graduationprojectjava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.graduationprojectjava.entity.ComicsEntity;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComicsDtoWithCharacters {
    private UUID id;
    private String title;
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
