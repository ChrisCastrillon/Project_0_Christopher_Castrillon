package com.revature.uis;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainScreenUIs {
	private final String FILE_NAME = "files/welcome.txt";
	private BufferedReader br = null;
	public MainScreenUIs() {
	}
	public void getWelcomeScreen() throws IOException {
		try {
			br = new BufferedReader(new FileReader(FILE_NAME));
			String line = "";
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
		}finally {
			br.close();
		}
	}

}
