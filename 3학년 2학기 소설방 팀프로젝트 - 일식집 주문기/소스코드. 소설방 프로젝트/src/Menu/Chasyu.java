package Menu;

public class Chasyu extends RamenDecorator{
	Ramen ramen;
	
	public Chasyu(Ramen ramen){
		this.ramen = ramen;
		setCost(ramen.getCost()+800);
	}
	
	public String getDescription() {
		return (ramen.getDescription() + ",Chasyu");
	}
	
	public double cost() {
		return ramen.cost()+800;
	}
}