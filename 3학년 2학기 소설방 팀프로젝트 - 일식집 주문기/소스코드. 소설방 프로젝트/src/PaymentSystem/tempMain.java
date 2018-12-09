package PaymentSystem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class tempMain {

	public static void main(String[] args) {
		
		Payment cashPayment = PaymentFactory.getPaymentType(new CashPaymentFactory("현금"));
		Payment cardPayment = PaymentFactory.getPaymentType(new CardPaymentFactory("카드"));
		
		cashPayment.pay(2000);
		cardPayment.pay(3500);
		
		
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String toDate = transFormat.format(from);
	}

}
