package com.hastane.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.hastane.entity.Doktor;
import com.hastane.entity.Hasta;
import com.hastane.entity.Ilac;
import com.hastane.myenum.DoktorTipi;

public class DoktorIlacIO {

	public boolean save(List<Ilac> list) {
		PrintStream ps = null;
		try {
			ps = new PrintStream(new FileOutputStream("D:/doktorilac.txt"));
			for (int i = 0, n = list.size(); i < n; i++) {
				Ilac ilac = list.get(i);
				Doktor d = ilac.getDoktor();

				ps.println(ilac.getIlacAdi() + "," + ilac.getMiktar() + "," + ilac.getKod() + "," + d.getDoktorId()
						+ "," + d.getDoktorAdi() + "," + d.getDoktorSoyadi() + "," + d.getMaas() + ","
						+ d.getDoktorTipi());

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			ps.close();

		}

		return false;

	}

	public List<Ilac> load() {
		File file = new File("D:/doktorilac.txt");
		List<Ilac> list = new ArrayList<>();
		if (file.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {
					String[] attributes = line.split(",");
					int miktar = Integer.parseInt(attributes[1]);

					Doktor d = new Doktor();
					int x = Integer.parseInt(attributes[3]);
					d.setDoktorId(x);
					d.setDoktorAdi(attributes[4]);
					d.setDoktorSoyadi(attributes[5]);
					int y = Integer.parseInt(attributes[6]);
					d.setMaas(y);
					DoktorTipi doktortipi = DoktorTipi.getDoktorTipi(attributes[7]);
					d.setDoktorTipi(doktortipi);

					Ilac i = new Ilac(attributes[0], miktar, attributes[2], d);
					list.add(i);

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
