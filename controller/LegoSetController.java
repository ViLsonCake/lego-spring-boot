package com.project.SpringBootLegoScrap.controller;

import com.project.SpringBootLegoScrap.exception.LegoSetAlreadyExistException;
import com.project.SpringBootLegoScrap.exception.LegoSetNotFoundException;
import com.project.SpringBootLegoScrap.exception.UserNotFoundException;
import com.project.SpringBootLegoScrap.service.LegoSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/set")
public class LegoSetController {

    private final LegoSetService legoSetService;

    @Autowired
    public LegoSetController(LegoSetService legoSetService) {
        this.legoSetService = legoSetService;
    }

    @PostMapping("/{id}/{item}")
    public ResponseEntity addLegoSet1(@PathVariable("id") Long id, @PathVariable("item") Integer item) throws IOException {
        try {
            legoSetService.addLegoSet(id, item);

            return ResponseEntity.ok("Lego set added");
        } catch (LegoSetAlreadyExistException | UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateDataFromPage(@PathVariable("id") Long id) {
        try {
            legoSetService.updateOne(id);

            return ResponseEntity.ok("Lego set with id " + id + " updated");
        } catch (LegoSetNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity updateAllDataFromPage() {
        try {
            legoSetService.updateAll();

            return ResponseEntity.ok("All lego sets successfully updated");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLegoSet(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok("User with id " + legoSetService.deleteOne(id) + " was deleted");
        } catch (LegoSetNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}