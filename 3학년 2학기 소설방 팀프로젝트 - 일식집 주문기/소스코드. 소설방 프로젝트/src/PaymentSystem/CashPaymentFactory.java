package PaymentSystem;

public class CashPaymentFactory implements PaymentAbstractFactory{

	private String name;
    
    public CashPaymentFactory(String name) {
        this.name = name;
    }
	@Override
	public Payment createPayment() {
		return new CashPayment(name);
	}
}