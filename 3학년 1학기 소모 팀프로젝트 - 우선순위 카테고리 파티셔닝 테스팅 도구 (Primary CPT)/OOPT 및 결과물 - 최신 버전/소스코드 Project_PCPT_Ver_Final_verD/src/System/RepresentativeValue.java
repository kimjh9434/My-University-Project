package System;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class RepresentativeValue {
	private String representativeValueName;
	private ArrayList<Property> propertyConstraint;
	private ArrayList<Property> if_propertyConstraint;
	private int singleErrorConstraint;
	private int priority;
	private Hashtable<String, Property> propertyTable;
	
	//Constructor
	public RepresentativeValue() {
		this.representativeValueName = new String();
		this.propertyConstraint = new ArrayList<Property>();
		this.if_propertyConstraint = new ArrayList<Property>();
		this.priority=1;
	}
	public RepresentativeValue(String representativeValueName, Hashtable<String, Property> pt) {
		this.representativeValueName = new String();
		this.propertyConstraint = new ArrayList<Property>();
		this.if_propertyConstraint = new ArrayList<Property>();
		this.representativeValueName = representativeValueName;
		this.propertyTable = pt;
		this.priority=1;
	}
	//number of property constraints
	public int getNumberOfPropertyConstraints(){
		return propertyConstraint.size();
	}
	//number of property if constraints
	public int getNumberOfIfPropertyConstraints(){
		return if_propertyConstraint.size();
	}
	
	public int setRepresentativeValueName(String representativeValueName){
		if(getNumberOfPropertyConstraints() != 0)
		{
			for(int i = 0; i < getNumberOfPropertyConstraints(); i++)
			{
				getProperty(i).delName(this.getRepresentativeValueName());
				getProperty(i).addName(representativeValueName);
			}
		}
		this.representativeValueName = representativeValueName;
		return 0;
	}
	public String getRepresentativeValueName(){
		return representativeValueName;
	}
	public int setProperty(int propertyIndex, String propertyName){
		for(int i=0;i<propertyConstraint.size();i++){
			if(propertyConstraint.get(i).getName().compareTo(propertyName)==0){
				JOptionPane.showMessageDialog(null, "이름중복!");
				return 1;
			}
		}
		if(getProperty(propertyIndex).isOnly()){
			delProperty(propertyIndex);
			addProperty(propertyName);
		}else{
			int sel = JOptionPane.showConfirmDialog(null, "해당 property가 다수 발견되었습니다! 관련된 모든 property 및 if들을 변경하시겠습니까?", "Choice", 0);
			if(sel == 1)	//하나만 변경
			{
				propertyConstraint.get(propertyIndex).delName(this.representativeValueName);
				propertyConstraint.remove(propertyIndex);
				addProperty(propertyName);
			}
			else if(sel == 0)	//전부 변경
			{
				propertyConstraint.get(propertyIndex).setName(propertyName);
			}else{
				return 0;
			}
		}
		return 0;
	}
	public int addProperty(String propertyName){
		if(propertyTable.containsKey(propertyName))
		{
			if(this.propertyConstraint.contains(propertyTable.get(propertyName)))
			{
				JOptionPane.showMessageDialog(null, "이미 있음!");
				return 1;
			}
			this.propertyConstraint.add(propertyTable.get(propertyName));
			propertyTable.get(propertyName).addName(this.representativeValueName);
			return 0;
		}
		else
		{
			propertyTable.put(propertyName, new Property(propertyName));
			this.propertyConstraint.add(propertyTable.get(propertyName));
			propertyTable.get(propertyName).addName(this.representativeValueName);
			return 0;
		}
	}
	public int delProperty(int propertyIndex){
		if(propertyTable.get(getProperty(propertyIndex).getName()).isOnly()){
			propertyTable.remove(getProperty(propertyIndex).getName());
			propertyConstraint.remove(propertyIndex);
			return 1;
		}else{
			int sel = JOptionPane.showConfirmDialog(null, "해당 property가 다수 발견되었습니다! 관련된 모든 property 및 if들을 제거하시겠습니까?", "Choice", 0);
			if(sel == 1)	//하나만 삭제
			{
				propertyTable.get(getProperty(propertyIndex).getName()).delName(this.representativeValueName);
				
			}
			else if(sel == 0)	//전부 삭제
			{
				propertyTable.remove(getProperty(propertyIndex).getName());
			}else{
				return 0;
			}
			propertyConstraint.remove(propertyIndex);
		}
		return 0;
	}
	public int setIfProperty(int ifPropertyIndex, String ifPropertyName){
		if(propertyTable.get(ifPropertyName)!=null){
			if_propertyConstraint.set(ifPropertyIndex, propertyTable.get(ifPropertyName));
		}else{
			JOptionPane.showMessageDialog(null, "그런 Property는 없습니다.", "실패!!", 0);
			return 1;
		}
		return 0;
	}
	public int addIfProperty(String ifProperty){
		if(this.if_propertyConstraint.contains(propertyTable.get(ifProperty)))
		{
			JOptionPane.showMessageDialog(null, "이미 있습니다", "중복!!", 0);
			return 1;
		}
		if(propertyTable.get(ifProperty) != null){
			this.if_propertyConstraint.add(this.propertyTable.get(ifProperty));
			return 0;
		}else{
			JOptionPane.showMessageDialog(null, "그런 Property는 없습니다.", "실패!!", 0);
			return 2;
		}
	}
	public int delIfProperty(int ifPropertyIndex){
		if_propertyConstraint.remove(ifPropertyIndex);
		return 0;
	}
	public int setSingleError(int singleError){
		singleErrorConstraint = singleError;
		return 0;
	}
	public int setPriorityRank(int priorityRank){
		priority = priorityRank;
		return 0;
	}
	public Property getProperty(int propertyIndex){
		return propertyConstraint.get(propertyIndex);
	}
	public Property getIfProperty(int ifPropertyIndex){
		return if_propertyConstraint.get(ifPropertyIndex);
	}
	public int getSingleError(){
		return singleErrorConstraint;
	}
	public int getPriorityRank(){
		return priority;
	}
}
