package Menu;

public abstract class KyuDongStore {
	
	public KyuDong orderKyuDong(String type, String a, String b) 	
	{
		KyuDong kyudong;
		kyudong = createKyuDong(type); 
		kyudong.selectSize(a);
		kyudong.selectGarlicAmount(b);
		
		return kyudong;
	}
	
	abstract KyuDong createKyuDong(String type); 


}
