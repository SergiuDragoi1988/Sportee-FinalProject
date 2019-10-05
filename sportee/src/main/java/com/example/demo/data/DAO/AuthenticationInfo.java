package com.example.demo.data.DAO;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
public class AuthenticationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String userName;
    private String password;

    @OneToOne(mappedBy = "authenticationInfo")
    private User user;


    @Builder
    public AuthenticationInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
