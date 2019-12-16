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

    public static ComicsDto from(ComicsEntity entity) {
        return new ComicsDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getImage()
        );
    }
}

