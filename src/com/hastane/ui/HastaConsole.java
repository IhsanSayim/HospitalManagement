package com.hastane.ui;

import java.text.ParseException;
import java.util.Scanner;

import com.hastane.controller.HastaManager;
import com.hastane.entity.Hasta;
import com.hastane.io.HastaIO;

import Interfaces.Interface1;
import hastanemanagement.Menu;

public class HastaConsole implements Interface1 {
	private HastaManager hm;
	private HastaIO io;
	private Scanner sc;

	public HastaConsole() {
		this.sc = new Scanner(System.in);
		this.io = new HastaIO();
		this.hm = new HastaManager(io.load());

	}
	
    @Override
	public int menu() {

		System.out.println("---HASTA MENU---");
		System.out.println("1. Hasta ekle");
		System.out.println("2. Bütün hastalari getir");
		System.out.println("3. Hasta sil ");
		System.out.println("4. Hasta güncelle ");
		System.out.println("5. Bilgilerini öðrenmek istediðiniz hasta");
		System.out.println("6. Hastaya ilac tahsis etmek icin istenen bilgileri giriniz. ");
		System.out.println("7. Hastalarin bütün tahsis ettiði ilaçlari hastalar ile birlikte göster.");
		System.out.println("8. Ana Menuye donmek icin tiklayiniz. ");
		System.out.println("0. Cikis ");

		int choice = readInt(0, 8);
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
				addHasta();
				mesajgoster();
				break;
			case 2:
				showAll();
				mesajgoster2();
				break;
			case 3:
				removeHasta();
				break;
			case 4:
				updateHasta();
				mesajgoster1();
				break;
			case 5:
				RandevuConsole r = new RandevuConsole();
				r.getHastaList();
				break;
			case 6:
				IlacConsole ic = new IlacConsole();
				System.out.println("Ýlac tahsis etmek istediðiniz hastanin adini girin");
				String add = sc.nextLine();
				Hasta hasta = this.hm.hastaBul(add);
				System.out.println("Ýlac adini girin");
				String ilacadi = sc.nextLine();
				System.out.println("Ýlac miktarini girin");
				int miktar = readInt(0, Integer.MAX_VALUE);
				ic.hastaIlac(hasta, ilacadi, miktar);
				break;
			case 7:
				IlacConsole icc = new IlacConsole();
				icc.showHastaÝlac();
			case 8:
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
		System.out.println("Hasta basariyla eklendi");
	}

	@Override
	public void mesajgoster1() {
		System.out.println("Hasta basariyla güncellendi");

	}
	@Override
	public void mesajgoster2(){
		System.out.println("Hastalar basariyla listelendi.");
	}

	private void getHasta() {
		System.out.println("Bilgilerini gormek istediginiz hastanin adi:  ");
		String ad = sc.nextLine();
		Hasta h = this.hm.hastaBul(ad);
		System.out.println("ID\t\tHastaAdi\tHastaSoyAdi\t\t\t\tEmail\t\tTelefon\t\t\tTCKimlik\t\tCinsiyet");
		System.out.printf("%2d%22s%19s%34s%18s%25s%24s\n", h.getHastaId(), h.getHastaAdi(), h.getHastaSoyadi(),
				h.getEmail(), h.getTelefonNumarasi(), h.getTcKimlik(), h.getCinsiyet());

	}

	private void addHasta() {
		System.out.println("Hasta eklemek için asagida istenen bilgileri sirayla giriniz.");
		System.out.println("Hasta id girin: ");

		int y = 0;
		int z = 0;
		while (z == y) {

			int id = readInt(0, Integer.MAX_VALUE);

			for (int i = 0; i < this.hm.count(); i++) {
				for (int j = this.hm.count(); j > i; j--) {
					Hasta hh = this.hm.getHasta(i);
					if (hh.getHastaId() == id) {
						System.out.println("Hasta id'si kullanýlmýs baska bir id giriniz");
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

		System.out.println("Hasta adini girin: ");
		String name = sc.nextLine();
		System.out.println("Hasta soyadini girin ");
		String soyad = sc.nextLine();
		System.out.println("Hasta email adresini girin ");
		String email = sc.nextLine();
		System.out.println("Hasta telefon numarasini girin ");
		String telefon = sc.nextLine();
		System.out.println("Hasta TC kimlik numarasini girin ");
		String tckimlik = sc.nextLine();
		System.out.println("Hasta cinsiyetini girin ");
		String cinsiyet = sc.nextLine();
		Hasta h = new Hasta(z, name, soyad, email, telefon, tckimlik, cinsiyet);
		this.hm.addHasta(h);
		this.io.save(hm.getListOfHasta());
	}

	private void showAll() {
		System.out.println("--Butun Hastalar--");
		System.out.println("ID\t\tHastaAdi\tHastaSoyAdi\t\t\t\tEmail\t\tTelefon\t\t\tTCKimlik\t\tCinsiyet");
		for (int i = 0; i < this.hm.count(); i++) {
			Hasta h = this.hm.getHasta(i);
			System.out.printf("%2d%22s%19s%34s%18s%25s%24s\n", h.getHastaId(), h.getHastaAdi(), h.getHastaSoyadi(),
					h.getEmail(), h.getTelefonNumarasi(), h.getTcKimlik(), h.getCinsiyet());
		}
	}

	private void removeHasta() {
		System.out.println("Silmek istediginiz hastanin id numarasi: ");
		int id = readInt(0, Integer.MAX_VALUE);
		boolean result = this.hm.removeHasta(id);
		this.io.save(hm.getListOfHasta());
		if (result) {
			System.out.println("Hasta silindi.");
		} else {
			System.out.println("Hasta bulunamadi.");
		}

	}

	private void updateHasta() {
		System.out.println("Güncellemek istediginiz hastanin adi: ");
		String ad = sc.nextLine();

		Hasta h = this.hm.hastaBul(ad);

		System.out.println("Yeni bilgileri sýrayla giriniz.");

		System.out.println("Hasta id girin: ");
		int id = readInt(0, Integer.MAX_VALUE);
		System.out.println("Hasta adini girin: ");
		String name = sc.nextLine();
		System.out.println("Hasta soyadini girin ");
		String soyad = sc.nextLine();
		System.out.println("Hasta email adresini girin ");
		String email = sc.nextLine();
		System.out.println("Hasta telefon numarasini girin ");
		String telefon = sc.nextLine();
		System.out.println("Hasta TC kimlik numarasini girin ");
		String tckimlik = sc.nextLine();
		System.out.println("Hasta cinsiyetini girin ");
		String cinsiyet = sc.nextLine();
		System.out.println();

		h.setHastaId(id);
		h.setHastaAdi(name);
		h.setHastaSoyadi(soyad);
		h.setEmail(email);
		h.setTelefonNumarasi(telefon);
		h.setTcKimlik(tckimlik);
		h.setCinsiyet(cinsiyet);
		System.out.println("Bilgileri deðiþtirilen hastanin yeni bilgileri aþaðýdaki gibidir: ");

		System.out.println("ID\t\tHastaAdi\tHastaSoyAdi\t\t\t\tEmail\t\tTelefon\t\t\tTCKimlik\t\tCinsiyet");
		System.out.printf("%2d%22s%19s%34s%18s%25s%24s\n", h.getHastaId(), h.getHastaAdi(), h.getHastaSoyadi(),
				h.getEmail(), h.getTelefonNumarasi(), h.getTcKimlik(), h.getCinsiyet());
	}

}
