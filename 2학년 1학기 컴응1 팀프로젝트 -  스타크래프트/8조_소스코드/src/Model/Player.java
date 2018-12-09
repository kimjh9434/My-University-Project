package Model;

import UnitList.BattleCruiser;
import UnitList.CommandCenter;
import UnitList.DropShip;
import UnitList.Firebat;
import UnitList.Ghost;
import UnitList.Goliath;
import UnitList.Marin;
import UnitList.SCV;
import UnitList.ScienceVessel;
import UnitList.SiegeTank;
import UnitList.Unit;
import UnitList.Vulture;
import UnitList.Wraith;
import static java.lang.System.exit;
import java.util.Scanner;

public class Player {

    private String name; //플레이어 이름
    private int player;  //플레이어 순서
    private BuildingList buildingList;//건물 리스트
    private UnitList unitList; //유닛 리스트

    private int turn_mineral; //턴당 미네랄 증가량
    private int turn_gas;     //턴당 가스 증가량
    private int mineral;      //현재 미네랄
    private int gas;          //현재 가스
    private int food;         //현재 인구수
    private int maxFood;      //최대 인구수
    private int turn;         //턴
    private CommandCenter commandCennter;//커맨트 센터

    //생성자
    public Player(int num, String _name) {
        name = _name;
        player = num;
        buildingList = new BuildingList();
        unitList = new UnitList();
        turn_mineral = 80;
        turn_gas = 0;
        mineral = 50;
        gas = 0;
        food = 0;
        maxFood = 10;
        turn = 0;
        commandCennter = new CommandCenter(num);
        if (num == 1) {
            commandCennter.set_Pos(3, GameMain.map.maxY / 2);
            GameMain.map.unitLink[GameMain.map.maxY / 2][3] = commandCennter;
        } else if (num == 2) {
            commandCennter.set_Pos(GameMain.map.maxX - 2, GameMain.map.maxY / 2);
            GameMain.map.unitLink[GameMain.map.maxY / 2][GameMain.map.maxX - 4] = commandCennter;
        }
    }

    //접근자(get) 및 변경자(set) 메소드
    public String get_Name() {
        return this.name;
    }

    public int get_Player() {
        return this.player;
    }

    public int get_Mineral() {
        return this.mineral;
    }

    public int get_Turn_Mineral() {
        return this.turn_mineral;
    }

    public int get_Gas() {
        return this.gas;
    }

    public int get_Turn_Gas() {
        return this.turn_gas;
    }

    public int get_Food() {
        return this.food;
    }

    public int get_MaxFood() {
        return this.maxFood;
    }

    public int get_Turn() {
        return this.turn;
    }

    //해당 건물이 지어졌는지 여부
    public boolean isBuilt(int n) {
        return this.buildingList.isBuilt[n];
    }

    //커맨드센터가 안깨졌는지 여부
    public boolean alive() {
        return (commandCennter.get_Hp() > 0);
    }

    //현재 플래이어의 상태보여주기
    public void choiceMenu() {
        System.out.println("========================================");
        System.out.println("Player" + player + "의 턴");
        System.out.println("미네랄 :" + mineral + " 가스 :" + gas + " 인구 :" + food + "/" + maxFood);
        System.out.println("턴당 미네랄 증가량 :" + turn_mineral + " 턴당 가스 증가량 :" + turn_gas);
        System.out.println("메뉴를 선택하시오!!");
        System.out.println("1. 유닛생산");
        System.out.println("2. 건물건설");
        System.out.println("3. 유닛행동");
        System.out.println("4. 턴종료");
        System.out.println("5. 게임포기");
        System.out.println("========================================");
    }

    public boolean produceUnitcheck(int unitNum) {
        System.out.println("Player" + player + "의 유닛생산목록");

        int i;
        for (i = 0; i < unitList.unitInfo.length; i++) {
            if (unitList.isProduce[i]) {
                System.out.println(i + " : " + unitList.unitInfo[i]);
            }
        }

        //선택한 유닛을 생산할수  있는지 없는지 여부.
        if (!unitList.isProduce[unitNum]) {
            System.out.println("아직 생산할수 없습니다.");
        } else if (gas < unitList.unitInfo[unitNum].gas) {
            System.out.println("자원[가스]이 부족합니다. 유닛을 생산할 수 없습니다.");
        } else if (maxFood < unitList.unitInfo[unitNum].food + food) {
            System.out.println("자원[인구]이 부족합니다. 유닛을 생산할 수 없습니다.");
        } else if (mineral < unitList.unitInfo[unitNum].mineral) {
            System.out.println("자원[미네랄]이 부족합니다. 유닛을 생산할 수 없습니다.");
        } else {
            produceUnit(unitNum);
            return true;
        }
        return false;
    }

    //선택한 유닛을 생산함
    public void produceUnit(int unitNum) {
        //해당 유닛의 자원을 소모
        mineral -= unitList.unitInfo[unitNum].mineral;
        gas -= unitList.unitInfo[unitNum].gas;
        food += unitList.unitInfo[unitNum].food;

        Unit unit = null;
        switch (unitNum) {
            case 0: //SCV
                turn_mineral += 20;
                break;
            case 1: //마린
                unit = new Marin(player);
                break;
            case 2: //파이어뱃
                unit = new Firebat(player);
                break;
            case 3: //고스트
                unit = new Ghost(player);
                break;
            case 4: //벌쳐
                unit = new Vulture(player);
                break;
            case 5: //시즈탱크
                unit = new SiegeTank(player);
                break;
            case 6: //골리앗
                unit = new Goliath(player);
                break;
            case 7: //레이스
                unit = new Wraith(player);
                break;
            case 8: //드랍십 
                unit = new DropShip(player);
                break;
            case 9: //사이언스 베슬
                unit = new ScienceVessel(player);
                break;
            case 10: //베틀크루져
                unit = new BattleCruiser(player);
                break;
            default:
                break;
        }
        if (unitNum != 0) {
            GameMain.map.setStartPos(unit);
            unitList.list.add(unit);
            GameMain.map.unitLink[unit.get_Pos().y][unit.get_Pos().x] = unit;
        }
        System.out.println(unitList.unitInfo[unitNum].name + "을 생산했습니다.");
    }

    public boolean buildcheck(int num) {
        System.out.println("Player" + player + "의 건물목록");

        int i;
        System.out.println("건설한 건물목록");
        for (i = 0; i < buildingList.isBuilt.length; i++) {
            if (buildingList.isBuilt[i]) {
                System.out.println(i + " : " + buildingList.buildingInfo[i].name);
            }
        }
        System.out.println("건설가능한 건물목록");
        for (i = 0; i < buildingList.isBuilt.length; i++) {
            if (buildingList.isBuild[i] && !buildingList.isBuilt[i]) {
                System.out.println(i + ": " + buildingList.buildingInfo[i]);
            }
        }
        System.out.println("지을 건물의번호를 입력하세요. [-1]번은 건설취소");

        //선택한 건물을 지을수 있는지 없는지 여부.
        if (!buildingList.isBuild[num]) {
            System.out.println("아직 건물을 지으실수 없습니다.");
        } else if (mineral < buildingList.buildingInfo[num].mineral) {
            System.out.println("자원[미네랄]이 부족합니다. 건물을 지을 수 없습니다.");
        } else if (gas < buildingList.buildingInfo[num].gas) {
            System.out.println("자원[가스]이 부족합니다. 건물을 지을 수 없습니다.");
        } else if (isBuilt(num)) {
            if (num == 0 || num == 1) {
                build(num);
                return true;
            }
        } else{
            build(num);
            return true;
        }
        return false;
    }

    //선택한 건물을 지음
    public void build(int building) {
        buildingList.isBuilt[building] = true;
        mineral -= buildingList.buildingInfo[building].mineral;
        gas -= buildingList.buildingInfo[building].gas;

        switch (building) {
            case 0: //서플라이디팟
                if (maxFood + 8 < 200) {
                    maxFood += 8;
                } else {
                    maxFood = 200;
                }
                buildingList.isBuilt[building] = false;
                break;
            case 1: //리파이너리
                turn_gas += 40;
                buildingList.isBuilt[building] = false;
                break;
            case 2: //배럭
                buildingList.isBuild[3] = true;
                buildingList.isBuild[4] = true;
                buildingList.isBuild[5] = true;
                unitList.isProduce[1] = true;
                break;
            case 3: //엔지니어링 베이
                break;
            case 4: //아카데미
                unitList.isProduce[2] = true;
                break;
            case 5: //팩토리
                buildingList.isBuild[6] = true;
                buildingList.isBuild[7] = true;
                buildingList.isBuild[8] = true;
                unitList.isProduce[4] = true;
                break;
            case 6: //머신샵
                unitList.isProduce[5] = true;
                break;
            case 7: //아머리
                unitList.isProduce[6] = true;
                break;
            case 8: //스타포트 
                buildingList.isBuild[9] = true;
                buildingList.isBuild[10] = true;
                unitList.isProduce[7] = true;
                break;
            case 9: //컨트롤타워
                unitList.isProduce[8] = false;//드랍쉽 뽑기 막음
                if (buildingList.isBuilt[10]) {
                    unitList.isProduce[9] = true;
                }
                if (buildingList.isBuilt[12]) {
                    unitList.isProduce[10] = true;
                }
                break;
            case 10: //사이언스 퍼실리티
                buildingList.isBuild[11] = true;
                buildingList.isBuild[12] = true;
                if (buildingList.isBuilt[9]) {
                    unitList.isProduce[9] = true;
                }
                break;
            case 11:  //커버트 옵스
                unitList.isProduce[3] = true;
                break;
            case 12: //피직스 랩
                if (buildingList.isBuilt[9]) {
                    unitList.isProduce[10] = true;
                }
                break;
            default:
                break;
        }
        System.out.println(buildingList.buildingInfo[building].name + "을 지었습니다.");
    }

    public boolean turnEnd() {
        System.out.println("Player" + player + "가 턴을 종료했습니다.");
        //초기화
        reset();

        //자원 증가
        mineral += turn_mineral;
        gas += turn_gas;
        turn++;
        return true;
    }

    public boolean giveUp() {
        System.out.println("Player" + player + "가 게임을 포기했습니다.");
        return true;
    }

    //초기화
    private void reset() {
        int i = 0;
        while (i < unitList.list.size()) {
            if (unitList.list.get(i).get_Manager().isLive()) {
                unitList.list.get(i).set_isMoved(true);
                unitList.list.get(i).set_isAttacked(true);
                unitList.list.get(i).set_isSp(true);
                if (unitList.list.get(i).get_Turndown() == turn) {
                    unitList.list.get(i).specialOver();
                }
                if (unitList.list.get(i).get_dturn() == turn) {
                    unitList.list.get(i).specialOver();
                }
                i++;
            } else {
                unitList.list.remove(i);
            }
        }
    }
}
