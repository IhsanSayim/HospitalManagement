package com.hastane.controller;

import java.util.ArrayList;
import java.util.List;

import com.hastane.entity.Ilac;

import Interfaces.Interface2;

public class IlacManager implements Interface2 {

	private List<Ilac> listofIlac;

	public IlacManager() {
		this.listofIlac = new ArrayList<>();
	}

	public IlacManager(List<Ilac> listofIlac) {
		this.listofIlac = listofIlac;
	}

	public List<Ilac> getListOfIlac() {
		return listofIlac;
	}

	public int addIlac(Ilac i) {
		this.listofIlac.add(i);
		return this.listofIlac.size();
	}

	public int count() {
		return this.listofIlac.size();
	}

	public Ilac getIlac(int index) {
		if (index < 0 || index >= count()) {
			return null;
		}
		return this.listofIlac.get(index);
	}

	public Ilac ilacBul(String ad) {
		int index = -1;
		for (int i = 0, n = count(); i < n; i++) {
			if (this.listofIlac.get(i).getIlacAdi().equals(ad)) {
				index = i;
				break;
			}
		}

		return this.listofIlac.get(index);
	}

	public boolean ilacBull(String ad) {
		int index = -1;
		for (int i = 0, n = count(); i < n; i++) {
			if (this.listofIlac.get(i).getIlacAdi().equals(ad)) {
				index = i;
				break;
			}
		}

		if (index != -1)
			return true;
		return false;
	}

	public boolean removeIlac(String ad) {
		int index = -1;
		for (int i = 0, n = count(); i < n; i++) {
			if (this.listofIlac.get(i).getIlacAdi().equals(ad)) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			this.listofIlac.remove(index);
			return true;
		}
		return false;

	}
	
	public boolean ilacBulll(String kod) {
		int index = -1;
		for (int i = 0, n = count(); i < n; i++) {
			if (this.listofIlac.get(i).getKod().equals(kod)) {
				index = i;
				break;
			}
		}

		if (index != -1)
			return true;
		return false;
	}
	
	


}
