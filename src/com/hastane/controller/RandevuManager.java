package com.hastane.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hastane.entity.Doktor;
import com.hastane.entity.Hasta;
import com.hastane.entity.Randevu;

import Interfaces.Interface2;

public class RandevuManager implements Interface2 {

	private List<Randevu> listofRandevu;

	public RandevuManager() {
		this.listofRandevu = new ArrayList<>();
	}

	public RandevuManager(List<Randevu> listofRandevu) {
		this.listofRandevu = listofRandevu;
	}

	public List<Randevu> getListOfRandevu() {
		return listofRandevu;
	}

	public int addRandevu(Randevu r) {
		this.listofRandevu.add(r);
		return this.listofRandevu.size();
	}

	public int count() {
		return this.listofRandevu.size();
	}

	public Randevu getRandevu(int index) {
		if (index < 0 || index >= count()) {
			return null;
		}
		return this.listofRandevu.get(index);
	}

	public List<Randevu> doktorRandevuBul(Doktor d) {

		List<Randevu> b = null;
		b = new ArrayList<>();

		int x = d.getDoktorId();
		int index = -1;
		int sayac = 0;
		for (int i = 0, n = count(); i < n; i++) {
			if (this.listofRandevu.get(i).getDoktor().getDoktorId() == x) {
				index = i;
				b.add(this.listofRandevu.get(index));
				sayac++;
			}
		}
		if (sayac == 0) {
			b = null;
		}

		return b;
	}

	public List<Randevu> hastaRandevuBul(Hasta h) {

		List<Randevu> b = null;
		b = new ArrayList<>();

		int x = h.getHastaId();
		int index = -1;
		int sayac = 0;
		for (int i = 0, n = count(); i < n; i++) {
			if (this.listofRandevu.get(i).getHasta().getHastaId() == x) {

				index = i;
				b.add(this.listofRandevu.get(index));
				sayac++;
			}

		}
		if (sayac == 0) {
			b = null;
		}

		return b;
	}

	public Randevu randevuBul(int id) {
		int index = -1;
		for (int i = 0, n = count(); i < n; i++) {
			if (this.listofRandevu.get(i).getRandevuId() == id) {
				index = i;
				break;
			}
		}

		return this.listofRandevu.get(index);
	}

	public boolean removeRandevu(int id) {
		int index = -1;
		for (int i = 0, n = count(); i < n; i++) {
			if (this.listofRandevu.get(i).getRandevuId() == id) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			this.listofRandevu.remove(index);
			return true;
		}
		return false;

	}

	public boolean checkDoktorfree(Doktor d, Date randevuTarihii) {
		int sayac = 0;
		int x = d.getDoktorId();
		for (int i = 0, n = count(); i < n; i++) {

			if ((this.listofRandevu.get(i).getDoktor().getDoktorId() == x)
					&& (this.listofRandevu.get(i).getDate().equals(randevuTarihii))) {
				sayac++;
				break;
			}

		}
		if (sayac > 0)
			return false;
		return true;
	}

}
