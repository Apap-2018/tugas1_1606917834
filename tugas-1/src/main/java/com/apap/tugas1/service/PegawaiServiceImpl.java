package com.apap.tugas1.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDB;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {
	@Autowired
	private PegawaiDB pegawaiDb;
	@Override
	public PegawaiModel getPegawaiDetailByNip(String nip) {
		return pegawaiDb.findPegawaiByNip(nip);
	}
	@Override
	public List<PegawaiModel> getPegawaiList() {
		// TODO Auto-generated method stub
		return pegawaiDb.findAll();
	}
	@Override
	public List<PegawaiModel> getInstansiOrdered(InstansiModel instansi) {
		// TODO Auto-generated method stub
		return pegawaiDb.findByInstansiOrderByTanggalLahirAsc(instansi);
	}
	@Override
	public void addPegawai(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		pegawaiDb.save(pegawai);
	}
	@Override
	public void updatePegawai(PegawaiModel pegawai, Long id) {
		// TODO Auto-generated method stub
		PegawaiModel pegawaiToUpdate = pegawaiDb.findPegawaiById(id);
		pegawaiToUpdate.setNama(pegawai.getNama());
		pegawaiToUpdate.setTempatLahir(pegawai.getTempatLahir());
		pegawaiToUpdate.setTanggalLahir(pegawai.getTanggalLahir());
		pegawaiToUpdate.setTahunMasuk(pegawai.getTahunMasuk());
		pegawaiToUpdate.setNip(pegawai.getNip());
		pegawaiToUpdate.setInstansi(pegawai.getInstansi());
		pegawaiToUpdate.setPegawaiJabatan(pegawai.getPegawaiJabatan());
		pegawaiDb.save(pegawaiToUpdate);
	}
	/**
	@Override
	public List<PegawaiModel> getPegawaiByProvInstJabs(Long idProv, Long idInst, Long idJabs) {
		// TODO Auto-generated method stub
		return pegawaiDb.findPegawaiByProvinsiByInstansiByJabatan(idProv, idInst, idJabs);
	} */
	
	
	

}
