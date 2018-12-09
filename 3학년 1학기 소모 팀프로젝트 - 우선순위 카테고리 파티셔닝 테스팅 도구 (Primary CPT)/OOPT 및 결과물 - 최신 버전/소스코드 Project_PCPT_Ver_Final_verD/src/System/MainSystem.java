package System;

import javax.swing.UIManager;
import GUI.FirstFrame;

public class MainSystem {
	public static void main(String args[]) {
		Specification spec = new Specification();
		FileManager fm = new FileManager(spec);
		
		try{
			UIManager.setLookAndFeel(new com.jtattoo.plaf.acryl.AcrylLookAndFeel());
		}catch(Exception e){
			e.printStackTrace();
		}
		new FirstFrame(fm,spec).setVisible(true);
	}
}
