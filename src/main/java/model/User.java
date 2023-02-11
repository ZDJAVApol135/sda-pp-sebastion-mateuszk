package model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

    @Table(name = "users")
    public class User {
        @Id
        @Column(name = "username", unique = true)
        private String username;

        @Column(name = "password")
        private String password;

        @Column(name = "name", length = 50)
        private String name;

        @Column(name = "surname",length =50)
        private String surname;

        @Column(name = "age")
        private int age;

        @Column(name = "email")
        private String email;



    }
