package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;
import Model.*;

//GUI상에서 맵 버튼의 클릭과 관련해서 처리하는 클래스
public class mButton extends JButton {

    private int i, j;
    private GameMainUI gui;
    private Map map;
    public boolean moveOk;
    public boolean attackOk;
    public boolean spcOk;
    private ImageIcon pastIcOn;

    public mButton(int i, int j, GameMainUI gui, Map map) {
        this.i = i;
        this.j = j;
        this.gui = gui;
        this.map = map;
        this.addActionListener(new Action());
        this.addMouseListener(new Mouse());
        ImageIcon image = new ImageIcon("C:\\Starcraft_Image\\color.png");
        this.setIcon(image);
        this.setEnabled(false);
        moveOk = false;
        attackOk = false;
        spcOk = false;
        pastIcOn = null;
    }

    private class Mouse extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (gui.button[i][j].attackOk && gui.unit.isAttacked()) {
                if (gui.unit != null) {
                    gui.unit.attack(j, i);
                    if (!gui.get_OtherPlayer().alive()) {
                        gui.gameover(gui.get_Player().get_Name());
                    }
                }
                gui.mapping();
                gui.buttonEnable();
            }
            if (gui.button[i][j].spcOk && gui.unit.isSp()) {
                if (gui.unit != null) {
                    gui.unit.special(j, i);
                    if (!gui.get_OtherPlayer().alive()) {
                        gui.gameover(gui.get_Player().get_Name());
                    }
                }
                gui.mapping();
                gui.buttonEnable();
            }
            if (map.unitLink[i][j] != null) {
                gui.unit = map.unitLink[i][j];
                gui.refreshUnitLabel();
                gui.mapping();
                gui.buttonEnable();
            }
            if (gui.button[i][j].moveOk && gui.unit.isMoved()) {
                if (gui.unit != null) {
                    gui.unit.move(i, j);
                }
                gui.mapping();
                gui.buttonEnable();
            }
        }

        public void mouseEntered(MouseEvent e) {
            if (attackOk == true) {
                pastIcOn = (ImageIcon) gui.button[i][j].getIcon();
                gui.button[i][j].setIcon(new ImageIcon("C:\\Starcraft_Image\\color4.png"));
            }
            if (spcOk == true) {
                pastIcOn = (ImageIcon) gui.button[i][j].getIcon();
                gui.button[i][j].setIcon(new ImageIcon("C:\\Starcraft_Image\\color.png"));
            }
        }

        public void mouseExited(MouseEvent e) {
            if (attackOk == true) {
                gui.button[i][j].setIcon(pastIcOn);
            }
        }
    }

    private class Action implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        }
    }
}
