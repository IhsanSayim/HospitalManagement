package com.hastane.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.hastane.entity.Doktor;
import com.hastane.entity.Hasta;
import com.hastane.entity.Randevu;
import com.hastane.myenum.DoktorTipi;

public class RandevuIO {
	public boolean save(List<Randevu> list) {
		PrintStream ps = null;
		try {
			ps = new PrintStream(new FileOutputStream("D:/randevu.txt"));
			for (int i = 0, n = list.size(); i < n; i++) {
				Randevu r = list.get(i);
				Doktor d = r.getDoktor();
				Hasta h = r.getHasta();
				ps.println(r.getRandevuId() + "," + r.getDate() + "," + d.getDoktorId() + "," + d.getDoktorAdi() + ","
						+ d.getDoktorSoyadi() + "," + d.getMaas() + "," + d.getDoktorTipi() + "," + h.getHastaId() + ","
						+ h.getHastaAdi() + "," + h.getHastaSoyadi() + "," + h.getEmail() + "," + h.getTelefonNumarasi()
						+ "," + h.getTcKimlik() + "," + h.getCinsiyet());

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			ps.close();

		}

		return false;

	}

	public List<Randevu> load() throws ParseException {
		File file = new File("D:/randevu.txt");
		List<Randevu> list = new ArrayList<>();
		if (file.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {
					String[] attributes = line.split(",");
					int id = Integer.parseInt(attributes[0]);
					SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.ENGLISH);
					String date = attributes[1];
					Date randevuTarihi = formatter.parse(date);

					Doktor d = new Doktor();
					int y = Integer.parseInt(attributes[2]);
					d.setDoktorId(y);
					d.setDoktorAdi(attributes[3]);
					d.setDoktorSoyadi(attributes[4]);
					int z = Integer.parseInt(attributes[5]);
					d.setMaas(z);
					DoktorTipi dd = DoktorTipi.getDoktorTipi(attributes[6]);
					d.setDoktorTipi(dd);

					Hasta h = new Hasta();
					int x = Integer.parseInt(attributes[7]);
					h.setHastaId(x);
					h.setHastaAdi(attributes[8]);
					h.setHastaSoyadi(attributes[9]);
					h.setEmail(attributes[10]);
					h.setTelefonNumarasi(attributes[11]);
					h.setTcKimlik(attributes[12]);
					h.setCinsiyet(attributes[13]);

					Randevu r = new Randevu(id, randevuTarihi, d, h);
					list.add(r);

				}
				reader.close();
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return list;

	}

}
