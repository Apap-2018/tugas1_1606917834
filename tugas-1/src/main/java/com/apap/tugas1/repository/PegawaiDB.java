package com.apap.tugas1.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;

@Repository
public interface PegawaiDB extends JpaRepository<PegawaiModel, Long> {
	PegawaiModel findPegawaiByNip(String nip);
	PegawaiModel findPegawaiById (Long id);
	List<PegawaiModel> findByInstansiOrderByTanggalLahirAsc(InstansiModel instansi);
	//List<PegawaiModel> findPegawaiByProvinsiByInstansiByJabatan(Long idProvinsi, Long idInstansi, Long idJabatan);
}
