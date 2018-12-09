package UnitList;

import javax.swing.ImageIcon;

public class Vulture extends Unit {

    public Vulture(int player) {
        //Unit(player, name,     hp, dm,aDm,df,sp, r, aA,f, isAir, atkAir,visi,dete, isMo, isAtk, isSpc) 
        super(player, "Vulture", 80, 20, 0, 0, 8, 5, 0, 2, false, false, true, true, true, true, false, null);
        if (player == 1) {
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\벌쳐.png"));
        } else {
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\벌쳐2.png"));
        }
    }
}
