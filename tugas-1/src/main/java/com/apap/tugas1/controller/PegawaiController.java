package com.apap.tugas1.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanPegawaiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;

@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired
	private JabatanPegawaiService jabatanPegawaiService;
	
	@Autowired
	private InstansiService instansiService;
	
	@Autowired
	private JabatanService jabatanService;
	
	@Autowired
	private ProvinsiService provinsiService;
	/**
	@RequestMapping("/")
	private String home() {
		return "home";
	}*/
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	private String home(Model model) {
		JabatanModel jabatan1 = new JabatanModel();
		model.addAttribute("jabatan", jabatan1);
		List<JabatanModel> jabatan = jabatanService.getJabatanList();
		model.addAttribute("jabatans", jabatan);
		InstansiModel instansi1 = new InstansiModel();
		model.addAttribute("instansi", instansi1);
		List<InstansiModel> instansi = instansiService.getInstansiList();
		model.addAttribute("instansis", instansi);
		return "home";
	}
	
	@RequestMapping(value = "/pegawai", method = RequestMethod.GET)
	private String view(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		List <JabatanModel> archiveJabatan = new ArrayList<JabatanModel>();
		List <JabatanPegawaiModel> archive = jabatanPegawaiService.getJabatanPegawaiList();
		for (JabatanPegawaiModel jabatan : archive) {
			if (jabatan.getPegawai().getId().equals(pegawai.getId())) {
				archiveJabatan.add(jabatan.getJabatan());
				
			}
		}
		
		String instansi = pegawai.getInstansi().getNama();
		String provinsi = pegawai.getInstansi().getProvinsi().getNama();
		double gajiPokok =0;
		for (JabatanModel search : archiveJabatan) {
			if (search.getGajiPokok() > gajiPokok) {
				gajiPokok=search.getGajiPokok();
			}
		}
		double tunjangan = pegawai.getInstansi().getProvinsi().getPresentaseTunjangan();
		
		double gajiTotal = gajiPokok + ((tunjangan/100)*gajiPokok);
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("listJabatan", archiveJabatan);
		model.addAttribute("instansi", instansi);
		model.addAttribute("provinsi", provinsi);
		model.addAttribute("gajiTotal", gajiTotal);
		return "view-pegawai";
	}
	
	
	@RequestMapping(value = "/pegawai/termuda-tertua", method=RequestMethod.GET)
	private String termudaTertua(@RequestParam("id") Long id, Model model) {
		InstansiModel instansii = instansiService.getDetailInstansi(id);
		List <PegawaiModel> pegawai = new ArrayList<PegawaiModel>();
		pegawai=pegawaiService.getInstansiOrdered(instansii);
		PegawaiModel tertua = pegawai.get(0);
		PegawaiModel termuda = pegawai.get(pegawai.size()-1);
		
		
		List <JabatanModel> archiveJabatan = new ArrayList<JabatanModel>();
		List <JabatanPegawaiModel> archive = jabatanPegawaiService.getJabatanPegawaiList();
		for (JabatanPegawaiModel jabatan : archive) {
			if (jabatan.getPegawai().getId().equals(tertua.getId())) {
				archiveJabatan.add(jabatan.getJabatan());
				
			}
		}
		
		String instansi = tertua.getInstansi().getNama();
		String provinsi = tertua.getInstansi().getProvinsi().getNama();
		double gajiPokok =0;
		for (JabatanModel search : archiveJabatan) {
			if (search.getGajiPokok() > gajiPokok) {
				gajiPokok=search.getGajiPokok();
			}
		}
		double tunjangan = tertua.getInstansi().getProvinsi().getPresentaseTunjangan();
		
		double gajiTotal = gajiPokok + ((tunjangan/100)*gajiPokok);
		
		
		
		List <JabatanModel> archiveJabatan2 = new ArrayList<JabatanModel>();
		List <JabatanPegawaiModel> archive2= jabatanPegawaiService.getJabatanPegawaiList();
		for (JabatanPegawaiModel jabatann : archive2) {
			if (jabatann.getPegawai().getId().equals(termuda.getId())) {
				archiveJabatan2.add(jabatann.getJabatan());
				
			}
		}
		String instansi2 = tertua.getInstansi().getNama();
		String provinsi2 = tertua.getInstansi().getProvinsi().getNama();
		double gajiPokok2 =0;
		for (JabatanModel search : archiveJabatan2) {
			if (search.getGajiPokok() > gajiPokok2) {
				gajiPokok2=search.getGajiPokok();
			}
		}
		double tunjangan2 = termuda.getInstansi().getProvinsi().getPresentaseTunjangan();
		
		double gajiTotal2 = gajiPokok2 + ((tunjangan2/100)*gajiPokok2);
		model.addAttribute("pegawai",tertua);
		model.addAttribute("pegawaimuda", termuda);
		
		model.addAttribute("listJabatan", archiveJabatan);
		model.addAttribute("instansi", instansi);
		model.addAttribute("provinsi", provinsi);
		model.addAttribute("gajiTotal", gajiTotal);
		
		model.addAttribute("listJabatan2", archiveJabatan2);
		model.addAttribute("instansi2", instansi2);
		model.addAttribute("provinsi2", provinsi2);
		model.addAttribute("gajiTotal2", gajiTotal2);
		return "tertua-termuda";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String tambahPegawai(Model model) {
		PegawaiModel pegawai = new PegawaiModel();
		model.addAttribute("pegawai", pegawai);
		JabatanModel jabatan = new JabatanModel();
		List <InstansiModel> instansi = instansiService.getInstansiList();
		List <InstansiModel> instansiDefault = new ArrayList<InstansiModel>();
		for (InstansiModel search : instansi) {
			if(search.getProvinsi().getNama().equalsIgnoreCase("ACEH")) {
				instansiDefault.add(search);
			}
		}
		ProvinsiModel provinsi = new ProvinsiModel();
		model.addAttribute("provinsi", provinsi);
		List <ProvinsiModel> listProvinsi = provinsiService.getProvinsiList();
		model.addAttribute("listProvinsi", listProvinsi);
		
		
		List<JabatanModel> listJabatan = jabatanService.getJabatanList();
		model.addAttribute("allJabatan", listJabatan);
		model.addAttribute("jabatan", jabatan);
		return "form-tambah-pegawai";
	}
	
	@RequestMapping (value = "/pegawai/cekInstansi", method = RequestMethod.GET)
	public @ResponseBody Object coba (@RequestParam ("id") String id, Model model) {
		List <InstansiModel> instansi = new ArrayList <InstansiModel>();
		if (id == null) {
			instansi = instansiService.getInstansiList();
			
		}
		
		Long id_id = Long.parseLong(id);
		
		ProvinsiModel provinsi = provinsiService.getProvinsiById(id_id);
		
		instansi = provinsi.getProvInstansi();
		model.addAttribute("allInstansi", instansi);
		Object inst = instansi;
		return inst;
	}
	
	@RequestMapping (value = "/pegawai/cekJabatan", method = RequestMethod.GET)
	public @ResponseBody Object jabatan (Model model) {
		
		List <JabatanModel> jabatan = jabatanService.getJabatanList();
		return jabatan;
	}
	
	@RequestMapping(value = "pegawai/tambah", method = RequestMethod.POST, params= {"save"})
	private String addPegawai(Model model, @RequestParam(value="nama") String nama,
			@RequestParam(value = "tempatLahir") String tempatLahir,
			@RequestParam(value = "tanggalLahir") String tanggalLahir,
			@RequestParam(value = "tahunMasuk") String tahunMasuk,
		//	@RequestParam(value = "provinsi") String idp,
			@RequestParam(value = "instansi") String id, 
			@ModelAttribute PegawaiModel pegawai)
		//	@ModelAttribute JabatanPegawaiModel jabatanPegawai)
		//	@RequestParam(value = "jabatanPegawai") JabatanPegawaiModel jabatanPegawai) 
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date formattedTglLahir =  formatter.parse(tanggalLahir);
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		String tanggalLahirFormat = format.format(formattedTglLahir);
		//java.sql.Date inputTglLahir = new java.sql.Date(formatter.parse(tanggalLahir).getTime());
		String tglLahir = tanggalLahirFormat.substring(0, 4) + ""+ tanggalLahirFormat.substring(6); //ddmmyy
		//System.out.println(tglLahir+" "+tahunMasuk);
		String idn = ""+id+""; //id instansi
		//System.out.println(idn+"kode ins");
		List <PegawaiModel> pegawaii = pegawaiService.getPegawaiList();
		int index = 1;
		String no_urut;
		for (PegawaiModel search : pegawaii) {
			String tgl = formatter.format(search.getTanggalLahir());
			Long idid = Long.parseLong(id);
			if (search.getTahunMasuk().equalsIgnoreCase(tahunMasuk) && tgl.equalsIgnoreCase(tanggalLahir) && search.getInstansi().getId().equals(idid)) {
				System.out.println(search.getNama());
				index++;
			}
		}
		if (index < 10) {
			no_urut = "0" + index;
		}
		else {
			no_urut = ""+ index;
		}
		String nip = idn + tglLahir + tahunMasuk + no_urut;
		pegawai.setNip(nip);
		/*
		for (int i =0;i<pegawai.getPegawaiJabatan().size();i++) {
			pegawai.getPegawaiJabatan().get(0).getJabatan().setId(pegawai.get);;;
			System.out.println(pegawai.getPegawaiJabatan().get(i).getJabatan().getNama());
		}**/
		
		
		//pegawai.getPegawaiJabatan().add(jabatanPegawai);
		for (JabatanPegawaiModel ss : pegawai.getPegawaiJabatan()) {
			ss.setPegawai(pegawai);
		}
		pegawaiService.addPegawai(pegawai);
		model.addAttribute("pegawai",pegawai);
		return "tambah-pegawai-sukses";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
	private String ubahPegawai(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		
		model.addAttribute("currPegawai", pegawai);
		pegawai.getPegawaiJabatan().clear();
		JabatanModel jabatan = new JabatanModel();
		List <InstansiModel> instansi = instansiService.getInstansiList();
		List <InstansiModel> instansiDefault = new ArrayList<InstansiModel>();
		for (InstansiModel search : instansi) {
			if(search.getProvinsi().getNama().equalsIgnoreCase("ACEH")) {
				instansiDefault.add(search);
			}
		}
		ProvinsiModel provinsi = new ProvinsiModel();
		model.addAttribute("provinsi", provinsi);
		List <ProvinsiModel> listProvinsi = provinsiService.getProvinsiList();
		model.addAttribute("listProvinsi", listProvinsi);
		
		
		List<JabatanModel> listJabatan = jabatanService.getJabatanList();
		model.addAttribute("allJabatan", listJabatan);
		model.addAttribute("jabatan", jabatan);
		return "ubah-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST)
	private String ubahJabatanSubmit(@ModelAttribute PegawaiModel pegawai, Model model) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String tanggalLahir = formatter.format(pegawai.getTanggalLahir());
		java.util.Date formattedTglLahir =  formatter.parse(tanggalLahir);
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		String tanggalLahirFormat = format.format(formattedTglLahir);
		//java.sql.Date inputTglLahir = new java.sql.Date(formatter.parse(tanggalLahir).getTime());
		String tglLahir = tanggalLahirFormat.substring(0, 4) + ""+ tanggalLahirFormat.substring(6); //ddmmyy
		//System.out.println(tglLahir+" "+tahunMasuk);
		String idn = ""+pegawai.getInstansi().getId()+""; //id instansi
		//System.out.println(idn+"kode ins");
		List <PegawaiModel> pegawaii = pegawaiService.getPegawaiList();
		int index = 0;
		String no_urut;
		for (PegawaiModel search : pegawaii) {
			String tgl = formatter.format(search.getTanggalLahir());
			//Long idid = Long.parseLong(pegawai.getInstansi().getId());
			if (search.getTahunMasuk().equalsIgnoreCase(pegawai.getTahunMasuk()) && tgl.equalsIgnoreCase(tanggalLahir) && search.getInstansi().getId().equals(pegawai.getInstansi().getId())) {
				System.out.println(search.getNama());
				index++;
			}
		}
		if (index < 10) {
			no_urut = "0" + index;
		}
		else {
			no_urut = ""+ index;
		}
		String nip = idn + tglLahir + pegawai.getTahunMasuk() + no_urut;
		//System.out.println(nip);
		pegawai.setNip(nip);
		//pegawaiService.updatePegawai(pegawai, pegawai.getId());
		for (JabatanPegawaiModel ss : pegawai.getPegawaiJabatan()) {
			ss.setPegawai(pegawai);
		}
		
		pegawaiService.updatePegawai(pegawai, pegawai.getId());
		model.addAttribute("pegawai",pegawai);
		return "ubah-pegawai-sukses";
	}
	/**
	@RequestMapping(value="/pegawai/cari", method=RequestMethod.GET)
	public String showDropdowns(Model model,
								@RequestParam(value="prov", required=false) Long idProv,
								@RequestParam(value="inst", required=false) Long idInst,
								@RequestParam(value="jabs", required=false) Long idJabs) {
		
		
		List<ProvinsiModel> allProv = provinsiService.getProvinsiList();
		model.addAttribute("allProv", allProv);
		if (idProv == null) {
			return "list-provinsi";
		}
		else {
			System.out.println(idProv + "id provinsi");
			List<InstansiModel> allInstansi = instansiService.getInstansiWithProvId(idProv);
			ProvinsiModel provinsi = provinsiService.getProvinsiById(idProv);
			model.addAttribute("provinsi",provinsi);
			model.addAttribute("allInstansi", allInstansi);
			
			if (idInst==null) {
				
				return "list-instansi";
			}
			else {
				System.out.println(idProv + "id provinsi");
				System.out.println(idInst + "id instansi");
				List<JabatanModel> allJabatan = jabatanService.getJabatanList();
				InstansiModel instansi = instansiService.getDetailInstansi(idInst);
				model.addAttribute("inst",instansi);
				model.addAttribute("allJabatan", allJabatan);
				if (idJabs==null) {
					
					return "list-jabatan";
				}
				else {
					System.out.println(idProv + "id provinsi");
					System.out.println(idInst + "id instansi");
					System.out.println(idJabs + "id jabatan");
					JabatanModel jabatan = jabatanService.getJabatanById(idJabs);
					model.addAttribute("jabs", jabatan);
					//List<PegawaiModel> allPegawai = pegawaiService.getPegawaiByProvInstJabs(idProv, idInst, idJabs);
					//model.addAttribute("allPegawai", allPegawai);
					return "cari-pegawai";
				}
			}
		
		
			
		}
	}**/
	
	@RequestMapping (value = "pegawai/cari", method = RequestMethod.GET)
	public String cariPegawaiGet (@RequestParam (value = "idProvinsi", required = false) String idProvinsi,
								@RequestParam (value = "idInstansi", required = false) String idInstansi,
								@RequestParam (value = "idJabatan", required = false) String idJabatan,
								Model model) {
		System.out.println("dibawah");
		
		List <ProvinsiModel> provinsi = provinsiService.getProvinsiList();
		List <InstansiModel> instansi = instansiService.getInstansiList();
		List <JabatanModel> jabatan = jabatanService.getJabatanList();
		
		model.addAttribute("provinsi", provinsi);
		model.addAttribute("instansi", instansi);
		model.addAttribute("jabatan", jabatan);
		
		List <PegawaiModel> pegawai = new ArrayList<PegawaiModel>();
		System.out.println(idProvinsi+ idInstansi+ idJabatan);
		
		if(!(idProvinsi ==null && idInstansi == null  && idJabatan == null)) {
			//1. isi provinsi aja
			if ((idProvinsi != "" && idInstansi == "" && idJabatan == "")) {
				System.out.println("isi provinsi aja");
				ProvinsiModel prov = provinsiService.getProvinsiById(Long.parseLong(idProvinsi));
				List <InstansiModel> inst = prov.getProvInstansi();
				for (InstansiModel i: inst) {
					for (PegawaiModel peg : i.getInstansiPegawai()) {
						pegawai.add(peg);
					}
				}
			}
			
			//2. instansi aja atau  (provinsi dan instansi)
			else if ( (idProvinsi == "" && idInstansi != ""  && idJabatan =="") || (idProvinsi != "" && idInstansi != "" && idJabatan == "" )) {
				System.out.println("instansi aja");
				InstansiModel ins = instansiService.getDetailInstansi(Long.parseLong(idInstansi));
				for (PegawaiModel p:ins.getInstansiPegawai()) {
					pegawai.add(p);
				}
			}
				
			//3. jabatan aja
			else if (idProvinsi =="" && idInstansi == "" && idJabatan != "") {
				System.out.println("jabatan aja");
				JabatanModel jab = jabatanService.getJabatanById(Long.parseLong(idJabatan));
				List <JabatanPegawaiModel> jabPeg = jab.getJabatanPegawai();
				for (JabatanPegawaiModel jp: jabPeg) {
					pegawai.add(jp.getPegawai());
				}
			}
				
			//5. instansi dan jabatan
			else if(idProvinsi == "" && idInstansi != "" && idJabatan != "" ) {
				System.out.println("instansi dan jabatan");
				InstansiModel ins = instansiService.getDetailInstansi(Long.parseLong(idInstansi));
				
				// ambil semua pegawai abis itu filter yang cocok jabatannya
				List <PegawaiModel> pegSementara = ins.getInstansiPegawai();
				for (PegawaiModel ps : pegSementara) {
					List<JabatanPegawaiModel> jabatanPegawaiPS = ps.getPegawaiJabatan();
					for (JabatanPegawaiModel jb : jabatanPegawaiPS) {
						if(jb.getJabatan().getId() == Long.parseLong(idJabatan)) {
							pegawai.add(ps);
						}
							
					}
				}
			}
				
			//6. provinsi dan jabatan
			else if (idProvinsi != "" && idInstansi == "" && idJabatan != "") {
				ProvinsiModel prov = provinsiService.getProvinsiById(Long.parseLong(idProvinsi));
				System.out.println("isi provinsi dan jabatan aja");
				List <InstansiModel> ins = prov.getProvInstansi();
				List <PegawaiModel> allPeg = new ArrayList <PegawaiModel>();
				for (InstansiModel i : ins){
					List <PegawaiModel> pegawaiPerInstansi = i.getInstansiPegawai();
					for (PegawaiModel p:pegawaiPerInstansi) {
						allPeg.add(p);
					}
				}
				
				for (PegawaiModel pp: allPeg) {
					List <JabatanPegawaiModel> allJabatanPerPegawai = pp.getPegawaiJabatan();
					for (JabatanPegawaiModel jpp : allJabatanPerPegawai) {
						if (jpp.getJabatan().getId() == Long.parseLong(idJabatan)) {
							pegawai.add(pp);
						}
					}
				}
			}
			
			//7. ketiga tiganya
			else {
				System.out.println("isi semuanya");
				InstansiModel in = instansiService.getDetailInstansi(Long.parseLong(idInstansi));
				System.out.println(in.getId());
				List <PegawaiModel> pegSeInstansi = in.getInstansiPegawai();
				for (PegawaiModel ps : pegSeInstansi) {
					List <JabatanPegawaiModel> psJabatan = ps.getPegawaiJabatan();
					for (JabatanPegawaiModel jbp : psJabatan) {
						if (jbp.getJabatan().getId() == Long.parseLong(idJabatan)) {
							pegawai.add(ps);
						}
					}
				}
			}
			
			model.addAttribute("pegawai", pegawai);
			return "caripegawai";
		}
		
		else {
			System.out.println("masuk awal");
			model.addAttribute("pegawai", pegawai);
			return "caripegawai";
		}
		
	
	}
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.POST , params = {"removeRow"})
	private String removeRow(@ModelAttribute PegawaiModel pegawai, final BindingResult bindingResult, 
	        final HttpServletRequest req, Model model) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
		pegawai.getPegawaiJabatan().remove(rowId.intValue());
		
		model.addAttribute("pegawai", pegawai);
		return "ubah-pegawai";
	}
	
}
