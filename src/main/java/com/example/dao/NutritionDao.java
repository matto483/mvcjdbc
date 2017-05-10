package com.example.dao;

import com.example.domain.Nutrition;

import java.util.List;

public interface NutritionDao {

    void add(Nutrition nutrition);
    List<Nutrition>findAll();

    Nutrition find(long id);
    void update(Nutrition nutrition);
    void delete(long id);
    void add(List<Nutrition> nutritions);


}
