package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.JabatanPegawaiModel;

public interface JabatanPegawaiService {
	List <JabatanPegawaiModel> getJabatanPegawaiList();
	void addJabatanPegawai(JabatanPegawaiModel jabatanPegawai);
}
