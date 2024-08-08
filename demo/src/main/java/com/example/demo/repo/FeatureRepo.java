package com.example.demo.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.pojo.Feature;

@Repository
public interface FeatureRepo extends JpaRepository<Feature,Integer>{

    // List<Epic> saveAll(List<Epic> epics);
    // Feature findById(int id);
}
