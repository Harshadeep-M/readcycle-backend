package com.readcycle.readcycle.entity;
import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String author;
    private Long ownerId;
    private boolean available = true;


    public Long getId(){
        return id;
    }
    public String getTitle(){
        return title;

    }
    public String getAuthor(){
        return author;
    }
    public String getDescription(){
        return description;
    }
    public Long getOwnerId(){
        return ownerId;
    }
    public boolean isAvailable(){
        return available;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setOwnerId(Long ownerId){
        this.ownerId = ownerId;
    }
    public void setAvailable(boolean available){
        this.available = available;
    }
}
