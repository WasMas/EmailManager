package comfst;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class test {

	public static void main(String[] args) throws IOException {
		String filePath = "D:/Study/JEE/Text/Emails.txt";
		String mh = "waqsdfdfss@gmail.com";
		String meh = mh.substring(mh.indexOf("@") + 1);
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		while ((line = br.readLine()) != null) {
			if (meh.equals(line)) {
				System.out.println("found");
			}
		}
	}
}