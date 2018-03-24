package org.softuni.nuggets.services;

import org.softuni.nuggets.entities.Nugget;
import org.softuni.nuggets.repositories.NuggetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class NuggetServiceImpl implements NuggetService {

    private final NuggetRepository nuggetRepository;

    @Autowired
    public NuggetServiceImpl(NuggetRepository nuggetRepository) {
        this.nuggetRepository = nuggetRepository;
    }

    @Override
    public List<String> findNuggets(List<String> preferences) {
        List<String> userNuggets = new LinkedList<>();
        for (String preference : preferences) {
            List<Nugget> nuggetIsFound = this.nuggetRepository.findIfNuggetIsFound("%" + preference.toLowerCase() + "%", PageRequest.of(0,1)).getContent();
            if (nuggetIsFound != null && !nuggetIsFound.isEmpty()) {
                userNuggets.add(nuggetIsFound.get(0).getName());
            }
        }

        return userNuggets;
    }
}
