package Menu;

public class KonkukRamenStore extends RamenStore {
	@Override
	Ramen createRamen(String type) {
		Ramen ramen = null;
		if (type.equals("Tokyo"))
			ramen = new TokyoRamen();
		else if (type.equals("Nagasaki"))
			ramen = new NagasakiRamen();
		else if(type.equals("Hokkaido")) 
			 ramen = new HokkaidoRamen();
		return ramen;
	}
}
