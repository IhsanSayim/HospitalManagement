package hastanemanagement;

import java.util.Scanner;

import com.hastane.ui.DoktorConsole;
import com.hastane.ui.HastaConsole;
import com.hastane.ui.IlacConsole;
import com.hastane.ui.RandevuConsole;

public class Menu {
	private Scanner sc;

	public Menu() {
		this.sc = new Scanner(System.in);
	}

	private int menu() {

		System.out.println("---HASTANE MENU---");
		System.out.println("1. Hasta kaydý ve güncelleme");
		System.out.println("2. Doktor kaydý ve güncelleme");
		System.out.println("3. Randevu iþlemleri ");
		System.out.println("4. Ilac Sorgulama ");
		System.out.println("0. Cikis ");

		int choice = readInt(0, 4);
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
				HastaConsole h = new HastaConsole();
				h.start();
				break;
			case 2:
				DoktorConsole d = new DoktorConsole();
				d.start();
				break;
			case 3:
				RandevuConsole r = new RandevuConsole();
				r.start();
				break;
			case 4:
				IlacConsole i=new IlacConsole();
				i.start();
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
}
