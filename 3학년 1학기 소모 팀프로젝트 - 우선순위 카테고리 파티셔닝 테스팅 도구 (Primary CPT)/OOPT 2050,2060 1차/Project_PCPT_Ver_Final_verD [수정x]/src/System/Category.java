package System;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Category {
	private String categoryName;
	private ArrayList<RepresentativeValue> representativeValues;
	private Hashtable<String, Property> pt;
	
	//Constructor
	public Category(){
		this.categoryName = new String();
		this.representativeValues = new ArrayList<RepresentativeValue>();
	}
	public Category(String categoryName, Hashtable<String, Property> pt){
		this.categoryName = new String(categoryName);
		this.representativeValues = new ArrayList<RepresentativeValue>();
		this.pt = pt;
	}
	//number of representativeValues
	public int getNumberOfRepresentativeValues(){
		return representativeValues.size();
	}
	
	
	//methods
	public int setCategoryName(String categoryName){
		this.categoryName = categoryName;
		return 0;
	}
	public String getCategoryName(){
		return this.categoryName;
	}
	public RepresentativeValue getRepresentativeValue(int representativeValueIndex){
		return this.representativeValues.get(representativeValueIndex);
	}
	public int setRepresentativeValue(int representativeValueIndex, String representativeValueName){
		for(int i=0;i<getNumberOfRepresentativeValues();i++){
			if(representativeValues.get(i).getRepresentativeValueName().compareTo(representativeValueName)==0){
				JOptionPane.showMessageDialog(null,"카테고리 내에 이미 같은 이름의 Value가 존재합니다!!");
				return 1;
			}
		}
		representativeValues.get(representativeValueIndex).setRepresentativeValueName(representativeValueName);
		return 0;
	}
	public int addRepresentativeValue(String representativeValueName){
		for(int i=0;i<getNumberOfRepresentativeValues();i++){
			if(representativeValues.get(i).getRepresentativeValueName().compareTo(representativeValueName)==0){
				JOptionPane.showMessageDialog(null,"카테고리 내에 이미 같은 이름의 Value가 존재합니다!!");
				return 1;
			}
		}
		representativeValues.add(new RepresentativeValue(representativeValueName, pt));
		return 0;
	}
	public int delRepresentativeValue(int representativeValueIndex){
		if(representativeValues.get(representativeValueIndex).getNumberOfPropertyConstraints() != 0)
		{
			for(int i = 0; i < representativeValues.get(representativeValueIndex).getNumberOfPropertyConstraints(); i++)
			{
				if(representativeValues.get(representativeValueIndex).getProperty(i).isOnly())
				{
					pt.remove(representativeValues.get(representativeValueIndex).getProperty(i).getName());
				}
				else
				{
					representativeValues.get(representativeValueIndex).getProperty(i).delName(representativeValues.get(representativeValueIndex).getRepresentativeValueName());
				}
			}
			representativeValues.remove(representativeValueIndex);
			return 0;
		}
		representativeValues.remove(representativeValueIndex);
		return 0;
	}
}
