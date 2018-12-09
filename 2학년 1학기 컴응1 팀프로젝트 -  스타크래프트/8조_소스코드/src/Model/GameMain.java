package Model;

import UnitList.SCV;
import UnitList.Unit;
import UnitList.Vulture;
import UnitList.Wraith;
import java.awt.EventQueue;


public class GameMain
{
	public static Map map;                //맵 static 변수
	public static boolean gameOver=false; //게임오버 static 변수
        public Player player1;                //플레이어1
        public Player player2;                //플레이어2
        
        //GameMain생성자 메소드
	public GameMain(String p1_name,String p2_name){
                map = new Map();
		player1 = new Player(1,p1_name);
                player2 = new Player(2,p2_name);
	}
        
        //한때 콘솔상에서 돌아갈때, 게임의 제어를 담당했던 메인메소드
       /* public static void main(){
            EventQueue.invokeLater(new Runnable() {
            public void run()
            {
		

//		Unit unit = new Vulture(1);
//		unit.set_Pos(37, 22);
//		player1.unitList.list.add(unit);
//		GameMain.map.unitLink[unit.get_Pos().y][unit.get_Pos().x]=unit;
//		unit.move();
//		
//		unit = new Vulture(2);
//		unit.set_Pos(5, 20);
//		player2.unitList.list.add(unit);
//		GameMain.map.unitLink[unit.get_Pos().y][unit.get_Pos().x]=unit;
		
		
		int player = 1;

		while(!gameOver)
		{
			if(player==1)
			{
				System.out.println("player1의 턴 시작");
				//gameOver=start(player1);
				player=2;
			}
			else{
				System.out.println("player2의 턴 시작");
				//gameOver=start(player2);
				player=1;
			}
		}
		System.out.println("Player"+player+"이 승리하셨습니다.");
                }
            });
        }
	public static boolean start(final Player player)
	{ 
            EventQueue.invokeLater(new Runnable() {
            public void run() {
		int menu=0;
		boolean turnEnd=false;
		
		while(!turnEnd && !gameOver)
		{
			//menu = player.choiceMenu();
                        
			switch(menu)
			{
			case 1 : player.produceUnit(); break;
			case 2 : player.build(); break;
			case 3 : player.actUnit(); break;
			case 4 : turnEnd=player.turnEnd(); break;
			case 5 : gameOver=player.giveUp(); break;
			default: break;
			}
		}
		
	}
        });
            return gameOver;
        }*/
}
