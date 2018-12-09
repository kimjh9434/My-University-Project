/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.StartUI;
import Model.*;
//import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author apam
 */

//GUI상에서 전체 제어를 담당하는 클래스
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                StartUI start_game = new StartUI();
                startUI(start_game);

            }
        });
    }

    public static void startUI(StartUI start_game) {
        start_game.setLocation(500, 250);
        start_game.setTitle("시작화면");

        start_game.setResizable(false);

        start_game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        start_game.setVisible(true);
    }

    public static void gameStart(final String p1_name, final String p2_name) {
        final GameMain game = new GameMain(p1_name, p2_name);
        final GameMainUI p1_gui = new GameMainUI(game, game.player1);
        final GameMainUI p2_gui = new GameMainUI(game, game.player2);
        p1_gui.setOther(p2_gui);
        p2_gui.setOther(p1_gui);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                startUI(p1_gui);
            }
        });

    }

    public static void startUI(GameMainUI g) {
        g.setLocation(200, 50);
        g.setTitle("미니스타크래프트");
        g.setResizable(false);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g.refreshLabel();
        g.disableButton();
        g.mapping();
        g.setVisible(true);
    }

    public static void gameOver(String playerName) {
        GameOver game = new GameOver();
        game.setLocation(500, 250);
        System.out.println(playerName);
        game.setTitle(playerName + "가 승리하였습니다 !");
        game.setVisible(true);
    }
}
