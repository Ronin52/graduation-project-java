package ru.rosbank.javaschool.graduationprojectjava.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rosbank.javaschool.graduationprojectjava.dto.CharacterDto;
import ru.rosbank.javaschool.graduationprojectjava.dto.CharacterDtoWithComics;
import ru.rosbank.javaschool.graduationprojectjava.dto.ComicsDto;
import ru.rosbank.javaschool.graduationprojectjava.dto.ComicsDtoWithCharacters;
import ru.rosbank.javaschool.graduationprojectjava.service.EntityService;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/generate")
public class Generate {
    private final EntityService<CharacterDto, CharacterDtoWithComics> characters;
    private final EntityService<ComicsDto, ComicsDtoWithCharacters> comics;

    @GetMapping
    public void generate(){
        final CharacterDtoWithComics ironMan = characters.save(new CharacterDtoWithComics(null, "Железный человек", "Гений, миллионер, плейбой, филантроп", "", "", "", Collections.emptyList()));
        final CharacterDtoWithComics cap = characters.save(new CharacterDtoWithComics(null, "Капитан Америка", "Дайте этому человеку щит", "", "", "", Collections.emptyList()));
        final CharacterDtoWithComics blackWidow = characters.save(new CharacterDtoWithComics(null, "Черная Вдова", "Самый ожидаемый фильм", "", "", "", Collections.emptyList()));
        final CharacterDtoWithComics spiderMan = characters.save(new CharacterDtoWithComics(null, "Человек-паук", "Ваш дружелюбный сосед", "", "", "", Collections.emptyList()));

        final ComicsDtoWithCharacters avengersFinal = comics.save(new ComicsDtoWithCharacters(null, "Мстители: Финал", "Да, этот фильм обогнал по сборам Аватар", "", "", "", Collections.emptyList()));
        final ComicsDtoWithCharacters homecoming = comics.save(new ComicsDtoWithCharacters(null, "Человек-паук: Возвращение домой", "Ну разве он не прекрасен?", "", "", "", Collections.emptyList()));
        final ComicsDtoWithCharacters winterSolder = comics.save(new ComicsDtoWithCharacters(null, "Первый мститель: Зимний солдат", "Лучшие друзья!", "", "", "", Collections.emptyList()));
        final ComicsDtoWithCharacters ironManComics = comics.save(new ComicsDtoWithCharacters(null, "Железный человек", "О нет, Тони взяли в плен!", "", "", "", Collections.emptyList()));

        ironMan.setComics(List.of(
                ComicsDto.from(avengersFinal),
                ComicsDto.from(ironManComics)
        ));
        characters.save(ironMan);
        cap.setComics(List.of(
                ComicsDto.from(avengersFinal),
                ComicsDto.from(winterSolder)
        ));
        characters.save(cap);
        blackWidow.setComics(List.of(
                ComicsDto.from(avengersFinal),
                ComicsDto.from(winterSolder)
        ));
        characters.save(blackWidow);
        spiderMan.setComics(List.of(
                ComicsDto.from(avengersFinal),
                ComicsDto.from(homecoming)
        ));
        characters.save(spiderMan);
    }
}
