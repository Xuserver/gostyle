package com.ageit.gostyle.repository;

import com.ageit.gostyle.models.Produit;
import org.springframework.data.repository.CrudRepository;

public interface ProduitsRepository extends CrudRepository<Produit, Long> {

    Produit findById(long id);
}
