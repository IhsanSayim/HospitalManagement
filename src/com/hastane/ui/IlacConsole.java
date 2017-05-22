package com.hastane.ui;

import java.util.Scanner;

import com.hastane.controller.DoktorIlacManager;
import com.hastane.controller.HastaIlacManager;
import com.hastane.controller.HastaManager;
import com.hastane.controller.IlacManager;
import com.hastane.entity.Doktor;
import com.hastane.entity.Hasta;
import com.hastane.entity.Ilac;
import com.hastane.io.DoktorIlacIO;
import com.hastane.io.HastaIO;
import com.hastane.io.HastaIlacIO;
import com.hastane.io.IlacIO;

import Interfaces.Interface1;
import hastanemanagement.Menu;

public class IlacConsole implements Interface1 {

	private IlacManager im;
	private IlacIO io;
	private Scanner sc;
	private HastaIO ioo;
	private HastaManager hm;
	private HastaIlacManager him;
	private HastaIlacIO iooo;
	private DoktorIlacIO ioooo;
	private DoktorIlacManager dim;

	public IlacConsole() {
		this.sc = new Scanner(System.in);
		this.io = new IlacIO();
		this.im = new IlacManager(io.load());
		this.ioo = new HastaIO();
		this.hm = new HastaManager(ioo.load());
		this.iooo = new HastaIlacIO();
		this.him = new HastaIlacManager(iooo.load());
		this.ioooo = new DoktorIlacIO();
		this.dim = new DoktorIlacManager(this.ioooo.load());

	}
    @Override
	public int menu() {

		System.out.println("---ILAC MENU---");
		System.out.println("1. Ilac ekle");
		System.out.println("2. Stoktaki b¸t¸n ilaclari getir");
		System.out.println("3. Ilac sil ");
		System.out.println("4. Ilac g¸ncelle ");
		System.out.println("5. Bilgilerini ˆrenmek istediiniz ilac");
		System.out.println("6. Ana Menuye donmek icin tiklayiniz. ");
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
				addIlac();
				mesajgoster();
				break;
			case 2:
				showAll();
				mesajgoster2();
				break;
			case 3:
				removeIlac();
				break;
			case 4:
				updateIlac();
				mesajgoster1();
				break;
			case 5:
				getIlac();
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

	public void hastaIlac(Hasta hasta, String ilacadi, int miktar) {
		Ilac i = this.im.ilacBul(ilacadi);
		Ilac ii = null;
		if ((i.getMiktar() > 0) && (i.getMiktar() > miktar)) {
			String kod = i.getKod();
			int y = i.getMiktar() - miktar;

			boolean result = this.im.removeIlac(ilacadi);
			Ilac b = new Ilac(ilacadi, y, kod);

			this.im.addIlac(b);
			this.io.save(im.getListOfIlac());

			ii = new Ilac(ilacadi, miktar, kod, hasta);

		} else {
			System.out.println("›lac sayisi yetersizdir.");
		}
		this.him.addIlac(ii);
		this.iooo.save(him.getListOfIlac());

	}

	public void doktorIlac(Doktor doktor, String ilacadi, int miktar) {
		Ilac i = this.im.ilacBul(ilacadi);
		Ilac ii = null;
		if ((i.getMiktar() > 0) && (i.getMiktar() > miktar)) {
			String kod = i.getKod();
			int y = i.getMiktar() - miktar;

			boolean result = this.im.removeIlac(ilacadi);
			Ilac b = new Ilac(ilacadi, y, kod);

			this.im.addIlac(b);
			this.io.save(im.getListOfIlac());

			ii = new Ilac(ilacadi, miktar, kod, doktor);

		} else {
			System.out.println("›lac sayisi yetersizdir.");
		}
		this.dim.addIlac(ii);
		this.ioooo.save(dim.getListOfIlac());

	}

	public void showHasta›lac() {
		System.out.println("--Butun Hasta ve Tahsis Edilen Ilaclar--");
		System.out.println(
				"IlacAdi\t\tIlacMiktari\t\tIlacKodu\tHastaId\t\tHastaAdi\tHastaSoyadi\t\t\tHastaEmail\t\tHastaTelefon\t\tHastaTCKimlik\t\tHastaCinsiyeti");
		for (int i = 0; i < this.him.count(); i++) {
			Ilac ii = this.him.getIlac(i);
			Hasta h = ii.getHasta();
			System.out.printf("%2s%22s%20s%15s%18s%18s%35s%22s%25s%25s\n", ii.getIlacAdi(), ii.getMiktar(), ii.getKod(),
					h.getHastaId(), h.getHastaAdi(), h.getHastaSoyadi(), h.getEmail(), h.getTelefonNumarasi(),
					h.getTcKimlik(), h.getCinsiyet());
		}

	}

	public void showDoktor›lac() {
		System.out.println("--Butun Doktor ve Tahsis Edilen Ilaclar--");
		System.out.println(
				"IlacAdi\t\tIlacMiktari\t\tIlacKodu\tDoktorID\tDoktorAdi\tDoktorSoyadi\t\tDoktorMaas\t\tDoktorTipi");
		for (int i = 0; i < this.dim.count(); i++) {
			Ilac ii = this.dim.getIlac(i);
			Doktor d = ii.getDoktor();
			System.out.printf("%2s%22s%20s%16s%18s%18s%23s%23s\n", ii.getIlacAdi(), ii.getMiktar(), ii.getKod(),
					d.getDoktorId(), d.getDoktorAdi(), d.getDoktorSoyadi(), d.getMaas(), d.getDoktorTipi());
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
		System.out.println("Ilac basariyla eklendi");
	}

	@Override
	public void mesajgoster1() {
		System.out.println("Ilac basariyla g¸ncellendi");

	}
	
	@Override
	public void mesajgoster2(){
		System.out.println("›laclar basariyla listelendi.");
	}

	private void getIlac() {
		System.out.println("Bilgilerini gormek istediginiz ilacin adi:  ");
		String ad = sc.nextLine();
		Ilac i = this.im.ilacBul(ad);
		System.out.println("IlacAdi\t\t\tIlacMiktari\t\tIlacKodu");
		System.out.printf("%2s%29s%22s\n", i.getIlacAdi(), i.getMiktar(), i.getKod());

	}

	private void addIlac() {

		System.out.println("Ilac eklemek iÁin asagida istenen bilgileri sirayla giriniz.");
		System.out.println("Ilac adini girin: ");
		String name = sc.nextLine();
		Boolean b = this.im.ilacBull(name);
		System.out.println("Ilac miktarini girin ");
		int miktar = readInt(0, Integer.MAX_VALUE);
		System.out.println("Ilac kodunu girin ");
		String kod = sc.nextLine();
		if (b) {

			Ilac ii = this.im.ilacBul(name);
			int x = ii.getMiktar();
			String kodd = ii.getKod();
			if (kodd.equals(kod)) {
				int z = x + miktar;
				boolean result = this.im.removeIlac(name);
				Ilac bb = new Ilac(name, z, kodd);
				this.im.addIlac(bb);
				this.io.save(im.getListOfIlac());
			} else {
				Ilac i = new Ilac(name, miktar, kod);
				this.im.addIlac(i);
				this.io.save(im.getListOfIlac());
			}

		} else {
			Ilac i = new Ilac(name, miktar, kod);
			this.im.addIlac(i);
			this.io.save(im.getListOfIlac());
		}
	}

	private void showAll() {
		System.out.println("--Butun Ilaclar--");
		System.out.println("IlacAdi\t\t\tIlacMiktari\t\tIlacKodu");
		for (int i = 0; i < this.im.count(); i++) {
			Ilac ii = this.im.getIlac(i);
			System.out.printf("%2s%29s%22s\n", ii.getIlacAdi(), ii.getMiktar(), ii.getKod());
		}
	}

	private void removeIlac() {
		System.out.println("Silmek istediginiz ilacin adi ve kodu veritabaninda bulunan bir ilaÁ ile ayn˝ olmalidir. ");
		System.out.println("Silmek istediginiz ilacin adi");
		String ad = sc.nextLine();
		System.out.println("Silmek istediginiz ilacin miktari");
		int miktar = readInt(0, Integer.MAX_VALUE);
		System.out.println("Silmek istediginiz ilacin kodunu girin ");
		String kod = sc.nextLine();
		Boolean b = this.im.ilacBull(ad);
		if (b) {

			Ilac ii = this.im.ilacBul(ad);
			int x = ii.getMiktar();
			String kodd = ii.getKod();
			if (kodd.equals(kod)) {
				int z = x - miktar;
				boolean result = this.im.removeIlac(ad);
				Ilac bb = new Ilac(ad, z, kodd);
				this.im.addIlac(bb);
				this.io.save(im.getListOfIlac());
			} else {
				System.out.println("›lac bulunamadi.");

			}

		} else {
			System.out.println("ilac bulunamadi");
		}

	}

	private void updateIlac() {
		System.out.println("G¸ncellemek istediginiz ilacin adi: ");
		String ad = sc.nextLine();

		Ilac i = this.im.ilacBul(ad);

		System.out.println("Yeni bilgileri s˝rayla giriniz.");

		System.out.println("Ilac eklemek iÁin asagida istenen bilgileri sirayla giriniz.");
		System.out.println("Ilac adini girin: ");
		String name = sc.nextLine();
		System.out.println("Ilac miktarini girin ");
		int miktar = readInt(0, Integer.MAX_VALUE);
		System.out.println("Ilac kodunu girin ");
		String kod = sc.nextLine();
		System.out.println();

		i.setIlacAdi(name);
		i.setMiktar(miktar);
		i.setKod(kod);
		this.io.save(im.getListOfIlac());

		System.out.println("Bilgileri dei˛tirilen ilacin yeni bilgileri a˛a˝daki gibidir: ");

		System.out.println("IlacAdi\t\t\tIlacMiktari\t\tIlacKodu");
		System.out.printf("%2s%29s%22s\n", i.getIlacAdi(), i.getMiktar(), i.getKod());
	}

}
