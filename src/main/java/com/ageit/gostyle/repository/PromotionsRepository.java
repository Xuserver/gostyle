package com.ageit.gostyle.repository;

import com.ageit.gostyle.models.Promotion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PromotionsRepository extends CrudRepository<Promotion, Long> {


    
    // cherche toutes les promotions
    /*
    @Query("select prom from Promotion prom")
    List<Promotion> getAllPromotions();
    */

    // cherche toutes les promotions en cours
    @Query("select prom from Promotion prom where prom.dateDebut<= CURRENT_DATE and prom.dateFin>= CURRENT_DATE ")
    List<Promotion> getCurrentPromotions();

    // cherche toutes les promotions en cours pour un produit donné;
    @Query("select prom from Promotion prom where prom.produitId = ?1 and prom.dateDebut<= CURRENT_DATE and prom.dateFin>= CURRENT_DATE " )
    List<Promotion> getCurrentPromotionsForProduit(long prodId);

    // cherche toutes les promotions en cours pour les produits de la même catégorie
    // @todo
    @Query("select prom from Promotion prom join Produit prod on prom.produitId=prod.id where prod.categorieId = ?1 and prom.dateDebut<= CURRENT_DATE and prom.dateFin>= CURRENT_DATE " )
    List<Promotion> getCurrentPromotionsForProduitCategorie(long prodId);


    // cherche toutes les promotions en cours pour les produits d'une certaine catégorie
    @Query("select prom from Promotion prom join Produit prod on prom.produitId=prod.id where prod.categorieId = ?1 and prom.dateDebut<= CURRENT_DATE and prom.dateFin>= CURRENT_DATE " )
    List<Promotion> getCurrentPromotionsForCategorie(long catId);
}
