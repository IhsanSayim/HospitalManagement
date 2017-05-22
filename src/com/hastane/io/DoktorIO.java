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
import com.hastane.myenum.DoktorTipi;

public class DoktorIO {

	public boolean save(List<Doktor> list) {
		PrintStream ps = null;
		try {
			ps = new PrintStream(new FileOutputStream("D:/doktor.txt"));
			for (int i = 0, n = list.size(); i < n; i++) {
				Doktor d = list.get(i);
				ps.println(d.getDoktorId() + "," + d.getDoktorAdi() + "," + d.getDoktorSoyadi() + "," + d.getMaas()
						+ "," + d.getDoktorTipi());

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			ps.close();

		}

		return false;

	}

	public List<Doktor> load() {
		File file = new File("D:/doktor.txt");
		List<Doktor> list = new ArrayList<>();
		if (file.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {
					String[] attributes = line.split(",");
					int id = Integer.parseInt(attributes[0]);
					int maas = Integer.parseInt(attributes[3]);
					DoktorTipi doktortipi = DoktorTipi.getDoktorTipi(attributes[4]);

					Doktor d = new Doktor(id, attributes[1], attributes[2], maas, doktortipi);
					list.add(d);
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
