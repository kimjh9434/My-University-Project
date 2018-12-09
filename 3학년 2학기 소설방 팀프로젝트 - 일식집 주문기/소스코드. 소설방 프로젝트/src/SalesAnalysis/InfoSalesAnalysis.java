package SalesAnalysis;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import OrderMachine.SalesInfo;

public class InfoSalesAnalysis extends SalesAnalysis{
	private String name;
	ClientThread clientThread;
	
    public InfoSalesAnalysis (String name) {
        this.name = name;
        clientThread = new ClientThread();
        salesInfos = new Vector<SalesInfo>();
    }

    @Override
    public String getName() {
        return this.name;
    }

	@Override
	public boolean salesAnalysis() {
		boolean isAnalysis = false;
		System.out.println("판매 정보를 분석하라고 전송해야함 ");
		if(clientThread.connect()){// 소켓 통신
    		System.out.println("판매 정보 회사 서버에 연결 성공");
    		isAnalysis = clientThread.SendSalesAnalysis(salesInfos);
    	}else{
    		JOptionPane.showMessageDialog(null, "서버가 켜져있지 않던지, 네트워크에 연결되어있지 않습니다.", "서버 접속 실패", JOptionPane.WARNING_MESSAGE);
    	}
		// 쫙 보내고, 그쪽에서 쫙 받아서 분석후, 
		// 이쪽은 정보를 받아서 보여줌
		return isAnalysis;
	}

	@Override
	public void addSalesInfos(SalesInfo salesInfo) {
		salesInfos.add(salesInfo);
	}
}
