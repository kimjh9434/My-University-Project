package UnitList;

import javax.swing.ImageIcon;

public class CommandCenter extends Unit
{
    public CommandCenter(int player){
        //Unit(player, name,           hp,  dm,aDm, df,sp, r, aA, f, isAir,atkAir,visi,dete, isMo, isAtk, isSpc) 
        super(player, "CommandCenter", 400, 0 , 0 , 2 , 0, 0, 0, 0, false, false, true, true, false, false, false, null);
        if(player==1)
        {
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\커맨드센터.png"));
        }else{
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\커맨드센터2.png"));  
        }
    }
}
