package ru.rosbank.javaschool.graduationprojectjava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.graduationprojectjava.entity.CharacterEntity;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDtoWithComics {
    private UUID id;
    private String name;
    private String description;
    private String image;
    private Collection<ComicsDto> comics;

    public static CharacterDtoWithComics from(CharacterEntity entity) {
        return new CharacterDtoWithComics(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImage(),
                entity.getComics().stream()
                        .map(ComicsDto::from)
                        .collect(Collectors.toList())
        );
    }
}
