package ru.rosbank.javaschool.graduationprojectjava.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.graduationprojectjava.dto.CharacterDto;
import ru.rosbank.javaschool.graduationprojectjava.dto.CharacterDtoWithComics;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(exclude = "comics")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "characters")
public class CharacterEntity {
    @Id
    private UUID id;

    private String name;
    private String description;
    private String image;
    private String sound;
    private String video;
    @ManyToMany()
    @JoinTable(name = "character_comics",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "comics_id"))
    private Collection<ComicsEntity> comics;

    public CharacterEntity(UUID id, String name, String description, String image, String sound, String video) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.sound = sound;
        this.video = video;
    }

    public static CharacterEntity from(CharacterDto dto) {
        return new CharacterEntity(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getImage(),
                dto.getSound(),
                dto.getVideo()
        );
    }

    public static CharacterEntity from(CharacterDtoWithComics dto) {
        return new CharacterEntity(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getImage(),
                dto.getSound(),
                dto.getVideo(),
                dto.getComics().stream()
                        .map(ComicsEntity::from)
                        .collect(Collectors.toList())
        );
    }
}
