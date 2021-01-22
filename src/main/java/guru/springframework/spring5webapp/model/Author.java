package guru.springframework.spring5webapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity // tells Hibernate that this is an Entity
public class Author {

    // each entity needs an id;
    // the GeneratedValue specifies how this id is generate --> in this case, automatically;
    // the underlying database will be generating this id;
    // remember to add getters and setters for the id, since it's private
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;


    /*
    Part 1
    this sets up a ManyToMany relationship, meaning that an Author can have multiple books and a Book can have multiple Authors
    This ManyToMany relationship will be represented within the database. See the other part of the relationship setup within Book class
     */
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    public Author() {
        System.out.println("Author: Parameter less constructor called");
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
//        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    // generating hash and equals methods for objects stored within the database (entities)
    // for pojo's you can get them for free, but these classes have been annotated as entities
    // but only for the id property of the entity. You use the ID to discern between different entities stored in your database.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return id != null ? id.equals(author.id) : author.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // will generate some description as well, using the toString method, but this time all the fields of the entity will be included
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", books=" + books +
                '}';
    }
}
