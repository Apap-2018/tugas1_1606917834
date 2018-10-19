package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.JabatanModel;

public interface JabatanService {
	List<JabatanModel> getJabatanList();
	void addJabatan(JabatanModel jabatan);
	JabatanModel getJabatanById(Long id);
	void updateJabatan(JabatanModel jabatan, Long id);
	void deleteJabatan(Long id);
}
