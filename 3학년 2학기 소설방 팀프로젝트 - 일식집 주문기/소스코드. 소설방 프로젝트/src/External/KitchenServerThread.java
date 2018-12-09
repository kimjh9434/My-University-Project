package External;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * Created by skrud on 2017-11-30.
 */
public class KitchenServerThread extends Thread {

    private Socket socket; // 주 소켓

    private DataInputStream reader; // 소켓의 입력 스트림

    KitchenServerThread(Socket socket) {
        System.out.println("주방 접속");
        this.socket = socket;

        try {
            reader = new DataInputStream(socket.getInputStream());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void run() {
        // 수신 메시지와 파싱 메시지 처리하는 변수 선언
        String msg;
        String[] rmsg;

        while (true) {
            try {
                msg = reader.readUTF();
//                System.out.println("Kitchen Server Thread: 주문기로부터 받은 값 : " );
//                System.out.println(msg);
                rmsg = msg.split("/"); // '/' 구분자를 기준으로 메시지를 문자열 배열로 파싱

                System.out.println(rmsg[0]);
                manuList(rmsg[1]);
                System.out.println("==================================");
                
            } catch (Exception e) {
            }
        }
    }
	// 메뉴 리스트에서 메뉴들을 추출하여 menus에 추가한다.
	private void manuList(String msg) {
		StringTokenizer st = new StringTokenizer(msg, "\t");
		int i=1;
		while (st.hasMoreElements()) {
			System.out.println("메뉴 " + i + " : "+ st.nextToken());
			i++;
		}
	}

}