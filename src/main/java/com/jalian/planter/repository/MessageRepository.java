package com.jalian.planter.repository;

import com.jalian.planter.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findByPotDevice_Pot_IdOrderByPotDevice_Device_Id(int id);
}
