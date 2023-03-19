package com.project.SpringBootLegoScrap.model;

import com.project.SpringBootLegoScrap.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserModel {
    private Long userId;
    private String username, email;
    private List<LegoSetModel> legoSets;

    public UserModel() {}

    public static UserModel toModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();

        userModel.setUserId(userEntity.getUser_id());
        userModel.setUsername(userEntity.getUsername());
        userModel.setEmail(userEntity.getEmail());
        userModel.setLegoSets(userEntity.getLegoSets().stream().map(LegoSetModel::toModel).collect(Collectors.toList()));

        return userModel;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<LegoSetModel> getLegoSets() {
        return legoSets;
    }

    public void setLegoSets(List<LegoSetModel> legoSets) {
        this.legoSets = legoSets;
    }
}
