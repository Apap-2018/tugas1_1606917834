package com.apap.tugas1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDB;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService {
	@Autowired
	private JabatanDB jabatanDb;
	@Override
	public void addJabatan(JabatanModel jabatan) {
		// TODO Auto-generated method stub
		jabatanDb.save(jabatan);
	}
	@Override
	public List<JabatanModel> getJabatanList() {
		// TODO Auto-generated method stub
		return jabatanDb.findAll();
	}
	@Override
	public JabatanModel getJabatanById(Long id) {
		// TODO Auto-generated method stub
		return jabatanDb.findJabatanById(id);
	}
	@Override
	public void updateJabatan(JabatanModel jabatan, Long id) {
		// TODO Auto-generated method stub
		JabatanModel jabatanToUpdate = jabatanDb.findJabatanById(id);
		jabatanToUpdate.setNama(jabatan.getNama());
		jabatanToUpdate.setDeskripsi(jabatan.getDeskripsi());
		jabatanToUpdate.setGajiPokok(jabatan.getGajiPokok());
		jabatanDb.save(jabatanToUpdate);
	}
	@Override
	public void deleteJabatan(Long id) {
		// TODO Auto-generated method stub
		jabatanDb.deleteById(id);
	}
	

}
