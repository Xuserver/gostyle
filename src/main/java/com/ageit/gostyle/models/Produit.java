package com.ageit.gostyle.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produit {

  @Id
  private long id;
  private String nom;
  private long categorieId;
  private String image;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }


  public long getCategorieId() {
    return categorieId;
  }

  public void setCategorieId(long categorieId) {
    this.categorieId = categorieId;
  }


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

}
