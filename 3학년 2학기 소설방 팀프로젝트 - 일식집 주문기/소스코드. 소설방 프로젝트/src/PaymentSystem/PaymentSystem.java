package PaymentSystem;


public class PaymentSystem {

	Payment payment;

	public void pay(int totalPrice, int payType) {

		// 1이면 카드, 2면 현금
		if (payType == 1) {
			payment = PaymentFactory.getPaymentType(new CardPaymentFactory("카드 결재기"));
		} else if (payType == 2) {
			payment = PaymentFactory.getPaymentType(new CashPaymentFactory("현금 결재기"));
		}
		
		payment.pay(totalPrice);

	}

}
