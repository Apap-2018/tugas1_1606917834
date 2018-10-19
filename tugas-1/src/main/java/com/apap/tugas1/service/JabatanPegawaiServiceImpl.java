package com.apap.tugas1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.repository.JabatanPegawaiDB;

@Service
@Transactional
public class JabatanPegawaiServiceImpl implements JabatanPegawaiService {
	@Autowired
	private JabatanPegawaiDB jabatanPegawaiDb;

	@Override
	public List<JabatanPegawaiModel> getJabatanPegawaiList() {
		// TODO Auto-generated method stub
		return jabatanPegawaiDb.findAll();
	}

	@Override
	public void addJabatanPegawai(JabatanPegawaiModel jabatanPegawai) {
		// TODO Auto-generated method stub
		jabatanPegawaiDb.save(jabatanPegawai);
	}
	

}
