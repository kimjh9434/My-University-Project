package SalesAnalysis;

public class InfoSalesAnalysistFactory implements SalesAnalysisAbstractFactory{

	private String name;
    
    public InfoSalesAnalysistFactory(String name) {
        this.name = name;
    }
	@Override
	public SalesAnalysis createSalesAnalysis() {
		return new InfoSalesAnalysis(name);
	}
}