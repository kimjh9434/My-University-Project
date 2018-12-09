package Menu;

public class Egg extends RamenDecorator{
	Ramen ramen;
	
	public Egg(Ramen ramen){
		this.ramen = ramen;
		setCost(ramen.getCost()+500);
	}

	
	public String getDescription() {
		return (ramen.getDescription() + ",Egg");
	}

	
	public double cost() {
		return ramen.cost()+500;
	}
}