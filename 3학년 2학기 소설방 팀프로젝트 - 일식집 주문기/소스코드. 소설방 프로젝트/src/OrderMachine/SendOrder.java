package OrderMachine;

import Menu.Menu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class SendOrder {
   private Socket socket; // 주 소켓
   private DataInputStream reader; // 소켓의 입력 스트림
   private DataOutputStream writer; // 소켓의 출력 스트림

   SendOrder(){
      connect();
   }

   // 여기서 옵저버 패턴?을 적용해서 각각의 주방으로 판매 주문정보들을 보내야함.
   public void sendOrder(SalesInfo salesInfo) {
      try {
         String str  = "주문번호 : " + salesInfo.getOrderNo()+ "/"+
        		 getManuNamesInSalesInfo(salesInfo.getMenus());
         System.out.println(str);
         writer.writeUTF(str);
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   // 주방 서버와의 접속 연결하는 메소드
   public boolean connect() {
      boolean isConnect = false;
      try {
         System.out.println("서버에 연결을 요청합니다.");
         socket = new Socket("127.0.0.1", 6001);

         System.out.println("---주방 서버 접속 성공--");
         isConnect = true;
         reader = new DataInputStream(socket.getInputStream());
         writer = new DataOutputStream(socket.getOutputStream());
      } catch (Exception e) {
         System.out.println("\n\n서버 연결 실패..\n");
      }
      return isConnect;
   }
   private String getManuNamesInSalesInfo(Vector<Menu> menus) {
      StringBuffer sb = new StringBuffer("");
      int size = menus.size();
      for (int i = 0; i < size; i++)
         sb.append(menus.get(i).getDescription() + "\t");
      return sb.toString();
   }
}