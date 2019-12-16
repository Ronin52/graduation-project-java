package ru.rosbank.javaschool.graduationprojectjava.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rosbank.javaschool.graduationprojectjava.dto.CharacterDto;
import ru.rosbank.javaschool.graduationprojectjava.dto.CharacterDtoWithComics;
import ru.rosbank.javaschool.graduationprojectjava.dto.ComicsDto;
import ru.rosbank.javaschool.graduationprojectjava.entity.CharacterEntity;
import ru.rosbank.javaschool.graduationprojectjava.exception.CharacterNotFoundException;
import ru.rosbank.javaschool.graduationprojectjava.repository.CharacterRepository;
import ru.rosbank.javaschool.graduationprojectjava.service.EntityService;
import ru.rosbank.javaschool.graduationprojectjava.service.RelationService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements EntityService<CharacterDto, CharacterDtoWithComics> {
    private final CharacterRepository repository;
    private final RelationService relationService;

    @Override
    public CharacterDtoWithComics save(CharacterDtoWithComics dto) {
        if (dto.getId() == null) {
            dto.setId(UUID.randomUUID());
            CharacterDtoWithComics saved = CharacterDtoWithComics.from(repository.save(CharacterEntity.from(dto)));
            if (!dto.getComics().isEmpty()) {
                for (ComicsDto dtoComics : dto.getComics()) {
                    relationService.bindCharacterAndComicsById(saved.getId(), dtoComics.getId());
                }
            }
            return saved;
        }
        return CharacterDtoWithComics.from(repository.save(CharacterEntity.from(dto)));
    }

    @Override
    public List<CharacterDto> getPage(int page, int count) {
        return repository.getPage(page, count).stream()
                .map(CharacterDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<CharacterDto> getSortedByName() {
        return repository.getSortedByName().stream()
                .map(CharacterDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<CharacterDto> findByName(String q) {
        return repository.findAllByNameContainsIgnoreCase(q).stream()
                .map(CharacterDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<CharacterDto> findByDescription(String q) {
        return repository.findAllByDescriptionContainsIgnoreCase(q).stream()
                .map(CharacterDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public CharacterDto getByIdWithoutCollection(UUID id) {
        return CharacterDto.from(repository.findById(id)
                .orElseThrow(CharacterNotFoundException::new));
    }

    @Override
    public CharacterDtoWithComics getByIdWithCollection(UUID id) {
        return CharacterDtoWithComics.from(repository.findById(id)
                .orElseThrow(CharacterNotFoundException::new));
    }

    @Override
    public List<CharacterDto> getAll() {
        return repository.findAll().stream()
                .map(CharacterDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public void removeById(UUID id) {
        repository.deleteById(id);
    }


}
