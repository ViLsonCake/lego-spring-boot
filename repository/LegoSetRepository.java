package com.project.SpringBootLegoScrap.repository;

import com.project.SpringBootLegoScrap.entity.LegoSetEntity;
import org.springframework.data.repository.CrudRepository;

public interface LegoSetRepository extends CrudRepository<LegoSetEntity, Long> {
    LegoSetEntity findByItem(Integer item);
}
