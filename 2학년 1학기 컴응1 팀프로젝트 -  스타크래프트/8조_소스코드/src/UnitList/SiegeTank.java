package UnitList;

import Magic.SiegeMode;
import Model.GameMain;
import Model.Position;
import javax.swing.ImageIcon;

public class SiegeTank extends Unit implements SiegeMode {
    int specialrange;
    boolean isSieged;

    public SiegeTank(int player) {
        //Unit(player, name,       hp,  dm,aDm,df,sp, r, aA,f, isAir, atkAir,visi,dete, isMo, isAtk, isSpc) 
        super(player, "SiegeTank", 150, 30, 0, 1, 3, 7, 0, 2, false, false, true, true, true, true, true, null);
        if (player == 1) {
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\탱크.png"));
        } else {
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\탱크2.png"));
        }
        isSieged = false;
        specialrange = 0;
        this.set_SpecialSkill("시즈모드");
    }

    public void siegeMode() {
        if (isSieged == false) {
            isSieged = true;
            this.set_Speed(0);
            this.set_Damage(70);
            this.set_Range(12);
            this.set_Name("Sieged-Tank");
            if (this.get_Player() == 1) {
                this.set_Image(new ImageIcon("C:\\Starcraft_Image\\시즈모드.png"));
            } else {
                this.set_Image(new ImageIcon("C:\\Starcraft_Image\\시즈모드2.png"));
            }
            System.out.println("시즈모드가 되었습니다.");
        } else {
            isSieged = false;
            this.set_Speed(3);
            this.set_Damage(30);
            this.set_Range(7);
            this.set_Name("SiegeTank");
            if (this.get_Player() == 1) {
                this.set_Image(new ImageIcon("C:\\Starcraft_Image\\탱크.png"));
            } else {
                this.set_Image(new ImageIcon("C:\\Starcraft_Image\\탱크2.png"));
            }
            System.out.println("시즈모드가 해제되었습니다.");
        }
    }

    public Position[] getrange(int range) {
        if (isSieged == true) {
            return tankRange();
        } else {
            Position[] isAtkArr = null;

            isAtkArr = getRangeArray(isAtkArr, range);

            return isAtkArr;
        }
    }

    public Position[] getAttackRangeArray(Position[] atkArr, int range, boolean b) {
        int i = 0;
        int x, y;
        atkArr = new Position[2 * (12 + 1) * (12 + 1) - 2 * (12 + 1)];

        for (x = -12; x <= 12; x++) {
            for (y = -12; y <= 12; y++) {
                if (Math.abs(x) + Math.abs(y) <= 12 && Math.abs(x) + Math.abs(y) >= 4) {//  <-----------------   요기
                    if (isMap(x + this.get_Pos().get_X(), y + this.get_Pos().get_Y())) {
                        if (GameMain.map.geography[this.get_Pos().get_Y() + y][this.get_Pos().get_X() + x] == 0) {
                            if (GameMain.map.unitLink[this.get_Pos().get_Y() + y][this.get_Pos().get_X() + x] != null && GameMain.map.unitLink[this.get_Pos().get_Y() + y][this.get_Pos().get_X() + x].get_Player() != this.get_Player()
                                    && !GameMain.map.unitLink[this.get_Pos().get_Y() + y][this.get_Pos().get_X() + x].isAir()) {
                                atkArr[i] = new Position(this.get_Pos().get_X() + x, this.get_Pos().get_Y() + y);
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

    public Position[] tankRange() {

        int i = 0;
        int x, y;
        Position[] rangeArray = new Position[2 * (12 + 1) * (12 + 1) - 2 * (12 + 1)];

        for (x = -12; x <= 12; x++) {
            for (y = -12; y <= 12; y++) {
                if (Math.abs(x) + Math.abs(y) <= 12 && Math.abs(x) + Math.abs(y) >= 4) {//  <-----------------   요기
                    if (isMap(x + this.get_Pos().get_X(), y + this.get_Pos().get_Y())) {
                        if (GameMain.map.geography[this.get_Pos().get_Y() + y][this.get_Pos().get_X() + x] == 0) {
                            if (GameMain.map.unitLink[this.get_Pos().get_Y() + y][this.get_Pos().get_X() + x] == null) {
                                rangeArray[i] = new Position(this.get_Pos().get_X() + x, this.get_Pos().get_Y() + y);
                                i++;
                            }
                        }
                    }
                }
            }
        }
        Position[] Array = new Position[i];
        for (int j = 0; j < i; j++) {
            Array[j] = rangeArray[j];
        }
        return Array;
    }

    public Position[] specialAttack(Position[] p) {
        this.siegeMode();
        return null;
    }
}
