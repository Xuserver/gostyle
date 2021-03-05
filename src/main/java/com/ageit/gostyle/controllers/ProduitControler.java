package com.ageit.gostyle.controllers;

import com.ageit.gostyle.models.Produit;
import com.ageit.gostyle.repository.ProduitsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

// @CrossOrigin sur le controler, la methode d'un controleur
// sur toute l'API =>
//
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProduitControler {

    private final ProduitsRepository produitsRepository;

    // @Autowired by Spring Boot because it is in the constructor !
    public ProduitControler(ProduitsRepository produitsRepository) {
        this.produitsRepository = produitsRepository;
    }

    /** Retourne tous les livres
     *
     * @return Une liste d'objet Book
     */
    @GetMapping("/produits")
    public Iterable<Produit> getBooks() {
        return produitsRepository.findAll();
    }

    /** Retourne le produit  associé à l'id ou une erreur si non trouvée
     * Méthode REST GET publique
     *
     * @param id di du livre
     * @return L'objet Produit correspondant à l'id ou une erreur ResponseStatusException
     */
    @GetMapping("/produits/{id}")
    public Produit getBooks(@PathVariable(value = "id") Long id) {
        return findById(id);
    }

    /** Retourne le livre associé à l'id ou une erreur si non trouvée
     * Méthode interne privée
     *
     * @param id isbn du livre
     * @return L'objet Book correspondant à l'id ou une erreur ResponseStatusException
     */
    private Produit findById(Long id) {

        Optional<Produit> res  =  produitsRepository.findById(id);
        if (res.isPresent()){
            LogMessage("Book "+id+" trouvé");
            return res.get();
        }else{
            LogMessage("Book "+id+" non trouvé");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Livre '%s' non trouvé", id)
            );

        }
        /*
        return res.orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Livre '%s' non trouvé", id)
                    )
                );
        * */
    }


    private void LogMessage(String message) {
        System.err.printf("Message=%s, Class=%s\n", message, this.getClass().getCanonicalName());
    }


}
