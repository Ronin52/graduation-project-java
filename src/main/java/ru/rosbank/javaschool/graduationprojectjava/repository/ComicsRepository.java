package ru.rosbank.javaschool.graduationprojectjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rosbank.javaschool.graduationprojectjava.entity.ComicsEntity;

import java.util.List;
import java.util.UUID;

public interface ComicsRepository extends JpaRepository<ComicsEntity, UUID> {
    boolean existsById(UUID id);

    List<ComicsEntity> findAllByTitleContainsIgnoreCase(String q);

    List<ComicsEntity> findAllByDescriptionContainsIgnoreCase(String q);

    @Query(value = "SELECT id, title, description, image FROM comics OFFSET (:page - 1) * (:count) LIMIT (:count)", nativeQuery = true)
    List<ComicsEntity> getPage(@Param("page") int page, @Param("count") int elements);

    @Query(value = "SELECT id, title, description, image FROM comics ORDER BY title", nativeQuery = true)
    List<ComicsEntity> getSortedByName();
}
