package SalesAnalysis;

import OrderMachine.SalesInfo;

public class SalesAnalysisSystem {

	SalesAnalysis salesAnalysis;

	public void salesAnalysis(SalesInfo salesInfo) {

		//
		if (true) {
			salesAnalysis = SalesAnalysisFactory.getSalesAnalysisType(new InfoSalesAnalysistFactory("제 3의 정보분석 회사"));
		} else {
			// 추후 확장 및 변경가능한 유연한 부분.
		}
	}

}
