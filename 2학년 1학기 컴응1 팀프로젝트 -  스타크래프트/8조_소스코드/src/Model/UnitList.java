package Model;

import java.util.ArrayList;

import UnitList.Unit;

public class UnitList 
{
	//유닛생산정보
	public UnitCatalog[] unitInfo;
	
	//생산할수 있는 유닛목록
	public boolean[] isProduce={true, false, false, false, false, false, false, false, false, false, 
			false};
	
	//이미 생산해 놓은 유닛목록
	public ArrayList<Unit> list;
	
	public UnitList()
	{
		unitInfo = new UnitCatalog[11];
		
		unitInfo[0]=new UnitCatalog("SCV",50,0,1);
		unitInfo[1]=new UnitCatalog("마린",50,0,1);
		unitInfo[2]=new UnitCatalog("파이어뱃",50,25,1);
		unitInfo[3]=new UnitCatalog("고스트",25,75,1);
		unitInfo[4]=new UnitCatalog("벌쳐",75,0,2);
		unitInfo[5]=new UnitCatalog("시즈탱크",150,100,2);
		unitInfo[6]=new UnitCatalog("골리앗",100,50,2);
		unitInfo[7]=new UnitCatalog("레이스",150,100,2);
		unitInfo[8]=new UnitCatalog("드랍쉽",100,100,2);
		unitInfo[9]=new UnitCatalog("사이언스 베슬",100,225,2);
		unitInfo[10]=new UnitCatalog("배틀크루져",400,300,6);
		
		list = new ArrayList<Unit>();
	}
	
	
}
