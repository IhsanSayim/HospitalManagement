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

import com.hastane.entity.Ilac;

public class IlacIO {

	public boolean save(List<Ilac> list) {
		PrintStream ps = null;
		try {
			ps = new PrintStream(new FileOutputStream("D:/ilac.txt"));
			for (int i = 0, n = list.size(); i < n; i++) {
				Ilac ilac = list.get(i);
				ps.println(ilac.getIlacAdi() + "," + ilac.getMiktar() + "," + ilac.getKod());

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			ps.close();

		}

		return false;

	}

	public List<Ilac> load() {
		File file = new File("D:/ilac.txt");
		List<Ilac> list = new ArrayList<>();
		if (file.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {
					String[] attributes = line.split(",");
					int miktar = Integer.parseInt(attributes[1]);

					Ilac i = new Ilac(attributes[0], miktar, attributes[2]);
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
