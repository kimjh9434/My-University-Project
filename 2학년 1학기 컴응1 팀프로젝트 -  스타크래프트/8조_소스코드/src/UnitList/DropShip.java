package UnitList;

public class DropShip extends Unit {

    public DropShip(int player) {
        //Unit(player, name,      hp,  dm,aDm, df,sp,r, aA, f, isAir,atkAir,visi,dete, isMo, isAtk, isSpc) 
        super(player, "DropShip", 150, 0, 0, 1, 4, 0, 0, 2, true, false, true, true, true, true, true, null);
    }
}
