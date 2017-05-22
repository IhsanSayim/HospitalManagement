package com.hastane.ui;

import java.util.Scanner;

import com.hastane.controller.DoktorManager;
import com.hastane.entity.Doktor;
import com.hastane.io.DoktorIO;
import com.hastane.myenum.DoktorTipi;

import Interfaces.Interface1;
import hastanemanagement.Menu;

public class DoktorConsole implements Interface1 {

	private DoktorManager dm;
	private DoktorIO io;
	private Scanner sc;

	public DoktorConsole() {
		this.sc = new Scanner(System.in);
		this.io = new DoktorIO();
		this.dm = new DoktorManager(io.load());
	}

	@Override
	public int menu() {

		System.out.println("---HASTA MENU---");
		System.out.println("1. Doktor ekle");
		System.out.println("2. Bütün doktorlari getir");
		System.out.println("3. Doktor sil ");
		System.out.println("4. Doktor güncelle ");
		System.out.println("5. Bilgilerini öðrenmek istediðiniz doktor");
		System.out.println("6. Doktora ilac tahsis etmek icin istenen bilgileri giriniz.");
		System.out.println("7. Doktorlarin bütün tahsis ettiði ilaçlari doktorlar ile birlikte göster.");
		System.out.println("8. Ana menuye donmek icin tiklayiniz. ");
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
				addDoktor();
				mesajgoster();
				break;
			case 2:
				showAll();
				mesajgoster2();
				break;
			case 3:
				removeDoktor();
				break;
			case 4:
				updateDoktor();
				mesajgoster1();
				break;
			case 5:
				RandevuConsole r = new RandevuConsole();
				r.getDoktorList();
				break;
			case 6:
				IlacConsole ic = new IlacConsole();
				System.out.println("Ýlac tahsis etmek istediðiniz doktorun adini girin");
				String add = sc.nextLine();
				Doktor doktor = this.dm.doktorBul(add);
				System.out.println("Ýlac adini girin");
				String ilacadi = sc.nextLine();
				System.out.println("Ýlac miktarini girin");
				int miktar = readInt(0, Integer.MAX_VALUE);
				ic.doktorIlac(doktor, ilacadi, miktar);
				break;
			case 7:
				IlacConsole icc = new IlacConsole();
				icc.showDoktorÝlac();
			case 8:
				Menu m = new Menu();
				m.start();
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

	private void getDoktor() {
		System.out.println("Bilgilerini gormek istediginiz doktorun adi:  ");
		String ad = sc.nextLine();
		Doktor d = this.dm.doktorBul(ad);
		System.out.println("ID\t\tDoktorAdi\t\tDoktorSoyAdi\t\tDoktorMaasi\t\t\tDoktorTipi");
		System.out.printf("%2s%25s%25s%23s%31s\n", d.getDoktorId(), d.getDoktorAdi(), d.getDoktorSoyadi(), d.getMaas(),
				d.getDoktorTipi());

	}

	@Override
	public void mesajgoster() {
		System.out.println("Doktor basariyla eklendi");
	}

	@Override
	public void mesajgoster1() {
		System.out.println("Doktor basariyla güncellendi");

	}
	@Override
	public void mesajgoster2(){
		System.out.println("Doktorlar basariyla listelendi.");
	}

	private void addDoktor() {
		System.out.println("Doktor eklemek için asagida istenen bilgileri sirayla giriniz.");
		System.out.println("Doktor id girin: ");

		int y = 0;
		int z = 0;
		while (z == y) {

			int id = readInt(0, Integer.MAX_VALUE);

			for (int i = 0; i < this.dm.count(); i++) {
				for (int j = this.dm.count(); j > i; j--) {
					Doktor dd = this.dm.getDoktor(i);
					if (dd.getDoktorId() == id) {
						System.out.println("Doktor id'si kullanýlmýs baska bir id giriniz");
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

		System.out.println("Doktor adini girin: ");
		String name = sc.nextLine();
		System.out.println("Doktor soyadini girin: ");
		String soyad = sc.nextLine();
		System.out.println("Doktor maasini girin: ");
		int maas = readInt(0, Integer.MAX_VALUE);
		System.out.println("Doktor tipini girin (STAJYER, KALICI, ZIYARETEDEN): ");
		String tip = sc.nextLine();
		DoktorTipi tipp = DoktorTipi.getDoktorTipi(tip);

		Doktor d = new Doktor(z, name, soyad, maas, tipp);
		this.dm.addDoktor(d);
		this.io.save(dm.getListOfDoktor());
	}

	private void showAll() {
		System.out.println("--Butun Doktorlar--");
		System.out.println("ID\t\tDoktorAdi\t\tDoktorSoyAdi\t\tDoktorMaasi\t\t\tDoktorTipi");
		for (int i = 0; i < this.dm.count(); i++) {
			Doktor d = this.dm.getDoktor(i);
			System.out.printf("%2s%25s%25s%23s%31s\n", d.getDoktorId(), d.getDoktorAdi(), d.getDoktorSoyadi(),
					d.getMaas(), d.getDoktorTipi());
		}
	}

	private void removeDoktor() {
		System.out.println("Silmek istediginiz doktorun id numarasi: ");
		int id = readInt(0, Integer.MAX_VALUE);
		boolean result = this.dm.removeDoktor(id);
		this.io.save(dm.getListOfDoktor());
		if (result) {
			System.out.println("Doktor silindi.");
		} else {
			System.out.println("Doktor bulunamadi.");
		}

	}

	private void updateDoktor() {
		System.out.println("Güncellemek istediginiz doktorun adi: ");
		String ad = sc.nextLine();

		Doktor d = this.dm.doktorBul(ad);

		System.out.println("Yeni bilgileri sýrayla giriniz.");

		System.out.println("Doktor id girin: ");
		int id = readInt(0, Integer.MAX_VALUE);
		System.out.println("Doktor adini girin: ");
		String name = sc.nextLine();
		System.out.println("Doktor soyadini girin ");
		String soyad = sc.nextLine();
		System.out.println("Doktor maasini girin ");
		int maas = readInt(0, Integer.MAX_VALUE);
		System.out.println("Doktor tipini girin ");
		String tip = sc.nextLine();
		DoktorTipi tipp = DoktorTipi.getDoktorTipi(tip);

		d.setDoktorId(id);
		d.setDoktorAdi(name);
		d.setDoktorSoyadi(soyad);
		d.setMaas(maas);
		d.setDoktorTipi(tipp);

		this.io.save(dm.getListOfDoktor());

		System.out.println("Bilgileri deðiþtirilen doktorun yeni bilgileri aþaðýdaki gibidir: ");

		System.out.println("ID\t\tDoktorAdi\t\tDoktorSoyAdi\t\tDoktorMaasi\t\t\tDoktorTipi");
		System.out.printf("%2s%25s%25s%23s%31s\n", d.getDoktorId(), d.getDoktorAdi(), d.getDoktorSoyadi(), d.getMaas(),
				d.getDoktorTipi());
	}

}
