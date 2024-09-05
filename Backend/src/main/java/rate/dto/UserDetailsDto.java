package rate.dto;

import rate.model.User;

public class UserDetailsDto {
    private Integer id;
    private String name;
    private String surname;
    private String email;

    public UserDetailsDto(Integer id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    public UserDetailsDto(User user){
        id= user.getId();
        name=user.getName();
        surname= user.getSurname();
        email=user.getEmail();
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
}
