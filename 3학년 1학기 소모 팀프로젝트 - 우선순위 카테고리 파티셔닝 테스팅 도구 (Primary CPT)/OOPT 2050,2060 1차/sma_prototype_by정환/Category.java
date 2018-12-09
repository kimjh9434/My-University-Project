import java.util.ArrayList;

public class Category {
	private String categoryName;
	private ArrayList<RepresentativeValue> representativeValues;
	
	//Constructor
	public Category(){
		this.categoryName = new String();
		this.representativeValues = new ArrayList<RepresentativeValue>();
	}
	public Category(String categoryName){
		this.categoryName = new String(categoryName);
		this.representativeValues = new ArrayList<RepresentativeValue>();
	}
	//number of representativeValues
	public int getNumberOfRepresentativeValues(){
		return representativeValues.size();
	}
	
	
	//methods
	public int setCategoryName(String categoryName){
		this.categoryName = categoryName;
		return 0;
	}
	public String getCategoryName(){
		return this.categoryName;
	}
	public RepresentativeValue getRepresentativeValue(int representativeValueIndex){
		return this.representativeValues.get(representativeValueIndex);
	}
	public int setRepresentativeValue(int representativeValueIndex, String representativeValueName){
		representativeValues.get(representativeValueIndex).setRepresentativeValueName(representativeValueName);
		return 0;
	}
	public int addRepresentativeValue(String representativeValueName){
		representativeValues.add(new RepresentativeValue(representativeValueName));
		return 0;
	}
	public int delRepresentativeValue(int representativeValueIndex){
		representativeValues.remove(representativeValueIndex);
		return 0;
	}
}
