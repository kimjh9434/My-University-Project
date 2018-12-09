package SalesAnalysis;

public class SalesAnalysisFactory {
	public static SalesAnalysis getSalesAnalysisType(SalesAnalysisAbstractFactory salesAnalysis){
		return salesAnalysis.createSalesAnalysis();
	}
}
