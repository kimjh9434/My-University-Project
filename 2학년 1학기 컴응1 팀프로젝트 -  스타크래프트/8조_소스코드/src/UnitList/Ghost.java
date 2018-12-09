package UnitList;

import Magic.*;
import Model.GameMain;
import Model.Position;
import javax.swing.ImageIcon;

public class Ghost extends Unit implements LockDown {
    private int specialrange;
    public Ghost(int player) {
        //Unit(player, name,   hp, dm,aDm, df,sp,r, aA, f, isAir, atkAir,visi,dete, isMo, isAtk, isSpc) 
        super(player, "Ghost", 45, 10, 10, 0, 3, 7, 7, 1, false, true, true, true, true, true, true, null);
        if (player == 1) {
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\고스트.png"));
        } else {
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\고스트2.png"));
        }
        this.set_SpecialSkill("락다운");
        this.specialrange = 8;
    }

    public Position[] specialRange(){
        return this.getrange(specialrange);
    }
    public Position[] specialAttack(Position[] p) {

        Position[] atkArr = null;

        atkArr = lockdownRangeArray(atkArr, specialrange, false);
        return atkArr;
    }
    
    private Position[] lockdownRangeArray(Position[] atkArr, int ran, boolean airAtk) {
        int i = 0;
        int x, y;
        atkArr = new Position[2 * (ran + 1) * (ran + 1) - 2 * (ran + 1)];

        for (x = -ran; x <= ran; x++) {
            for (y = -ran; y <= ran; y++) {
                if (Math.abs(x) + Math.abs(y) <= ran) {
                    if (isMap(x + pos.get_X(), y + pos.get_Y())) {
                        if (GameMain.map.geography[pos.get_Y() + y][pos.get_X() + x] == 0) {
                            if (GameMain.map.unitLink[pos.get_Y() + y][pos.get_X() + x] != null && GameMain.map.unitLink[pos.get_Y() + y][pos.get_X() + x].get_Player() != player) {
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

        Unit enemy = GameMain.map.unitLink[y][x];
        
        if (enemy.get_Name()=="CommandCenter"){
            System.out.println(enemy.get_Name() + "에게 락다운을 걸 수 없습니다.");
            return;
        }
        System.out.println(enemy.get_Name() + "가 락다운에 걸렸습니다.");

        this.lockdown(enemy);
        this.set_isSp(false);
    }
    
    public void lockdown(Unit u) {
        if (isLockdown(u)) {
            u.set_isAttacked(false);
            u.set_isMoved(false);
            u.set_isSp(false);
        }
    }

    public boolean isLockdown(Unit u) {
        boolean ret = false;
        if (String.valueOf(u.getClass()).compareTo("class UnitList.Marin") != 0
                && String.valueOf(u.getClass()).compareTo("class UnitList.Firebat") != 0
                && String.valueOf(u.getClass()).compareTo("class UnitList.Ghost") != 0) {
            ret = true;
        }
        return ret;
    }
}
