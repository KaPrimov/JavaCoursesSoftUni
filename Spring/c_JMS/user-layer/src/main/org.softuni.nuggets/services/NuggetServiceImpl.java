package org.softuni.nuggets.services;

import org.softuni.nuggets.messaging.NuggetsMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NuggetServiceImpl implements NuggetService {

    private final NuggetsMessageListener nuggetsMessageListener;

    @Autowired
    public NuggetServiceImpl(NuggetsMessageListener nuggetsMessageListener) {
        this.nuggetsMessageListener = nuggetsMessageListener;
    }

    @Override
    public String[] getAllNuggets() {
        return this.nuggetsMessageListener.getNuggets();
    }
}

