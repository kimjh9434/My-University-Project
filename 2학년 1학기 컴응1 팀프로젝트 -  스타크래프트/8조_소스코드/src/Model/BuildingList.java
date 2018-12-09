package Model;

public class BuildingList 
{
	//건물 목록
	BuildingCatalog[] buildingInfo;
	
	//지을수 있는 건물목록
	boolean[] isBuild={true, true, true, false, false, false, false, false, false, false, 
			false, false, false};
	
	//이미 지은 건물목록
	boolean[] isBuilt={false, false, false, false, false, false, false, false, false, false, 
			false, false, false};
	
	public BuildingList()
	{
		buildingInfo = new BuildingCatalog[13];
		
		buildingInfo[0]=new BuildingCatalog("서플라이 디팟",100,0);
		buildingInfo[1]=new BuildingCatalog("리파이너리",100,0);
		buildingInfo[2]=new BuildingCatalog("배럭",150,0);
		buildingInfo[3]=new BuildingCatalog("엔지니어링 베이",125,0);
		buildingInfo[4]=new BuildingCatalog("아카데미",200,0);
		buildingInfo[5]=new BuildingCatalog("팩토리",200,100);
		buildingInfo[6]=new BuildingCatalog("머신샵",50,50);
		buildingInfo[7]=new BuildingCatalog("아머리",50,100);
		buildingInfo[8]=new BuildingCatalog("스타포트",150,100);
		buildingInfo[9]=new BuildingCatalog("컨트롤 타워",100,50);
		buildingInfo[10]=new BuildingCatalog("사이언스 퍼실리티",150,200);
		buildingInfo[11]=new BuildingCatalog("커버트 옵스",100,100);
		buildingInfo[12]=new BuildingCatalog("피직스 랩",50,50);
	}
}
