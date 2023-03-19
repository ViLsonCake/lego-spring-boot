package com.project.SpringBootLegoScrap.controller;

import com.project.SpringBootLegoScrap.entity.UserEntity;
import com.project.SpringBootLegoScrap.exception.*;
import com.project.SpringBootLegoScrap.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(userService.findOne(id));
        } catch (Exception ex) {
            return ResponseEntity.ok("Unknown error");
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(userService.getAll());
        } catch (Exception ex) {
            return ResponseEntity.ok("Unknown error");
        }
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody @Valid UserEntity user, BindingResult bindingResult) {
        try {
            userService.registraion(user, bindingResult);

            return ResponseEntity.ok("User saved");
        } catch (UserAlreadyExistException | EmailNotValidException | PasswordNotValidException |
                 UsernameNotValidException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserEntity user,
                                     BindingResult bindingResult) {
        try {
            return ResponseEntity.ok("User with id " + userService.updateOne(id, user, bindingResult) + " was updated");
        } catch (UserNotFoundException | EmailNotValidException | PasswordNotValidException |
                 UsernameNotValidException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok("User with id " + userService.deleteOne(id) + " was deleted");
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
