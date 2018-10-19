package com.apap.tugas1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.ProvinsiDB;

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService {
	@Autowired
	private ProvinsiDB provinsiDb;
	@Override
	public List<ProvinsiModel> getProvinsiList() {
		// TODO Auto-generated method stub
		return provinsiDb.findAll();
	}
	@Override
	public ProvinsiModel getProvinsiById(Long id) {
		// TODO Auto-generated method stub
		return provinsiDb.findProvinsiById(id);
	}

}
