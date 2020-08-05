package ru.vlapin.experiments.rabbitmqluxoftcourse.dao;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlapin.experiments.rabbitmqluxoftcourse.model.Cat;

public interface CatRepository extends JpaRepository<Cat, UUID> {
}
