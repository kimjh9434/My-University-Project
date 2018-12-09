package PaymentSystem;

public class CardPayment extends Payment{

	private String name;

    public CardPayment (String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

	@Override
	public void pay(int totalPrice) {
		System.out.println("카드로 결재하는 과정이 진행중");
		System.out.println("총 " + totalPrice + "원이 성공적으로 결제되었습니다.");
	}
}
