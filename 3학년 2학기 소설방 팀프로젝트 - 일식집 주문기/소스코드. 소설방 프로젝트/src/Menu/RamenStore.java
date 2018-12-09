package Menu;

public abstract class RamenStore {

	public Ramen orderRamen(String type, String a, String b, String c, String d, String e, String f ) {
		
		Ramen ramen;
		ramen = createRamen(type); 
		ramen.selectNoodleThickness(a);
		ramen.selectNoodleType(b);
		ramen.selectSoupConcentration(c);
		ramen.selectSoupType(d);
		ramen.selectAmountOfSpice(e);
		ramen.selectAmountOfGarlic(f);
		return ramen;
	}
	
	abstract Ramen createRamen(String type); 

}
