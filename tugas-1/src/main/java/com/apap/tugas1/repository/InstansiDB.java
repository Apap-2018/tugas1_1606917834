package com.apap.tugas1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas1.model.InstansiModel;

@Repository
public interface InstansiDB extends JpaRepository<InstansiModel, Long> {
	InstansiModel findInstansiById(Long id);
	List<InstansiModel> findInstansiByProvinsi(Long idProvinsi);
}
