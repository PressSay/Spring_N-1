package com.example.accessingdata.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private long id;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public Book(String title) {
        this.title = title;
    }

    public Book(long id, String title, Library library, Set<Author> authors) {
        this.id = id;
        this.title = title;
        this.library = library;
        this.authors = authors;
    }

    public Book() {
    }
}
