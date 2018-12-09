package PaymentSystem;

public class CardPaymentFactory implements PaymentAbstractFactory{

	private String name;
    
    public CardPaymentFactory(String name) {
        this.name = name;
    }
	@Override
	public Payment createPayment() {
		return new CardPayment(name);
	}
}
