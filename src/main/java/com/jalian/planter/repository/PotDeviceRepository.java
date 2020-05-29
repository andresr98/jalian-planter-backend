package com.jalian.planter.repository;

import com.jalian.planter.model.Device;
import com.jalian.planter.model.Pot;
import com.jalian.planter.model.PotDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PotDeviceRepository extends JpaRepository<PotDevice, Integer> {

    Optional<PotDevice> findByPotAndDevice (Pot pot, Device device);
}
