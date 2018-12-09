package OrderMachine;

import SalesAnalysis.InfoSalesAnalysistFactory;
import SalesAnalysis.SalesAnalysis;
import SalesAnalysis.SalesAnalysisFactory;

public class OrderMachine {
	
	public Order order;
	static public SalesAnalysis salesAnalysis;
	
	public OrderMachine(){
		order = new Order();
		salesAnalysis = SalesAnalysisFactory.getSalesAnalysisType(new InfoSalesAnalysistFactory("제 3의 분석회사"));
	}
	
	// 주문하기
	public void order(String o){
		// 주문을 하고, 
//		SalesInfo salesInfo = order.a(o);
		
		// 판매정보를 받아서, 판매분석에 해당 정보를 추가한다.
//		salesAnalysis.addSalesInfos(salesInfo);
	}
	
	
	// 정산 [판매 정보 분석하기]
	public boolean salesAnalysis(){
		return salesAnalysis.salesAnalysis();
	}
}
