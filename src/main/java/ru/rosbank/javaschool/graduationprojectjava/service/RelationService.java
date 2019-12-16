package ru.rosbank.javaschool.graduationprojectjava.service;

import java.util.UUID;

public interface RelationService {
    boolean bindCharacterAndComicsById(UUID characterId, UUID comicsId);
}
