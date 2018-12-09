package Model;

public class BuildingCatalog 
{
	String name;
	int mineral;
	int gas;
	
	public BuildingCatalog(String name, int mineral, int gas) 
	{
		this.name = name;
		this.mineral = mineral;
		this.gas = gas;
	}
	
	public String toString()
	{
		return "[건물명 : "+name+", 필요 미네랄 : "+mineral+", 필요 가스 : "+gas+"]";
	}
}
