package UnitList;

import Magic.*;
import javax.swing.ImageIcon;

public class Marin extends Unit implements Steampack {

    private boolean isSteam;

    public Marin(int player) {
        //Unit(player, name,   hp,  dm,aDm, df,sp,r, aA, f, isAir, atkAir,visi,dete, isMo, isAtk, isSpc) 
        super(player, "Marin", 40, 12, 20, 0, 3, 6, 8, 1, false, true, true, true, true, true, true, null);
        isSteam = false;
        if (player == 1) {
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\마린.png"));
        } else {
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\마린2.png"));
        }
        this.set_SpecialSkill("스팀팩");
    }

    public void specialAttack(int turn) {
        this.steampack(turn);
    }

    public void specialOver() {
        this.steamover();
    }

    public void steampack(int turn) {
        if (isSteam == false) {
            this.set_Turndown(turn + 3);
            this.isSteam = true;
            if (this.get_Hp() >= 20) {
                this.set_Hp(this.get_Hp() - 10);
            } else if (this.get_Hp() >= 10 && this.get_Hp() < 20) {
                this.set_Hp(10);
            }
            this.set_AirDamage(this.get_AirDamage() + 12);
            this.set_Damage(this.get_Damage() + 12);
            this.set_Speed(this.get_Speed() + 3);
            System.out.println("스팀팩을 사용합니다");
        } else {
            this.set_Turndown(turn + 3); /// 지속효과중에 다시 쓴 경우
            if (this.get_Hp() >= 20) {
                this.set_Hp(this.get_Hp() - 10);
            } else if (this.get_Hp() >= 10 && this.get_Hp() < 20) {
                this.set_Hp(10);
            }
            System.out.println("스팀팩을 사용합니다");
        }
    }

    public void specialAttackover() {
        this.steamover();
    }

    public void steamover() {
        isSteam = false;
        this.set_AirDamage(this.get_AirDamage() - 12);
        this.set_Damage(this.get_Damage() - 12);
        this.set_Speed(this.get_Speed() - 3);
        System.out.println(this.get_Name() + "의 스팀팩이 종료되었습니다.");
    }
}
