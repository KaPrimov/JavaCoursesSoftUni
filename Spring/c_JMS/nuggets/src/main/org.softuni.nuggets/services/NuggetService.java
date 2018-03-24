package org.softuni.nuggets.services;

import java.util.List;

public interface NuggetService {
    List<String> findNuggets(List<String> preferences);

    List<String> allNuggets();
}
