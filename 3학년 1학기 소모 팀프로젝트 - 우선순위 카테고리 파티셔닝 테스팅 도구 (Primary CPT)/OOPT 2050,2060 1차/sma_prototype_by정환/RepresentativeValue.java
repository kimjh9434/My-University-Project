import java.util.ArrayList;

public class RepresentativeValue {
	private String representativeValueName;
	private ArrayList<Property> propertyConstraint;
	private ArrayList<Property> if_propertyConstraint;
	private Property singleErrorConstraint;
	private int priority;
	
	//Constructor
	public RepresentativeValue() {
		this.representativeValueName = new String();
		this.propertyConstraint = new ArrayList<Property>();
		this.if_propertyConstraint = new ArrayList<Property>();
	}
	public RepresentativeValue(String representativeValueName) {
		this.representativeValueName = new String();
		this.propertyConstraint = new ArrayList<Property>();
		this.if_propertyConstraint = new ArrayList<Property>();
		this.representativeValueName = representativeValueName;
	}
	//number of property constraints
	public int getNumberOfPropertyConstraints(){
		return propertyConstraint.size();
	}
	//number of property if constraints
	public int getNumberOfIfPropertyConstraints(){
		return if_propertyConstraint.size();
	}
	
	
	
	public int setRepresentativeValueName(String representativeValueName){
		this.representativeValueName = representativeValueName;
		return 0;
	}
	public String getRepresentativeValueName(){
		return representativeValueName;
	}
	public int setProperty(int propertyIndex, String propertyName){
		propertyConstraint.get(propertyIndex).setName(propertyName);
		return 0;
	}
	public int addProperty(String propertyName){
		propertyConstraint.add(new Property(propertyName));
		return 0;
	}
	public int delProperty(int propertyIndex){
		propertyConstraint.remove(propertyIndex);
		return 0;
	}
	public int setIfProperty(int ifPropertyIndex, Property ifProperty){
		if_propertyConstraint.set(ifPropertyIndex, ifProperty);
		return 0;
	}
	public int addIfProperty(Property ifProperty){
		if_propertyConstraint.add(ifProperty);
		return 0;
	}
	public int delIfProperty(int ifPropertyIndex){
		if_propertyConstraint.remove(ifPropertyIndex);
		return 0;
	}
	public int setSingleError(Property singleError){
		singleErrorConstraint = singleError;
		return 0;
	}
	public int setPriorityRank(int priorityRank){
		priority = priorityRank;
		return 0;
	}
	public Property getProperty(int propertyIndex){
		return propertyConstraint.get(propertyIndex);
	}
	public Property getIfProperty(int ifPropertyIndex){
		return if_propertyConstraint.get(ifPropertyIndex);
	}
	public Property getSingleError(){
		return singleErrorConstraint;
	}
	public int getPriorityRank(){
		return priority;
	}
}
