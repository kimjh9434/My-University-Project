package Menu;

public class KonkukKyuDongStore extends KyuDongStore {

	KyuDong createKyuDong(String type) {

		KyuDong kyuDong = null;

		if (type.equals("Chicken"))
			kyuDong = new ChickenMayoDong();
		else if (type.equals("Pork"))
			kyuDong = new PorkKyuDong();
		else if (type.equals("Beef"))
			kyuDong = new BeefKyuDong();

		return kyuDong;
	}
}
