package ru.rosbank.javaschool.graduationprojectjava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.graduationprojectjava.entity.CharacterEntity;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDto {
    private UUID id;
    private String name;
    private String description;
    private String image;

    public static CharacterDto from(CharacterEntity entity) {
        return new CharacterDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImage()
        );
    }
}
