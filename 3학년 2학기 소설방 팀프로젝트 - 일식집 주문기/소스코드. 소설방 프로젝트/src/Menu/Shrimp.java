package Menu;

public class Shrimp extends KyuDongDecorator{

	KyuDong kyuDong;
	public Shrimp(KyuDong kyuDong){
		this.kyuDong = kyuDong;
		setCost(kyuDong.getCost()+400);
	}
	public String getDescription() {
		return (kyuDong.getDescription() + ",Shrimp");
	}

	public double cost() {
		return kyuDong.cost()+400;
	}
}
