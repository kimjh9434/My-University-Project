package External;

import java.net.ServerSocket;
import java.net.Socket;

public class Kitchen {

    private ServerSocket server; // 서버 소켓

    public void startServer() {
        try {
            server = new ServerSocket(6001);
            System.out.println("주방쪽 서버소켓[PortNum = 6001]이 생성되었습니다.");
            while (true) {
                // 클라이언트와 연결된 스레드를 얻는다.
                Socket socket = server.accept(); // 주 소켓
                KitchenServerThread kitchenServerThread= new KitchenServerThread(socket);
                kitchenServerThread.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Kitchen kitchenServer= new Kitchen();
        kitchenServer.startServer();
    }
}