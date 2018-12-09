package PaymentSystem;

public class PaymentFactory {
	public static Payment getPaymentType(PaymentAbstractFactory payment){
		return payment.createPayment();
	}
}
