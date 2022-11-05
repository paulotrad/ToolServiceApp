package com.toolapp.repos;

import com.toolapp.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartRepository extends JpaRepository<Part,Long> {
    Part findByName(String name);

    @Query("SELECT t FROM Part t WHERE t.name LIKE ?1%")
    List<Part> findByNameLike(String name);

}
