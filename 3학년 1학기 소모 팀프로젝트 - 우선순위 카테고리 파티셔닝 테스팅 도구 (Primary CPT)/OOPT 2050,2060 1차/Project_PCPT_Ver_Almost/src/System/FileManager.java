package System;

import java.io.*;

import javax.swing.JOptionPane;

public class FileManager {
	public String[] recentFileList={};
	private Specification spec;
	File f;

	public FileManager(Specification s){
		spec =s;
		StringBuilder str = new StringBuilder();
		int c;
		try {
			FileReader fr = new FileReader(".\\Recent.dslab");
			while((c=fr.read())!=-1){
				str.append((char)c);
			}
			recentFileList = str.toString().split("\r\n");

		} catch (FileNotFoundException e) {
			try {
				new File("Recent.dslab").createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int newSpecification(String newFileName){
		f = new File(newFileName+".yzb");
		try {
			if(f.exists()){
				int sel = JOptionPane.showConfirmDialog(null, "이미 있는 파일입니다. 그걸 불러오시겠습니까?", "Exist", 0);
				if(sel==0){
					loadSpecification(f.getAbsolutePath());
				}else{
					return 1;
				}
			}else{
				f.createNewFile();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateRecentList(f.getAbsolutePath());
		return 0;
	}

	public int loadSpecification(String filePath){
		f = new File(filePath);
		StringBuilder s = new StringBuilder();
		int c;
		String[] sub={};
		String[] categories;
		String[] values;
		String[] temp;
		String[] properties;
		StringBuilder ifPropertybuff = new StringBuilder();
		String[] ifPropertysemibuff;
		String[] ifPropertyqbuff;
		String[] ifProperties;

		try {
			FileReader fr = new FileReader(f);
			while((c=fr.read())!=-1){
				s.append((char)c);
			}

			if(s.length()!=0){
				sub =  s.toString().split("\r\n");
			}

			for(int i=0;i<sub.length;i++){
				if(i==0){
					categories = sub[i].split("/");
					for(int j=0;j<categories.length;j++){
						spec.addCategory(categories[j]);
					}
				}
				else{
					values = sub[i].split("/");

					for(int j=0;j<values.length;j++){
						temp = values[j].split("=");
						spec.getCategory(i-1).addRepresentativeValue(temp[0]);
						if(temp[1].length()!=0){
							properties = temp[1].split(",");
							for(int k=0;k<properties.length;k++){
								spec.getCategory(i-1).getRepresentativeValue(j).addProperty(properties[k]);
							}
						}
						ifPropertybuff.append(i-1+""+j+":"+temp[2]+"/");
						spec.getCategory(i-1).getRepresentativeValue(j).setSingleError(Integer.parseInt(temp[3]));
						spec.getCategory(i-1).getRepresentativeValue(j).setPriorityRank(Integer.parseInt(temp[4]));
					}
				}
			}
			
			if(ifPropertybuff.toString().length()!=0){
				ifPropertysemibuff = ifPropertybuff.toString().split("/");
				for(int i=0;i<ifPropertysemibuff.length;i++){
					ifPropertyqbuff = ifPropertysemibuff[i].split(":");
					int cNumber = Integer.parseInt(ifPropertyqbuff[0].substring(0, 1));
					int rNumber = Integer.parseInt(ifPropertyqbuff[0].substring(1));
					if(ifPropertyqbuff.length!=1){
						ifProperties = ifPropertyqbuff[1].split(",");
						for(int j=0;j<ifProperties.length;j++){
							spec.getCategory(cNumber).getRepresentativeValue(rNumber).addIfProperty(ifProperties[j]);
						}	
					}
				}
			}
			
			updateRecentList(filePath);
		} catch (FileNotFoundException e) {
			int sel = JOptionPane.showConfirmDialog(null, "파일이 이동하거나 삭제 되었습니다! 목록에서 제거하시겠습니까?", "404 File Not Found", 0);
			if(sel==0){
				StringBuilder str = new StringBuilder();
				int n;
				int index;
				try {
					FileReader fr = new FileReader(".\\Recent.dslab");
					while((n=fr.read())!=-1){
						str.append((char)n);
					}

					index = str.indexOf(filePath);
					str.delete(index, (index+filePath.length()+2));	

					recentFileList = str.toString().split("\r\n");

					FileWriter fw = new FileWriter("Recent.dslab");
					for(int i=0;i<recentFileList.length;i++){
						fw.write(recentFileList[i]+"\r\n");
					}
					fw.close();
				}catch(IOException err){
					err.printStackTrace();
				}
			}else{

			}
			return 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public int saveSpecification(){
		try {
			int i=0;
			int j=0;
			int k=0;
			FileWriter fw = new FileWriter(f);
			for(i=0;i<spec.getNumberOfCategories()-1;i++){
				fw.write(spec.getCategory(i).getCategoryName()+'/');
			}
			fw.write(spec.getCategory(i).getCategoryName());
			for(i=0;i<spec.getNumberOfCategories();i++){
				fw.write("\r\n");
				for(j=0;j<spec.getCategory(i).getNumberOfRepresentativeValues();j++){
					fw.write(spec.getCategory(i).getRepresentativeValue(j).getRepresentativeValueName()+'=');
					for(k=0;k<spec.getCategory(i).getRepresentativeValue(j).getNumberOfPropertyConstraints()-1;k++){
						fw.write(spec.getCategory(i).getRepresentativeValue(j).getProperty(k).getName()+',');
					}
					if(spec.getCategory(i).getRepresentativeValue(j).getNumberOfPropertyConstraints()!=0){
						fw.write(spec.getCategory(i).getRepresentativeValue(j).getProperty(k).getName());
					}
					fw.write('=');
					for(k=0;k<spec.getCategory(i).getRepresentativeValue(j).getNumberOfIfPropertyConstraints()-1;k++){
						fw.write(spec.getCategory(i).getRepresentativeValue(j).getIfProperty(k).getName()+',');
					}
					if(spec.getCategory(i).getRepresentativeValue(j).getNumberOfIfPropertyConstraints()!=0){
						fw.write(spec.getCategory(i).getRepresentativeValue(j).getIfProperty(k).getName());
					}
					fw.write('=');
					fw.write(Integer.toString(spec.getCategory(i).getRepresentativeValue(j).getSingleError()));
					fw.write('=');
					if(j==spec.getCategory(i).getNumberOfRepresentativeValues()-1){
						fw.write(Integer.toString(spec.getCategory(i).getRepresentativeValue(j).getPriorityRank()));	
					}else{
						fw.write(Integer.toString(spec.getCategory(i).getRepresentativeValue(j).getPriorityRank())+'/');
					}
				}
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int saveSpecification(String filePath){
		try {
			int i=0;
			int j=0;
			int k=0;
			f = new File(filePath);
			FileWriter fw = new FileWriter(f);
			for(i=0;i<spec.getNumberOfCategories()-1;i++){
				fw.write(spec.getCategory(i).getCategoryName()+'/');
			}
			fw.write(spec.getCategory(i).getCategoryName());
			for(i=0;i<spec.getNumberOfCategories();i++){
				fw.write("\r\n");
				for(j=0;j<spec.getCategory(i).getNumberOfRepresentativeValues();j++){
					fw.write(spec.getCategory(i).getRepresentativeValue(j).getRepresentativeValueName()+'=');
					for(k=0;k<spec.getCategory(i).getRepresentativeValue(j).getNumberOfPropertyConstraints()-1;k++){
						fw.write(spec.getCategory(i).getRepresentativeValue(j).getProperty(k).getName()+',');
					}
					if(spec.getCategory(i).getRepresentativeValue(j).getNumberOfPropertyConstraints()!=0){
						fw.write(spec.getCategory(i).getRepresentativeValue(j).getProperty(k).getName());
					}
					fw.write('=');
					for(k=0;k<spec.getCategory(i).getRepresentativeValue(j).getNumberOfIfPropertyConstraints()-1;k++){
						fw.write(spec.getCategory(i).getRepresentativeValue(j).getIfProperty(k).getName()+',');
					}
					if(spec.getCategory(i).getRepresentativeValue(j).getNumberOfIfPropertyConstraints()!=0){
						fw.write(spec.getCategory(i).getRepresentativeValue(j).getIfProperty(k).getName());
					}
					fw.write('=');
					if(j==spec.getCategory(i).getNumberOfRepresentativeValues()-1){
						fw.write(Integer.toString(spec.getCategory(i).getRepresentativeValue(j).getSingleError()));	
					}else{
						fw.write(Integer.toString(spec.getCategory(i).getRepresentativeValue(j).getSingleError())+'/');
					}
				}
			}
			fw.close();
			updateRecentList(f.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int updateRecentList(String filePath){
		StringBuilder str = new StringBuilder();
		int c;
		int index;
		try {
			FileReader fr = new FileReader(".\\Recent.dslab");
			while((c=fr.read())!=-1){
				str.append((char)c);
			}

			index = str.indexOf(filePath);

			if(index==-1){
				str.insert(0,filePath+"\r\n");
			}else{
				str.delete(index, (index+filePath.length()+2));
				str.insert(0, filePath+"\r\n");
			}

			recentFileList = str.toString().split("\r\n");

			FileWriter fw = new FileWriter("Recent.dslab");
			for(int i=0;i<recentFileList.length;i++){
				fw.write(recentFileList[i]+"\r\n");
			}
			fw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getPath(){
		return f.getAbsolutePath();
	}
}
