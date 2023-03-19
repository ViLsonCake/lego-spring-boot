package com.project.SpringBootLegoScrap.service;

import com.project.SpringBootLegoScrap.entity.LegoSetEntity;
import com.project.SpringBootLegoScrap.exception.LegoSetAlreadyExistException;
import com.project.SpringBootLegoScrap.exception.LegoSetNotFoundException;
import com.project.SpringBootLegoScrap.exception.UserNotFoundException;
import com.project.SpringBootLegoScrap.model.LegoSetModel;
import com.project.SpringBootLegoScrap.repository.LegoSetRepository;
import com.project.SpringBootLegoScrap.repository.UserRepository;
import com.scrap.jsoup.LegoParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LegoSetService {

    private final UserRepository userRepository;
    private final LegoSetRepository legoSetRepository;

    @Autowired
    public LegoSetService(UserRepository userRepository, LegoSetRepository legoSetRepository) {
        this.userRepository = userRepository;
        this.legoSetRepository = legoSetRepository;
    }

    // Post
    public LegoSetModel addLegoSet(Long id, Integer item) throws UserNotFoundException, LegoSetAlreadyExistException, IOException {
        LegoSetEntity legoSetEntity = LegoParser.getData(item);

        if (userRepository.findById(id).isEmpty())
            throw new UserNotFoundException("User not found");
        if (legoSetRepository.findByItem(legoSetEntity.getItem()) != null) {
            if (legoSetRepository.findByItem(legoSetEntity.getItem()).getUser().getUser_id() == id)
                throw new LegoSetAlreadyExistException("Lego set already exist");
        }

        legoSetEntity.setUser(userRepository.findById(id).get());

        return LegoSetModel.toModel(legoSetRepository.save(legoSetEntity));
    }

    // Put
    public Long updateOne(Long id) throws LegoSetNotFoundException, IOException {
        if (legoSetRepository.findById(id).isEmpty())
            throw new LegoSetNotFoundException("Lego set with id " + id + " not found");

        LegoSetEntity legoSetEntity = legoSetRepository.findById(id).get();
        LegoSetEntity newLegoSetEntity = LegoParser.getData(legoSetEntity.getItem());

        // Set new values
        legoSetEntity.setTitle(newLegoSetEntity.getTitle());
        legoSetEntity.setItem(newLegoSetEntity.getItem());
        legoSetEntity.setAvailability(newLegoSetEntity.getAvailability());
        legoSetEntity.setAges(newLegoSetEntity.getAges());
        legoSetEntity.setPrice(newLegoSetEntity.getPrice());
        legoSetEntity.setPieces(newLegoSetEntity.getPieces());

        legoSetRepository.save(legoSetEntity);

        return id;
    }

    public void updateAll() throws IOException {
        Iterable<LegoSetEntity> legoSets = legoSetRepository.findAll();

        for (LegoSetEntity legoSetEntity : legoSets) {
            LegoSetEntity newLegoSetEntity = LegoParser.getData(legoSetEntity.getItem());

            // Set new values
            legoSetEntity.setTitle(newLegoSetEntity.getTitle());
            legoSetEntity.setItem(newLegoSetEntity.getItem());
            legoSetEntity.setAvailability(newLegoSetEntity.getAvailability());
            legoSetEntity.setAges(newLegoSetEntity.getAges());
            legoSetEntity.setPrice(newLegoSetEntity.getPrice());
            legoSetEntity.setPieces(newLegoSetEntity.getPieces());

            legoSetRepository.save(legoSetEntity);
        }
    }

    // Delete
    public Long deleteOne(Long id) throws LegoSetNotFoundException {
        if (legoSetRepository.findById(id).isEmpty())
            throw new LegoSetNotFoundException("Lego set not found");

        legoSetRepository.deleteById(id);

        return id;
    }
}
