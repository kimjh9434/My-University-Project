package Model;

public class UnitCatalog 
{
	String name;
	int mineral;
	int gas;
	int food;
	
	public UnitCatalog(String name, int mineral, int gas, int food) 
	{
		this.name = name;
		this.mineral = mineral;
		this.gas = gas;
		this.food = food;
	}
	
	public String toString()
	{
		return "[유닛명 : "+name+", 필요 미네랄 : "+mineral+", 필요 가스 : "+gas+" 필요 인구 : "+food+"]";
	}
}
