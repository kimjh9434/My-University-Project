package System;

import java.util.LinkedList;

public class Property {
	private String name;
	private LinkedList<String> valueNames;
	
	public Property(){
		this.name = new String();
	}
	
	public Property(String name){
		this.name = new String(name);
		valueNames = new LinkedList<>();
	}
	
	public String getName(){
		return this.name;
	}
	public int setName(String name){
		this.name = name;
		return 0;
	}
	
	public void addName(String valueName)
	{
		this.valueNames.add(valueName);
	}
	
	public boolean isOnly()
	{
		if(this.valueNames.size() == 1)
		{
			return true;
		}
		return false;
	}
	
	public void delName(String valueName)
	{
		this.valueNames.remove(valueName);
	}
}