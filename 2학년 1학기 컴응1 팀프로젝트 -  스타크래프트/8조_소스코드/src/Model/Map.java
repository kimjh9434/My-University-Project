package Model;

import java.util.Scanner;

import UnitList.Unit;

public class Map {

    public final int maxX = 40;
    public final int maxY = 40;
    public int[][] geography;
    public Unit[][] unitLink;
    public UnitList totalUnitList;

    public Map() {
        this.geography = new int[maxX][maxY];
        this.unitLink = new Unit[maxX][maxY];

        //x자 형 물
        for (int i = 5; i < maxX - 5; i++) {
            geography[i][i] = 1;
            geography[maxX - i][i] = 1;
        }
        geography[10][10] = 0;
        geography[20][20] = 0;
        geography[30][30] = 0;
        geography[10][30] = 0;
        geography[30][10] = 0;
        geography[15][15] = 0;
        geography[25][25] = 0;
        geography[15][25] = 0;
        geography[15][25] = 0;

        geography[20][10] = 1;
        geography[20][30] = 1;
        geography[10][20] = 1;
        geography[30][20] = 1;
        geography[7][28] = 1;
        geography[24][5] = 1;
        geography[5][7] = 1;
        geography[13][21] = 1;
        geography[32][22] = 1;
        geography[17][7] = 1;
        geography[33][25] = 1;
        geography[17][14] = 1;
        geography[21][27] = 1;
        geography[15][17] = 1;
        geography[10][17] = 1;
        geography[24][33] = 1;
        geography[17][8] = 1;

        this.totalUnitList = new UnitList();
    }

    public void displayMap() {
        System.out.println("현재 맵 상태");
        System.out.println("================================");
        for (int j = 0; j < maxY; j++) {
            for (int i = 0; i < maxX; i++) {
                if (i == 3 && j == maxY / 2) {
                    System.out.print("☆");
                } else if (i == maxX - 4 && j == maxY / 2) {
                    System.out.print("★");
                } else if (unitLink[j][i] != null) {
                    if (unitLink[j][i].get_Player() == 1) {
                        System.out.print("◇");
                    } else {
                        System.out.print("◆");
                    }
                } else {
                    if (geography[j][i] == 0) {
                        System.out.print("□");
                    } else {
                        System.out.print("■");
                    }
                }
            }
            System.out.println("               ☆");
        }
        System.out.println("========================================");
    }

    public void viewRange(Position[] moveArr) {
        System.out.println("================================");
        for (int j = 0; j < maxY; j++) {
            for (int i = 0; i < maxX; i++) {
                if (i == 3 && j == maxY / 2) {
                    System.out.print("☆");
                } else if (i == maxX - 4 && j == maxY / 2) {
                    System.out.print("★");
                } else if (unitLink[j][i] != null) {
                    if (unitLink[j][i].get_Player() == 1) {
                        System.out.print("◇");
                    } else {
                        System.out.print("◆");
                    }
                } else if (Unit.isIncludeArr(moveArr, i, j)) {
                    System.out.print("○");
                } else {
                    if (geography[j][i] == 0) {
                        System.out.print("□");
                    } else {
                        System.out.print("■");
                    }
                }
            }
            System.out.println("               ☆");
        }
        System.out.println("========================================");
    }

    public void setStartPos(Unit unit) {
        boolean isSetPos = false;

        int i, j = 1;
        int x, y;
        if (unit.get_Player() == 1) {
            x = 3;
            y = maxY / 2;
        } else {
            x = maxX - 4;
            y = maxY / 2;
        }
        while (!isSetPos) {
            for (i = 0; i < j && unitLink[y][x] != null && !isSetPos; i++) {
                y++;
            }
            if (unitLink[y][x] == null && !isSetPos) {
                unit.set_Pos(x, y);
                isSetPos = true;
            }
            for (i = 0; i < j && unitLink[y][x] != null && !isSetPos; i++) {
                x++;
            }
            if (unitLink[y][x] == null && !isSetPos) {
                unit.set_Pos(x, y);
                isSetPos = true;
            }
            j++;
            for (i = 0; i < j && unitLink[y][x] != null && !isSetPos; i++) {
                y--;
            }
            if (unitLink[y][x] == null && !isSetPos) {
                unit.set_Pos(x, y);
                isSetPos = true;
            }
            for (i = 0; i < j && unitLink[y][x] != null && !isSetPos; i++) {
                x--;
            }
            if (unitLink[y][x] == null && !isSetPos) {
                unit.set_Pos(x, y);
                isSetPos = true;
            }
            j++;
        }
    }

    public boolean isMap(int x, int y) {
        return (0 <= x && x < GameMain.map.maxX && 0 <= y && y < GameMain.map.maxY);
    }

}
