package com.apap.tugas1.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "provinsi")
public class ProvinsiModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false, unique = true)
	private String nama;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "presentase_tunjangan", nullable=false)
	private Double presentaseTunjangan;
	
	@OneToMany(mappedBy = "provinsi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<InstansiModel> provInstansi;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Double getPresentaseTunjangan() {
		return presentaseTunjangan;
	}

	public void setPresentaseTunjangan(Double presentaseTunjangan) {
		this.presentaseTunjangan = presentaseTunjangan;
	}

	public List<InstansiModel> getProvInstansi() {
		return provInstansi;
	}

	public void setProvInstansi(List<InstansiModel> provInstansi) {
		this.provInstansi = provInstansi;
	}
	
	
}
