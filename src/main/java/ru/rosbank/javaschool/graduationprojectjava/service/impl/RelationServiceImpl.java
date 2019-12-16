package ru.rosbank.javaschool.graduationprojectjava.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rosbank.javaschool.graduationprojectjava.entity.CharacterEntity;
import ru.rosbank.javaschool.graduationprojectjava.entity.ComicsEntity;
import ru.rosbank.javaschool.graduationprojectjava.exception.CharacterNotFoundException;
import ru.rosbank.javaschool.graduationprojectjava.exception.ComicsNotFoundException;
import ru.rosbank.javaschool.graduationprojectjava.repository.CharacterRepository;
import ru.rosbank.javaschool.graduationprojectjava.repository.ComicsRepository;
import ru.rosbank.javaschool.graduationprojectjava.service.RelationService;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RelationServiceImpl implements RelationService {
    private final CharacterRepository characterRepository;
    private final ComicsRepository comicsRepository;

    @Override
    public boolean bindCharacterAndComicsById(UUID characterId, UUID comicsId) {
        CharacterEntity characterEntity = characterRepository.findById(characterId).orElseThrow(CharacterNotFoundException::new);
        ComicsEntity comicsEntity = comicsRepository.findById(comicsId).orElseThrow(ComicsNotFoundException::new);

        Collection<ComicsEntity> comics = characterEntity.getComics();
        if (comics.contains(comicsEntity)) {
            return false;
        }
        comics.add(comicsEntity);
        characterEntity.setComics(comics);

        Collection<CharacterEntity> characters = comicsEntity.getCharacters();
        if (characters.contains(characterEntity)) {
            return false;
        }
        characters.add(characterEntity);
        comicsEntity.setCharacters(characters);

        characterRepository.save(characterEntity);
        comicsRepository.save(comicsEntity);
        return true;
    }
}
