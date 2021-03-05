package com.ageit.gostyle.controllers;


import com.ageit.gostyle.models.Produit;
import com.ageit.gostyle.models.Promotion;
import com.ageit.gostyle.repository.ProduitsRepository;
import com.ageit.gostyle.repository.PromotionsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PromotionControler {
    private final PromotionsRepository repoPromos;
    private final ProduitsRepository repoProds;

    // @Autowired by Spring Boot because it is in the constructor !
    public PromotionControler(PromotionsRepository repoPromos, ProduitsRepository repoProds) {
        this.repoPromos = repoPromos;
        this.repoProds = repoProds;
    }

    @GetMapping("/promotions")
    public Iterable<Promotion> getAllPromotions() {
        return repoPromos.findAll();
    }

    @GetMapping("/promotions/current")
    public Iterable<Promotion> getCurrentPromotions() {
        return repoPromos.getCurrentPromotions();
    }

    @GetMapping("/promotions/produit/{prodId}")
    public Iterable<Promotion> getCurrentPromotionsForProduit(@PathVariable long prodId) {
        return repoPromos.getCurrentPromotionsForProduit(prodId);
    }

    /*
     * @todo
     */
    @GetMapping("/promotions/produit/{prodId}/categorie")
    public Iterable<Promotion> getCurrentPromotionsForProduitCategorie(@PathVariable long prodId) {
        try{
            Produit p = repoProds.findById(prodId);
            System.out.println("LISTE des promotions pour le produit "+prodId);
            List<Promotion> promosForProduit = repoPromos.getCurrentPromotionsForProduit(prodId);

            System.out.println("LISTE des promotions pour la catégorie  "+p.getCategorieId());
            List<Promotion> promosForCategorie = repoPromos.getCurrentPromotionsForCategorie(p.getCategorieId());

            promosForProduit.addAll(promosForCategorie) ;

            return promosForProduit;

        }
        catch (Exception ex){
            throw ex;
        }
    }


    @GetMapping("/promotions/categorie/{catId}")
    public Iterable<Promotion> getCurrentPromotionsForCategorie(@PathVariable long catId) {
        return repoPromos.getCurrentPromotionsForCategorie(catId);
    }


    @GetMapping(value="/promotions/all/{prodId}")
    public Iterable<Promotion> buyerLandingReport(@PathVariable long prodId) {
        try{

            List<Promotion> list1 = repoPromos.getCurrentPromotionsForProduit(prodId);
            List<Promotion> list2 = repoPromos.getCurrentPromotions();

            list1.addAll(list2) ;
            return list1;

        }
        catch (Exception ex){
            throw ex;
        }
    }



/*
    @GetMapping("/promotions/{prodid}")
    public Promotion getPromotionsOfProduit(@PathVariable(value = "prodid") Long id) {
        return queryPromotions(id);
    }
    private Promotion queryPromotions(Long id) {

        Optional<Promotion> res  =  repository.queryPromotions(id);
        if (res.isPresent()){
            LogMessage("Promotion pour le produit "+id+" trouvé");
            return res.get();
        }else{
            LogMessage("Promotion pour le produit "+id+" non trouvé");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Promotion pour le produit '%s' non trouvé", id)
            );

        }
    }
*/
    private void LogMessage(String message) {
        System.err.printf("Message=%s, Class=%s\n", message, this.getClass().getCanonicalName());
    }



}
