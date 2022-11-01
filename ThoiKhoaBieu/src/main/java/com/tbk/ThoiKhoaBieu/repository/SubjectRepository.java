package com.tbk.ThoiKhoaBieu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tbk.ThoiKhoaBieu.entity.SubjectEntity;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
	
	List<SubjectEntity> findAll();
}
