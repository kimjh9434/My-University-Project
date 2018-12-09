package UnitList;

import Model.Position;

public class SCV extends Unit {

    public SCV(int player) {
        //Unit(player, name, hp,dm,aDm,df,sp,r, aA,f, isAir, atkAir,visi, dete, isMo, isAtk, isSpc) 
        super(player, "SCV", 60, 5, 0, 0, 4, 1, 0, 0, false, false, true, true, true, true, false, null);
    }
}
