package Menu;

public class Egg_Kyudong extends KyuDongDecorator {

	KyuDong kyuDong;

	public Egg_Kyudong(KyuDong kyuDong) {
		this.kyuDong = kyuDong;
		setCost(kyuDong.getCost()+500);
	}

	public String getDescription() {
		return (kyuDong.getDescription() + ",Egg");
	}

	public double cost() {
		return kyuDong.cost() + 500;
	}
}
