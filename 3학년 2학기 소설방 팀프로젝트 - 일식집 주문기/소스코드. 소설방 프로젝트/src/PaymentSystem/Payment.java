package PaymentSystem;

// 결재 유형 - 슈퍼 클래스
public abstract class Payment {
	String name;
	
	public abstract String getName();
    public abstract void pay(int totalPrice);

    @Override
    public String toString() {
        return "product name : " + getName();
    }
}
