package SalesAnalysis;

import java.util.Vector;

import OrderMachine.SalesInfo;


// 결재 유형 - 슈퍼 클래스
public abstract class SalesAnalysis {
	String name;
	Vector<SalesInfo> salesInfos;
	
	public abstract String getName();
    public abstract boolean salesAnalysis();
    public abstract void addSalesInfos(SalesInfo salesInfo);

    @Override
    public String toString() {
        return "product name : " + getName();
    } 
}
