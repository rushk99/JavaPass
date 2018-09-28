package javapass;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHandling {

	static void write(String path, byte[] b, boolean append) {
		try {
			FileOutputStream os = new FileOutputStream(path, append);
			os.write(b);
			os.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(FileHandling.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(FileHandling.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	static String[] read(String path) {
		BufferedReader r = null;
		Vector<String> v = new Vector<>();
		try {
			r = new BufferedReader(new FileReader(path));
			String line = null;
			while ((line = r.readLine()) != null) {
				v.add(line);
			}
			return v.toArray(new String[v.size()]);
		} catch (Exception ex) {
			Logger.getLogger(FileHandling.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				r.close();
			} catch (IOException ex) {
				Logger.getLogger(FileHandling.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}
}
