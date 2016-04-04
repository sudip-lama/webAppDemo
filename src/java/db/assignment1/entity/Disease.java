/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.entity;

/**
 *
 * @author maha
 */
public class Disease {
     private int id;
    
    private String diseaseName;
    
    private String diseaseType;
    
    private String diseasesymptoms;
    
    public Disease(){
    }
     public Disease(String diseaseName, String diseaseType, String diseasesymptoms) {
        this.diseaseName= diseaseName;
        this.diseaseType= diseaseType;
        this.diseasesymptoms= diseasesymptoms;
    }

    public Disease(int id, String diseaseName, String diseaseType, String diseasesymptoms) {
        this.id = id;
        this.diseaseName= diseaseName;
        this.diseaseType= diseaseType;
        this.diseasesymptoms= diseasesymptoms;   
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public String getDiseasesymptoms() {
        return diseasesymptoms;
    }

    public void setDiseasesymptoms(String diseasesymptoms) {
        this.diseasesymptoms = diseasesymptoms;
    }
    
}
