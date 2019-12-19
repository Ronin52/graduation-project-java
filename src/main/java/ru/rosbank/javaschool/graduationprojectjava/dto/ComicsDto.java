package ru.rosbank.javaschool.graduationprojectjava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.graduationprojectjava.entity.ComicsEntity;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComicsDto {
    private UUID id;
    private String title;
    private String description;
    private String image;
    private String sound;
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


