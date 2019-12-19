package ru.rosbank.javaschool.graduationprojectjava.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.rosbank.javaschool.graduationprojectjava.entity.CharacterEntity;
import ru.rosbank.javaschool.graduationprojectjava.entity.ComicsEntity;
import ru.rosbank.javaschool.graduationprojectjava.exception.CharacterNotFoundException;
import ru.rosbank.javaschool.graduationprojectjava.exception.ComicsNotFoundException;
import ru.rosbank.javaschool.graduationprojectjava.repository.CharacterRepository;
import ru.rosbank.javaschool.graduationprojectjava.repository.ComicsRepository;
import ru.rosbank.javaschool.graduationprojectjava.service.RelationService;
import ru.rosbank.javaschool.graduationprojectjava.service.impl.RelationServiceImpl;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RelationServiceImplTest {
    private CharacterRepository characterRepository;
    private ComicsRepository comicsRepository;
    private RelationService service;
    private Collection characterComics;
    private CharacterEntity characterEntity;
    private Collection comicsCharacters;
    private ComicsEntity comicsEntity = mock(ComicsEntity.class);


    @BeforeEach
    void init() {
        characterRepository = mock(CharacterRepository.class);
        comicsRepository = mock(ComicsRepository.class);
        service = new RelationServiceImpl(characterRepository, comicsRepository);
        characterComics = mock(Collection.class);
        characterEntity = mock(CharacterEntity.class);
        comicsCharacters = mock(Collection.class);
    }

    @Test
    void whenBindThenSuccess() {
        when(characterRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(characterEntity));
        when(comicsRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(comicsEntity));
        when(characterEntity.getComics())
                .thenReturn(characterComics);
        when(characterComics.contains(comicsEntity))
                .thenReturn(false);
        when(comicsEntity.getCharacters())
                .thenReturn(comicsCharacters);
        when(comicsCharacters.contains(characterEntity))
                .thenReturn(false);

        assertTrue(service.bindCharacterAndComicsById(UUID.randomUUID(), UUID.randomUUID()));
    }

    @Test
    void whenBindThenThrowCharacterNotFoundException() {
        when(characterRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThrows(CharacterNotFoundException.class, () -> service.bindCharacterAndComicsById(UUID.randomUUID(), UUID.randomUUID()));
    }

    @Test
    void whenBindThenThrowComicsNotFoundException() {
        when(characterRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(characterEntity));
        when(comicsRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThrows(ComicsNotFoundException.class, () -> service.bindCharacterAndComicsById(UUID.randomUUID(), UUID.randomUUID()));
    }

    @Test
    void whenBindThenComicsContainsInCharacterComics() {
        when(characterRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(characterEntity));
        when(comicsRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(comicsEntity));
        when(characterEntity.getComics())
                .thenReturn(characterComics);
        when(characterComics.contains(comicsEntity))
                .thenReturn(true);

        assertFalse(service.bindCharacterAndComicsById(UUID.randomUUID(), UUID.randomUUID()));
    }

    @Test
    void whenBindThenCharacterContainsIntComicsCharacters() {
        when(characterRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(characterEntity));
        when(comicsRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(comicsEntity));
        when(characterEntity.getComics())
                .thenReturn(characterComics);
        when(characterComics.contains(comicsEntity))
                .thenReturn(false);
        when(comicsEntity.getCharacters())
                .thenReturn(comicsCharacters);
        when(comicsCharacters.contains(characterEntity))
                .thenReturn(true);

        assertFalse(service.bindCharacterAndComicsById(UUID.randomUUID(), UUID.randomUUID()));
    }


}