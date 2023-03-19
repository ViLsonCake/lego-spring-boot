package com.project.SpringBootLegoScrap.service;

import com.project.SpringBootLegoScrap.entity.UserEntity;
import com.project.SpringBootLegoScrap.exception.*;
import com.project.SpringBootLegoScrap.model.UserModel;
import com.project.SpringBootLegoScrap.repository.UserRepository;
import com.project.SpringBootLegoScrap.service.serviceConst.ServiceConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Post
    public UserEntity registraion(UserEntity user, BindingResult bindingResult) throws UserAlreadyExistException, UsernameNotValidException, PasswordNotValidException, EmailNotValidException {
        if (userRepository.findByUsername(user.getUsername()) != null ||
                userRepository.findByEmail(user.getEmail()) != null)
            throw new UserAlreadyExistException("User already exist");

        // Check user fields
        isValid(bindingResult);

        return userRepository.save(user);
    }

    // Get
    public UserModel findOne(Long id) throws UserNotFoundException {
        if (userRepository.findById(id).isEmpty())
            throw new UserNotFoundException("User not found");

        return UserModel.toModel(userRepository.findById(id).get());
    }

    public List<UserModel> getAll() {
        Iterable<UserEntity> users = userRepository.findAll();
        List<UserModel> output = new ArrayList<>();

        users.forEach(user -> output.add(UserModel.toModel(user)));

        return output;
    }

    // Put
    public Long updateOne(Long id, UserEntity updatedUser, BindingResult bindingResult) throws UserNotFoundException, UsernameNotValidException, PasswordNotValidException, EmailNotValidException {
        if (userRepository.findById(id).isEmpty())
            throw new UserNotFoundException("User not found");

        // Check user fields
        isValid(bindingResult);

        // Find current user
        UserEntity user = userRepository.findById(id).get();

        // Set new values
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setEmail(updatedUser.getEmail());

        if (userRepository.findByUsername(user.getUsername()) != null &&
                userRepository.findByUsername(user.getUsername()).getUser_id() != id)
            throw new UserNotFoundException("User already exist");
        if (userRepository.findByEmail(user.getEmail()) != null &&
                userRepository.findByEmail(user.getEmail()).getUser_id() != id)
            throw new UserNotFoundException("User already exist");

        return userRepository.save(user).getUser_id();
    }

    // Delete
    public Long deleteOne(Long id) throws UserNotFoundException {
        if (!(userRepository.existsById(id)))
            throw new UserNotFoundException("User not found");

        userRepository.deleteById(id);

        return id;
    }

    // Validation
    public boolean isValid(BindingResult bindingResult) throws UsernameNotValidException, PasswordNotValidException, EmailNotValidException {
        if (bindingResult.hasFieldErrors(ServiceConst.usernameField))
            throw new UsernameNotValidException("Username is not valid");
        else if (bindingResult.hasFieldErrors(ServiceConst.passwordField))
            throw new PasswordNotValidException("Password is not valid");
        else if (bindingResult.hasFieldErrors(ServiceConst.emailField))
            throw new EmailNotValidException("Email is not valid");

        return true;
    }

}
