package com.example.accessingdata.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Author {
    @Id
    @GeneratedValue
    private long id;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private Set<Book> books;

    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
    
    @ManyToMany(mappedBy = "authors")
    private Set<GroupAuthor> groupAuthors;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(long id, String name, Set<Book> books, Set<GroupAuthor> groupAuthors) {
        this.id = id;
        this.name = name;
        this.books = books;
        this.groupAuthors = groupAuthors;
    }

    public Author() {
    }
}
