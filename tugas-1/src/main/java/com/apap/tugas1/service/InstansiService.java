package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.InstansiModel;

public interface InstansiService {
	List <InstansiModel> getInstansiList();
	InstansiModel getDetailInstansi(Long id);
	List<InstansiModel> getInstansiWithProvId(Long idProvinsi);
}
