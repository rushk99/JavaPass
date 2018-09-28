package javapass;

public class JavaPass {

	public static void main(String[] args) {
		//TODO:
		/*
			* Make GUI
			* FILE handling
		*/
		AES e =  new AES();
		String encrypted = e.encode(e.encrypt("This is the data", "s3cretPassword"));
	}
	
}
