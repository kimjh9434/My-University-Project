package Menu;

public class TokyoRamen extends Ramen {

	public TokyoRamen() {
		setDescription("TokyoRamen ");
		setCost(3000);
	}
	
	@Override
	public void selectNoodleType(String a) {
		System.out.println("도쿄라면은 얇은 면이 특징");
		noodleType = "1";
		setDescription(getDescription()+"|면 굵기 선택[1]");
	}

	@Override
	public double cost() {
		return 3000;
	}

}
