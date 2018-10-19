package com.apap.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;

@Controller
public class JabatanController {
	@Autowired
	private JabatanService jabatanService;
	
	@Autowired
	private InstansiService instansiService;
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
	private String add (Model model) {
		JabatanModel jabatan = new JabatanModel();
		
		model.addAttribute("jabatan",  jabatan);
		return "add-jabatan";
	}
	
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	private String addJabatanSubmit(@ModelAttribute JabatanModel jabatan) {
		jabatanService.addJabatan(jabatan);
		return "add-sukses";
	}
	
	
	
	@RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
	private String lihatJabatan(@RequestParam("id") Long id,Model model) {
		List<JabatanModel> jabatan = jabatanService.getJabatanList();
		for (JabatanModel search : jabatan) { 
			if (search.getId().equals(id)) {
				model.addAttribute("jabatan", search);
				int size = search.getJabatanPegawai().size();
				model.addAttribute("size",size);
				return "view-jabatan";
			}
		}
		return "home";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
	private String ubah(@RequestParam("idJabatan") Long id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanById(id);
		model.addAttribute("currJabatan", jabatan);
		return "ubah-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
	private String ubahJabatanSubmit(@ModelAttribute JabatanModel jabatan) {
		jabatanService.updateJabatan(jabatan, jabatan.getId());
		return "ubah-sukses";
	}
	
	@RequestMapping(value = "/jabatan/hapus", method=RequestMethod.POST)
	private String deleteJabatan(Long id) {
		List<JabatanPegawaiModel> jabatan = jabatanService.getJabatanById(id).getJabatanPegawai();
		if (jabatan.isEmpty()) {
			jabatanService.deleteJabatan(id);
			return "hapus-sukses";
		}
		return "hapus-gagal";
		
	}
	
	@RequestMapping(value = "/jabatan/viewall")
	private String viewAllJabatan(Model model) {
		List<JabatanModel> listJabatan = jabatanService.getJabatanList();
		model.addAttribute("listJabatan", listJabatan);
		return "view-all-jabatan";
	}
	
}
