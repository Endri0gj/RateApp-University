package rate.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (name = "user")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;

    public User() {
    }

    public User(Integer id, String name, String surname, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return ((id==null && user.id==null) || (id.equals(user.id))) &&

                ((name==null && user.name==null) || (name.equals(user.name))) &&
                ((surname==null && user.surname==null) || (surname.equals(user.surname))) &&
                ((email==null && user.email==null) || (email.equals(user.email))) &&
                ((password==null && user.password==null) || (password.equals(user.password)));
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
