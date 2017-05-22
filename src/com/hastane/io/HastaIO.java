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

import com.hastane.entity.Hasta;

public class HastaIO {

	public boolean save(List<Hasta> list) {
		PrintStream ps = null;
		try {
			ps = new PrintStream(new FileOutputStream("D:/hasta.txt"));
			for (int i = 0, n = list.size(); i < n; i++) {
				Hasta h = list.get(i);
				ps.println(h.getHastaId() + "," + h.getHastaAdi() + "," + h.getHastaSoyadi() + "," + h.getEmail() + ","
						+ h.getTelefonNumarasi() + "," + h.getTcKimlik() + "," + h.getCinsiyet());

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			ps.close();

		}

		return false;

	}

	public List<Hasta> load() {
		File file = new File("D:/hasta.txt");
		List<Hasta> list = new ArrayList<>();
		if (file.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {
					String[] attributes = line.split(",");
					int id = Integer.parseInt(attributes[0]);

					Hasta h = new Hasta(id, attributes[1], attributes[2], attributes[3], attributes[4], attributes[5],
							attributes[6]);
					list.add(h);
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
