package com.project.SpringBootLegoScrap.entity;

import com.project.SpringBootLegoScrap.entity.entityConsts.EntityConst;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = EntityConst.USER_TABLE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @NotEmpty(message = EntityConst.USERNAME_EMPTY_MESSAGE)
    @Size(min = 2, max = 20, message = EntityConst.USERNAME_SIZE_MESSAGE)
    private String username;

    @NotEmpty(message = EntityConst.PASSWORD_EMPTY_MESSAGE)
    @Pattern(regexp = EntityConst.REGEX_PASSWORD_PATTERN, message = EntityConst.PASSWORD_PATTERN_VALID_MESSAGE)
    private String password;

    @NotEmpty(message = EntityConst.EMAIL_EMPTY_MESSAGE)
    @Email(message = EntityConst.EMAIL_VALID_MESSAGE)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<LegoSetEntity> legoSets;

    public UserEntity() {}

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<LegoSetEntity> getLegoSets() {
        return legoSets;
    }

    public void setLegoSets(List<LegoSetEntity> legoSets) {
        this.legoSets = legoSets;
    }
}
