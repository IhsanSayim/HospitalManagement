package com.hastane.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.hastane.controller.DoktorManager;
import com.hastane.controller.HastaManager;
import com.hastane.controller.RandevuManager;
import com.hastane.entity.Doktor;
import com.hastane.entity.Hasta;
import com.hastane.entity.Randevu;
import com.hastane.io.DoktorIO;
import com.hastane.io.HastaIO;
import com.hastane.io.RandevuIO;

import Interfaces.Interface1;
import Interfaces.Interface2;
import hastanemanagement.Menu;

public class RandevuConsole implements Interface1 {

	private RandevuManager rm;
	private HastaManager hm;
	private DoktorManager dm;
	private RandevuIO io;
	private Scanner sc;
	public DoktorIO ioo;
	private HastaIO iooo;

	public RandevuConsole() throws ParseException {
		this.sc = new Scanner(System.in);
		this.io = new RandevuIO();
		this.rm = new RandevuManager(io.load());
		this.ioo = new DoktorIO();
		this.dm = new DoktorManager(ioo.load());
		this.iooo = new HastaIO();
		this.hm = new HastaManager(iooo.load());
	}
	
	@Override
	public int menu() {

		System.out.println("---RANDEVU MENU---");
		System.out.println("1. Randevu ekle");
		System.out.println("2. Bütün randevulari getir");
		System.out.println("3. randevu sil ");
		System.out.println("4. randevu güncelle ");
		System.out.println("5. Bilgilerini öðrenmek istediðiniz randevu");
		System.out.println("6. Ana menuye dönmek için tuslayiniz. ");
		System.out.println("0. Cikis ");

		int choice = readInt(0, 6);
		return choice;
	}

	public void start() throws Exception {
		while (true) {
			int choice = menu();
			switch (choice) {
			case 0:
				System.exit(0);
				break;
			case 1:
				addRandevu();
				mesajgoster();
				break;
			case 2:
				showAll();
				break;
			case 3:
				removeRandevu();
				mesajgoster2();
				break;
			case 4:
				updateRandevu();
				mesajgoster1();
				break;
			case 5:
				getRandevu();
				break;
			case 6:
				Menu m = new Menu();
				m.start();
				break;
			default:
				throw new AssertionError();

			}
		}
	}

	private int readInt(int min, int max) {

		int choice;
		while (true) {
			try {
				choice = Integer.parseInt(sc.nextLine());
				if (choice >= min && choice <= max) {
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return choice;
	}

	@Override
	public void mesajgoster() {
		System.out.println("Randevu basariyla eklendi");
	}

	@Override
	public void mesajgoster1() {
		System.out.println("Randevu basariyla güncellendi");

	}
	@Override
	public void mesajgoster2() {
		System.out.println("Randevu basariyla iptal edildi");

	}


	private void getRandevu() {
		System.out.println("Bilgilerini gormek istediginiz randevunun Id numarasi:  ");
		int idd = readInt(0, Integer.MAX_VALUE);
		Randevu r = this.rm.randevuBul(idd);
		System.out.println("ID\t\tRandevuTarihi\t\t\t\tDoktor\t\t\t\t\t\t\t\t\t\t\t\t\tHasta");
		System.out.println(r.getRandevuId() + "\t\t" + r.getDate() + "\t\t" + r.getDoktor() + "\t\t" + r.getHasta());

	}

	public void getDoktorList() {
		System.out.println("Bilgilerini gormek istediginiz doktorun adi:  ");
		String ad = sc.nextLine();
		Doktor d = this.dm.doktorBul(ad);
		List<Randevu> r = this.rm.doktorRandevuBul(d);
		
		if (r == null) {
			System.out.println("Doktor");
			System.out.println(d);
		} else {
			System.out.println("RandevuID\t\tRandevuTarihi\t\t\tDoktor\t\t\t\t\t\t\t\t\t\t\t\t\tHasta");
			for (int i = 0; i < r.size(); i++) {
				Randevu rr = r.get(i);
				System.out.println(
						rr.getRandevuId() + "\t\t" + rr.getDate() + "\t\t" + rr.getDoktor() + "\t\t" + rr.getHasta());
			}
		}

	}

	public void getHastaList() {
		System.out.println("Bilgilerini gormek istediginiz hastanin adi:  ");
		String ad = sc.nextLine();
		Hasta h = this.hm.hastaBul(ad);
		List<Randevu> r = this.rm.hastaRandevuBul(h);
		
		if (r == null) {
			System.out.println("Hasta");
			System.out.println(h);
		} else {
			System.out.println("RandevuID\t\tRandevuTarihi\t\t\tDoktor\t\t\t\t\t\t\t\t\t\t\t\tHasta");
			for (int i = 0; i < r.size(); i++) {

				Randevu rr = r.get(i);
				System.out.println(
						rr.getRandevuId() + "\t\t" + rr.getDate() + "\t\t" + rr.getDoktor() + "\t\t" + rr.getHasta());

			}
		}

	}

	private void addRandevu() throws Exception {

		System.out.println("Randevu eklemek için asagida istenen bilgileri sirayla giriniz.");
		System.out.println("randevu id girin: ");
		int y = 0;
		int z = 0;
		while (z == y) {

			int id = readInt(0, Integer.MAX_VALUE);

			for (int i = 0; i < this.rm.count(); i++) {
				for (int j = this.rm.count(); j > i; j--) {
					Randevu rr = this.rm.getRandevu(i);
					if (rr.getRandevuId() == id) {
						System.out.println("Randevu id'si kullanýlmýs baska bir id giriniz");
						id = readInt(0, Integer.MAX_VALUE);
						i = 0;

					} else {

						y = id;

					}
				}
			}

			z = y;
			break;

		}

		System.out.println("Randevu tarihi girin");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
		String date = sc.nextLine();
		Date randevuTarihi = formatter.parse(date);

		System.out.println("Doktor adi girin");
		String ad = sc.nextLine();
		Doktor doktor = this.dm.doktorBul(ad);
		if (doktor == null) {
			throw new Exception("Doktor id bos olamaz");
		}

		boolean checkDoktorfree1 = this.rm.checkDoktorfree(doktor, randevuTarihi);

		while (!checkDoktorfree1) {
			System.out.println("Doktor dolu.");

			System.out.println("Randevu tarihi girin");
			SimpleDateFormat formatterr = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
			String datee = sc.nextLine();
			Date randevuTarihii = formatterr.parse(datee);

			System.out.println("Doktor adi girin");
			String addd = sc.nextLine();
			Doktor doktorr = this.dm.doktorBul(addd);
			if (doktor == null) {
				throw new Exception("Doktor id bos olamaz");
			}

			checkDoktorfree1 = this.rm.checkDoktorfree(doktorr, randevuTarihii);
			doktor = doktorr;
			randevuTarihi = randevuTarihii;
		}

		System.out.println("Hasta adi girin");
		String add = sc.nextLine();
		Hasta hasta = this.hm.hastaBul(add);

		if (hasta == null) {
			throw new Exception("Hasta id bos olamaz");
		}

		Randevu r = new Randevu(z, randevuTarihi, doktor, hasta);
		this.rm.addRandevu(r);
		this.io.save(rm.getListOfRandevu());
	}

	private void showAll() {
		System.out.println("--Butun Randevular--");
		System.out.println("ID\t\tRandevuTarihi\t\t\t\tDoktor\t\t\t\t\t\t\t\t\t\t\t\t\tHasta");
		for (int i = 0; i < this.rm.count(); i++) {
			Randevu r = this.rm.getRandevu(i);
			System.out
					.println(r.getRandevuId() + "\t\t" + r.getDate() + "\t\t" + r.getDoktor() + "\t\t" + r.getHasta());
		}
	}

	private void removeRandevu() {
		System.out.println("Silmek istediginiz randevunun id numarasi: ");
		int id = readInt(0, Integer.MAX_VALUE);
		boolean result = this.rm.removeRandevu(id);
		this.io.save(rm.getListOfRandevu());
		if (result) {
			System.out.println("Randevu silindi.");
		} else {
			System.out.println("Randevu bulunamadi.");
		}

	}

	private void updateRandevu() throws Exception {
		System.out.println("Güncellemek istediginiz randevunun id numarasi: ");
		int idd = readInt(0, Integer.MAX_VALUE);
		Randevu r = this.rm.randevuBul(idd);

		System.out.println("Yeni bilgileri sýrayla giriniz.");

		System.out.println("Randevu id girin: ");

		int y = 0;
		int z = 0;
		while (z == y) {

			int id = readInt(0, Integer.MAX_VALUE);

			for (int i = 0; i < this.rm.count(); i++) {
				for (int j = this.rm.count(); j > i; j--) {
					Randevu rr = this.rm.getRandevu(i);
					if (rr.getRandevuId() == id) {
						System.out.println("Randevu id'si kullanýlmýs baska bir id giriniz");
						id = readInt(0, Integer.MAX_VALUE);
						i = 0;

					} else {

						y = id;

					}
				}
			}

			z = y;
			break;

		}
		System.out.println("Randevu tarihi girin: ");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
		String date = sc.nextLine();
		Date randevuTarihi = formatter.parse(date);
		System.out.println("Doktor adi girin");

		Doktor doktor = this.dm.doktorBul(sc.nextLine());

		boolean checkDoktorfree1 = this.rm.checkDoktorfree(doktor, randevuTarihi);

		while (!checkDoktorfree1) {
			System.out.println("Doktor dolu.");

			System.out.println("Randevu tarihi girin");
			SimpleDateFormat formatterr = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
			String datee = sc.nextLine();
			Date randevuTarihii = formatterr.parse(datee);

			System.out.println("Doktor adi girin");
			String addd = sc.nextLine();
			Doktor doktorr = this.dm.doktorBul(addd);
			if (doktor == null) {
				throw new Exception("Doktor id bos olamaz");
			}

			checkDoktorfree1 = this.rm.checkDoktorfree(doktorr, randevuTarihii);
			doktor = doktorr;
			randevuTarihi = randevuTarihii;
		}

		System.out.println("Hasta adi girin");
		Hasta h = this.hm.hastaBul(sc.nextLine());

		r.setRandevuId(z);
		r.setDate(randevuTarihi);
		r.setDoktor(doktor);
		r.setHasta(h);

		this.io.save(rm.getListOfRandevu());

		System.out.println("Bilgileri deðiþtirilen hastanin yeni bilgileri aþaðýdaki gibidir: ");

		System.out.println("ID\t\tRandevuTarihi\t\t\t\tDoktor\t\t\t\t\t\t\t\t\t\t\t\t\tHasta");
		System.out.println(r.getRandevuId() + "\t\t" + r.getDate() + "\t\t" + r.getDoktor() + "\t\t" + r.getHasta());
	}

}
