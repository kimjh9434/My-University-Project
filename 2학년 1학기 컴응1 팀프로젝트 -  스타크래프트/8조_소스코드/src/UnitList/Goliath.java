package UnitList;

import javax.swing.ImageIcon;

public class Goliath extends Unit {

    public Goliath(int player) {
        //Unit(player, name,     hp,  dm,aDm, df,sp,r, aA, f, isAir, atkAir,visi,dete, isMo, isAtk, isSpc) 
        super(player, "Goliath", 125, 12, 20, 1, 3, 6, 8, 2, false, true, true, true, true, true, false, null);
        if (player == 1) {
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\골리앗.png"));
        } else {
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\골리앗2.png"));
        }
    }
}
