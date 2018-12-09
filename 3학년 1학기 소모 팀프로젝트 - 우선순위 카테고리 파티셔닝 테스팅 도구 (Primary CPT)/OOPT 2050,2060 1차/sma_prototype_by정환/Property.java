
public class Property {
	private String name;
	
	public Property(){
		this.name = new String();
	}
	public Property(String name){
		this.name = new String(name);
	}
	
	public String getName(){
		return this.name;
	}
	public int setName(String name){
		this.name = name;
		return 0;
	}
}
