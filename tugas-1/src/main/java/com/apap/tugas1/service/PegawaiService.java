package com.apap.tugas1.service;


import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;

public interface PegawaiService {
	PegawaiModel getPegawaiDetailByNip(String nip);
	PegawaiModel getPegawaiDetailById(Long id);
	List<PegawaiModel> getPegawaiList();
	List<PegawaiModel> getInstansiOrdered(InstansiModel instansi);
	void addPegawai(PegawaiModel pegawai);
	void updatePegawai(PegawaiModel pegawai, Long id);
	//List<PegawaiModel> getPegawaiByProvInstJabs(Long idProv, Long idInst, Long idJabs);
	void deleteJabatan(PegawaiModel pegawai);
}
