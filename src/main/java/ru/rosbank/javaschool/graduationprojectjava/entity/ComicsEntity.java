package ru.rosbank.javaschool.graduationprojectjava.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.graduationprojectjava.dto.ComicsDto;
import ru.rosbank.javaschool.graduationprojectjava.dto.ComicsDtoWithCharacters;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(exclude = "characters")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comics")
public class ComicsEntity {
    @Id
    private UUID id;
    private String title;
    private String description;
    private String image;
    private String sound;
    private String video;
    @ManyToMany(mappedBy = "comics")
    private Collection<CharacterEntity> characters;

    public ComicsEntity(UUID id, String title, String description, String image, String sound, String video) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.sound = sound;
        this.video = video;
    }

    public static ComicsEntity from(ComicsDto dto) {
        return new ComicsEntity(
                dto.getId(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getImage(),
                dto.getSound(),
                dto.getVideo()
        );
    }

    public static ComicsEntity from(ComicsDtoWithCharacters dto) {
        return new ComicsEntity(
                dto.getId(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getImage(),
                dto.getSound(),
                dto.getVideo(),
                dto.getCharacters().stream()
                        .map(CharacterEntity::from)
                        .collect(Collectors.toList())
        );
    }
}
