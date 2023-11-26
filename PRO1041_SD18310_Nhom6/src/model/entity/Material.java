/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entity;

/**
 *
 * @author TgNam
 */
import java.util.Date;

public class Material {
    // private fields
    private boolean status;
    private Date createdAt;
    private String id;
    private Date updatedAt;
    private String nameMaterial;

    public Material() {
    }

    public Material(boolean status, Date createdAt, String id, Date updatedAt, String nameMaterial) {
        this.status = status;
        this.createdAt = createdAt;
        this.id = id;
        this.updatedAt = updatedAt;
        this.nameMaterial = nameMaterial;
    }
    //them cai nay 22-11 2:45sa
    public Material(String nameMaterial) {
        this.nameMaterial = nameMaterial;
    }
    
    

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNameMaterial() {
        return nameMaterial;
    }

    public void setNameMaterial(String nameMaterial) {
        this.nameMaterial = nameMaterial;
    }
    
}

