package com.example.demo.data.DAO;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

//@Getter
//@Setter
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private String email;
    private LocalDate birthDate;

    @OneToOne
    @JoinColumn(name="authentication_id")
    private AuthenticationInfo authenticationInfo;



    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;


    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public Address getAddress() {
        return address;
    }

    public long getAge() {
        return age;
    }

    @Transient
    private long age;

//    public User() {
//
//    }
//
//    public User(int id, String name, String email) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//
//    }
//
    @Builder
    public User(String name, String email) {
        this.name = name;
        this.email = email;

    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }


    @PostLoad
    public void calculateAge() {
        if (birthDate != null) {
            age = ChronoUnit.YEARS.between(birthDate, LocalDate.now());
        }
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return id == user.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
