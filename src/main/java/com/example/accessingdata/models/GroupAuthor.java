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
public class GroupAuthor {
    
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
    @JoinTable(name = "author_groupAuthor", joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    private Set<Author> authors;

	public Set<Author> getAuthors() {
		return this.authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public GroupAuthor(String name) {
        this.name = name;
    }

    public GroupAuthor(long id, String name, Set<Author> authors) {
        this.id = id;
        this.name = name;
        this.authors = authors;
    }

    public GroupAuthor() {
    }
}
