package Menu;

public abstract class Ramen extends Menu{
	String noodleThickness;
	String noodleType;
	String soupConcentration;
	String soupType;
	String amountOfSpice;
	String amountOfGarlic;
	
	String description = "노오멀한 라면";

	public void selectNoodleThickness(String a) {
		noodleThickness = a;
		setDescription(getDescription()+"|면 굵기 선택["+a+"]");
	}
	
	public void selectNoodleType(String a) {
		noodleType = a;
		setDescription(getDescription()+"|면 종류 선택["+a+"]");
	}

	public void selectSoupConcentration(String a) {
		soupConcentration = a;
		setDescription(getDescription()+"|국물 농도 선택["+a+"]");
	}
	
	public void selectSoupType(String a) {
		soupType = a;
		setDescription(getDescription()+"|국물 종류 선택["+a+"]");
	}
	
	public void selectAmountOfSpice(String a) {
		amountOfSpice = a;
		setDescription(getDescription()+"|양념의 양 선택["+a+"]");
	}
	
	public void selectAmountOfGarlic(String a) {
		amountOfGarlic = a;
		setDescription(getDescription()+"|마늘의 양 선택["+a+"]");
	}
	
	public abstract double cost();
}
