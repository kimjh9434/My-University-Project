package UnitList;

import Magic.YamatoGun;
import Model.GameMain;
import Model.Position;
import javax.swing.ImageIcon;

public class BattleCruiser extends Unit {

    private int specialrange;

    public BattleCruiser(int player) {
        //Unit(player, name,           hp,  dm,aDm, df,sp,r, aA,f, isAir,atkAir,visi,dete, isMo, isAtk, isSpc) 
        super(player, "BattleCruiser", 500, 25, 25, 3, 2, 6, 6, 6, true, true, true, true, true, true, true, null);
        if (player == 1) {
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\베틀크루저.png"));
        } else {
            this.set_Image(new ImageIcon("C:\\Starcraft_Image\\베틀크루저2.png"));
        }
        specialrange = 13;//야마토포 사거리
        this.set_SpecialSkill("야마토포");
    }

    public Position[] specialRange() {
        return this.getrange(specialrange);

    }

    public Position[] specialAttack(Position[] p) {

        Position[] atkArr = null;

        atkArr = yamatoRangeArray(atkArr, specialrange, false);
        return atkArr;
    }

    private Position[] yamatoRangeArray(Position[] atkArr, int ran, boolean airAtk) {
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
        int dam = 100;

        int enemy_hp = enemy.get_Hp();
        if (enemy.isDfm()) {
            int shield = enemy.get_shield();
            if (shield > dam) {
                enemy.set_shield(shield - dam);
                enemy_hp--;
            } else {
                dam -= shield;
                if (dam > enemy.get_Defense()) {
                    enemy_hp += enemy.get_Defense() - dam;
                } else {
                    enemy_hp--;
                }
                enemy.set_shield(0);
                enemy.set_dfm(false);

            }

        } else {
            enemy_hp -= dam;
        }
        System.out.println(dam - enemy.get_Defense() + "만큼의 피해을 주었습니다.");

        if (enemy_hp > 0) {
            enemy.set_Hp(enemy_hp);
        } else {
            enemy.set_Hp(0);
            enemy.manager.isDie();
            GameMain.map.unitLink[enemy.pos.get_Y()][enemy.pos.get_X()] = null;
            if (String.valueOf(enemy.getClass()).compareTo("class UnitList.CommandCenter") == 0) {
                GameMain.gameOver = true;
                System.out.println("적 커맨드센터를 파괴하였습니다.");
            } else {
                System.out.println("적 유닛을 파괴하였습니다.");
            }
        }
        this.set_isSp(false);
    }

}
