package Menu;

public class tempMain {

	public static void main(String[] args) {

		// 초밥Store 초밥Store = new 초밥Store();

		RamenStore ramenStore = new KonkukRamenStore();
		KyuDongStore kyuDongStore = new KonkukKyuDongStore();
		
		System.out.println("============ 라면 테스트 ===============");
		Ramen ramen1 = ramenStore.orderRamen("Tokyo","1","1","1","1","1","1");
		System.out.println(ramen1);
		ramen1 = new Egg(ramen1);
		System.out.println(ramen1.getDescription() + " : " + ramen1.cost() + "원 \n");
//		System.out.println("==================================");
//		ramen1 = ramenStore.orderRamen("Hokkaido");
//		System.out.println(ramen1.getDescription() + " : " + ramen1.cost() + "원 \n");
//		System.out.println("==================================");
//		ramen1 = ramenStore.orderRamen("Nagasiki");
//		System.out.println(ramen1.getDescription() + " : " + ramen1.cost() + "원 \n");
//		System.out.println("============ 토핑 테스트 ===============");
//		ramen1 = new Chasyu(ramen1);
//		ramen1 = new Egg(ramen1);
//		System.out.println(ramen1.getDescription() + " : " + ramen1.cost() + "원 \n");
//		
//		System.out.println("============ 규동 테스트 ===============");
//		KyuDong kyuDong1 = kyuDongStore.orderKyuDong("Beef");
//		System.out.println(kyuDong1.getDescription() + " : " + kyuDong1.cost() + "원 \n");
//		System.out.println("==================================");
//		kyuDong1 = kyuDongStore.orderKyuDong("Pork");
//		System.out.println(kyuDong1.getDescription() + " : " + kyuDong1.cost() + "원 \n");
//		System.out.println("==================================");
//		kyuDong1 = kyuDongStore.orderKyuDong("Chicken");
//		System.out.println(kyuDong1.getDescription() + " : " + kyuDong1.cost() + "원 \n");
//		System.out.println("============ 토핑 테스트 ===============");
//		kyuDong1 = new Shrimp(kyuDong1);
//		kyuDong1 = new Egg_Kyudong(kyuDong1);
//		System.out.println(ramen1.getDescription() + " : " + ramen1.cost() + "원 \n");
//		System.out.println("==================================");
	}
}
