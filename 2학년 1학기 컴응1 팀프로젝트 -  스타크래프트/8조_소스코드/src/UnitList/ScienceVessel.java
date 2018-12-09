package UnitList;

import Magic.DefensiveMatrix;
import Model.GameMain;
import Model.Position;
import javax.swing.ImageIcon;

public class ScienceVessel extends Unit implements DefensiveMatrix {
    private int specialrange;
    private int turn;
    public ScienceVessel(int player) {
        //Unit(player, name,           hp,  dm,aDm,df,sp,r, aA,f, isAir, atkAir,visi,dete, isMo, isAtk, isSpc) 
        super(player, "ScienceVessel", 200, 0, 0, 1, 6, 0, 0, 2, true, false, true, true, true, false, true, null);
        if (player == 1) {
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\vessel.png"));
        } else {
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\vessel2.png"));
        }
        this.set_SpecialSkill("디펜시브M");
        this.specialrange = 5;
        this.turn = -1;
    }
    public Position[] specialRange(){
        return this.getrange(specialrange);

    }
    public Position[] specialAttack(Position[] p) {

        Position[] atkArr = null;

        atkArr = dfmRangeArray(atkArr, specialrange, false);
        return atkArr;

//		this.yamatogun(u);
    }
    
    private Position[] dfmRangeArray(Position[] atkArr, int ran, boolean airAtk) {
        int i = 0;
        int x, y;
        atkArr = new Position[2 * (ran + 1) * (ran + 1) - 2 * (ran + 1)];

        for (x = -ran; x <= ran; x++) {
            for (y = -ran; y <= ran; y++) {
                if (Math.abs(x) + Math.abs(y) <= ran) {
                    if (isMap(x + pos.get_X(), y + pos.get_Y())) {
                        if (GameMain.map.geography[pos.get_Y() + y][pos.get_X() + x] == 0) {
                            if (GameMain.map.unitLink[pos.get_Y() + y][pos.get_X() + x] != null) {
                                atkArr[i] = new Position(pos.get_X() + x, pos.get_Y() + y);
                                i++;
                            }
                        }
                    }
                }
            }
        }
        Position[] Array = new Position[i];
        for (int j = 0; j < i; j++) {
            Array[j] = atkArr[j];
        }
        return Array;
    }
    
    public void special(int x, int y) {

        Unit unit = GameMain.map.unitLink[y][x];
        if (unit.get_Name()=="CommandCenter"){
            System.out.println(unit.get_Name() + "에게 디펜시브매트릭스를 걸 수 없습니다.");
            return;
        }
        this.defensiveMatrix(unit);
        System.out.println(unit.get_Name() + "에게 디펜시브매트릭스를 걸어주었습니다.");
        this.set_isSp(false);
    }
    public void specialAttack(int turn) {
        this.turn = turn; //현재 턴설정
    }

    public void defensiveMatrix(Unit unit) {
        unit.set_shield();
        unit.set_Turndown(turn + 3);
        unit.set_isDfm(true);
        
    }
    public void specialOver(){
        this.set_shield(0);
        this.set_isDfm(false);
        System.out.println(this.get_Name()+"의 디펜시브메트릭스가 종료되었습니다.");
    }
}
