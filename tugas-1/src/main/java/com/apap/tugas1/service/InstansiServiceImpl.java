package com.apap.tugas1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.repository.InstansiDB;

@Service
@Transactional
public class InstansiServiceImpl implements InstansiService {
	@Autowired
	private InstansiDB instansiDb;
	@Override
	public List<InstansiModel> getInstansiList() {
		// TODO Auto-generated method stub
		return instansiDb.findAll();
	}
	@Override
	public InstansiModel getDetailInstansi(Long id) {
		// TODO Auto-generated method stub
		return instansiDb.findInstansiById(id);
	}
	@Override
	public List<InstansiModel> getInstansiWithProvId(Long idProvinsi) {
		// TODO Auto-generated method stub
		return instansiDb.findInstansiByProvinsi(idProvinsi);
	}

}
