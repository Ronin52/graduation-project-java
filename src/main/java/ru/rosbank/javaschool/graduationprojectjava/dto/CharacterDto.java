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
    private String sound;
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
