package Menu;

public abstract class KyuDong extends Menu{
	
	String Size;
	String amountOfGarlic;
	
	String description = "노오멀한  규동";

	public void selectSize(String a){
		Size = a;
		setDescription(getDescription()+"|전체양 선택["+a+"]");
	}
	public void selectGarlicAmount(String a){
		amountOfGarlic = a;
		setDescription(getDescription()+"|마늘의 양 선택["+a+"]");
	}
	  
	public abstract double cost();
}
