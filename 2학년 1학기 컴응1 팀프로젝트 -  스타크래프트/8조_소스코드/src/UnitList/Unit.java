package UnitList;

import Model.GameMain;
import Model.Position;
import java.awt.Point;
import java.util.Scanner;
import javax.swing.ImageIcon;

public class Unit {

    UnitManager manager;        //유닛관리자
    protected int player;       //유닛의 소속

    //Unit 속성등
    private String name;        //유닛명
    private int hp;             //체력
    private int totalHp;        //전체체력
    private int damage;         //지상공격력
    private int airDamage;      //공중공격력
    private int defense;        //방어력
    private int speed;          //이동속도
    private int range;          //지상공격범위
    private int airAange;       //공중공격범위
    private int food;           //인구수

    //Unit 이동 및 공격 판정
    private boolean isAir;      //공중유닛인지 여부
    private boolean atkAir;     //공중공격이 가능한지 여부
    private boolean isMoved;    //이동할수 있는지 여부
    private boolean isAttacked; //공격능력 있는지 여부

    //기타 GUI관련
    Position pos;               //현재 유닛의 위치
    private ImageIcon image;    //유닛 이미지

    //특수능력 관련
    private String specialSkill;//특수능력 이름
    private boolean isSpecial;  //특수능력 가능 여부
    private boolean isSp;       //특수능력 한번썼는지여부
    private boolean visible;    //보이는지 여부 - 클로킹(고스트, 레이스 등)
    private boolean detect;     //탐지되었는지 여부 - 디택트(사이언스베슬, 스캔 등)
    private int turndown;       //스팀팩 턴세기
    private int dturndown;      //디펜시브매트릭스 턴세기
    private boolean isdfm;      //디펜시브매트릭스 받았는지 여부
    private int shield;         //디펜시브매트릭스 쉴드

    public Unit() {
    }

    //유닛 생성자 메소드
    public Unit(int player, String name, int hp,
            int damage, int airDamage, int defense, int speed, int range, int airAange, int food,
            boolean isAir, boolean atkAir, boolean visible, boolean detect,
            boolean isMoved, boolean isAttacked, boolean isSpc, ImageIcon img) {
        this.manager = new UnitManager();
        this.player = player;
        this.name = name;
        this.hp = hp;
        this.totalHp = hp;
        this.damage = damage;
        this.airDamage = airDamage;
        this.defense = defense;
        this.speed = speed;
        this.range = range;
        this.airAange = airAange;
        this.food = food;

        this.isAir = isAir;
        this.atkAir = atkAir;
        this.isMoved = isMoved;
        this.isAttacked = isAttacked;

        this.pos = new Position();
        this.image = img;

        this.specialSkill = "없음";
        this.isSpecial = isSpc;
        this.isSp = true;
        this.visible = visible;
        this.detect = detect;
        this.turndown = -1;
        this.dturndown = -1;
        this.isdfm = false;
        this.shield = 0;
    }

    //접근자(get) 및 변경자(set) 메소드
    public UnitManager get_Manager() {
        return manager;
    }

    public int get_Player() {
        return player;
    }

    public String get_Name() {
        return name;
    }

    public void set_Name(String name) {
        this.name = name;
    }

    public int get_Hp() {
        return hp;
    }

    public void set_Hp(int hp) {
        this.hp = hp;
    }

    public int get_totalHP() {
        return totalHp;
    }

    public int get_Damage() {
        return damage;
    }

    public void set_Damage(int damage) {
        this.damage = damage;
    }

    public int get_AirDamage() {
        return airDamage;
    }

    public void set_AirDamage(int airDamage) {
        this.airDamage = airDamage;
    }

    public int get_Defense() {
        return defense;
    }

    public void set_Defense(int defense) {
        this.defense = defense;
    }

    public int get_Speed() {
        return speed;
    }

    public void set_Speed(int speed) {
        this.speed = speed;
    }

    public int get_Range() {
        return range;
    }

    public void set_Range(int range) {
        this.range = range;
    }

    public int get_AirAange() {
        return airAange;
    }

    public void set_AirAange(int airAange) {
        this.airAange = airAange;
    }

    public void get_Food(int food) {
        this.food = food;
    }

    public boolean isAir() {
        return isAir;
    }

    public boolean isAtkAir() {
        return atkAir;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void set_isMoved(boolean isMoved) {
        this.isMoved = isMoved;
    }

    public boolean isAttacked() {
        return isAttacked;
    }

    public void set_isAttacked(boolean isAttacked) {
        this.isAttacked = isAttacked;
    }

    public Position get_Pos() {
        return pos;
    }

    public void set_Pos(Position pos) {
        this.pos = pos;
    }

    public void set_Pos(int x, int y) {
        this.pos.set_X(x);
        this.pos.set_Y(y);
    }

    public ImageIcon getImage() {
        return this.image;
    }

    public void set_Image(ImageIcon image) {
        this.image = image;
    }

    public String get_SpecialSkill() {
        return specialSkill;
    }

    public void set_SpecialSkill(String specialSkill) {
        this.specialSkill = specialSkill;
    }

    public boolean isSpc() {
        return this.isSpecial;
    }

    public boolean isSp() {
        return this.isSp;
    }

    public void set_isSp(boolean isSp) {
        this.isSp = isSp;
    }

    public boolean get_Visible() {
        return visible;
    }

    public void set_Visible(boolean visible) {
        this.visible = visible;
    }

    public boolean get_Detect() {
        return detect;
    }

    public void set_Detect(boolean detect) {
        this.detect = detect;
    }

    public int get_Turndown() {
        return this.turndown;
    }

    public void set_Turndown(int turndown) {
        this.turndown = turndown;
    }

    public boolean isDfm() {
        return this.isdfm;
    }

    public void set_isDfm(boolean isdfm) {
        this.isdfm = isdfm;
    }

    public void set_dfm(boolean isdfm) {
        this.isdfm = isdfm;
    }

    public int get_shield() {
        return this.shield;
    }

    public void set_shield() {
        this.shield = 100;
    }

    public void set_shield(int shield) {
        this.shield = shield;
    }

    public int get_dturn() {
        return this.dturndown;
    }

    //유닛의 이동처리 메소드
    public Position[] move() {
        if (!this.isMoved) {
            return null;
        }
        Position[] moveArr = null;

        //이동할수 있는 공간을 구한다.
        if (isAir)//공중군일때 : 물만 아니면 다 갈수 있음
        {
            moveArr = getRangeArray(moveArr, speed);//공중군이 갈수 있는 공간의 배열을 구한다.
        } else {//지상군일때 : 물이 있는 지형이 가로막을때는 이동상의 제약이 생김 -> 어려움...
            moveArr = getGroundMoveRangeArray(moveArr);
        }
        System.out.println("맵의 이동가능한 공간");

        //한때 콘솔상에서 돌아갔을 당시 코드
        /*GameMain.map.viewRange(moveArr);
         Scanner scan = new Scanner(System.in);
         System.out.printf("현재 위치 : ("+pos.get_X()+","+pos.get_Y()+")");
         System.out.println("이동할 좌표를 입력하세요.");
         System.out.print("x : ");
         int x_=scan.nextInt();
         System.out.print("y : ");
         int y_=scan.nextInt();
         if(isIncludeArr(moveArr, x_, y_)){
         GameMain.map.unitLink[this.pos.get_Y()][this.pos.get_X()]=null;
         this.pos.set_X(x_);
         this.pos.set_Y(y_);
         GameMain.map.unitLink[this.pos.get_Y()][this.pos.get_X()]=this;
         isMoved = false;
         }else{
         System.out.println("잘못 입력하셨습니다.");
         }
         if(pos!=null){

         }
         GameMain.map.displayMap();*/
        return moveArr;
    }

    //유닛의 이동을 GUI상에서 처리하는 메소드
    public void move(int y, int x) {
        GameMain.map.unitLink[this.pos.get_Y()][this.pos.get_X()] = null;
        this.pos.set_X(x);
        this.pos.set_Y(y);
        GameMain.map.unitLink[this.pos.get_Y()][this.pos.get_X()] = this;
        isMoved = false;
    }

    //입력받은 x,y좌표가 posArr안에 들어있는지 판단하는 메소드
    public static boolean isIncludeArr(Position[] posArr, int x, int y) {
        boolean ret = false;
        int i = 0;
        while (posArr[i] != null && !posArr[i].isEqual(x, y)) {
            i++;
        }
        if (posArr[i] != null && posArr[i].isEqual(x, y)) {
            ret = true;
        }
        return ret;
    }

    //ran만큼의 마름모의 내에서 이동가능한 범위를 구하는 메소드 - 공중유닛
    protected Position[] getRangeArray(Position[] rangeArray, int ran) {
        int i = 0;
        int x, y;
        rangeArray = new Position[2 * (ran + 1) * (ran + 1) - 2 * (ran + 1)];

        for (x = -ran; x <= ran; x++) {
            for (y = -ran; y <= ran; y++) {
                if (Math.abs(x) + Math.abs(y) <= ran) {
                    if (isMap(x + pos.get_X(), y + pos.get_Y())) {
                        if (GameMain.map.geography[pos.get_Y() + y][pos.get_X() + x] == 0) {
                            if (GameMain.map.unitLink[pos.get_Y() + y][pos.get_X() + x] == null) {
                                rangeArray[i] = new Position(pos.get_X() + x, pos.get_Y() + y);
                                i++;
                            }
                        }
                    }
                }
            }
        }
        Position[] Array = new Position[i];
        for (int j = 0; j < i; j++) {
            Array[j] = rangeArray[j];
        }
        return Array;
    }

    //ran만큼의 마름모의 내에서 이동가능한 범위를 구하는 메소드 - 지상유닛 : 제약이 많음
    private Position[] getGroundMoveRangeArray(Position[] moveArr) {
        int i = 0;
        int x, y;

        moveArr = new Position[1000000];

        for (x = 0; x <= speed && isMap(x + pos.get_X(), pos.get_Y()) && GameMain.map.geography[0 + pos.get_Y()][x + pos.get_X()] != 1; x++) {
            for (y = 0; y <= speed && isMap(x + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x + pos.get_X()] != 1; y++) {
                if (Math.abs(x) + Math.abs(y) <= speed) {
                    if (isMap(x + pos.get_X(), y + pos.get_Y())) {
                        if (GameMain.map.geography[pos.get_Y() + y][pos.get_X() + x] == 0) {
                            if (GameMain.map.unitLink[pos.get_Y() + y][pos.get_X() + x] == null) {
                                moveArr[i] = new Position(pos.get_X() + x, pos.get_Y() + y);
                                i++;
                            }
                        }
                    }
                }
            }
            if (y <= speed && isMap(x + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x + pos.get_X()] == 1) {
                if (GameMain.map.geography[y - 1 + pos.get_Y()][x + 1 + pos.get_X()] != 1) {
                    i = moveDownRight(moveArr, x + 1, y - 1, i);
                } else if (GameMain.map.geography[y - 1 + pos.get_Y()][x - 1 + pos.get_X()] != 1) {
                    i = moveDownLeft(moveArr, x - 1, y - 1, i);
                }
            }
            for (y = -1; y >= -speed && isMap(x + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x + pos.get_X()] != 1; y--) {
                if (Math.abs(x) + Math.abs(y) <= speed) {
                    if (isMap(x + pos.get_X(), y + pos.get_Y())) {
                        if (GameMain.map.geography[pos.get_Y() + y][pos.get_X() + x] == 0) {
                            if (GameMain.map.unitLink[pos.get_Y() + y][pos.get_X() + x] == null) {
                                moveArr[i] = new Position(pos.get_X() + x, pos.get_Y() + y);
                                i++;
                            }
                        }
                    }
                }
            }
            if (y >= -speed && isMap(x + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x + pos.get_X()] == 1) {
                if (GameMain.map.geography[y + 1 + pos.get_Y()][x + 1 + pos.get_X()] != 1) {
                    i = moveUpRight(moveArr, x + 1, y + 1, i);
                } else if (GameMain.map.geography[y + 1 + pos.get_Y()][x - 1 + pos.get_X()] != 1) {
                    i = moveUpLeft(moveArr, x - 1, y + 1, i);
                }
            }
        }
        if (x <= speed && isMap(x + pos.get_X(), pos.get_Y()) && GameMain.map.geography[pos.get_Y()][x + pos.get_X()] == 1) {
            if (GameMain.map.geography[-1 + pos.get_Y()][x - 1 + pos.get_X()] != 1) {
                i = moveRightUp(moveArr, x - 1, -1, i);
            } else if (GameMain.map.geography[1 + pos.get_Y()][x - 1 + pos.get_X()] != 1) {
                i = moveRightDown(moveArr, x - 1, 1, i);
            }
        }
        for (x = -1; x >= -speed && isMap(x + pos.get_X(), pos.get_Y()) && GameMain.map.geography[pos.get_Y()][x + pos.get_X()] != 1; x--) {
            for (y = 0; y <= speed && isMap(x + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x + pos.get_X()] != 1; y++) {
                if (Math.abs(x) + Math.abs(y) <= speed) {
                    if (isMap(x + pos.get_X(), y + pos.get_Y())) {
                        if (GameMain.map.geography[pos.get_Y() + y][pos.get_X() + x] == 0) {
                            if (GameMain.map.unitLink[pos.get_Y() + y][pos.get_X() + x] == null) {
                                moveArr[i] = new Position(pos.get_X() + x, pos.get_Y() + y);
                                i++;
                            }
                        }
                    }
                }
            }
            if (y <= speed && isMap(x + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x + pos.get_X()] == 1) {
                if (GameMain.map.geography[y - 1 + pos.get_Y()][x - 1 + pos.get_X()] != 1) {
                    i = moveDownLeft(moveArr, x - 1, y - 1, i);
                } else if (GameMain.map.geography[y - 1 + pos.get_Y()][x + 1 + pos.get_X()] != 1) {
                    i = moveDownRight(moveArr, x + 1, y - 1, i);
                }
            }
            for (y = -1; y >= -speed && isMap(x + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x + pos.get_X()] != 1; y--) {
                if (Math.abs(x) + Math.abs(y) <= speed) {
                    if (isMap(x + pos.get_X(), y + pos.get_Y())) {
                        if (GameMain.map.geography[pos.get_Y() + y][pos.get_X() + x] == 0) {
                            if (GameMain.map.unitLink[pos.get_Y() + y][pos.get_X() + x] == null) {
                                moveArr[i] = new Position(pos.get_X() + x, pos.get_Y() + y);
                                i++;
                            }
                        }
                    }
                }
            }
            if (y >= -speed && isMap(x + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x + pos.get_X()] == 1) {
                if (GameMain.map.geography[y + 1 + pos.get_Y()][x - 1 + pos.get_X()] != 1) {
                    i = moveUpLeft(moveArr, x - 1, y + 1, i);
                } else if (GameMain.map.geography[y + 1 + pos.get_Y()][x + 1 + pos.get_X()] != 1) {
                    i = moveUpRight(moveArr, x + 1, y + 1, i);
                }
            }
        }
        if (x >= -speed && isMap(x + pos.get_X(), pos.get_Y()) && GameMain.map.geography[pos.get_Y()][x + pos.get_X()] == 1) {
            if (GameMain.map.geography[-1 + pos.get_Y()][x + 1 + pos.get_X()] != 1) {
                i = moveLeftUp(moveArr, x + 1, -1, i);
            } else if (GameMain.map.geography[1 + pos.get_Y()][x + 1 + pos.get_X()] != 1) {
                i = moveLeftDown(moveArr, x + 1, 1, i);
            }
        }
        Position[] Array = new Position[i];
        for (int j = 0; j < i; j++) {
            Array[j] = moveArr[j];
        }
        return Array;
    }

    private int moveUpLeft(Position[] moveArr, int x, int y, int i) {
        int x_, y_;
        for (x_ = x; x_ >= -speed && isMap(x_ + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x_ + pos.get_X()] != 1; x_--) {
            for (y_ = y; y_ >= -speed && isMap(x_ + pos.get_X(), y_ + pos.get_Y()) && GameMain.map.geography[y_ + pos.get_Y()][x_ + pos.get_X()] != 1; y_--) {
                if (Math.abs(x_) + Math.abs(y_) < speed) {
                    if (pos.get_X() + x_ >= 0 && pos.get_X() + x_ < GameMain.map.maxX && pos.get_Y() + y_ >= 0 && pos.get_Y() + y_ < GameMain.map.maxY) {
                        if (GameMain.map.geography[pos.get_Y() + y_][pos.get_X() + x_] == 0) {
                            if (GameMain.map.unitLink[pos.get_Y() + y_][pos.get_X() + x_] == null) {
                                moveArr[i] = new Position(pos.get_X() + x_, pos.get_Y() + y_);
                                i++;
                            }
                        }
                    }
                }
            }
            if (y_ >= -speed && isMap(x + pos.get_X(), y_ + pos.get_Y()) && GameMain.map.geography[y_ + pos.get_Y()][x_ + pos.get_X()] == 1) {
                if (GameMain.map.geography[y_ + 1 + pos.get_Y()][x_ - 1 + pos.get_X()] != 1) {
                    i = moveUpLeft(moveArr, x_ - 1, y_ + 1, i);
                }
            }
        }
        return i;
    }

    private int moveUpRight(Position[] moveArr, int x, int y, int i) {
        int x_, y_;

        for (x_ = x; x_ <= speed && isMap(x_ + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x_ + pos.get_X()] != 1; x_++) {
            for (y_ = y; y_ >= -speed && isMap(x_ + pos.get_X(), y_ + pos.get_Y()) && GameMain.map.geography[y_ + pos.get_Y()][x_ + pos.get_X()] != 1; y_--) {
                if (Math.abs(x_) + Math.abs(y_) < speed) {
                    if (pos.get_X() + x_ >= 0 && pos.get_X() + x_ < GameMain.map.maxX && pos.get_Y() + y_ >= 0 && pos.get_Y() + y_ < GameMain.map.maxY) {
                        if (GameMain.map.geography[pos.get_Y() + y_][pos.get_X() + x_] == 0) {
                            if (GameMain.map.unitLink[pos.get_Y() + y_][pos.get_X() + x_] == null) {
                                moveArr[i] = new Position(pos.get_X() + x_, pos.get_Y() + y_);
                                i++;
                            }
                        }
                    }
                }
            }
            if (y_ >= -speed && isMap(x + pos.get_X(), y_ + pos.get_Y()) && GameMain.map.geography[y_ + pos.get_Y()][x_ + pos.get_X()] == 1) {
                if (GameMain.map.geography[y_ + 1 + pos.get_Y()][x_ + 1 + pos.get_X()] != 1) {
                    i = moveUpRight(moveArr, x_ + 1, y_ + 1, i);
                }
            }
        }
        return i;
    }

    private int moveDownLeft(Position[] moveArr, int x, int y, int i) {
        int x_, y_;
        for (x_ = x; x_ >= -speed && isMap(x_ + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x_ + pos.get_X()] != 1; x_--) {
            for (y_ = y; y_ <= speed && isMap(x_ + pos.get_X(), y_ + pos.get_Y()) && GameMain.map.geography[y_ + pos.get_Y()][x_ + pos.get_X()] != 1; y_++) {
                if (Math.abs(x_) + Math.abs(y_) < speed) {
                    if (pos.get_X() + x_ >= 0 && pos.get_X() + x_ < GameMain.map.maxX && pos.get_Y() + y_ >= 0 && pos.get_Y() + y_ < GameMain.map.maxY) {
                        if (GameMain.map.geography[pos.get_Y() + y_][pos.get_X() + x_] == 0) {
                            if (GameMain.map.unitLink[pos.get_Y() + y_][pos.get_X() + x_] == null) {
                                moveArr[i] = new Position(pos.get_X() + x_, pos.get_Y() + y_);
                                i++;
                            }
                        }
                    }
                }
            }
            if (y_ <= speed && isMap(x + pos.get_X(), y_ + pos.get_Y()) && GameMain.map.geography[y_ + pos.get_Y()][x_ + pos.get_X()] == 1) {
                if (GameMain.map.geography[y_ - 1 + pos.get_Y()][x_ - 1 + pos.get_X()] != 1) {
                    i = moveDownLeft(moveArr, x_ - 1, y_ - 1, i);
                }
            }
        }
        return i;
    }

    private int moveDownRight(Position[] moveArr, int x, int y, int i) {
        int x_, y_;
        for (x_ = x; x_ <= speed && isMap(x_ + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x_ + pos.get_X()] != 1; x_++) {
            for (y_ = y; y_ <= speed && isMap(x_ + pos.get_X(), y_ + pos.get_Y()) && GameMain.map.geography[y_ + pos.get_Y()][x_ + pos.get_X()] != 1; y_++) {
                if (Math.abs(x_) + Math.abs(y_) < speed) {
                    if (pos.get_X() + x_ >= 0 && pos.get_X() + x_ < GameMain.map.maxX && pos.get_Y() + y_ >= 0 && pos.get_Y() + y_ < GameMain.map.maxY) {
                        if (GameMain.map.geography[pos.get_Y() + y_][pos.get_X() + x_] == 0) {
                            if (GameMain.map.unitLink[pos.get_Y() + y_][pos.get_X() + x_] == null) {
                                moveArr[i] = new Position(pos.get_X() + x_, pos.get_Y() + y_);
                                i++;
                            }
                        }
                    }
                }
            }
            if (y_ <= speed && isMap(x + pos.get_X(), y_ + pos.get_Y()) && GameMain.map.geography[y_ + pos.get_Y()][x_ + pos.get_X()] == 1) {
                if (GameMain.map.geography[y_ - 1 + pos.get_Y()][x_ + 1 + pos.get_X()] != 1) {
                    i = moveDownRight(moveArr, x_ + 1, y_ - 1, i);
                }
            }
        }
        return i;
    }

    private int moveLeftUp(Position[] moveArr, int x, int y, int i) {
        int x_, y_;
        for (x_ = x; x_ >= -speed && isMap(x_ + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x_ + pos.get_X()] != 1; x_--) {
            for (y_ = y; y_ >= -speed && isMap(x_ + pos.get_X(), y_ + pos.get_Y()) && GameMain.map.geography[y_ + pos.get_Y()][x_ + pos.get_X()] != 1; y_--) {
                if (Math.abs(x_) + Math.abs(y_) < speed) {
                    if (pos.get_X() + x_ >= 0 && pos.get_X() + x_ < GameMain.map.maxX && pos.get_Y() + y_ >= 0 && pos.get_Y() + y_ < GameMain.map.maxY) {
                        if (GameMain.map.geography[pos.get_Y() + y_][pos.get_X() + x_] == 0) {
                            if (GameMain.map.unitLink[pos.get_Y() + y_][pos.get_X() + x_] == null) {
                                moveArr[i] = new Position(pos.get_X() + x_, pos.get_Y() + y_);
                                i++;
                            }
                        }
                    }
                }
            }
            if (y_ >= -speed && isMap(x + pos.get_X(), y_ + pos.get_Y()) && GameMain.map.geography[y_ + pos.get_Y()][x_ + pos.get_X()] == 1) {
                if (GameMain.map.geography[y_ + 1 + pos.get_Y()][x_ - 1 + pos.get_X()] != 1) {
                    i = moveUpLeft(moveArr, x_ - 1, y_ + 1, i);
                }
            }
        }
        return i;
    }

    private int moveLeftDown(Position[] moveArr, int x, int y, int i) {
        int x_, y_;
        for (x_ = x; x_ >= -speed && isMap(x_ + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x_ + pos.get_X()] != 1; x_--) {
            for (y_ = y; y_ <= speed && isMap(x_ + pos.get_X(), y_ + pos.get_Y()) && GameMain.map.geography[y_ + pos.get_Y()][x_ + pos.get_X()] != 1; y_++) {
                if (Math.abs(x_) + Math.abs(y_) < speed) {
                    if (pos.get_X() + x_ >= 0 && pos.get_X() + x_ < GameMain.map.maxX && pos.get_Y() + y_ >= 0 && pos.get_Y() + y_ < GameMain.map.maxY) {
                        if (GameMain.map.geography[pos.get_Y() + y_][pos.get_X() + x_] == 0) {
                            if (GameMain.map.unitLink[pos.get_Y() + y_][pos.get_X() + x_] == null) {
                                moveArr[i] = new Position(pos.get_X() + x_, pos.get_Y() + y_);
                                i++;
                            }
                        }
                    }
                }
            }
        }
        if (x_ >= -speed && isMap(x + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x_ + pos.get_X()] == 1) {
            if (GameMain.map.geography[y + 1 + pos.get_Y()][x_ + 1 + pos.get_X()] != 1) {
                i = moveLeftDown(moveArr, x_ + 1, y + 1, i);
            }
        }
        return i;
    }

    private int moveRightUp(Position[] moveArr, int x, int y, int i) {
        int x_, y_;

        for (x_ = x; x_ <= speed && isMap(x_ + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x_ + pos.get_X()] != 1; x_++) {
            for (y_ = y; y_ >= -speed && isMap(x_ + pos.get_X(), y_ + pos.get_Y()) && GameMain.map.geography[y_ + pos.get_Y()][x_ + pos.get_X()] != 1; y_--) {
                if (Math.abs(x_) + Math.abs(y_) < speed) {
                    if (pos.get_X() + x_ >= 0 && pos.get_X() + x_ < GameMain.map.maxX && pos.get_Y() + y_ >= 0 && pos.get_Y() + y_ < GameMain.map.maxY) {
                        if (GameMain.map.geography[pos.get_Y() + y_][pos.get_X() + x_] == 0) {
                            if (GameMain.map.unitLink[pos.get_Y() + y_][pos.get_X() + x_] == null) {
                                moveArr[i] = new Position(pos.get_X() + x_, pos.get_Y() + y_);
                                i++;
                            }
                        }
                    }
                }
            }
        }
        if (x_ <= speed && isMap(x + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x_ + pos.get_X()] == 1) {
            if (GameMain.map.geography[y - 1 + pos.get_Y()][x_ - 1 + pos.get_X()] != 1) {
                i = moveRightUp(moveArr, x_ - 1, y - 1, i);
            }
        }
        return i;
    }

    private int moveRightDown(Position[] moveArr, int x, int y, int i) {
        int x_, y_;

        for (x_ = x; x_ <= speed && isMap(x_ + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x_ + pos.get_X()] != 1; x_++) {
            for (y_ = y; y_ <= speed && isMap(x_ + pos.get_X(), y_ + pos.get_Y()) && GameMain.map.geography[y_ + pos.get_Y()][x_ + pos.get_X()] != 1; y_++) {
                if (Math.abs(x_) + Math.abs(y_) < speed) {
                    if (pos.get_X() + x_ >= 0 && pos.get_X() + x_ < GameMain.map.maxX && pos.get_Y() + y_ >= 0 && pos.get_Y() + y_ < GameMain.map.maxY) {
                        if (GameMain.map.geography[pos.get_Y() + y_][pos.get_X() + x_] == 0) {
                            if (GameMain.map.unitLink[pos.get_Y() + y_][pos.get_X() + x_] == null) {
                                moveArr[i] = new Position(pos.get_X() + x_, pos.get_Y() + y_);
                                i++;
                            }
                        }
                    }
                }
            }
        }
        if (x_ <= speed && isMap(x + pos.get_X(), y + pos.get_Y()) && GameMain.map.geography[y + pos.get_Y()][x_ + pos.get_X()] == 1) {
            if (GameMain.map.geography[y + 1 + pos.get_Y()][x_ - 1 + pos.get_X()] != 1) {
                i = moveRightDown(moveArr, x_ - 1, y + 1, i);
            }
        }
        return i;
    }

//    //한때 콘솔상에서 유닛의 공격을 처리했던 메소드
//    public void attack() {
//        int atkSelect = 1;
//
//        //공격할수 있는 공간을 구한다.
//        if (atkAir)//공중군을 공격할수 있을때,
//        {
//            System.out.println("1. 지상군을 공격한다.");
//            System.out.println("2. 공중군을 공격한다.");
//
//            System.out.println("공격할 유닛의 행동를 입력하세요. [-1]번은 행동취소");
//            Scanner scan = new Scanner(System.in);
//            //	atkSelect = scan.nextInt();
//        }
//
//        Position[] isAtkArr = null;
//        Position[] atkArr = null;
//
//        int dam = 0;
//
//        if (atkSelect == 1)//지상군을 공격할때
//        {
//            dam = damage;
//            isAtkArr = getRangeArray(isAtkArr, range);
//            atkArr = getAttackRangeArray(atkArr, range, false);
//        } else if (atkSelect == 2) {//공중군을 공격할때
//            dam = airDamage;
//            isAtkArr = getRangeArray(isAtkArr, airAange);
//            atkArr = getAttackRangeArray(atkArr, airAange, true);
//        }
//
//        System.out.println("맵의 공격 가능한 공간");
//        GameMain.map.viewRange(isAtkArr);
//        Scanner scan = new Scanner(System.in);
//        System.out.printf("현재 위치 : (" + pos.get_X() + "," + pos.get_Y() + ")");
//        System.out.println("공격할 좌표를 입력하세요.");
//        System.out.print("x : ");
//        int x_ = scan.nextInt();
//        System.out.print("y : ");
//        int y_ = scan.nextInt();
//        if (atkArr.length != 0 && isIncludeArr(atkArr, x_, y_)) {
//            Unit enemy = GameMain.map.unitLink[y_][x_];
//            int enemy_hp = enemy.get_Hp() + enemy.get_Defense() - dam;
//
//            System.out.println(dam - enemy.get_Defense() + "만큼의 피해을 주었습니다.");
//
//            if (enemy_hp > 0) {
//                enemy.set_Hp(enemy_hp);
//            } else {
//                enemy.set_Hp(0);
//                enemy.manager.isDie();
//                GameMain.map.unitLink[enemy.pos.get_Y()][enemy.pos.get_X()] = null;
//                if (String.valueOf(enemy.getClass()).compareTo("class UnitList.CommandCenter") == 0) {
//                    GameMain.gameOver = true;
//                    System.out.println("적 커맨드센터를 파괴하였습니다.");
//                } else {
//                    System.out.println("적 유닛을 파괴하였습니다.");
//                }
//            }
//            isAttacked = false;
//        } else {
//            System.out.println("잘못 입력하셨습니다.");
//        }
//        GameMain.map.displayMap();
//    }
    //현재 GUI에서 유닛의 공격을 처리하는 메소드
    public Position[] getrange(int range) {
        Position[] isAtkArr = null;

        isAtkArr = getRangeArray(isAtkArr, range);

        return isAtkArr;
    }

    public Position[] groundAttack() {
        Position[] atkArr = null;

        atkArr = getAttackRangeArray(atkArr, range, false);
        return atkArr;
    }

    public void attack(int x, int y) {
        Unit enemy = GameMain.map.unitLink[y][x];
        int dam;
        if (enemy.isAir()) {
            dam = this.airDamage;
        } else {
            dam = this.damage;
        }

        int enemy_hp = enemy.get_Hp();
        if (enemy.isDfm()) {
            int shield = enemy.get_shield();
            if (shield > dam) {
                enemy.set_shield(shield - dam);
                enemy_hp--;
            } else {
                dam -= shield;
                if (dam > enemy.get_Defense()) {
                    enemy_hp += enemy.get_Defense() - dam;
                } else {
                    enemy_hp--;
                }
                enemy.set_shield(0);
                enemy.set_dfm(false);
            }
        } else {
            if (dam > enemy.get_Defense()) {
                enemy_hp += enemy.get_Defense() - dam;
            } else {
                enemy_hp--;
            }
        }
        System.out.println(enemy.get_Name() + "에게 " + (dam - enemy.get_Defense()) + "만큼의 피해을 주었습니다.");

        if (enemy_hp > 0) {
            enemy.set_Hp(enemy_hp);
        } else {
            enemy.set_Hp(0);
            enemy.manager.isDie();
            GameMain.map.unitLink[enemy.pos.get_Y()][enemy.pos.get_X()] = null;
            if (String.valueOf(enemy.getClass()).compareTo("class UnitList.CommandCenter") == 0) {
                GameMain.gameOver = true;
                System.out.println("적 커맨드센터를 파괴하였습니다.");
            } else {
                System.out.println("적 유닛" + enemy.get_Name() + "를 파괴하였습니다.");
            }
        }
        isAttacked = false;
    }

    public Position[] airAttack() {
        Position[] isAtkArr = null;
        Position[] atkArr = null;

        int dam = 0;
        dam = airDamage;
        isAtkArr = getRangeArray(isAtkArr, airAange);
        atkArr = getAttackRangeArray(atkArr, airAange, true);
        return atkArr;
    }

    protected Position[] getAttackRangeArray(Position[] atkArr, int ran, boolean airAtk) {
        int i = 0;
        int x, y;
        atkArr = new Position[2 * (ran + 1) * (ran + 1) - 2 * (ran + 1)];

        for (x = -ran; x <= ran; x++) {
            for (y = -ran; y <= ran; y++) {
                if (Math.abs(x) + Math.abs(y) <= ran) {
                    if (isMap(x + pos.get_X(), y + pos.get_Y())) {
                        if (GameMain.map.geography[pos.get_Y() + y][pos.get_X() + x] == 0) {
                            if (GameMain.map.unitLink[pos.get_Y() + y][pos.get_X() + x] != null && GameMain.map.unitLink[pos.get_Y() + y][pos.get_X() + x].get_Player() != player
                                    && GameMain.map.unitLink[pos.get_Y() + y][pos.get_X() + x].isAir == airAtk) {
                                atkArr[i] = new Position(pos.get_X() + x, pos.get_Y() + y);
                                i++;
                            }
                        }
                    }
                }
            }
        }
        Position[] Array = new Position[i];
        for (int j = 0; j < i; j++) {
            Array[j] = atkArr[j];
        }
        return Array;
    }

    public boolean isMap(int x, int y) {
        return (0 <= x && x < GameMain.map.maxX && 0 <= y && y < GameMain.map.maxY);
    }

    public void specialAttack(int turn) {
    }

    public Position[] specialAttack(Position[] p) {
        return null;
    }

    public Position[] specialRange() {
        return null;
    }

    public void specialOver() {
    }

    public void special(int x, int y) {
    }

    public void viewUnit() {
        System.out.printf("유닛명 : %s, HP : %2d\n", name, hp);
        System.out.printf("지상공격력 : %2d, 공중공격력 : %2d\n", damage, airDamage);
        System.out.printf("방여력 : %d, 이동거리 : %d\n", defense, speed);
        System.out.printf("지상사정거리 : %2d, 공중사정거리 : %2d\n", range, airAange);
    }
}
