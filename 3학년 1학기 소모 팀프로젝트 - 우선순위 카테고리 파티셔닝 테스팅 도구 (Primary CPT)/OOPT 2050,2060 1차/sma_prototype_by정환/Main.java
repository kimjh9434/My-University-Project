

public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Specification spec = new Specification();
		
		//1단계 category 입력
		spec.addCategory("A");
		spec.addCategory("B");
		spec.addCategory("C");
		//2단계 representative value 입력
		spec.getCategory(0).addRepresentativeValue("a1");
		spec.getCategory(0).addRepresentativeValue("a2");
		
		spec.getCategory(1).addRepresentativeValue("b1");
		spec.getCategory(1).addRepresentativeValue("b2");
		spec.getCategory(1).addRepresentativeValue("b3");
		
		spec.getCategory(2).addRepresentativeValue("c1");
		spec.getCategory(2).addRepresentativeValue("c2");
		//3단계 property 입력
//		spec.getCategory(0).getRepresentativeValue(0).addProperty("p1");
		
		//4단계 if property 입력
//		spec.getCategory(1).getRepresentativeValue(0).addIfProperty(spec.getCategory(0).getRepresentativeValue(0).getProperty(0));
		
		
		//5단계 single error 입력
//		spec.getCategory(1).getRepresentativeValue(1).setSingleError(new Property("single"));
		
		
		//test case generate
		spec.generateTestCase();
		
	}

}
