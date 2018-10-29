package com.apap.tugas1.service;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.JabatanPegawaiDB;
import com.apap.tugas1.repository.PegawaiDB;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {
	@Autowired
	private PegawaiDB pegawaiDb;
	
	@Autowired
	private JabatanPegawaiDB jabatanPegawaiDb;
	@Autowired
	private JabatanPegawaiService jabatanPegawaiService;
	
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
		/**
		List <JabatanPegawaiModel> list = jabatanPegawaiDb.findAll();
		
		for (int i=0;i< list.size()-1 ;i++) {
			//pegawaiToUpdate.getPegawaiJabatan().remove(i);
			if(list.get(i).getPegawai().getId().equals(id)) {
				list.remove(i);
				
			}
		}
		*/
		System.out.println(pegawai.getPegawaiJabatan().size()+" ini size");
		pegawaiToUpdate.setPegawaiJabatan(pegawai.getPegawaiJabatan());
		System.out.println(pegawaiToUpdate.getPegawaiJabatan().size()+" ini size sesudah");
		pegawaiDb.save(pegawaiToUpdate);
		System.out.println(pegawaiToUpdate.getPegawaiJabatan().size()+ " ini size habis save");
		System.out.println(pegawaiToUpdate.getNip()+ "nip update" + pegawai.getNip() +" nip belom update");
	}
	/**
	@Override
	public List<PegawaiModel> getPegawaiByProvInstJabs(Long idProv, Long idInst, Long idJabs) {
		// TODO Auto-generated method stub
		return pegawaiDb.findPegawaiByProvinsiByInstansiByJabatan(idProv, idInst, idJabs);
	} */
	@Override
	public PegawaiModel getPegawaiDetailById(Long id) {
		// TODO Auto-generated method stub
		return pegawaiDb.findPegawaiById(id);
	}
	@Override
	public void deleteJabatan(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		List<JabatanPegawaiModel> jabatanFound = jabatanPegawaiService.findJabatanPegawaiByPegawai(pegawai);
		for (JabatanPegawaiModel jab : jabatanFound) {
			jabatanPegawaiService.deleteJabatanPegawai(jab);
		}
	}
	
	
	

}
