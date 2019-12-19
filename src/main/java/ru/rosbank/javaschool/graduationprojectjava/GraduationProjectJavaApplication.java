package ru.rosbank.javaschool.graduationprojectjava;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.rosbank.javaschool.graduationprojectjava.dto.CharacterDto;
import ru.rosbank.javaschool.graduationprojectjava.dto.CharacterDtoWithComics;
import ru.rosbank.javaschool.graduationprojectjava.dto.ComicsDto;
import ru.rosbank.javaschool.graduationprojectjava.dto.ComicsDtoWithCharacters;
import ru.rosbank.javaschool.graduationprojectjava.service.EntityService;
import ru.rosbank.javaschool.graduationprojectjava.service.RelationService;
import ru.rosbank.javaschool.graduationprojectjava.service.impl.CharacterServiceImpl;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class GraduationProjectJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationProjectJavaApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(
            EntityService<CharacterDto, CharacterDtoWithComics> characters,
            EntityService<ComicsDto, ComicsDtoWithCharacters> comics) {
        return args -> {
            CharacterDtoWithComics ironMan = characters.save(new CharacterDtoWithComics(null, "Железный человек", "Гений, миллионер, плейбой, филантроп", "", "", "", Collections.emptyList()));
            CharacterDtoWithComics cap = characters.save(new CharacterDtoWithComics(null, "Капитан Америка", "Дайте этому человеку щит", "", "", "", Collections.emptyList()));
            CharacterDtoWithComics blackWidow = characters.save(new CharacterDtoWithComics(null, "Черная Вдова", "Самый ожидаемый фильм", "", "", "", Collections.emptyList()));
            CharacterDtoWithComics spiderMan = characters.save(new CharacterDtoWithComics(null, "Человек-паук", "Ваш дружелюбный сосед", "", "", "", Collections.emptyList()));

            ComicsDtoWithCharacters avengersFinal = comics.save(new ComicsDtoWithCharacters(null, "Мстители: Финал", "Да, этот фильм обогнал по сборам Аватар", "", "", "", Collections.emptyList()));
            ComicsDtoWithCharacters homecoming = comics.save(new ComicsDtoWithCharacters(null, "Человек-паук: Возвращение домой", "Ну разве он не прекрасен?", "", "", "", Collections.emptyList()));
            ComicsDtoWithCharacters winterSoilder = comics.save(new ComicsDtoWithCharacters(null, "Первый мститель: Зимний солдат", "Лучшие друзья!", "", "", "", Collections.emptyList()));
            ComicsDtoWithCharacters ironManComics = comics.save(new ComicsDtoWithCharacters(null, "Железный человек", "О нет, Тони взяли в плен!", "", "", "", Collections.emptyList()));

            ironMan.setComics(List.of(
                    ComicsDto.from(avengersFinal),
                    ComicsDto.from(ironManComics)
                    ));
            characters.save(ironMan);
            cap.setComics(List.of(
                    ComicsDto.from(avengersFinal),
                    ComicsDto.from(winterSoilder)
                    ));
            characters.save(cap);
            blackWidow.setComics(List.of(
                    ComicsDto.from(avengersFinal),
                    ComicsDto.from(winterSoilder)
            ));
            characters.save(blackWidow);
            spiderMan.setComics(List.of(
                    ComicsDto.from(avengersFinal),
                    ComicsDto.from(homecoming)
            ));
            characters.save(spiderMan);
        };
    }
}