/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.MainClass;
import GUI.mButton;
import Model.*;
import UnitList.Unit;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author apam
 */
public class GameMainUI extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    private ImageIcon image[] = new ImageIcon[50];
    private ImageIcon mapImage[] = new ImageIcon[7];
    private Image mimage;
    private GameMain game;
    private Player player;
    private GameMainUI otherplayer;
    mButton[][] button;
    public Unit unit;

    public GameMainUI(GameMain game, Player player) {
        initComponents();
        image[0] = new ImageIcon("C:\\Starcraft_Image\\scv.png");
        produce_SCV_jButton29.setIcon(image[0]);
        image[1] = new ImageIcon("C:\\Starcraft_Image\\마린0.png");
        produce_Marin_jButton14.setIcon(image[1]);
        image[2] = new ImageIcon("C:\\Starcraft_Image\\파뱃0.png");
        produce_Firebat_jButton15.setIcon(image[2]);
        image[3] = new ImageIcon("C:\\Starcraft_Image\\고스트0.png");
        produce_Ghost_jButton12.setIcon(image[3]);
        image[4] = new ImageIcon("C:\\Starcraft_Image\\벌쳐0.png");
        produce_Vulture_jButton13.setIcon(image[4]);
        image[5] = new ImageIcon("C:\\Starcraft_Image\\탱크0.png");
        produce_SiegeTank_jButton10.setIcon(image[5]);
        image[6] = new ImageIcon("C:\\Starcraft_Image\\골리앗0.png");
        produce_Goliath_jButton11.setIcon(image[6]);
        image[7] = new ImageIcon("C:\\Starcraft_Image\\레이스0.png");
        produce_Wraith_jButton9.setIcon(image[7]);
        image[8] = new ImageIcon("C:\\Starcraft_Image\\vessel0.png");
        produce_ScienceVessel_Button6.setIcon(image[8]);
        image[9] = new ImageIcon("C:\\Starcraft_Image\\베틀크루저0.png");
        produce_BattleCruiser_jButton8.setIcon(image[9]);
        image[10] = new ImageIcon("C:\\Starcraft_Image\\드랍쉽.png");
        produce_DropShip_jButton7.setIcon(image[10]);
        image[11] = new ImageIcon("C:\\Starcraft_Image\\서플라이.png");
        build_SupplyDepot_jButton16.setIcon(image[11]);
        image[12] = new ImageIcon("C:\\Starcraft_Image\\가스.png");
        build_Refinery_jButton17.setIcon(image[12]);
        image[13] = new ImageIcon("C:\\Starcraft_Image\\배럭스.png");
        build_Barrack_jButton18.setIcon(image[13]);
        image[14] = new ImageIcon("C:\\Starcraft_Image\\엔지니어링베이.png");
        build_EngineeringBay_jButton19.setIcon(image[14]);
        image[15] = new ImageIcon("C:\\Starcraft_Image\\아카데미0.png");
        build_Academy_jButton20.setIcon(image[15]);
        image[16] = new ImageIcon("C:\\Starcraft_Image\\팩토리0.png");
        build_Factory_jButton21.setIcon(image[16]);
        image[17] = new ImageIcon("C:\\Starcraft_Image\\머신샵0.png");
        build_MachineShop_jButton22.setIcon(image[17]);
        image[18] = new ImageIcon("C:\\Starcraft_Image\\아머리0.png");
        build_Armory_jButton25.setIcon(image[18]);
        image[19] = new ImageIcon("C:\\Starcraft_Image\\스타포트0.png");
        build_Starport_jButton23.setIcon(image[19]);
        image[20] = new ImageIcon("C:\\Starcraft_Image\\컨트롤타워0.png");
        build_ControlTower_jButton24.setIcon(image[20]);
        image[21] = new ImageIcon("C:\\Starcraft_Image\\베슬0.png");
        build_ScienceFacility_jButton26.setIcon(image[21]);
        image[22] = new ImageIcon("C:\\Starcraft_Image\\커버트옵스0.png");
        build_CovertOps_jButton27.setIcon(image[22]);
        image[23] = new ImageIcon("C:\\Starcraft_Image\\피직스랩0.png");
        build_PhysicsLab_jButton28.setIcon(image[23]);
        image[24] = new ImageIcon("C:\\Starcraft_Image\\마린.png");

        image[25] = new ImageIcon("C:\\Starcraft_Image\\파뱃.png");

        image[26] = new ImageIcon("C:\\Starcraft_Image\\고스트.png");

        image[27] = new ImageIcon("C:\\Starcraft_Image\\벌쳐.png");

        image[28] = new ImageIcon("C:\\Starcraft_Image\\탱크.png");

        image[29] = new ImageIcon("C:\\Starcraft_Image\\골리앗.png");

        image[30] = new ImageIcon("C:\\Starcraft_Image\\레이스.png");

        image[31] = new ImageIcon("C:\\Starcraft_Image\\vessel.png");

        image[32] = new ImageIcon("C:\\Starcraft_Image\\베틀크루저.png");

        image[33] = new ImageIcon("C:\\Starcraft_Image\\아카데미.png");

        image[34] = new ImageIcon("C:\\Starcraft_Image\\팩토리.png");

        image[35] = new ImageIcon("C:\\Starcraft_Image\\머신샵.png");

        image[36] = new ImageIcon("C:\\Starcraft_Image\\아머리.png");

        image[37] = new ImageIcon("C:\\Starcraft_Image\\스타포트.png");

        image[38] = new ImageIcon("C:\\Starcraft_Image\\컨트롤타워.png");

        image[39] = new ImageIcon("C:\\Starcraft_Image\\베슬.png");

        image[40] = new ImageIcon("C:\\Starcraft_Image\\커버트옵스.png");

        image[41] = new ImageIcon("C:\\Starcraft_Image\\피직스랩.png");
        image[43] = new ImageIcon("C:\\Starcraft_Image\\커맨드센터.png");
        mapImage[0] = new ImageIcon("C:\\Starcraft_Image\\땅지형.png");//땅
        mapImage[1] = new ImageIcon("C:\\Starcraft_Image\\color2.png");//이동범위
        mapImage[2] = new ImageIcon("C:\\Starcraft_Image\\물지형.png");//물
        mapImage[3] = new ImageIcon("C:\\Starcraft_Image\\color3.png");//공격범위
        mapImage[4] = new ImageIcon("C:\\Starcraft_Image\\color4.png");//공격선택
        mapImage[5] = new ImageIcon("C:\\Starcraft_Image\\color5.png");//특수공격범위
        mimage = new ImageIcon("C:\\Starcraft_Image\\땅지형2.png").getImage();
        //    jPanel6.repaint();
        this.game = game;
        this.player = player;
        this.unit = null;
        button = new mButton[40][40];
        int x = 0, y = 0;
        // 버튼 배치
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                button[i][j] = new mButton(i, j, this, game.map);
                button[i][j].setBounds(x, y, 50, 50);
                jPanel6.add(button[i][j]);
                x += 51;
            }
            x = 0;
            y += 51;
        }
        mapping();
        if (player.get_Player() == 2) {
            jScrollPane1.getHorizontalScrollBar().setValue(1500);
        }
        jScrollPane1.getVerticalScrollBar().setValue(820);

    }

    public Player get_Player() {
        return this.player;
    }

    public Player get_OtherPlayer() {
        return this.otherplayer.get_Player();
    }

    public void refreshUnitLabel() {
        unit.viewUnit();
        unitName_jLabel17.setText(unit.get_Name());
        hp_totalHp_jLabel18.setText(Integer.toString(unit.get_Hp()) + "/ " + Integer.toString(unit.get_totalHP()));
        special_jLabel19.setText(unit.get_SpecialSkill());
        dam_def_jLabel20.setText("공격력 : " + unit.get_Damage() + " 방어력 : " + unit.get_Defense());
        if (unit.isDfm()) {
            shield_jLabel21.setText("쉴드 : " + Integer.toString(unit.get_shield()) + "/100");
        } else {
            shield_jLabel21.setText("");
        }
    }

    public void mapping() {
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                if (game.map.unitLink[i][j] != null) {
                    button[i][j].setIcon(game.map.unitLink[i][j].getImage());
                    button[i][j].setEnabled(true);
                } else if (game.map.geography[i][j] == 1) {
                    button[i][j].setIcon(mapImage[2]);
                    button[i][j].setEnabled(true);
                } else {
                    button[i][j].setIcon(mapImage[0]);
                    button[i][j].setEnabled(true);
                }
                button[i][j].moveOk = false;
                button[i][j].attackOk = false;
                button[i][j].spcOk = false;
            }
        }
    }

    public void disableButton() {
        Move_jButton1.setEnabled(false);
        Attack_jButton2.setEnabled(false);
        Special_jButton31.setEnabled(false);
        AirAttack_jButton30.setEnabled(false);
    }

    public void buttonEnable() {
        unit.viewUnit();
        if (unit.isMoved() && player.get_Player() == unit.get_Player()) {
            Move_jButton1.setEnabled(true);
        } else {
            Move_jButton1.setEnabled(false);
        }

        if (unit.isAttacked() && player.get_Player() == unit.get_Player()) {

            Attack_jButton2.setEnabled(true);
            if (unit.isAtkAir()) {
                AirAttack_jButton30.setEnabled(true);
            }
        } else {
            Attack_jButton2.setEnabled(false);
            AirAttack_jButton30.setEnabled(false);
        }
        if (unit.isSpc() && unit.isSp() && player.get_Player() == unit.get_Player()) {
            Special_jButton31.setEnabled(true);
        } else {
            Special_jButton31.setEnabled(false);
        }
    }

    public void setOther(GameMainUI other) {
        this.otherplayer = other;
    }

    public void refreshLabel() {
        player.choiceMenu();
        turn_jLabel7.setText(Integer.toString(player.get_Turn()));
        playerName_jLabel11.setText(player.get_Name());
        minaral_jLabel12.setText(player.get_Mineral() + " ( +" + player.get_Turn_Mineral() + ")");
        gas_jLabel15.setText(player.get_Gas() + " ( +" + player.get_Turn_Gas() + ")");
        trun_jLabel16.setText(player.get_Food() + " / " + player.get_MaxFood());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Move_jButton1 = new javax.swing.JButton();
        TurnEnd_jButton4 = new javax.swing.JButton();
        GiveUp_jButton5 = new javax.swing.JButton();
        AirAttack_jButton30 = new javax.swing.JButton();
        Attack_jButton2 = new javax.swing.JButton();
        Special_jButton31 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        unitName_jLabel17 = new javax.swing.JLabel();
        hp_totalHp_jLabel18 = new javax.swing.JLabel();
        special_jLabel19 = new javax.swing.JLabel();
        dam_def_jLabel20 = new javax.swing.JLabel();
        shield_jLabel21 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        produce_ScienceVessel_Button6 = new javax.swing.JButton();
        produce_DropShip_jButton7 = new javax.swing.JButton();
        produce_BattleCruiser_jButton8 = new javax.swing.JButton();
        produce_Wraith_jButton9 = new javax.swing.JButton();
        produce_SiegeTank_jButton10 = new javax.swing.JButton();
        produce_Goliath_jButton11 = new javax.swing.JButton();
        produce_Ghost_jButton12 = new javax.swing.JButton();
        produce_Vulture_jButton13 = new javax.swing.JButton();
        produce_Marin_jButton14 = new javax.swing.JButton();
        produce_Firebat_jButton15 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        produce_SCV_jButton29 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        build_SupplyDepot_jButton16 = new javax.swing.JButton();
        build_Refinery_jButton17 = new javax.swing.JButton();
        build_Barrack_jButton18 = new javax.swing.JButton();
        build_EngineeringBay_jButton19 = new javax.swing.JButton();
        build_Academy_jButton20 = new javax.swing.JButton();
        build_Factory_jButton21 = new javax.swing.JButton();
        build_MachineShop_jButton22 = new javax.swing.JButton();
        build_Starport_jButton23 = new javax.swing.JButton();
        build_ControlTower_jButton24 = new javax.swing.JButton();
        build_Armory_jButton25 = new javax.swing.JButton();
        build_ScienceFacility_jButton26 = new javax.swing.JButton();
        build_CovertOps_jButton27 = new javax.swing.JButton();
        build_PhysicsLab_jButton28 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        turn_jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        playerName_jLabel11 = new javax.swing.JLabel();
        minaral_jLabel12 = new javax.swing.JLabel();
        gas_jLabel15 = new javax.swing.JLabel();
        trun_jLabel16 = new javax.swing.JLabel();

        popupMenu1.setLabel("popupMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel6.setPreferredSize(new java.awt.Dimension(2050, 2050));
        jPanel6.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2050, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2050, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel6);

        Move_jButton1.setText("이동하기");
        Move_jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Move_jButton1ActionPerformed(evt);
            }
        });

        TurnEnd_jButton4.setText("턴종료");
        TurnEnd_jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TurnEnd_jButton4ActionPerformed(evt);
            }
        });

        GiveUp_jButton5.setText("항복하기");
        GiveUp_jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GiveUp_jButton5ActionPerformed(evt);
            }
        });

        AirAttack_jButton30.setText("공중공격");
        AirAttack_jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AirAttack_jButton30ActionPerformed(evt);
            }
        });

        Attack_jButton2.setText("지상공격");
        Attack_jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Attack_jButton2ActionPerformed(evt);
            }
        });

        Special_jButton31.setText("특수능력");
        Special_jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Special_jButton31ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Special_jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Move_jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Attack_jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AirAttack_jButton30)
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(TurnEnd_jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GiveUp_jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Move_jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AirAttack_jButton30)
                    .addComponent(Attack_jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Special_jButton31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TurnEnd_jButton4)
                    .addComponent(GiveUp_jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("유닛정보");

        jLabel4.setText("이름 : ");

        jLabel5.setText("체력 :");

        jLabel6.setText("특수능력 : ");

        unitName_jLabel17.setText(" ");

        hp_totalHp_jLabel18.setText(" ");

        special_jLabel19.setText(" ");

        dam_def_jLabel20.setText(" ");

        shield_jLabel21.setText(" ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(dam_def_jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unitName_jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hp_totalHp_jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(shield_jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(special_jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(unitName_jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(hp_totalHp_jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dam_def_jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(shield_jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(special_jLabel19)))
        );

        jPanel4.setName(""); // NOI18N

        produce_ScienceVessel_Button6.setText("jButton6");
        produce_ScienceVessel_Button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produce_ScienceVessel_Button6ActionPerformed(evt);
            }
        });

        produce_DropShip_jButton7.setText("jButton6");
        produce_DropShip_jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produce_DropShip_jButton7ActionPerformed(evt);
            }
        });

        produce_BattleCruiser_jButton8.setText("jButton6");
        produce_BattleCruiser_jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produce_BattleCruiser_jButton8ActionPerformed(evt);
            }
        });

        produce_Wraith_jButton9.setText("jButton6");
        produce_Wraith_jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produce_Wraith_jButton9ActionPerformed(evt);
            }
        });

        produce_SiegeTank_jButton10.setText("jButton6");
        produce_SiegeTank_jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produce_SiegeTank_jButton10ActionPerformed(evt);
            }
        });

        produce_Goliath_jButton11.setText("jButton6");
        produce_Goliath_jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produce_Goliath_jButton11ActionPerformed(evt);
            }
        });

        produce_Ghost_jButton12.setText("jButton6");
        produce_Ghost_jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produce_Ghost_jButton12ActionPerformed(evt);
            }
        });

        produce_Vulture_jButton13.setText("jButton6");
        produce_Vulture_jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produce_Vulture_jButton13ActionPerformed(evt);
            }
        });

        produce_Marin_jButton14.setText("jButton6");
        produce_Marin_jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produce_Marin_jButton14ActionPerformed(evt);
            }
        });

        produce_Firebat_jButton15.setText("jButton6");
        produce_Firebat_jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produce_Firebat_jButton15ActionPerformed(evt);
            }
        });

        jLabel1.setText("유닛생산");

        produce_SCV_jButton29.setText("jButton6");
        produce_SCV_jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produce_SCV_jButton29ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(produce_Marin_jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(produce_Ghost_jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(produce_SiegeTank_jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(produce_Wraith_jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(produce_BattleCruiser_jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(produce_SCV_jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(produce_Firebat_jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(produce_Vulture_jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(produce_Goliath_jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(produce_ScienceVessel_Button6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(produce_DropShip_jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(produce_BattleCruiser_jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produce_Wraith_jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produce_SiegeTank_jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produce_Ghost_jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produce_Marin_jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(produce_SCV_jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produce_Firebat_jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produce_Vulture_jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produce_Goliath_jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produce_ScienceVessel_Button6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produce_DropShip_jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("건물생산");

        build_SupplyDepot_jButton16.setText("jButton16");
        build_SupplyDepot_jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                build_SupplyDepot_jButton16ActionPerformed(evt);
            }
        });

        build_Refinery_jButton17.setText("jButton16");
        build_Refinery_jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                build_Refinery_jButton17ActionPerformed(evt);
            }
        });

        build_Barrack_jButton18.setText("jButton16");
        build_Barrack_jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                build_Barrack_jButton18ActionPerformed(evt);
            }
        });

        build_EngineeringBay_jButton19.setText("jButton16");
        build_EngineeringBay_jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                build_EngineeringBay_jButton19ActionPerformed(evt);
            }
        });

        build_Academy_jButton20.setText("jButton16");
        build_Academy_jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                build_Academy_jButton20ActionPerformed(evt);
            }
        });

        build_Factory_jButton21.setText("jButton16");
        build_Factory_jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                build_Factory_jButton21ActionPerformed(evt);
            }
        });

        build_MachineShop_jButton22.setText("jButton16");
        build_MachineShop_jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                build_MachineShop_jButton22ActionPerformed(evt);
            }
        });

        build_Starport_jButton23.setText("jButton16");
        build_Starport_jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                build_Starport_jButton23ActionPerformed(evt);
            }
        });

        build_ControlTower_jButton24.setText("jButton16");
        build_ControlTower_jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                build_ControlTower_jButton24ActionPerformed(evt);
            }
        });

        build_Armory_jButton25.setText("jButton16");
        build_Armory_jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                build_Armory_jButton25ActionPerformed(evt);
            }
        });

        build_ScienceFacility_jButton26.setText("jButton16");
        build_ScienceFacility_jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                build_ScienceFacility_jButton26ActionPerformed(evt);
            }
        });

        build_CovertOps_jButton27.setText("jButton16");
        build_CovertOps_jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                build_CovertOps_jButton27ActionPerformed(evt);
            }
        });

        build_PhysicsLab_jButton28.setText("jButton16");
        build_PhysicsLab_jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                build_PhysicsLab_jButton28ActionPerformed(evt);
            }
        });

        jLabel14.setText("턴 :");

        turn_jLabel7.setText(" ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(build_SupplyDepot_jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(build_Refinery_jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(build_MachineShop_jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(build_Starport_jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(build_EngineeringBay_jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(build_Academy_jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(build_Armory_jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(build_ScienceFacility_jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(build_PhysicsLab_jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(build_Barrack_jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(build_Factory_jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(build_ControlTower_jButton24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(build_CovertOps_jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(turn_jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(build_SupplyDepot_jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(build_Refinery_jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(build_Barrack_jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(build_EngineeringBay_jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(build_Academy_jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(build_Factory_jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(build_MachineShop_jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(build_Starport_jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(build_ControlTower_jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(build_Armory_jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(build_ScienceFacility_jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(build_CovertOps_jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(build_PhysicsLab_jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(turn_jLabel7)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel8.setText("플레이어 이름 :");

        jLabel9.setText("미네랄 :");

        jLabel10.setText("가스 :");

        jLabel13.setText("인구수 :");

        playerName_jLabel11.setText(" ");

        minaral_jLabel12.setText(" ");

        gas_jLabel15.setText(" ");

        trun_jLabel16.setText(" ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playerName_jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(trun_jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(18, 18, 18)
                            .addComponent(gas_jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(minaral_jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(playerName_jLabel11))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(minaral_jLabel12))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(gas_jLabel15))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(trun_jLabel16))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void produce_SCV_jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produce_SCV_jButton29ActionPerformed
        // TODO add your handling code here:
        player.produceUnitcheck(0);
        refreshLabel();

    }//GEN-LAST:event_produce_SCV_jButton29ActionPerformed

    private void TurnEnd_jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TurnEnd_jButton4ActionPerformed
        // TODO add your handling code here:
        player.turnEnd();
        this.setVisible(false);
        MainClass.startUI(otherplayer);

    }//GEN-LAST:event_TurnEnd_jButton4ActionPerformed

    private void build_SupplyDepot_jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_build_SupplyDepot_jButton16ActionPerformed
        // TODO add your handling code here:
        player.buildcheck(0);
        refreshLabel();
    }//GEN-LAST:event_build_SupplyDepot_jButton16ActionPerformed

    private void build_Refinery_jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_build_Refinery_jButton17ActionPerformed
        // TODO add your handling code here:
        player.buildcheck(1);
        refreshLabel();
    }//GEN-LAST:event_build_Refinery_jButton17ActionPerformed

    private void build_Barrack_jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_build_Barrack_jButton18ActionPerformed
        // TODO add your handling code here:
        if (player.buildcheck(2)) {
            produce_Marin_jButton14.setIcon(image[24]);
            build_Academy_jButton20.setIcon(image[33]);
            build_Factory_jButton21.setIcon(image[34]);
        }
        refreshLabel();
    }//GEN-LAST:event_build_Barrack_jButton18ActionPerformed

    private void build_EngineeringBay_jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_build_EngineeringBay_jButton19ActionPerformed
        // TODO add your handling code here:
        player.buildcheck(3);
        refreshLabel();
    }//GEN-LAST:event_build_EngineeringBay_jButton19ActionPerformed

    private void build_Academy_jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_build_Academy_jButton20ActionPerformed
        // TODO add your handling code here:
        if (player.buildcheck(4)) {
            produce_Firebat_jButton15.setIcon(image[25]);
            if (player.isBuilt(11)) {
                produce_Ghost_jButton12.setIcon(image[26]);
            }
        }
        refreshLabel();
    }//GEN-LAST:event_build_Academy_jButton20ActionPerformed

    private void build_Factory_jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_build_Factory_jButton21ActionPerformed
        // TODO add your handling code here:
        if (player.buildcheck(5)) {
            produce_Vulture_jButton13.setIcon(image[27]);
            build_Starport_jButton23.setIcon(image[37]);
            build_MachineShop_jButton22.setIcon(image[35]);
            build_Starport_jButton23.setIcon(image[37]);
            build_Armory_jButton25.setIcon(image[36]);
        }
        refreshLabel();
    }//GEN-LAST:event_build_Factory_jButton21ActionPerformed

    private void build_MachineShop_jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_build_MachineShop_jButton22ActionPerformed
        // TODO add your handling code here:
        if (player.buildcheck(6)) {
            produce_SiegeTank_jButton10.setIcon(image[28]);
        }
        refreshLabel();
    }//GEN-LAST:event_build_MachineShop_jButton22ActionPerformed

    private void build_Starport_jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_build_Starport_jButton23ActionPerformed
        // TODO add your handling code here:
        if (player.buildcheck(8)) {
            produce_Wraith_jButton9.setIcon(image[30]);
            build_ControlTower_jButton24.setIcon(image[38]);
            build_ScienceFacility_jButton26.setIcon(image[39]);
        }
        refreshLabel();
    }//GEN-LAST:event_build_Starport_jButton23ActionPerformed

    private void build_ControlTower_jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_build_ControlTower_jButton24ActionPerformed
        // TODO add your handling code here:
        if (player.buildcheck(9)) {
            if (player.isBuilt(10)) {
                produce_ScienceVessel_Button6.setIcon(image[31]);
                if (player.isBuilt(12)) {
                    produce_BattleCruiser_jButton8.setIcon(image[32]);
                }
            }
        }
        refreshLabel();
    }//GEN-LAST:event_build_ControlTower_jButton24ActionPerformed

    private void build_Armory_jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_build_Armory_jButton25ActionPerformed
        // TODO add your handling code here:
        if (player.buildcheck(7)) {
            produce_Goliath_jButton11.setIcon(image[29]);
        }
        refreshLabel();
    }//GEN-LAST:event_build_Armory_jButton25ActionPerformed

    private void build_ScienceFacility_jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_build_ScienceFacility_jButton26ActionPerformed
        // TODO add your handling code here:
        if (player.buildcheck(10)) {
            if (player.isBuilt(9)) {
                produce_ScienceVessel_Button6.setIcon(image[31]);
                build_CovertOps_jButton27.setIcon(image[40]);
                build_PhysicsLab_jButton28.setIcon(image[41]);
            }
        }
        refreshLabel();
    }//GEN-LAST:event_build_ScienceFacility_jButton26ActionPerformed

    private void build_CovertOps_jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_build_CovertOps_jButton27ActionPerformed
        // TODO add your handling code here:
        if (player.buildcheck(11)) {
            if (player.isBuilt(4)) {
                produce_Ghost_jButton12.setIcon(image[26]);
            }
        }
        refreshLabel();
    }//GEN-LAST:event_build_CovertOps_jButton27ActionPerformed

    private void build_PhysicsLab_jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_build_PhysicsLab_jButton28ActionPerformed
        // TODO add your handling code here:
        if (player.buildcheck(12)) {
            if (player.isBuilt(9)) {
                produce_BattleCruiser_jButton8.setIcon(image[32]);
            }
        }
        refreshLabel();
    }//GEN-LAST:event_build_PhysicsLab_jButton28ActionPerformed

    private void GiveUp_jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GiveUp_jButton5ActionPerformed
        // TODO add your handling code here:
        player.giveUp();
        MainClass.gameOver(otherplayer.player.get_Name());
    }//GEN-LAST:event_GiveUp_jButton5ActionPerformed

    public void gameover(String playerName) {
        MainClass.gameOver(playerName);
    }

    private void produce_Marin_jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produce_Marin_jButton14ActionPerformed
        // TODO add your handling code here:
        player.produceUnitcheck(1);
        refreshLabel();
        mapping();
    }//GEN-LAST:event_produce_Marin_jButton14ActionPerformed

    private void produce_Firebat_jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produce_Firebat_jButton15ActionPerformed
        // TODO add your handling code here:
        player.produceUnitcheck(2);
        refreshLabel();
        mapping();
    }//GEN-LAST:event_produce_Firebat_jButton15ActionPerformed

    private void produce_Ghost_jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produce_Ghost_jButton12ActionPerformed
        // TODO add your handling code here:
        player.produceUnitcheck(3);
        refreshLabel();
        mapping();
    }//GEN-LAST:event_produce_Ghost_jButton12ActionPerformed

    private void produce_Vulture_jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produce_Vulture_jButton13ActionPerformed
        // TODO add your handling code here:
        player.produceUnitcheck(4);
        refreshLabel();
        mapping();

    }//GEN-LAST:event_produce_Vulture_jButton13ActionPerformed

    private void produce_SiegeTank_jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produce_SiegeTank_jButton10ActionPerformed
        // TODO add your handling code here:
        player.produceUnitcheck(5);
        refreshLabel();
        mapping();
    }//GEN-LAST:event_produce_SiegeTank_jButton10ActionPerformed

    private void produce_Goliath_jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produce_Goliath_jButton11ActionPerformed
        // TODO add your handling code here:
        player.produceUnitcheck(6);
        refreshLabel();
        mapping();
    }//GEN-LAST:event_produce_Goliath_jButton11ActionPerformed

    private void produce_Wraith_jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produce_Wraith_jButton9ActionPerformed
        // TODO add your handling code here:
        player.produceUnitcheck(7);
        refreshLabel();
        mapping();
    }//GEN-LAST:event_produce_Wraith_jButton9ActionPerformed

    private void produce_ScienceVessel_Button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produce_ScienceVessel_Button6ActionPerformed
        // TODO add your handling code here:
        player.produceUnitcheck(9);
        refreshLabel();
        mapping();
    }//GEN-LAST:event_produce_ScienceVessel_Button6ActionPerformed

    private void produce_BattleCruiser_jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produce_BattleCruiser_jButton8ActionPerformed
        // TODO add your handling code here:
        player.produceUnitcheck(10);
        refreshLabel();
        mapping();
    }//GEN-LAST:event_produce_BattleCruiser_jButton8ActionPerformed

    private void produce_DropShip_jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produce_DropShip_jButton7ActionPerformed
        // TODO add your handling code here:
        player.produceUnitcheck(8);
        refreshLabel();
        mapping();
    }//GEN-LAST:event_produce_DropShip_jButton7ActionPerformed

    private void Move_jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Move_jButton1ActionPerformed
        // TODO add your handling code here:
        mapping();
        if (unit != null && player.get_Player() == unit.get_Player()) {
            Position[] p;
            p = unit.move();
            if (p != null) {
                for (Position pos : p) {
                    button[pos.get_Y()][pos.get_X()].setIcon(mapImage[1]);
                    button[pos.get_Y()][pos.get_X()].setEnabled(true);
                    button[pos.get_Y()][pos.get_X()].moveOk = true;
                }
            }
        }
    }//GEN-LAST:event_Move_jButton1ActionPerformed

    private void Attack_jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Attack_jButton2ActionPerformed
        // TODO add your handling code here:
        mapping();
        if (unit != null && player.get_Player() == unit.get_Player()) {
            Position[] p, range;
            range = unit.getrange(unit.get_Range());
            if (range != null) {
                for (Position pos : range) {
                    if (game.map.unitLink[pos.get_Y()][pos.get_X()] == null) {
                        button[pos.get_Y()][pos.get_X()].setIcon(mapImage[3]);
                        button[pos.get_Y()][pos.get_X()].setEnabled(true);
                    }
                }
                p = unit.groundAttack();
                if (p != null) {
                    for (Position pos : p) {
                        button[pos.get_Y()][pos.get_X()].setEnabled(true);
                        button[pos.get_Y()][pos.get_X()].attackOk = true;
                    }
                }
            }
        }
    }//GEN-LAST:event_Attack_jButton2ActionPerformed

    private void AirAttack_jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AirAttack_jButton30ActionPerformed
        // TODO add your handling code here:
        mapping();
        if (unit != null && player.get_Player() == unit.get_Player()) {
            Position[] p, range;
            range = unit.getrange(unit.get_AirAange());
            if (range != null) {
                for (Position pos : range) {
                    if (game.map.unitLink[pos.get_Y()][pos.get_X()] == null) {
                        button[pos.get_Y()][pos.get_X()].setIcon(mapImage[3]);
                        button[pos.get_Y()][pos.get_X()].setEnabled(true);
                    }
                }
                p = unit.airAttack();
                if (p != null) {
                    for (Position pos : p) {
                        button[pos.get_Y()][pos.get_X()].setEnabled(true);
                        button[pos.get_Y()][pos.get_X()].attackOk = true;
                    }
                }
            }
        }
    }//GEN-LAST:event_AirAttack_jButton30ActionPerformed

    private void Special_jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Special_jButton31ActionPerformed
        // TODO add your handling code here:
        mapping();

        if (unit != null && player.get_Player() == unit.get_Player()) {
            Position[] p, range;
            range = unit.specialRange();
            if (range != null) {

                for (Position pos : range) {
                    if (game.map.unitLink[pos.get_Y()][pos.get_X()] == null) {
                        button[pos.get_Y()][pos.get_X()].setIcon(mapImage[5]);
                        button[pos.get_Y()][pos.get_X()].setEnabled(true);
                    }
                }
            }

            p = unit.specialAttack(range);
            unit.specialAttack(player.get_Turn());
            if (p != null) {
                for (Position pos : p) {
                    button[pos.get_Y()][pos.get_X()].setEnabled(true);
                    button[pos.get_Y()][pos.get_X()].spcOk = true;
                }
            }
            button[unit.get_Pos().get_Y()][unit.get_Pos().get_X()].setIcon(unit.getImage());
            refreshUnitLabel();
        }
    }//GEN-LAST:event_Special_jButton31ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AirAttack_jButton30;
    private javax.swing.JButton Attack_jButton2;
    private javax.swing.JButton GiveUp_jButton5;
    private javax.swing.JButton Move_jButton1;
    private javax.swing.JButton Special_jButton31;
    private javax.swing.JButton TurnEnd_jButton4;
    private javax.swing.JButton build_Academy_jButton20;
    private javax.swing.JButton build_Armory_jButton25;
    private javax.swing.JButton build_Barrack_jButton18;
    private javax.swing.JButton build_ControlTower_jButton24;
    private javax.swing.JButton build_CovertOps_jButton27;
    private javax.swing.JButton build_EngineeringBay_jButton19;
    private javax.swing.JButton build_Factory_jButton21;
    private javax.swing.JButton build_MachineShop_jButton22;
    private javax.swing.JButton build_PhysicsLab_jButton28;
    private javax.swing.JButton build_Refinery_jButton17;
    private javax.swing.JButton build_ScienceFacility_jButton26;
    private javax.swing.JButton build_Starport_jButton23;
    private javax.swing.JButton build_SupplyDepot_jButton16;
    private javax.swing.JLabel dam_def_jLabel20;
    private javax.swing.JLabel gas_jLabel15;
    private javax.swing.JLabel hp_totalHp_jLabel18;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel minaral_jLabel12;
    private javax.swing.JLabel playerName_jLabel11;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JButton produce_BattleCruiser_jButton8;
    private javax.swing.JButton produce_DropShip_jButton7;
    private javax.swing.JButton produce_Firebat_jButton15;
    private javax.swing.JButton produce_Ghost_jButton12;
    private javax.swing.JButton produce_Goliath_jButton11;
    private javax.swing.JButton produce_Marin_jButton14;
    private javax.swing.JButton produce_SCV_jButton29;
    private javax.swing.JButton produce_ScienceVessel_Button6;
    private javax.swing.JButton produce_SiegeTank_jButton10;
    private javax.swing.JButton produce_Vulture_jButton13;
    private javax.swing.JButton produce_Wraith_jButton9;
    private javax.swing.JLabel shield_jLabel21;
    private javax.swing.JLabel special_jLabel19;
    private javax.swing.JLabel trun_jLabel16;
    private javax.swing.JLabel turn_jLabel7;
    private javax.swing.JLabel unitName_jLabel17;
    // End of variables declaration//GEN-END:variables

}
