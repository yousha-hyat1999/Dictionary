import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DictioClass {
	private static Scanner sc;
	private static PrintWriter pw;
	private static int entries = 0;

	private static ArrayList<String> perfectArray = null;

	public static void readText() {
		perfectArray = new ArrayList<String>();
		int phys = 0;
		Character letter = 'A';
		int index = 0;
		String str = "";

		System.out.println(
				"*********************Welcome to the sub-dictionary creator**********************************");

		try {

			sc = new Scanner(new FileInputStream("PersonOfTheCentury.txt"));
			while (sc.hasNext()) {
				str = sc.next();

				if (str.contains("’")) {
					index = str.indexOf("’");
					str = str.substring(0, (index));
					str = str.trim();
				}

				str = str.replaceAll("^\\d+|\\d+$", "*");
				if (str.contains("*"))
					continue;

				if (str.length() == 1) {
					if (!str.toUpperCase().contains("A") && (!str.toUpperCase().contains("I")))
						continue;
				}

				if (!perfectArray.contains(str.replaceAll("[^a-zA-Z ]", "").toUpperCase())) {

					perfectArray.add(str.replaceAll("[^a-zA-Z ]", "").toUpperCase());
					entries++;

				}

			}

		} catch (FileNotFoundException e) {
			System.out.println("No files found!! :(");
		}

		perfectArray.trimToSize();
		perfectArray.sort(null);
		phys = perfectArray.indexOf("MC");
		perfectArray.set(phys, "MC²");

		for (int i = 0; i < 26; i++) {

			perfectArray.add(letter.toString());
			perfectArray.sort(null);

			letter++;
		}

		letter = 'A';
		for (int i = 0; i < 26; i++) {
			index = perfectArray.indexOf(letter.toString());

			perfectArray.add(index + 1, "==");
			perfectArray.add(index, "");
			letter++;
		}

		for (int i = 0, j = 0; i < perfectArray.size(); i++, j++) {

			if (perfectArray.get(j) == "==") {

				if (perfectArray.get(j + 1) == "") {
					perfectArray.remove(j);
					perfectArray.remove(j - 1);
					perfectArray.remove(j - 2);

				}
			}
		}

	}

	public static void write() {
		String str1 = "The document produced this sub-dictionary, which includes " + entries + " entries.";

		try {
			pw = new PrintWriter(new FileOutputStream("sub-dictionary"));
			pw.print(str1 + "\n");
			pw.println();
			for (String s : perfectArray) {
				pw.println(s);
//				pw.println("\n");
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file is corrupted");
		} finally {
			System.out.println("\nHope you have enjoyed using the sub-dictionary creator");
			pw.close();
		}

	}
}
