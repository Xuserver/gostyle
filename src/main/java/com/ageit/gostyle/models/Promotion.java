package com.ageit.gostyle.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Promotion {

  @Id
  private long id;
  private long produitId;
  private java.sql.Date dateDebut;
  private java.sql.Date dateFin;
  private String reduction;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getProduitId() {
    return produitId;
  }

  public void setProduitId(long produitId) {
    this.produitId = produitId;
  }


  public java.sql.Date getDateDebut() {
    return dateDebut;
  }

  public void setDateDebut(java.sql.Date dateDebut) {
    this.dateDebut = dateDebut;
  }


  public java.sql.Date getDateFin() {
    return dateFin;
  }

  public void setDateFin(java.sql.Date dateFin) {
    this.dateFin = dateFin;
  }


  public String getReduction() {
    return reduction;
  }

  public void setReduction(String reduction) {
    this.reduction = reduction;
  }

}
