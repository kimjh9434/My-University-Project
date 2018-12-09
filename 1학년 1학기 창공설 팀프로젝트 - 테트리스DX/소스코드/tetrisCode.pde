import ddf.minim.*;
 
Minim minim;

//테트리스 DX

/*
  tetriminos: I J L O S T Z
  0  : //푸른색 'I' , 'ㅡ'
  1  : //파란색 'L' , 'ㄴ'
  2  : //주황색 'J' , 'ㄱ'반대
  3  : //노란색 'O' , 'ㅁ'
  4  : //초록색 'S' , 'ㄹ'반대
  5  : //보라색 'T' , 'ㅗ' 148,0,211
  6  : //빨간색 'Z' , 'ㄹ'
  
*/


//테트리미노
//gridTemplate [tetrisType] [tetrisRotation] [y] [x]
final int gridTemplate[][][][] = {
  {{{0,0,0,0}, {1,1,1,1}, {0,0,0,0}, {0,0,0,0}}, /* 0  : //푸른색 'ㅡ' */
   {{0,0,1,0}, {0,0,1,0}, {0,0,1,0}, {0,0,1,0}}, 
   {{0,0,0,0}, {0,0,0,0}, {1,1,1,1}, {0,0,0,0}},
   {{0,1,0,0}, {0,1,0,0}, {0,1,0,0}, {0,1,0,0}}},
  {{{2,0,0,0}, {2,2,2,0}, {0,0,0,0}, {0,0,0,0}}, /* 1  : //파란색 'ㄴ' */
   {{0,2,2,0}, {0,2,0,0}, {0,2,0,0}, {0,0,0,0}},
   {{0,0,0,0}, {2,2,2,0}, {0,0,2,0}, {0,0,0,0}},
   {{0,2,0,0}, {0,2,0,0}, {2,2,0,0}, {0,0,0,0}}}, 
  {{{0,0,3,0}, {3,3,3,0}, {0,0,0,0}, {0,0,0,0}}, /* 2  : //주황색 'ㄱ'반대 */
   {{0,3,0,0}, {0,3,0,0}, {0,3,3,0}, {0,0,0,0}},
   {{0,0,0,0}, {3,3,3,0}, {3,0,0,0}, {0,0,0,0}},
   {{3,3,0,0}, {0,3,0,0}, {0,3,0,0}, {0,0,0,0}}},
  {{{0,4,4,0}, {0,4,4,0}, {0,0,0,0}, {0,0,0,0}}, /* 3  : //노란색 'ㅁ' */
   {{0,4,4,0}, {0,4,4,0}, {0,0,0,0}, {0,0,0,0}},
   {{0,4,4,0}, {0,4,4,0}, {0,0,0,0}, {0,0,0,0}},
   {{0,4,4,0}, {0,4,4,0}, {0,0,0,0}, {0,0,0,0}}},
  {{{0,5,5,0}, {5,5,0,0}, {0,0,0,0}, {0,0,0,0}}, /* 4  : //초록색 'ㄹ'반대 */
   {{0,5,0,0}, {0,5,5,0}, {0,0,5,0}, {0,0,0,0}},
   {{0,0,0,0}, {0,5,5,0}, {5,5,0,0}, {0,0,0,0}},
   {{5,0,0,0}, {5,5,0,0}, {0,5,0,0}, {0,0,0,0}}},
  {{{0,6,0,0}, {6,6,6,0}, {0,0,0,0}, {0,0,0,0}}, /* 5  : //보라색 'ㅗ' 148,0,211 */
   {{0,6,0,0}, {0,6,6,0}, {0,6,0,0}, {0,0,0,0}},
   {{0,0,0,0}, {6,6,6,0}, {0,6,0,0}, {0,0,0,0}},
   {{0,6,0,0}, {6,6,0,0}, {0,6,0,0}, {0,0,0,0}}},
  {{{7,7,0,0}, {0,7,7,0}, {0,0,0,0}, {0,0,0,0}}, /* 6  : //빨간색 'ㄹ' */
   {{0,0,7,0}, {0,7,7,0}, {0,7,0,0}, {0,0,0,0}},
   {{0,0,0,0}, {7,7,0,0}, {0,7,7,0}, {0,0,0,0}},
   {{0,7,0,0}, {7,7,0,0}, {7,0,0,0}, {0,0,0,0}}},
  {{{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}, /* clear */
   {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}},
   {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}},
   {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}}};

///////////////////////////////////////////////////   
   
int state=0;            //게임 화면 상태
boolean GameSetup=true; //게임 재사전작업
boolean gameOver=false; //게임 오버
boolean PauseGame=false;//게임 일시정지
boolean InputNameOK=false;


int tetrisSaved;      //테트리미노 킵핑(저장) 
boolean tetrisCanSave;//테트리미노 킵핑(저장) 가능여부

///////////////////////////////////////////////////

final int gridWidth=10;  //격자무늬 가로칸
final int gridHeight=32; //격자무늬 세로칸
final int gridHidden=2; //격자무늬 숨겨진칸
final int gridStartX=0; //격자무늬 시작가로위치
final int gridStartY=0; //격자무늬 시작가로위치

///////////////////////////////////////////////////

int speedDelay;    // ms between steps 초간 간격
int grid[][];          //격자무늬 배열 설정
int rectWidth, rectHeight; //직사각형 가로,세로
float timer; //시간

///////////////////////////////////////////////////

int tetrisPosX, tetrisPosY, tetrisType, tetrisRotation, tetrisSize; //테트리스 떨어지는 기둥 가로,세로,유형, 사각형 개수
boolean tetrisLocked, tetrisDownStep, tetrisDrop;          //테트리스 락 , 테트리스 밑으로 내려오는중, 테트리스 밑으로

///////////////////////////////////////////////////

final int maxQueue=3;
int tetrisQueue[];     //테트리스 대기목록
int queueX, queueY;  //

///////////////////////////////////////////////////

PImage a,b,b2,c,d,e; //이미지 불러오기

//테트리스게임 아이템 불러오기
PImage ItemImage1,ItemImage2, ItemImage3, ItemImage4, ItemImage5, ItemImage6, ItemImage7, ItemImage8;
PImage ItemImage9,ItemImage10,ItemImage11,ItemImage12,ItemImage13,ItemImage14,ItemImage15,ItemImage16;


//배경음악 및 효과음 불러오기
AudioPlayer am,bm,cm,dm,em,gameBGM;//배경음악
AudioPlayer selectBGM=bm;//옵션중 선택한 음악
String playBGM="TROIKA"; //현재 메인 BGM 이름
boolean play=false;
int playNumber=1;
int bgmNumber=1;

//폰트 불러오기
PFont fontA,fontB,fontC;//폰트 A, B

///////////////////////////////////////////////////

//아이탬

PImage[] ItemImage = new PImage[5];
int[] ItemList = new int[5];

int randomItem;
int ItemLength=0;
int randomLine;
int randomTile;

boolean nextState=false;
boolean holdState=false;
boolean turnState=false;
boolean downState=false;
boolean blackState=false;
boolean levelState=false;

///////////////////////////////////////////////////

int level; //레벨
int score; //점수
int totalBreakLine; //게임진행동안 껜 총 줄수
int breakLine;      //한번 테트리미노가 깬 줄의 수
int tetrisisCount=0,tripleCount=0,doubleCount=0,monoCount=0; //테트리스,트리플,더블,모노 수

String name;
int ranking;


void setup()
{
  size(600, 600); //게임창 크기 설정
  
  //사용할 이미지들을 불러온다.
  a=loadImage("테트리스-배경.gif"); //게임 메인화면
  b=loadImage("게임-조작법.jpg");//게임 설명 화면
  b2=loadImage("아이템-설명.jpg");//게임 설명 화면
  c=loadImage("테트리스-게임-별도화면-수정.jpg"); //테트리스 게임 별도화면
  d=loadImage("테트리스 랭킹.jpg");
  e=loadImage("옵션.png");
  
  //게임 아이템 이미지들
  ItemImage1=loadImage("게임 아이템\\아이탬1.png");
  ItemImage2=loadImage("게임 아이템\\아이탬2.png");
  ItemImage3=loadImage("게임 아이템\\아이탬3.png");
  ItemImage4=loadImage("게임 아이템\\아이탬4.png");
  ItemImage5=loadImage("게임 아이템\\아이탬5.png");
  ItemImage6=loadImage("게임 아이템\\아이탬6.png");
  ItemImage7=loadImage("게임 아이템\\아이탬7.png");
  ItemImage8=loadImage("게임 아이템\\아이탬8.png");
  ItemImage9=loadImage("게임 아이템\\아이탬9.png");
  ItemImage10=loadImage("게임 아이템\\아이탬10.png");
  ItemImage11=loadImage("게임 아이템\\아이탬11.png");
  ItemImage12=loadImage("게임 아이템\\아이탬12.png");
  ItemImage13=loadImage("게임 아이템\\아이탬13.png");
  ItemImage14=loadImage("게임 아이템\\아이탬14.png");
  ItemImage15=loadImage("게임 아이템\\아이탬15.png");
  ItemImage16=loadImage("게임 아이템\\아이탬16.png");
  
  //사용할 bgm, 효과음 들을 불러온다.
  minim = new Minim(this);
  am = minim.loadFile("data\\01-title.mp3", 2048);
  bm = minim.loadFile("data\\02-LOGINSKA.mp3", 2048);
  cm = minim.loadFile("data\\03-BRADINSKY.mp3", 2048);
  dm = minim.loadFile("data\\04-KARINKA.mp3", 2048);
  em = minim.loadFile("data\\05-TROIKA.mp3", 2048);
  
  gameBGM=bm;
  am.loop();
  
  //사용할 폰트를 불러온다.
  fontA = loadFont("data\\BodoniMTCondensed-BoldItalic-48.vlw");
  fontB = loadFont("data\\ArialNarrow-Bold-48.vlw");
  fontC = loadFont("data\\HYsupB-48.vlw");

}


void draw()
{
  switch(state)
  {
   case 0 :  image(a, 0, 0, 600, 600);  break;//게임 처음 화면 : 테트리스-배경
  
   case 1 :  GameStart(); break;
   
   case 2 : image(b, 0, 0, 600, 600); //게임 조작법 화면
            if(mousePressed)
            {
              image(b2, 0, 0, 600, 600);
            }
   
            if(keyPressed)
            {
              state=0;
            }
            break;
            
   case 3 : //image(d, 0, 0, 600, 600); //게임 랭킹
   
   /*
   저장된 랭킹들 정렬후 순위 보여지게 할수 있어야함.
   */
    textFont(fontC,40);
    background(0);
    
    int[][] scores = new int[10][4];
    String[] lines = loadStrings("score.txt");
    for(int i=0; i<lines.length; i++) 
    {
      scores[i] = int(split(lines[i],'\t'));
    }
    
    textSize(80);
    fill(255,0,0);
    text("Tetris Rank",70,130);
    
    textSize(30);
    fill(255);
    text("순위       점수             일시  ",80,240);
    textSize(20);
    for(int i=0;i<10;i++)
    {
      text(i+1+"위  " ,95,275+i*30);
      text(scores[i][0],200,275+i*30);
      text(scores[i][1]+"년",300,275+i*30);
      text(scores[i][2]+"월",390,275+i*30);
      text(scores[i][3]+"일",440,275+i*30);
    } 
    
            if(keyPressed)
            {
              state=0;
            }
            break;
           
   case 4 :    image(e, 0, 0, 600, 600); //게임 옵션
               fill(0);
               textFont(fontB,15);
               text(playBGM, 441, 406); 
   
            if(keyPressed)
            {
              if(play)
              {
                selectBGM.pause();
              }
              am.loop();
              state=0;
            }
            break;
   case 5 : //테트리스 게임 종료
   minim.stop(); 
   exit(); break;
   default: break;
  }
}

////////////////////////////////////////////////////////////////////////////////////////////////////

void GameStart()
{ 
  if(GameSetup)
  {
    size (600,600);
    gameBGM.loop();
    
    //격자무늬 객체 생성후 초기화
    grid = new int[gridWidth][gridHeight];
    for (int x=0;x<gridWidth;x++) 
    {
      for (int y=0;y<gridHeight;y++) 
      {
        grid[x][y]=0;
      }
    }
    
    rectWidth = int(200/10); //20
    rectHeight = int(400/20);//20
    queueX = 11+gridStartX+rectWidth*gridWidth;  //11+0+20*20 = 411
    queueY = 10;
    
    //테트리스 대기목록 설정
    tetrisQueue = new int[maxQueue]; //maxQueue=3
    for(int i=0;i<maxQueue;i++) 
    {
      tetrisQueue[i]=round(random(0,6));
    }
    tetrisNew();
  
    tetrisSaved=7;
    tetrisCanSave=true;
    timer = millis(); //시간 진행
    
    score=0;
    totalBreakLine=0;
    tetrisisCount=0;
    tripleCount=0;
    doubleCount=0;
    monoCount=0;
    
    name="";
    InputNameOK=false;
    ranking=11;
    
    
     nextState=false;
     holdState=false;
     turnState=false;
     downState=false;
     blackState=false;
     levelState=false;
    
    
    //아이탬
    for(int i=0;i<ItemList.length;i++)
    {
      ItemList[i]=0;
      ItemImage[i]=null;
    }
    ItemLength=0;
    
    
    GameSetup=false;
  }
  
  if(levelState) //‘level 아이탬' 사용시
  {
    thread("LEVEL");
  }
  else if(!(levelState) )
  {
    //난이도에 따른 레벨 설정 구간
    level=totalBreakLine/30+1;
    if(level<=7)
    {
      speedDelay=550-50*level;
    }
    else if(level<=8)
    {
      speedDelay=200-40*(level-7);
    }
    else
    {
      speedDelay=160-10*(level-9);
    }
  }
  
  background(0);
  Background();        //추가 배경화면
  GameImplementation();//게임 구현
  
}

////////////////////////////////////////////////////////////////////////////////////////////////////

// 테트리미노 결정하기
void tetrisNew() 
{
  int type=tetrisQueue[0];
  for(int i=0;i<maxQueue-1;i++) 
  {
    tetrisQueue[i]=tetrisQueue[i+1];
  }
  tetrisQueue[maxQueue-1]=round(random(0,6));

  tetrisNew(type);  
}

void tetrisNew(int type) 
{
  tetrisType=type;
  if (tetrisType==0 || tetrisType==3)
  {
    tetrisSize=4;
  }
  else
  { 
    tetrisSize=3;
  }
  
  tetrisRotation=0;
  tetrisPosY=0;
  tetrisPosX = int(gridWidth/2) - (tetrisSize-1);
  
}

////////////////////////////////////////////////////////////////////////////////////////////////////

void Background()
{
  //배경화면 색 정하기  
  switch(level)
  {
  case 1 : background(255);       break;
  case 2 : background(204,51,51); break;
  case 3 : background(204,204,51);break;
  case 4 : background(2,56,204);  break;
  case 5 : background(255,56,95); break;
  case 6 : background(22,165,36); break;
  case 7 : background(255,245,39); break;
  case 8 : background(255,45,69); break;
  case 9 : background(87,96,255); break;
  case 10 : background(102,153,51);break;
  case 11 : background(102);break;
  case 12 : background(196,13,151);break;
  case 13 : background(102,53,251);break;
  case 14 : background(102,204,102);break;
  case 15 : background(0,102,153);break;
  case 16 : background(153,51,0);break;
  case 17 : background(255,153,203);break;
  case 18 : background(102,255,255);break;
  case 19 : background(0,51,0);break;
  case 20 : background(0,0,51);break;
  default : background(level*50,1000/level,level*level);break;
  }
  
  //아이탬 보여주기
  fill(000,020,040);  //밤하늘색 배경
  rect(width/2-100, 440, 100, 20);
  
  for(int i=0;i<ItemLength;i++)
  {
   image(ItemImage[i],width/2-100+20*i, 440, 20, 20);
  }
  
  image(c, 300, 0, 300, 600); //테트리스 게임내 화면 절반
}

void GameImplementation()
{
  if(blackState) //‘암흑 아이탬' 사용시
  {
    thread("BLACK");
  }
  else if(!(blackState) )
  {
    renderGrid();  //테트리스 판 나타내기
  }
  
  if(nextState) //‘NEXT 못보게하기 아이탬' 사용
  {
    thread("NEXT");
  }
  else if(!(nextState) )
  {
    renderQueue(); //테트리스 예비목록, 킵킹 모양 나타내기
  }
  

  if (!gameOver && (millis()>timer+speedDelay || tetrisDownStep || tetrisDrop))   //게임오버가 아닐경우 && ( 시간이 0.5초 경과시 || ↓눌렀을때  || ↑눌렀을때 )
  {
    if (!tetrisDrop)  //↑을 안눌렀을때
    {
      tetrisStepDown(); //테트리미노 떨어트리기
    }
    else  //↑을 눌렀을때
    {
      while (tetrisDrop) 
      { 
        //전체가 떨어지기 전에 왼쪽/오른쪽으로 이동 할 수 있어야한다.
        tetrisStepDown(); //테트리미노 떨어트리기
        tetrisDrop = tetrisCheckMove(0,1);
      }
    }

    tetrisDownStep=false;
    tetrisDrop=false;
    timer = millis();
  }
  
  if (gameOver)
  {
    tetrisLocked=true;
    renderGameOver();
  }
  
  textFont(fontB,15);
  fill(0);
  text("Next", width/2-95, 10); //NEXT
  text("KEEPING", width/2-95, 500); //테트리미노 킵핑
 
  text("ItemList", width/2-95, 430); //지금까지 깬 모노수
  if(!(ranking<11 && !(gameOver)))
  {
    text("Level"+"    :" +level, width/2-95, 220); //레벨
    text("Score"+"    :" +score, width/2-95, 240); //스코어
    text("BreakLine"+":" +totalBreakLine, width/2-95, 260); //지금까지 깬 줄수
    text("tetrisis"+"   :" +tetrisisCount, width/2-95, 280); //지금까지 깬 테트리스수
    text("Triple"+"   :" +tripleCount, width/2-95, 300); //지금까지 깬 트리플수
    text("Double"+" :" +doubleCount, width/2-95, 320); //지금까지 깬 더블수
    text("Mono"+"    :" +monoCount, width/2-95, 340); //지금까지 깬 모노수
  }
  
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//테트리스 판 나타내기
void renderGrid()
{
  stroke(55);
  
  for(int x=0;x<gridWidth;x++)
  {
    for(int y=0;y<gridHeight;y++)
    {
        switchFillColor(grid[x][y]);
        rect(gridStartX + x*rectWidth , gridStartY + (y-gridHidden)*rectHeight , rectWidth , rectHeight);
    }
  }
  
  // 테트리미노 그림자 나타내기
  renderShadow();
  
  // 테트리미노 내려오는것 나타내기
  for(int y=0;y<tetrisSize;y++) 
  {
    for(int x=0;x<tetrisSize;x++) 
    {
      if (gridTemplate[tetrisType] [tetrisRotation] [y] [x] !=0 && y + tetrisPosY >= gridHidden) 
      {
        switchFillColor(gridTemplate[tetrisType] [tetrisRotation] [y] [x]);
        rect(gridStartX + (x+tetrisPosX)*rectWidth , gridStartY + ( (y+tetrisPosY - gridHidden)*rectHeight ) ,rectWidth , rectHeight);
      }
    }
  }
  
}

// 테트리미노 그림자 나타내기
void renderShadow() 
{
  int xx=tetrisPosX;
  int yy=tetrisPosY;
  boolean moveOK=false;
  
  while (tetrisCheckMove(0,1,xx,yy,tetrisRotation)) 
  {
    yy++;
  }
  
  for(int y=0;y<tetrisSize;y++) 
  {
    for(int x=0;x<tetrisSize;x++) 
    {
      if (gridTemplate[tetrisType][tetrisRotation][y][x] != 0 && y + yy >= gridHidden) 
      {
        if (gridTemplate[tetrisType][tetrisRotation][y][x]!=0) 
        {
          fill(35);
        }
        rect(gridStartX + (x+xx)*rectWidth , gridStartY + ((y+yy-gridHidden) * rectHeight) , rectWidth , rectHeight);
      }
    }
  }
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//테트리스 예비목록, 킵킹 모양 나타내기
void renderQueue() 
{
  //테트리스 대기목록 첫번째
  for(int y=0;y<4;y++) 
  {
    for(int x=0;x<4;x++) 
    {
      switchFillColor(gridTemplate[ tetrisQueue[0] ] [0] [y] [x] ); 
      rect(queueX + x*rectWidth , queueY + y*rectHeight , rectWidth , rectHeight);
    }
  }
  
  //테트리스 대기목록 두번째,세번째
  for(int i=1;i<maxQueue;i++)
  {
    for(int y=0;y<4;y++) 
    {
      for(int x=0;x<4;x++) 
      {
        int yi = 10 + queueY + 4*rectHeight + ((i-1) * (10 + 4*(rectHeight/2) ));
        switchFillColor(gridTemplate[ tetrisQueue[i] ] [0] [y] [x] );
        rect(queueX + (rectWidth*2) + x*(rectWidth/2) , yi + (y*(rectHeight/2)) ,rectWidth/2,rectHeight/2);
      }
    }
  }
  
  //킵킹 테트리미노
  int yi = ( (gridHeight-gridHidden-4)*rectHeight ) -queueY;
  for(int y=0;y<4;y++)
  {
    for(int x=0;x<4;x++)
    {
      switchFillColor(gridTemplate[tetrisSaved] [0] [y] [x] );
      rect(queueX + x*rectWidth , yi + y*rectHeight , rectWidth , rectHeight);
    }
  }
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//테트리미노 떨어트리기 : 아래로 tetrisimino를 한 단계 이동, 만약 중지가 된 경우 TRUE를 반환합니다.
boolean tetrisStepDown() 
{
  boolean moveOK = tetrisCheckMove(0,1);
       
  if (moveOK)
  {
    tetrisPosY++;
  }
  else 
  {
    if (tetrisPosY!=0) 
    {
      tetrisFixToGrid(); // 격자무늬 판에 테트리미노를 수정시키기
      gridCheck();       // 격자무늬 판에 가득찬 줄이 있는지 판단하기
      tetrisNew();       // 새로운 테트리스 생성
      tetrisCanSave=true;
    } 
    else
    {
      gameOver=true;     //게임오버
    }
  }
    

  return !moveOK;
}

////////////////////////////////////////////////////////////////////////////////////////////////////

// 이동하기 전에 움직일수 있는지 일반적인 검사를 시행한다.
boolean tetrisCheckMove(int xm, int ym)
{
  return tetrisCheckMove(xm, ym, tetrisPosX, tetrisPosY, tetrisRotation);
}

boolean tetrisCheckMove(int xm, int ym, int tetrisX, int tetrisY, int tetrisR) 
{
  boolean moveOK=true;
  int gridC=0;
  
  for(int y=0;y<tetrisSize;y++)
  {
    for(int x=0;x<tetrisSize;x++)
    {
      if ((tetrisY + y + ym < 0 || tetrisY + y + ym >= gridHeight) || (tetrisX + x + xm < 0 || tetrisX + x + xm >= gridWidth)) 
      {
        gridC=1;  //테트리미노 4조각중 하나가 격자무늬 판중 하나를 벗어남.
      }
      else
      {
        gridC=grid[tetrisX + x + xm][tetrisY+ y + ym];
      }

      if (gridC!=0 && gridTemplate[tetrisType][tetrisR][y][x]!=0)
      {
        moveOK=false;
      }
    }
  }
  
  return moveOK;
}

/////////////////////////////////////////////

// 만약 회전하면서 움직일때 테트리미노가 격자무늬 밖으로 나가는지 확인합니다.
boolean tetrisCheckRotate(int rm)
{
  boolean rotateOK=false;
  int x = tetrisPosX;
  int y = tetrisPosY;
  int tetrisR = tetrisRotation+rm;
  if (tetrisR==4) tetrisR=0;
  if (tetrisR==-1) tetrisR=3;

  rotateOK = tetrisCheckMove(0, 0, x, y, tetrisR);

  if (!rotateOK && tetrisCheckMove(-1,0,x,y,tetrisR))
  {
      x--;
      rotateOK = tetrisCheckMove(0, 0, x, y, tetrisR);
  }
      
  if (!rotateOK && tetrisCheckMove(-2,0,x,y,tetrisR)) 
  {
      x=x-2; 
      rotateOK = tetrisCheckMove(0, 0, x, y, tetrisR);
  }
  if (!rotateOK && tetrisCheckMove(+1,0,x,y,tetrisR)) 
  {
    x++;
    rotateOK = tetrisCheckMove(0, 0, x, y, tetrisR);
  }

  if (!rotateOK && tetrisCheckMove(+2,0,x,y,tetrisR))
  {
      x=x+2;
      rotateOK = tetrisCheckMove(0, 0, x, y, tetrisR);
  }

  if (rotateOK)
  {
    tetrisPosX=x;
  }
  
  return rotateOK;
}

////////////////////////////////////////////////////////////////////////////////////////////////////

// 격자무늬 판에 테트리미노를 수정시키기
void tetrisFixToGrid()
{
  for(int y=0;y<tetrisSize;y++)
  {
    for(int x=0;x<tetrisSize;x++)
    {
      if (gridTemplate [tetrisType] [tetrisRotation] [y] [x] != 0) 
      {
        grid[tetrisPosX+x][tetrisPosY+y] = gridTemplate [tetrisType] [tetrisRotation] [y] [x];
      }
    }
  }
}

// 격자무늬 판에 가득찬 줄이 있는지 판단하기
void gridCheck() 
{
  boolean removeLine;
  breakLine=0;
  
  for (int y=0;y<gridHeight;y++) 
  {
    removeLine=true;
    for (int x=0;x<gridWidth;x++) 
    {
      if (grid[x][y]==0)
      { 
        removeLine=false; 
        break;
      }
    }
    
    // 가득찬 줄 제거
    if (removeLine) 
    {
      breakLine++;
      totalBreakLine++;
      
       for (int yi=y ; yi>0 ; yi--)
       {
         for (int xi=0 ; xi<gridWidth ; xi++) 
         {
           grid[xi][yi]=grid[xi][yi-1];
         }
       }
    }
  }
  switch(breakLine)
  { 
    case 0: break;
    case 1: score=score+100*1;  monoCount++; break;
    case 2: score=score+100*4; doubleCount++; break;
    case 3: score=score+100*9;  tripleCount++; break;
    case 4: score=score+100*16; tetrisisCount++; 
     if (ItemLength<5)
     {
      randomItem=round(random(0,15))+1;
      switch(randomItem)
      {
        case 1   : ItemImage[ItemLength]=ItemImage1;  break;
        case 2   : ItemImage[ItemLength]=ItemImage2;  break;
        case 3   : ItemImage[ItemLength]=ItemImage3;  break;
        case 4   : ItemImage[ItemLength]=ItemImage4;  break;
        case 5   : ItemImage[ItemLength]=ItemImage5;  break;
        case 6   : ItemImage[ItemLength]=ItemImage6;  break;
        case 7   : ItemImage[ItemLength]=ItemImage7;  break;
        case 8   : ItemImage[ItemLength]=ItemImage8;  break;
        case 9   : ItemImage[ItemLength]=ItemImage9;  break;
        case 10  : ItemImage[ItemLength]=ItemImage10; break;
        case 11  : ItemImage[ItemLength]=ItemImage11; break;
        case 12  : ItemImage[ItemLength]=ItemImage12; break;
        case 13  : ItemImage[ItemLength]=ItemImage13; break;
        case 14  : ItemImage[ItemLength]=ItemImage14; break;
        case 15  : ItemImage[ItemLength]=ItemImage15; break;
        case 16  : ItemImage[ItemLength]=ItemImage16; break;
      }
      
      ItemList[ItemLength]=randomItem;
      ItemLength++;
    }
    
    break;
  }
}

////////////////////////////////////////////////////////////////////////////////////////////////////


void switchFillColor(int c) 
{
  switch (c) 
  {
    case 0: fill(000,020,040); break;  //검은색 배경
    case 1: fill(000,255,255); break;  //하늘색 'ㅡ'
    case 2: fill(051,102,255); break;  //파란색 'ㄴ'
    case 3: fill(255,102,000); break;  //주황색 'ㄱ'반대
    case 4: fill(255,255,000); break;  //노란색 'ㅁ'
    case 5: fill(000,153,051); break;  //초록색 'ㄹ'반대
    case 6: fill(153,000,204); break;  //보라색 'ㅗ' 
    case 7: fill(255,000,000); break;  //빨간색 'ㄹ'
    case 8: fill(200,000,050); break;  //분홍색 방해 아이탬 
    default: fill(255); break;
  }
}

////////////////////////////////////////////////////////////////////////////////////////////////////


// Save tetrisimino for later
void tetrisSave() 
{
  int t = tetrisSaved;
  tetrisSaved = tetrisType;
  if (t==7)
  {
    tetrisNew();
  }
  else 
  {
    tetrisNew(t);
    tetrisCanSave=false;
  }
}

////////////////////////////////////////////////////////////////////////////////////////////////////

void renderGameOver()
{
  textFont(fontA, 32);
  fill(255);
  int x = int(((gridWidth*rectWidth)/2)-(textWidth("Game Over")/2));
  text("Game Over", x, 295);
  
  nextState=false;
  holdState=false;
  turnState=false;
  downState=false;
  blackState=false;
  levelState=false;
  
  gameBGM.pause();
  
  /////////////////////////////////////////////////////////////////////////////////////////////////////
  //score 저장공간
  // 1. 점수 저장후 게임 순위안에 들어가는지 판단 후에 순위내에 들면 점수 파일 입력을 이용해서 text파일로 저장
  // 될수 있으면 2. 성함 + 3.알파로 날짜(년,월,일)도 같이 저장될수 있도록
  //////////////////////////////////////////////////////////////////////////////////////////////////////
  
  int[][] scores = new int[10][4];
  int[] s = new int[4];
  int ranking=11;


  s[0] = score;
  s[1] = year();
  s[2] = month();
  s[3] = day();
  
  
  String[] lines = loadStrings("score.txt");
  for(int i=0; i<lines.length; i++) 
  {
    scores[i] = int(split(lines[i],'\t'));
  }
  
  for(int i=0; i<scores.length; i++)
  {
    if(scores[i][0] < score)
    {
      ranking=i+1;
      break;
    }
  }
  for(int i=0; i<scores.length; i++)
  {
    if(scores[i][0] < score)
    {
      int[] tmp = scores[i];
      scores[i] = s;
      s = tmp;
    }
  }

  
  lines = new String[10];
  for(int i=0; i<lines.length; i++)
  {
    lines[i] = "";
  }
  
  for(int i=0; i<scores.length; i++) 
  {
    for(int j=0; j<4; j++) 
    {
      lines[i] += scores[i][j] + "\t";
    }
  }

  if(ranking<11)
  {
    fill(144);
    rect(164,204,272,200);
  
    fill(0);
    textFont(fontC,40);
    text("게임 랭킹",216,263);
    textSize(20);
    text("축하합니다.",253,305);
    textSize(16);
    text("전체순위 중에서 "+ranking+"위를 하셨습니다.",177,347);
    textFont(fontC,30);
    text("성명 : ",211,390);
    fill(255);
    rect(297,364,121,32);
    fill(255);
    rect(297,364,textWidth(name)+20,32);
    fill(0);
    text(name,305,390);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
   
  if(mousePressed)
  {
    saveStrings("score.txt",lines); //점수 저장
    am.loop(); //메인 bgm 재생
    state=0;   //메인화면
    GameSetup=true; 
    tetrisLocked=false;
    gameOver=false;
  }
}



///////////////////////////////////////////////////////////////////////////////////////////////////


void mousePressed()
{
  switch(state)
  {
   case 0 :
   
   if(mouseX>180  && mouseX<456 && mouseY>147 && mouseY<202 )
   {
     am.pause();
     state=1;//게임 시작
   }
   else if(mouseX>267  && mouseX<360 && mouseY>244 && mouseY<268 )
   {
     //am.close();
     state=2;//게임방법 화면
   }
   else if(mouseX>278  && mouseX<343 && mouseY>300 && mouseY<327 )
   {
     //am.close();
     state=3;//게임 랭킹
   }
   else if(mouseX>281  && mouseX<341 && mouseY>347 && mouseY<376 )
   {
     am.pause();
     state=4;//게임 옵션
   }
   else if(mouseX>240  && mouseX<375 && mouseY>400 && mouseY<429 )
   {
    //am.close();
     state=5;//게임 종료
   }
   break;
   
   case 4 :

   
   if(mouseX>14  && mouseX<80 && mouseY>273 && mouseY<362 )
   {
     if(play)
     {
       selectBGM.pause();
     }
     selectBGM=bm;
     selectBGM.loop();
     playNumber=1;
     play=true;
   }
   else if(mouseX>88  && mouseX<156 && mouseY>273 && mouseY<362 )
   {
     if(play)
     {
       selectBGM.pause();
     }
     selectBGM=cm;
     selectBGM.loop();
     playNumber=2;
     play=true;
   }
   else if(mouseX>163  && mouseX<231 && mouseY>273 && mouseY<362 )
   {
     if(play)
     {
       selectBGM.pause();
     }
     selectBGM=dm;
     selectBGM.loop();
     playNumber=3;
     play=true;
   }
   else if(mouseX>239  && mouseX<306 && mouseY>273 && mouseY<362 )
   {
     if(play)
     {
       selectBGM.pause();
     }
     selectBGM=em;
     selectBGM.loop();
     playNumber=4;
     play=true;
   }
   else if(mouseX>151  && mouseX<184 && mouseY>426 && mouseY<469 )
   {
     selectBGM.pause();
     play=false;
   }
   else if(mouseX>448  && mouseX<516 && mouseY>273 && mouseY<362 )
   {
     gameBGM=selectBGM;
     bgmNumber=playNumber;
   }
   
   switch(bgmNumber)
   {
     case 1 :  playBGM="TROIKA"; break;
     case 2 :  playBGM="LOGINSKA"; break;
     case 3 :  playBGM="BRADINSKY"; break;
     case 4 :  playBGM="KARINKA"; break;
     default: break;
   }
   
   
   
   break;
   
   default: break;
  }
}  

// Process input
void keyPressed() 
{
  if (!tetrisLocked  && state==1) 
  {
    // 일시정지 
    if(keyCode==ENTER && !(PauseGame))
    {
      textFont(fontA, 32);
      fill(255);
     
      int x = int(((gridWidth*rectWidth)/2)-(textWidth("Pause the game")/2));
      text("Pause the game", x, 295); 
      gameBGM.pause();
     
      PauseGame=true;
      
      keyCode=65;
     
      noLoop();
     
    }
    if(keyCode==ENTER && PauseGame )
    {
      gameBGM.play();
      loop();
      PauseGame=false;
    }
    
    if (keyCode==' ' && ItemLength>0)
    {
      int item=ItemList[0];

      for(int i=1;i<ItemLength;i++)
      {
        ItemList[i-1]=ItemList[i];
        ItemImage[i-1]=ItemImage[i];
      }
      ItemLength--;
      
      ItemEffects(item);
     
    }
    
    ///////////////////////////////////////////시연용.
    
   if ((keyCode=='A' || keyCode=='a')&& ItemLength<5)
    {
      randomItem=round(random(0,15))+1;
      switch(randomItem)
      {
        case 1   : ItemImage[ItemLength]=ItemImage1;  break;
        case 2   : ItemImage[ItemLength]=ItemImage2;  break;
        case 3   : ItemImage[ItemLength]=ItemImage3;  break;
        case 4   : ItemImage[ItemLength]=ItemImage4;  break;
        case 5   : ItemImage[ItemLength]=ItemImage5;  break;
        case 6   : ItemImage[ItemLength]=ItemImage6;  break;
        case 7   : ItemImage[ItemLength]=ItemImage7;  break;
        case 8   : ItemImage[ItemLength]=ItemImage8;  break;
        case 9   : ItemImage[ItemLength]=ItemImage9;  break;
        case 10  : ItemImage[ItemLength]=ItemImage10; break;
        case 11  : ItemImage[ItemLength]=ItemImage11; break;
        case 12  : ItemImage[ItemLength]=ItemImage12; break;
        case 13  : ItemImage[ItemLength]=ItemImage13; break;
        case 14  : ItemImage[ItemLength]=ItemImage14; break;
        case 15  : ItemImage[ItemLength]=ItemImage15; break;
        case 16  : ItemImage[ItemLength]=ItemImage16; break;
      }
      
      ItemList[ItemLength]=randomItem;
      ItemLength++;
    }
    
    ////////////////////////////////////////////////////
     
   
   
   
   // MOVE LEFT
    if (keyCode==LEFT) 
    {
      if (tetrisCheckMove(-1,0))
      {
        tetrisPosX--;
      }
    }
    
    // MOVE RIGHT
    if (keyCode==RIGHT && tetrisCheckMove(1,0)) 
    {
      tetrisPosX++;
    }
    
    // DOWN
    if (keyCode==DOWN) 
    {
      tetrisDownStep=true;
    }
    
    if(downState) //‘down 아이탬' 사용시
    {
      thread("DOWN");
    }
    else if(!(downState) )
    {
      // UP
      if (keyCode==UP) 
      {
        tetrisDrop=true;
      }
    }
    
    if(holdState) //‘hold 아이탬' 사용시
    {
      thread("HOLD");
    }
    else if(!(holdState) )
    {
      // SAVE tetrisIMINO
      if ((key=='w' || key=='W') && tetrisCanSave)
      {
        tetrisSave();
      }
    }
    
    
    if(turnState) //‘turn 아이탬' 사용시
    {
      thread("TURN");
    }
    else if(!(turnState) )
    {
      // ROTATE LEFT
      if ((key=='q' || key=='Q') && tetrisCheckRotate(-1)) 
      {
        tetrisRotation--; timer = millis(); 
      }
    
      // ROTATE RIGHT
      if ((key=='e' || key=='E') && tetrisCheckRotate(+1)) 
      { 
        tetrisRotation++; timer = millis(); 
      }
      if (tetrisRotation==4) 
      {
        tetrisRotation=0;
      }
      if (tetrisRotation==-1) 
      {
        tetrisRotation=3;
      }
   
    }
  }
  else{
    if(keyPressed && !(InputNameOK))
    {
      name=name+key;
    }
    if(key==ENTER)
    {
      InputNameOK=true;
    }
  }
}

void ItemEffects(int Item)
{
  switch(Item)
  {
    case 1 : //1~4줄 플러스 아이탬
    randomLine=round(random(0,3))+1;
    
    for (int y=randomLine;y<gridHeight;y++)
    {
      for (int x=0;x<gridWidth;x++)
      {
        grid[x][y-randomLine]=grid[x][y];
      }
    }
    
    for (int y=gridHeight-randomLine;y<gridHeight;y++)
    {
      randomTile=round(random(0,9));
      for (int x=0;x<gridWidth;x++) 
      {
        if(x!=randomTile)
        {
          grid[x][y]=8;
        }
        else
        {
          grid[x][y]=0;
        }
      }
    }
    break;
    
    case 2 : //현재까지 모은 아이탬 증발
    for(int i=0;i<ItemLength;i++)//ItemList.length
    {
      ItemList[i]=0;
      ItemImage[i]=null;
    }
    ItemLength=0;
    break;
    
    case 3 : //일정시간동안 NEXT 못보게함
    nextState=true;
    break;
    
    case 4 : //일정시간동안 HOLD 못하게함
    holdState=true;
    break;
    
    case 5 : //일정시간동안 회전 못하게함
    turnState=true;
    break;
    
    case 6 : //일정시간동안 테트리미노를 빨리 못떨어트림
    downState=true;
    break;
      
    case 7 : //폭탄 : 지금까지 쌓아 올린 탑에 구멍을 여러개 냄
    randomTile=round(random(5,50));
    for(int i=0;i<randomTile;i++)
    {
      int tileX=round(random(0,gridWidth-1));
      int tileY=round(random(0,gridHeight-1));
      
      grid[tileX][tileY]=0;
    }
    break;
    
    case 8 :  //암흑 : 일정시간동안 테트리미노 화면이 어둡게 됨
    blackState=true;
    break;
    
    case 9 : //일정시간동안 테트리미노 떨어지는 속도 20레벨
    levelState=true;
    break;
    
    case 10 : //'ㅡ'자 3개
    for(int i=0;i<maxQueue;i++) 
    {
      tetrisQueue[i]=0;
    }
    break;
    
    case 11 : //좋은 아이탬으로 풀로 리셋
    ItemLength=0;
    for(int i=0;i<5;i++)
    {
      randomItem=int(random(10,16));
      switch(randomItem)
      {
        case 10 : ItemImage[i]=ItemImage10; break;
        case 12 : ItemImage[i]=ItemImage12; break;
        case 13 : ItemImage[i]=ItemImage13; break;
        case 14 : ItemImage[i]=ItemImage14; break;
        case 16 : ItemImage[i]=ItemImage16; break;
        default :  break;
      }
      if(randomItem==11 || randomItem==15)
      {
        i--;
      }
      else
      {
        ItemLength++;
        ItemList[i]=randomItem;
      }
    }
    break;
    
    case 12 : //1~4줄 마이너스 아이탬q
    randomLine=round(random(0,3))+1;
    for (int y=gridHeight-randomLine;y<gridHeight;y++)
    {
      for (int x=0;x<gridWidth;x++) 
      {
        grid[x][y]=0;
      }
    
      for (int yi=y ; yi>0 ; yi--)
      {
        for (int xi=0 ; xi<gridWidth ; xi++) 
        {
           grid[xi][yi]=grid[xi][yi-1];
        }
      }
      
    }
    switch(randomLine)
    {
      case 1: score=score+100*1;  break;
      case 2: score=score+100*4;  break;
      case 3: score=score+100*9;  break;
      case 4: score=score+100*16; break;
    }
    break;
    
    case 13 : //'ㅁ'자 3개
    for(int i=0;i<maxQueue;i++) 
    {
      tetrisQueue[i]=3;
    }break;
    
    case 14 : //모든 상태이상을 품
     nextState=false;
     holdState=false;
     turnState=false;
     downState=false;
     blackState=false;
     levelState=false;
    break;
    
    case 15 : //클리어 : 판쓸
    
    for (int x=0;x<gridWidth;x++) 
    {
      for (int y=0;y<gridHeight;y++) 
      {
        grid[x][y]=0;
      }
    }
    
    break;
    
    case 16 : //점수 물약
    score+=1000*int(random(1,4))*int(random(1,4));
    break;
    
    default : break;
 
  }
}



void BLACK()
{
  //blackNumber++;
  fill(0);
  rect(0,0,200,600);
  for(int i=0 ; i<5 ; i++)
  {  
    try
    {
      Thread.sleep(1000);
    } catch(Exception e){
    }
  }
  blackState=false;
}

void NEXT()
{
  fill(0);
  rect(200,0,100,200);
  for(int i=0 ; i<30 ; i++)
  {
    try
    {
      Thread.sleep(1000);
    } catch(Exception e){
    }
  }
  nextState=false;
}

void HOLD()
{
  tetrisCanSave=false;
  for(int i=0 ; i<30 ; i++)
  {
    try
    {
      Thread.sleep(1000);
    } catch(Exception e){
    }
  }
  tetrisCanSave=true;
  holdState=false;
}

void TURN()
{
  for(int i=0 ; i<10 ; i++)
  {
    try
    {
      Thread.sleep(1000);
    } catch(Exception e){
    }
  }
  turnState=false;
}

void DOWN()
{
  for(int i=0 ; i<10 ; i++)
  {
    try
    {
      Thread.sleep(1000);
    } catch(Exception e){
    }
  }
  downState=false;
}

void LEVEL()
{
  level=20;
  speedDelay=40;
  for(int i=0 ; i<10 ; i++)
  {
    try
    {
      Thread.sleep(1000);
    } catch(Exception e){
    }
  }
  levelState=false;
}



