package model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age
                && username.equals(user.username)
                && password.equals(user.password)
                && name.equals(user.name)
                && surname.equals(user.surname)
                && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, name, surname, age, email);
    }
}

