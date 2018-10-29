package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;

public interface JabatanPegawaiService {
	List <JabatanPegawaiModel> getJabatanPegawaiList();
	void addJabatanPegawai(JabatanPegawaiModel jabatanPegawai);
	List <JabatanPegawaiModel> findJabatanPegawaiByPegawai (PegawaiModel pegawai);
	void deleteJabatanPegawai(JabatanPegawaiModel jabatanPegawai);
}
