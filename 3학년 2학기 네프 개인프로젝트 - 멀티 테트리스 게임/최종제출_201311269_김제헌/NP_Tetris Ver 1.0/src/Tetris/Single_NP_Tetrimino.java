package Tetris;

public class Single_NP_Tetrimino {
	
	// 테트리미노 틀. 총 7종류 [어차피 0만 아니면 되지만, 순서 구분을 하기 위해 숫자를 다르게 적었다]
	// gridTemplate [tetrisType] [tetrisRotation] [y] [x]
	static int[][][][] gridTemplate = { // gridTemplate[8][4][4][4]
	{ { { 0,0,0,0 },{ 1,1,1,1 },{ 0,0,0,0 },{ 0,0,0,0 } }, // 0   'ㅡ' 
		{ { 0,0,1,0 },{ 0,0,1,0 },{ 0,0,1,0 },{ 0,0,1,0 } },
		{ { 0,0,0,0 },{ 0,0,0,0 },{ 1,1,1,1 },{ 0,0,0,0 } },
		{ { 0,1,0,0 },{ 0,1,0,0 },{ 0,1,0,0 },{ 0,1,0,0 } } },
		{ { { 2,0,0,0 },{ 2,2,2,0 },{ 0,0,0,0 },{ 0,0,0,0 } }, // 1   'ㄴ' 
		{ { 0,2,2,0 },{ 0,2,0,0 },{ 0,2,0,0 },{ 0,0,0,0 } },
		{ { 0,0,0,0 },{ 2,2,2,0 },{ 0,0,2,0 },{ 0,0,0,0 } },
		{ { 0,2,0,0 },{ 0,2,0,0 },{ 2,2,0,0 },{ 0,0,0,0 } } },
		{ { { 0,0,3,0 },{ 3,3,3,0 },{ 0,0,0,0 },{ 0,0,0,0 } }, // 2   'ㄱ'반대 
		{ { 0,3,0,0 },{ 0,3,0,0 },{ 0,3,3,0 },{ 0,0,0,0 } },
		{ { 0,0,0,0 },{ 3,3,3,0 },{ 3,0,0,0 },{ 0,0,0,0 } },
		{ { 3,3,0,0 },{ 0,3,0,0 },{ 0,3,0,0 },{ 0,0,0,0 } } },
		{ { { 0,4,4,0 },{ 0,4,4,0 },{ 0,0,0,0 },{ 0,0,0,0 } }, // 3   'ㅁ'
		{ { 0,4,4,0 },{ 0,4,4,0 },{ 0,0,0,0 },{ 0,0,0,0 } },
		{ { 0,4,4,0 },{ 0,4,4,0 },{ 0,0,0,0 },{ 0,0,0,0 } },
		{ { 0,4,4,0 },{ 0,4,4,0 },{ 0,0,0,0 },{ 0,0,0,0 } } },
		{ { { 0,5,5,0 },{ 5,5,0,0 },{ 0,0,0,0 },{ 0,0,0,0 } }, // 4   'ㄹ'반대 
		{ { 0,5,0,0 },{ 0,5,5,0 },{ 0,0,5,0 },{ 0,0,0,0 } },
		{ { 0,0,0,0 },{ 0,5,5,0 },{ 5,5,0,0 },{ 0,0,0,0 } },
		{ { 5,0,0,0 },{ 5,5,0,0 },{ 0,5,0,0 },{ 0,0,0,0 } } },
		{ { { 0,6,0,0 },{ 6,6,6,0 },{ 0,0,0,0 },{ 0,0,0,0 } }, // 5   '┴' 
		{ { 0,6,0,0 },{ 0,6,6,0 },{ 0,6,0,0 },{ 0,0,0,0 } },
	    { { 0,0,0,0 },{ 6,6,6,0 },{ 0,6,0,0 },{ 0,0,0,0 } },
		{ { 0,6,0,0 },{ 6,6,0,0 },{ 0,6,0,0 },{ 0,0,0,0 } } },
		{ { { 7,7,0,0 },{ 0,7,7,0 },{ 0,0,0,0 },{ 0,0,0,0 } }, // 6   'ㄹ'
		{ { 0,0,7,0 },{ 0,7,7,0 },{ 0,7,0,0 },{ 0,0,0,0 } },
		{ { 0,0,0,0 },{ 7,7,0,0 },{ 0,7,7,0 },{ 0,0,0,0 } },
		{ { 0,7,0,0 },{ 7,7,0,0 },{ 7,0,0,0 },{ 0,0,0,0 } } },
		{ { { 0,0,0,0 },{ 0,0,0,0 },{ 0,0,0,0 },{ 0,0,0,0 } }, // 7   공백
		{ { 0,0,0,0 },{ 0,0,0,0 },{ 0,0,0,0 },{ 0,0,0,0 } },
		{ { 0,0,0,0 },{ 0,0,0,0 },{ 0,0,0,0 },{ 0,0,0,0 } },
		{ { 0,0,0,0 },{ 0,0,0,0 },{ 0,0,0,0 },{ 0,0,0,0 } } }
	};
	
	//테트리스게임에서 '현재 떨어지고 있는' 테트리미노의
	int[][][] grid; // 격자 위치 grid[4][4][4]
	int type;    // 유형(종류)    CF. 0번은 아직 아무것도 없는 공백
	int size;    // 크기         
	int color;   // 색깔
	int rotation;// 회전상태
	int posX;    // 가로 위치(좌표)
	int posY;    // 세로 위치(좌표)
	
	//상태값
	boolean isTetriminoLocked;  // 테트리미노 락
	boolean isTetriminoDownStep;// 테트리미노 밑으로 내려오는중인지 여부
	boolean isTetriminoDrop;    // 테트리미노 완전히 내려왔는지 여부

	public Single_NP_Tetrimino() {
		// 테트리미노 유형을 정한다. [0~6번 블록을 램덤으로 생성한다]
		this.type =  (int)(Math.random() * 7); // 0~6의 값을 램덤으로 넣는다.
//		System.out.println("type : "+ type);
		// 테트리미노 크기를 정한다.
		if (this.type == 0 || this.type == 3) {
			this.size = 4;
		} else {
			this.size = 3;
		}

		// 테트리미노 색깔을 정한다.
		switch (this.type) {
		case 0:  // 0 'ㅡ' [하늘색]
			this.color = 1;
			break; 
		case 1:  // 1 'ㄴ' [귤색]
			this.color = 2;
			break; 
		case 2:  // 2 'ㄱ'반대 [파랑색]
			this.color = 3;
			break; 
		case 3:  // 3 'ㅁ' [노란색]
			this.color = 4;
			break; 
		case 4:  // 4 'ㄹ'반대 [녹색]
			this.color = 5;
			break; 
		case 5:  // 5 '┴'[자주색]
			this.color = 6;
			break; 
		case 6:  // 6 'ㄹ' [빨간색]
			this.color = 7;
			break; 
		default:  // ? 아직 아무것도 없는 공백[회색]
			this.color = 0;
			break; 
		}

		// 테트리미노 회전상태 및 현재 좌표를 정한다.
		this.rotation = 0;
		this.posY = 0;
		this.posX = 5 - (this.size - 1);
		
		// 테트리미노를 정한다.
		grid = new int[4][4][4];
		int i, j, k;
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				for (k = 0; k < 4; k++) {
					if (gridTemplate[type][i][j][k] != 0) {
						grid[i][j][k] = color;
					} else {
						grid[i][j][k] = 0;
					}
				}
			}
		}

		// 기타 상태값을 정한다.
		this.isTetriminoLocked = false;
		this.isTetriminoDownStep = false;
		this.isTetriminoDrop = false;
	}

	
	public void reSeting() {
		// 테트리미노 크기를 정한다.
		if (this.type == 0 || this.type == 3) {
			this.size = 4;
		} else {
			this.size = 3;
		}
		// 테트리미노 회전상태 및 현재 좌표를 정한다.
		this.rotation = 0;
		this.posY = 0;
		this.posX = 5 - (this.size - 1);
	}
	
	public void moveLeft() {
		this.posX--;
	}
	
	public void moveRight() {
		this.posX++;
	}
	
	public void moveDown() {
		this.posY++;
	}
	
	public void turnLeft() {
		this.rotation--;
		if (this.rotation == -1)
			this.rotation = 3;
	}
	
	public void turnRight() {
		this.rotation++;
		if (this.rotation == 4)
			this.rotation = 0;
	}
	
	public int getGrid(int x, int y) {
		return this.grid[this.rotation][x][y];
	}
	
	public void gridClear() {
		int i, j, k;
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				for (k = 0; k < 4; k++) {
					this.grid[i][j][k] = 0;
				}
			}
		}
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;

		int i, j, k;
		// 테트리미노의 형태도 바꾼다.
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				for (k = 0; k < 4; k++) {
					if (gridTemplate[type][i][j][k] != 0) {
						grid[i][j][k] = color;
					} else {
						grid[i][j][k] = 0;
					}
				}
			}
		}
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getColor() {
		return this.color;
	}

	public int getRotation() {
		return this.rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
		return this.posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public boolean getIsLocked() {
		return this.isTetriminoLocked;
	}
	
	public void setIsLocked(boolean flag) {
		this.isTetriminoLocked = flag;

	}
	
	public boolean getIsDownStep() {
		return this.isTetriminoDownStep;
	}
	
	public void setIsDownStep(boolean flag) {
		this.isTetriminoDownStep = flag;
	}

	public boolean getIsDrop() {
		return this.isTetriminoDrop;
	}
	
	public void setIsDrop(boolean flag) {
		this.isTetriminoDrop = flag;
	}
}
