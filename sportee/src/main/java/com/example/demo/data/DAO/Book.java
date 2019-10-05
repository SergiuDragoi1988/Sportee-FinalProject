package com.example.demo.data.DAO;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Book {

    @Id
    private int id;
    private String title;

   @ManyToOne
   @JoinColumn(name="owner_id")
    private User owner;


    @ManyToMany
   @JoinTable(name="book_to_author")
   private Set<Author> authors;

    public Set<Author> getAuthors() {
        return authors;
    }
    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
