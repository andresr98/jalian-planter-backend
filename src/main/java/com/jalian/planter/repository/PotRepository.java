package com.jalian.planter.repository;

import com.jalian.planter.model.Pot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PotRepository extends JpaRepository<Pot, Integer> {

    List<Pot> findByUser_Id(int id);
}
