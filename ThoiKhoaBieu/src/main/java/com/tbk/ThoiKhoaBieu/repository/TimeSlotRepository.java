package com.tbk.ThoiKhoaBieu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tbk.ThoiKhoaBieu.entity.TimeSlotEntity;

public interface TimeSlotRepository extends JpaRepository<TimeSlotEntity, Long> {

	List<TimeSlotEntity> findAll();
}
