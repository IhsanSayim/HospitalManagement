package com.hastane.controller;

import java.util.ArrayList;
import java.util.List;

import com.hastane.entity.Hasta;

import Interfaces.Interface2;

public class HastaManager implements Interface2 {

	private List<Hasta> listofHasta;

	public HastaManager() {
		this.listofHasta = new ArrayList<>();
	}

	public HastaManager(List<Hasta> listofHasta) {
		this.listofHasta = listofHasta;
	}

	public List<Hasta> getListOfHasta() {
		return listofHasta;
	}

	public int addHasta(Hasta h) {
		this.listofHasta.add(h);
		return this.listofHasta.size();
	}

	public int count() {
		return this.listofHasta.size();
	}

	public Hasta getHasta(int index) {
		if (index < 0 || index >= count()) {
			return null;
		}
		return this.listofHasta.get(index);
	}

	public Hasta hastaBul(String ad) {
		int index = -1;
		for (int i = 0, n = count(); i < n; i++) {
			if (this.listofHasta.get(i).getHastaAdi().equals(ad)) {
				index = i;
				break;
			}
		}

		return this.listofHasta.get(index);
	}

	public boolean removeHasta(int id) {
		int index = -1;
		for (int i = 0, n = count(); i < n; i++) {
			if (this.listofHasta.get(i).getHastaId() == id) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			this.listofHasta.remove(index);
			return true;
		}
		return false;

	}

}
