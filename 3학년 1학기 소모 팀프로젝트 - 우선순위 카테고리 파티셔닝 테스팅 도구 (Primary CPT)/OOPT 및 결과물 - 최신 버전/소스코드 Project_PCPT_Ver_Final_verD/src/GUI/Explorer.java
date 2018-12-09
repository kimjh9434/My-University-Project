package GUI;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.*;
import System.*;

public class Explorer {
	JFileChooser chooser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter(".yzb files","yzb");
	String filePath;
	int ret;
	public Explorer(){
		String userprofile = System.getenv("USERPROFILE");
		chooser.setCurrentDirectory(new File(userprofile+"\\Desktop"));
	}
	
	public int doit(int i, FileManager fm, Specification spec){
		switch(i){
		case 0:
			chooser.setDialogTitle("불러오기");
			chooser.setFileFilter(filter);
			ret = chooser.showOpenDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "선택 안했음", "Alert", JOptionPane.WARNING_MESSAGE);
				return 1;
		    }
			else{
				filePath = chooser.getSelectedFile().getPath();
		    	int ret = fm.loadSpecification(filePath);
		    	if(ret==0){	
		    		new MainFrame(fm,spec).setVisible(true);
		    		return 0;
		    	}else{
		    		return 1;
		    	}
			}
		case 1:
			chooser.setDialogTitle("저장하기");
			chooser.setFileFilter(filter);
			ret = chooser.showSaveDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "선택 안했음", "Alert", JOptionPane.WARNING_MESSAGE);
				return 1;
		    }else{
		    	filePath = chooser.getSelectedFile().getPath();
		    	if(filePath.indexOf('.')==-1){
		    		filePath = new String(filePath+".yzb");
		    	}
		    	int ret = fm.saveSpecification(filePath);
		    	if(ret==0){
		    		return 0;
		    	}else{
		    		return 1;
		    	}
		    }
		default :
			return 1;
		}
	}
}
