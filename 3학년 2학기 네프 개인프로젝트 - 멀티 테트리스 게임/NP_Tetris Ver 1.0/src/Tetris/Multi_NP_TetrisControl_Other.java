package Tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Multi_NP_TetrisControl_Other implements Runnable {

	public static final int UP = KeyEvent.VK_UP; // 방향키 위키
	public static final int DOWN = KeyEvent.VK_DOWN; // 방향키 아래키
	public static final int LEFT = KeyEvent.VK_LEFT; // 방향키 왼쪽키
	public static final int RIGHT = KeyEvent.VK_RIGHT; // 방향키 오른쪽키
	public static final int ENTER = KeyEvent.VK_ENTER; // 엔터키

	boolean isGameOver; // 게임 오버 여부
	boolean isPauseGame; // 게임 일시정지 여부

	Multi_NP_Tetrimino tetrimino; // '현재 떨어지고 있는' 테트리미노
	Multi_NP_Tetrimino tetriminoSaved;// 테트리미노 킵핑(저장)
	boolean tetrisCanSave; // 테트리미노 킵핑(저장) 가능여부

	///////////////////////////////////////////////////

	int gridWidth; // 격자무늬 가로칸 - 10칸
	int gridHeight;// 격자무늬 세로칸 - 20칸
	int gridHidden; // 격자무늬 숨겨진칸
	int gridStartX; // 격자무늬 시작가로위치
	int gridStartY; // 격자무늬 시작가로위치

	///////////////////////////////////////////////////

	Color[] colors;

	///////////////////////////////////////////////////

	int speedDelay; // 테트리미노가 떨어지는 간격 (ms between steps 초간 간격)
	int[][] grid; // 격자무늬 배열 설정 grid[gridWidth][gridHeight] = grid[10][20]
	int rectWidth, rectHeight; // 직사각형 가로,세로
	long startTime; // 측정 시작 시각
	long endTime; // 측정 종료 시각

	///////////////////////////////////////////////////

	int maxQueue; // 테트리스 대기목록 저장한도
	Multi_NP_Tetrimino[] tetrisQueue; // 테트리스 대기목록 tetrisQueue[3]
	int queueX; // 예비목록 보여줄 기준 x좌표
	int queueY; // 예비목록 보여줄 기준 y좌표

	///////////////////////////////////////////////////

	int level; // 레벨 - totalBreakLine를 기준으로 10으로 나눈 값이다. [원래 원작은 30으로 나눠야 함]
	int score; // 점수
	int totalBreakLine; // 게임진행동안 껜 총 줄수
	int breakLine; // 한번 테트리미노가 깬 줄의 수
	int tetrisisCount; // 테트리스 수
	int tripleCount; // 트리플 수
	int doubleCount; // 더블 수
	int monoCount; // 모노 수

	int tetriminoNum = 0;

	int playerNum;
	String playerName;

	public Multi_NP_TetrisControl_Other(int playerNum, String playerName) {
		tetrimino = new Multi_NP_Tetrimino(tetriminoNum); // 전역변수에 초기값 설정
		tetriminoNum++;
		tetriminoSaved = new Multi_NP_Tetrimino(tetriminoNum);// 전역변수에 초기값 설정
		tetriminoNum++;

		///////////////////////////////////////////////////

		gridWidth = 10; // 격자무늬 가로칸 - 10칸
		gridHeight = 20;// 격자무늬 세로칸 - 20칸
		gridHidden = 2; // 격자무늬 숨겨진칸
		gridStartX = 0; // 격자무늬 시작가로위치
		gridStartY = 20; // 격자무늬 시작세로위치

		this.playerNum = playerNum;
		this.playerName = playerName;

		switch (playerNum) {
		case 2:
			gridStartX += 20;
			gridStartY += 30;
			break;
		case 3:
			gridStartX += 140;
			gridStartY += 30;
			break;
		case 4:
			gridStartX += 20;
			gridStartY += 270;
			break;
		case 5:
			gridStartX += 140;
			gridStartY += 270;
			break;
		}

		///////////////////////////////////////////////////

		colors = new Color[10];
		colors[0] = new Color(128, 128, 128); // gray
		colors[1] = new Color(0, 200, 255); // light blue
		colors[2] = new Color(255, 150, 0); // orange
		colors[3] = new Color(40, 0, 240); // dark blue
		colors[4] = new Color(255, 255, 0); // yellow
		colors[5] = new Color(0, 200, 0); // green
		colors[6] = new Color(210, 0, 240); // purple
		colors[7] = new Color(255, 0, 0); // red
		colors[8] = new Color(0, 0, 0); // black
		colors[9] = new Color(64, 64, 64); // dark gray 그림자

		///////////////////////////////////////////////////

		grid = new int[10][20]; // 격자무늬 배열 설정 grid[gridWidth][gridHeight][]

		///////////////////////////////////////////////////

		maxQueue = 3; // 테트리스 대기목록 저장한도
		tetrisQueue = new Multi_NP_Tetrimino[3];// 테트리스 대기목록
		queueX = 28; // 예비목록 보여줄 기준 x좌표
		queueY = 6; // 예비목록 보여줄 기준 y좌표

		GameSeting();// 게임을 셋팅한다.
	}

	public void keyPressed(int keyCode) {

		if (!isGameOver)// 게임이 돌아가고 있다면,
		{
			// 어떤 key값을 입력받았는지 확인하기 위한 임시 코드
			System.out.println(" 입력받은 키값은 : " + keyCode);
		}
		if (!tetrimino.getIsLocked()) // 테트리미노가 잠겨있지 않다면,
		{

			if (keyCode == LEFT && tetrisCheckMove(-1, 0)) // MOVE LEFT
			{
				tetrimino.moveLeft();// tetrimino.posX--;
			}
			// MOVE RIGHT
			else if (keyCode == RIGHT && tetrisCheckMove(1, 0)) {
				tetrimino.moveRight();// tetrimino.posX++;
			} else if (keyCode == DOWN) // DOWN
			{
				tetrimino.setIsDownStep(true);
			} else if (keyCode == UP) // UP
			{
				tetrimino.setIsDrop(true);
			} else if ((keyCode == 'w' || keyCode == 'W') && tetrisCanSave)
			{
				tetrisSave();
			}
			// ROTATE LEFT
			else if ((keyCode == 'q' || keyCode == 'Q') && tetrisCheckRotate(-1)) {
				tetrimino.turnLeft();
			} else if ((keyCode == 'e' || keyCode == 'E') && tetrisCheckRotate(+1)) {
				tetrimino.turnRight();
			}
		}
	}

	// 화면에 그려줌
	public void TetrisStart() {
		// TODO Auto-generated method stub

		startTime = System.currentTimeMillis(); // 시간 체크 시작

		while (!isGameOver) { // 게임오버가 아닌동안 반복한다.
			if (!isPauseGame) // 게임을 일시정지한 상황이 아니라면,
			{
				levelCheck();// 레벨 확인 함수

				GameImplementation();// 실질적인 게임내부 구현 함수
			}
		}
	}

	public void GameSeting() {
		// TODO Auto-generated method stub
		// 전역변수 초기화
		isGameOver = false;
		isPauseGame = false;

		score = 0;
		totalBreakLine = 0;
		tetrisisCount = 0;
		tripleCount = 0;
		doubleCount = 0;
		monoCount = 0;

		// 격자무늬 이차원 배열 초기화
		for (int x = 0; x < gridWidth; x++) {
			for (int y = 0; y < gridHeight; y++) {
				grid[x][y] = 0;
			}
		}

		rectWidth = 10; // 10
		rectHeight = 10;// 10
		queueX = 11 + gridStartX + rectWidth * gridWidth; // 11+0+20*20 = 411
		queueY = 10;

		// 테트리스 대기목록 설정
		for (int i = 0; i < maxQueue; i++) {
			tetrisQueue[i] = new Multi_NP_Tetrimino(tetriminoNum);
			tetriminoNum++;
		}

		// 키핑 테트리미노 설정
		tetriminoSaved.setType(7);
		tetrisCanSave = true;

		tetrisNew(); // 첫번째 테트리미노 불러오기
		// startTime = System.currentTimeMillis(); // 시간 체크 시작
	}

	public void tetrisNew() {
		tetrimino = tetrisQueue[0];// 현재 테트리미노를 대기목록 첫번째로 갈아치운다.

		for (int i = 0; i < maxQueue - 1; i++)// 대기목록을 한칸씩 앞당긴다.
		{
			tetrisQueue[i] = tetrisQueue[i + 1];
		}

		tetrisQueue[maxQueue - 1] = new Multi_NP_Tetrimino(tetriminoNum);
		tetriminoNum++;
	}

	public void levelCheck() {
		// 난이도에 따른 레벨 설정 구간
		level = totalBreakLine / 10 + 1; // 원래라면 30으로 나눠야 함. [추후에 수정할 것]
		if (level <= 7) {
			speedDelay = 550 - 50 * level;
		} else if (level <= 8) {
			speedDelay = 200 - 40 * (level - 7);
		} else {
			speedDelay = 160 - 10 * (level - 9);
		}
	}

	public synchronized void paintComponent(Graphics g) {

		BackGround(g);// 부가적인 뒷 배경그려주는 함수

		renderGrid(g); // 테트리스 전체 판 나타내기

		if (isGameOver)// 게임 오버시
		{
			renderGameOver(g); // 게임오버화면을 보여준다.
		}
	}

	public void BackGround(Graphics g) {
		g.setColor(colors[8]);// SetColor(WHITE, BLACK);
		g.setFont(new Font("Dialog", Font.BOLD, 12));
		g.drawString(playerName, gridStartX, gridStartY - 30);
	}

	public void GameImplementation() {
		if (!isGameOver && (System.currentTimeMillis() - endTime > speedDelay || tetrimino.getIsDownStep()
				|| tetrimino.getIsDrop()))// 게임오버가 아닐경우 && (시간이 speedDelay 경과 ||
											// ↓눌렀을때 || ↑눌렀을때 )
		{
			if (!tetrimino.getIsDrop()) // ↑을 안눌렀을때
			{
				tetrisStepDown(); // 테트리미노 떨어트리기
			} else // ↑을 눌렀을때
			{
				while (tetrimino.getIsDrop()) // 테트리미노가 아래로 내려갈수 있는동안 내려간다.
				{
					// 전체가 떨어지기 전에 왼쪽/오른쪽으로 이동 할 수 있어야한다.
					tetrisStepDown(); // 테트리미노 떨어트리기
					tetrimino.setIsDrop(tetrisCheckMove(0, 1));
				}
			}
			tetrimino.setIsDownStep(false);
			tetrimino.setIsDrop(false);
			endTime = System.currentTimeMillis();// 시간 체크
		}

		if (isGameOver)// 게임 오버시
		{
			tetrimino.setIsLocked(true);// 테트리미노를 이동못하게 막는다.
		}
	}

	public void renderGrid(Graphics g) {
		for (int x = 0; x < gridWidth; x++) {
			for (int y = 0; y < gridHeight; y++) {
				// switchFillColor(grid[x][y]);
				// gotoxy(gridStartX + x * 2, gridStartY + (y - gridHidden));
				// cout << "■";
				g.setColor(colors[grid[x][y]]);
				g.fill3DRect(gridStartX + x * rectWidth, gridStartY + (y - gridHidden) * rectHeight, rectWidth,
						rectHeight, true);

			}
		}

		// 테트리미노 내려오는것 나타내기
		int tetriminoSize = tetrimino.getSize();
		for (int y = 0; y < tetriminoSize; y++) {
			for (int x = 0; x < tetriminoSize; x++) {
				if (tetrimino.getGrid(y, x) != 0 && y + tetrimino.getPosY() >= gridHidden) {
					// switchFillColor(tetrimino.getGrid(x, y));
					// gotoxy(gridStartX + (x + tetrimino.getPosX()) * 2,
					// gridStartY + ((y + tetrimino.getPosY() - gridHidden)));
					// cout << "■";
					g.setColor(colors[tetrimino.getGrid(y, x)]);
					g.fill3DRect(gridStartX + (x + tetrimino.getPosX()) * rectWidth,
							gridStartY + ((y + tetrimino.getPosY() - gridHidden) * rectHeight), rectWidth, rectHeight,
							true);
				}
			}
		}
	}

	public boolean tetrisStepDown() {
		boolean moveOK = tetrisCheckMove(0, 1);// 테트리미노를 아래로 한칸 이동할수 있는지 없는지 여부를
												// 먼저 확인한다.

		if (moveOK)// 이동 가능하면,
		{
			tetrimino.moveDown();// 테트리미노를 아래로 한칸 이동한다. - tetrisPosY++;
		} else // 이동 가능하지 않다면, <=> [무언가에 닿은 경우]
		{
			if (tetrimino.getType() == 13)// 여기서 해당 테트리미노가 폭탄이라면,
			{
				bomb(); // 펑!. gird판을 0으로 초기화
				tetrisNew();// 새로운 테트리미노
			} else if (tetrimino.getPosY() != 0)// 테트리미노가 천장에 닿은 경우가 아니라면,
			{
				tetrisFixToGrid(); // 격자무늬 판에 테트리미노를 수정시키기
				gridCheck(); // 격자무늬 판에 가득찬 줄이 있는지 판단하기
				tetrisNew(); // 새로운 테트리스 생성
				tetrisCanSave = true;// 테트리스 킵핑 가능
			} else// 테트리미노가 천장에 닿은 경우라면,
			{
				isGameOver = true; // 게임오버
			}
		}
		return !moveOK;
	}

	public boolean tetrisCheckMove(int xm, int ym) {
		// 현재 테트리미노의 x,y좌표, 회전상태도 넘져준다.
		return tetrisCheckMove(xm, ym, tetrimino.getPosX(), tetrimino.getPosY(), tetrimino.getRotation());
	}

	public boolean tetrisCheckMove(int xm, int ym, int tetriminoX, int tetriminoY, int tetriminoR) {
		boolean moveOK = true;// 이동 가능 여부
		int gridC = 0; // 이탈 여부
		int tetriminoSize = tetrimino.getSize();
		for (int y = 0; y < tetriminoSize; y++) {
			for (int x = 0; x < tetriminoSize; x++) {
				// 테트리미노의 각 블록을 이동한 좌표가 모두 테트리스 판 내무에 들어있는지 확인한다.
				if ((tetriminoY + y + ym < 0 || tetriminoY + y + ym >= gridHeight)
						|| (tetriminoX + x + xm < 0 || tetriminoX + x + xm >= gridWidth)) {
					gridC = 1; // 테트리미노 조각중 하나가 격자무늬 판중 하나를 벗어남.
				} else {
					gridC = grid[tetriminoX + x + xm][tetriminoY + y + ym];
				}
				if (gridC != 0 && tetrimino.getGrid(y, x) != 0) {
					moveOK = false;
				}
			}
		}

		return moveOK;
	}

	public boolean tetrisCheckRotate(int rm) {
		boolean rotateOK = false;// 회전 가능 여부
		int x = tetrimino.getPosX();
		int y = tetrimino.getPosY();
		int originalRotation = tetrimino.getRotation();
		int tetrisR = originalRotation + rm; // 현재 회전 상태에, 원하는 회전 방향을 더한 회전상태값을
												// 얻는다.
		if (tetrisR == 4)
			tetrisR = 0;
		if (tetrisR == -1)
			tetrisR = 3;

		tetrimino.setRotation(tetrisR);
		rotateOK = tetrisCheckMove(0, 0, x, y, tetrisR);// 회전가능한지 여부를 본다.

		if (!rotateOK && tetrisCheckMove(-1, 0, x, y, tetrisR)) {
			x--;
			rotateOK = tetrisCheckMove(0, 0, x, y, tetrisR);
		}
		if (!rotateOK && tetrisCheckMove(-2, 0, x, y, tetrisR)) {
			x = x - 2;
			rotateOK = tetrisCheckMove(0, 0, x, y, tetrisR);
		}
		if (!rotateOK && tetrisCheckMove(+1, 0, x, y, tetrisR)) {
			x++;
			rotateOK = tetrisCheckMove(0, 0, x, y, tetrisR);
		}
		if (!rotateOK && tetrisCheckMove(+2, 0, x, y, tetrisR)) {
			x = x + 2;
			rotateOK = tetrisCheckMove(0, 0, x, y, tetrisR);
		}

		if (rotateOK)// 회전이 된다면,
		{
			tetrimino.setPosX(x);// 문제 없으므로 그대로 수정한다.
		}
		tetrimino.setRotation(originalRotation); // 다시 원상태로 돌린다.

		return rotateOK;// 회전가능여부 반환
	}

	public void tetrisFixToGrid() {
		int tetriminoSize = tetrimino.getSize();
		int posX = tetrimino.getPosX();
		int posY = tetrimino.getPosY();

		for (int y = 0; y < tetriminoSize; y++) {
			for (int x = 0; x < tetriminoSize; x++) {
				if (tetrimino.getGrid(y, x) != 0) {
					grid[posX + x][posY + y] = tetrimino.getGrid(y, x);
				}
			}
		}
	}

	public void gridCheck() {
		boolean removeLine;
		breakLine = 0;

		for (int y = 0; y < gridHeight; y++) {
			removeLine = true;
			for (int x = 0; x < gridWidth; x++) {
				if (grid[x][y] == 0) {
					removeLine = false;
					break;
				}
			}

			// 가득찬 줄 제거
			if (removeLine) {
				breakLine++;
				totalBreakLine++;

				for (int yi = y; yi > 0; yi--) {
					for (int xi = 0; xi < gridWidth; xi++) {
						grid[xi][yi] = grid[xi][yi - 1];
					}
				}
			}
		}
		switch (breakLine) {
		case 0:
			break;
		case 1:
			score = score + 10 * 1;
			monoCount++;
			break;
		case 2:
			score = score + 10 * 4;
			doubleCount++;
			break;
		case 3:
			score = score + 10 * 9;
			tripleCount++;
			break;
		case 4:
			score = score + 10 * 16;
			tetrisisCount++;
			break;
		default:
			break;
		}
	}

	public void bomb() {
		// 격자무늬 객체 초기화
		for (int x = 0; x < gridWidth; x++) {
			for (int y = 0; y < gridHeight; y++) {
				grid[x][y] = 0;
			}
		}

	}

	public void tetrisSave() {
		Multi_NP_Tetrimino temp = tetriminoSaved;// 기존에 저장된 테트리미노를 임시변수로 기억한다.

		tetriminoSaved = tetrimino;// 현재 테트리미노를 저장한다.

		if (temp.getType() == 7)// 기존에 저장된 테트리미노의 유형이 공백이라면,
		{
			tetrisNew();// 현재 저장된 테트리미노의 유형을 저장후, 새로운 테트리미노를 생성한다.
		} else// 무언가 값이 있다면
		{
			tetrimino = temp;// 기존에 저장된 테트리미노를 현재 테트리미노로 바꾼다.
			tetrimino.reSeting();// 위치를 조정한다.
			tetrisCanSave = false; // '테트리미노 저장 불가능'으로 바꾼다.
		}
	}

	public void renderGameOver(Graphics g) {
		Font f = new Font("Arial", Font.ITALIC, 15);
		g.setColor(colors[8]);
		g.setFont(f);
		int x = ((gridWidth * rectWidth) / 2) - 40 + gridStartX;
		int y = ((gridHeight * rectHeight) / 2) + gridStartY - 20;
		g.drawString("Game Over", x, y);// 레벨

		// Sleep(5000);
		tetrimino.setIsLocked(false);
	}

	@Override
	public void run() {
		TetrisStart();
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public String getPlayerName() {
		return playerName;
	}

}
