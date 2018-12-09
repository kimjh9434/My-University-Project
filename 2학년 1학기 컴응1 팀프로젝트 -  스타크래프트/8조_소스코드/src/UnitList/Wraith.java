package UnitList;

import javax.swing.ImageIcon;

public class Wraith extends Unit {

    public Wraith(int player) {
        //Unit(player, name,    hp,  dm,aDm,df,sp, r, aA,f, isAir, atkAir,visi,dete, isMo, isAtk, isSpc) 
        super(player, "Wraith", 120, 8, 20, 0, 7, 5, 5, 2, true, true, true, true, true, true, false, null);
        if (player == 1) {
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\레이스.png"));
        } else {
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\레이스2.png"));
        }
    }
}
