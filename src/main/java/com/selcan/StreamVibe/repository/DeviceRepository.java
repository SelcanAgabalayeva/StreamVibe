package com.selcan.StreamVibe.repository;

import com.selcan.StreamVibe.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device,Integer> {
}
