import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class tetrisCode extends PApplet {


 
Minim minim;

//\ud14c\ud2b8\ub9ac\uc2a4 DX

/*
  tetriminos: I J L O S T Z
  0  : //\ud478\ub978\uc0c9 'I' , '\u3161'
  1  : //\ud30c\ub780\uc0c9 'L' , '\u3134'
  2  : //\uc8fc\ud669\uc0c9 'J' , '\u3131'\ubc18\ub300
  3  : //\ub178\ub780\uc0c9 'O' , '\u3141'
  4  : //\ucd08\ub85d\uc0c9 'S' , '\u3139'\ubc18\ub300
  5  : //\ubcf4\ub77c\uc0c9 'T' , '\u3157' 148,0,211
  6  : //\ube68\uac04\uc0c9 'Z' , '\u3139'
  
*/


//\ud14c\ud2b8\ub9ac\ubbf8\ub178
//gridTemplate [tetrisType] [tetrisRotation] [y] [x]
final int gridTemplate[][][][] = {
  {{{0,0,0,0}, {1,1,1,1}, {0,0,0,0}, {0,0,0,0}}, /* 0  : //\ud478\ub978\uc0c9 '\u3161' */
   {{0,0,1,0}, {0,0,1,0}, {0,0,1,0}, {0,0,1,0}}, 
   {{0,0,0,0}, {0,0,0,0}, {1,1,1,1}, {0,0,0,0}},
   {{0,1,0,0}, {0,1,0,0}, {0,1,0,0}, {0,1,0,0}}},
  {{{2,0,0,0}, {2,2,2,0}, {0,0,0,0}, {0,0,0,0}}, /* 1  : //\ud30c\ub780\uc0c9 '\u3134' */
   {{0,2,2,0}, {0,2,0,0}, {0,2,0,0}, {0,0,0,0}},
   {{0,0,0,0}, {2,2,2,0}, {0,0,2,0}, {0,0,0,0}},
   {{0,2,0,0}, {0,2,0,0}, {2,2,0,0}, {0,0,0,0}}}, 
  {{{0,0,3,0}, {3,3,3,0}, {0,0,0,0}, {0,0,0,0}}, /* 2  : //\uc8fc\ud669\uc0c9 '\u3131'\ubc18\ub300 */
   {{0,3,0,0}, {0,3,0,0}, {0,3,3,0}, {0,0,0,0}},
   {{0,0,0,0}, {3,3,3,0}, {3,0,0,0}, {0,0,0,0}},
   {{3,3,0,0}, {0,3,0,0}, {0,3,0,0}, {0,0,0,0}}},
  {{{0,4,4,0}, {0,4,4,0}, {0,0,0,0}, {0,0,0,0}}, /* 3  : //\ub178\ub780\uc0c9 '\u3141' */
   {{0,4,4,0}, {0,4,4,0}, {0,0,0,0}, {0,0,0,0}},
   {{0,4,4,0}, {0,4,4,0}, {0,0,0,0}, {0,0,0,0}},
   {{0,4,4,0}, {0,4,4,0}, {0,0,0,0}, {0,0,0,0}}},
  {{{0,5,5,0}, {5,5,0,0}, {0,0,0,0}, {0,0,0,0}}, /* 4  : //\ucd08\ub85d\uc0c9 '\u3139'\ubc18\ub300 */
   {{0,5,0,0}, {0,5,5,0}, {0,0,5,0}, {0,0,0,0}},
   {{0,0,0,0}, {0,5,5,0}, {5,5,0,0}, {0,0,0,0}},
   {{5,0,0,0}, {5,5,0,0}, {0,5,0,0}, {0,0,0,0}}},
  {{{0,6,0,0}, {6,6,6,0}, {0,0,0,0}, {0,0,0,0}}, /* 5  : //\ubcf4\ub77c\uc0c9 '\u3157' 148,0,211 */
   {{0,6,0,0}, {0,6,6,0}, {0,6,0,0}, {0,0,0,0}},
   {{0,0,0,0}, {6,6,6,0}, {0,6,0,0}, {0,0,0,0}},
   {{0,6,0,0}, {6,6,0,0}, {0,6,0,0}, {0,0,0,0}}},
  {{{7,7,0,0}, {0,7,7,0}, {0,0,0,0}, {0,0,0,0}}, /* 6  : //\ube68\uac04\uc0c9 '\u3139' */
   {{0,0,7,0}, {0,7,7,0}, {0,7,0,0}, {0,0,0,0}},
   {{0,0,0,0}, {7,7,0,0}, {0,7,7,0}, {0,0,0,0}},
   {{0,7,0,0}, {7,7,0,0}, {7,0,0,0}, {0,0,0,0}}},
  {{{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}, /* clear */
   {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}},
   {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}},
   {{0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}}};

///////////////////////////////////////////////////   
   
int state=0;            //\uac8c\uc784 \ud654\uba74 \uc0c1\ud0dc
boolean GameSetup=true; //\uac8c\uc784 \uc7ac\uc0ac\uc804\uc791\uc5c5
boolean gameOver=false; //\uac8c\uc784 \uc624\ubc84
boolean PauseGame=false;//\uac8c\uc784 \uc77c\uc2dc\uc815\uc9c0
boolean InputNameOK=false;


int tetrisSaved;      //\ud14c\ud2b8\ub9ac\ubbf8\ub178 \ud0b5\ud551(\uc800\uc7a5) 
boolean tetrisCanSave;//\ud14c\ud2b8\ub9ac\ubbf8\ub178 \ud0b5\ud551(\uc800\uc7a5) \uac00\ub2a5\uc5ec\ubd80

///////////////////////////////////////////////////

final int gridWidth=10;  //\uaca9\uc790\ubb34\ub2ac \uac00\ub85c\uce78
final int gridHeight=32; //\uaca9\uc790\ubb34\ub2ac \uc138\ub85c\uce78
final int gridHidden=2; //\uaca9\uc790\ubb34\ub2ac \uc228\uaca8\uc9c4\uce78
final int gridStartX=0; //\uaca9\uc790\ubb34\ub2ac \uc2dc\uc791\uac00\ub85c\uc704\uce58
final int gridStartY=0; //\uaca9\uc790\ubb34\ub2ac \uc2dc\uc791\uac00\ub85c\uc704\uce58

///////////////////////////////////////////////////

int speedDelay;    // ms between steps \ucd08\uac04 \uac04\uaca9
int grid[][];          //\uaca9\uc790\ubb34\ub2ac \ubc30\uc5f4 \uc124\uc815
int rectWidth, rectHeight; //\uc9c1\uc0ac\uac01\ud615 \uac00\ub85c,\uc138\ub85c
float timer; //\uc2dc\uac04

///////////////////////////////////////////////////

int tetrisPosX, tetrisPosY, tetrisType, tetrisRotation, tetrisSize; //\ud14c\ud2b8\ub9ac\uc2a4 \ub5a8\uc5b4\uc9c0\ub294 \uae30\ub465 \uac00\ub85c,\uc138\ub85c,\uc720\ud615, \uc0ac\uac01\ud615 \uac1c\uc218
boolean tetrisLocked, tetrisDownStep, tetrisDrop;          //\ud14c\ud2b8\ub9ac\uc2a4 \ub77d , \ud14c\ud2b8\ub9ac\uc2a4 \ubc11\uc73c\ub85c \ub0b4\ub824\uc624\ub294\uc911, \ud14c\ud2b8\ub9ac\uc2a4 \ubc11\uc73c\ub85c

///////////////////////////////////////////////////

final int maxQueue=3;
int tetrisQueue[];     //\ud14c\ud2b8\ub9ac\uc2a4 \ub300\uae30\ubaa9\ub85d
int queueX, queueY;  //

///////////////////////////////////////////////////

PImage a,b,b2,c,d,e; //\uc774\ubbf8\uc9c0 \ubd88\ub7ec\uc624\uae30

//\ud14c\ud2b8\ub9ac\uc2a4\uac8c\uc784 \uc544\uc774\ud15c \ubd88\ub7ec\uc624\uae30
PImage ItemImage1,ItemImage2, ItemImage3, ItemImage4, ItemImage5, ItemImage6, ItemImage7, ItemImage8;
PImage ItemImage9,ItemImage10,ItemImage11,ItemImage12,ItemImage13,ItemImage14,ItemImage15,ItemImage16;


//\ubc30\uacbd\uc74c\uc545 \ubc0f \ud6a8\uacfc\uc74c \ubd88\ub7ec\uc624\uae30
AudioPlayer am,bm,cm,dm,em,gameBGM;//\ubc30\uacbd\uc74c\uc545
AudioPlayer selectBGM=bm;//\uc635\uc158\uc911 \uc120\ud0dd\ud55c \uc74c\uc545
String playBGM="TROIKA"; //\ud604\uc7ac \uba54\uc778 BGM \uc774\ub984
boolean play=false;
int playNumber=1;
int bgmNumber=1;

//\ud3f0\ud2b8 \ubd88\ub7ec\uc624\uae30
PFont fontA,fontB,fontC;//\ud3f0\ud2b8 A, B

///////////////////////////////////////////////////

//\uc544\uc774\ud0ec

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

int level; //\ub808\ubca8
int score; //\uc810\uc218
int totalBreakLine; //\uac8c\uc784\uc9c4\ud589\ub3d9\uc548 \uaedc \ucd1d \uc904\uc218
int breakLine;      //\ud55c\ubc88 \ud14c\ud2b8\ub9ac\ubbf8\ub178\uac00 \uae6c \uc904\uc758 \uc218
int tetrisisCount=0,tripleCount=0,doubleCount=0,monoCount=0; //\ud14c\ud2b8\ub9ac\uc2a4,\ud2b8\ub9ac\ud50c,\ub354\ube14,\ubaa8\ub178 \uc218

String name;
int ranking;


public void setup()
{
  size(600, 600); //\uac8c\uc784\ucc3d \ud06c\uae30 \uc124\uc815
  
  //\uc0ac\uc6a9\ud560 \uc774\ubbf8\uc9c0\ub4e4\uc744 \ubd88\ub7ec\uc628\ub2e4.
  a=loadImage("\ud14c\ud2b8\ub9ac\uc2a4-\ubc30\uacbd.gif"); //\uac8c\uc784 \uba54\uc778\ud654\uba74
  b=loadImage("\uac8c\uc784-\uc870\uc791\ubc95.jpg");//\uac8c\uc784 \uc124\uba85 \ud654\uba74
  b2=loadImage("\uc544\uc774\ud15c-\uc124\uba85.jpg");//\uac8c\uc784 \uc124\uba85 \ud654\uba74
  c=loadImage("\ud14c\ud2b8\ub9ac\uc2a4-\uac8c\uc784-\ubcc4\ub3c4\ud654\uba74-\uc218\uc815.jpg"); //\ud14c\ud2b8\ub9ac\uc2a4 \uac8c\uc784 \ubcc4\ub3c4\ud654\uba74
  d=loadImage("\ud14c\ud2b8\ub9ac\uc2a4 \ub7ad\ud0b9.jpg");
  e=loadImage("\uc635\uc158.png");
  
  //\uac8c\uc784 \uc544\uc774\ud15c \uc774\ubbf8\uc9c0\ub4e4
  ItemImage1=loadImage("\uac8c\uc784 \uc544\uc774\ud15c\\\uc544\uc774\ud0ec1.png");
  ItemImage2=loadImage("\uac8c\uc784 \uc544\uc774\ud15c\\\uc544\uc774\ud0ec2.png");
  ItemImage3=loadImage("\uac8c\uc784 \uc544\uc774\ud15c\\\uc544\uc774\ud0ec3.png");
  ItemImage4=loadImage("\uac8c\uc784 \uc544\uc774\ud15c\\\uc544\uc774\ud0ec4.png");
  ItemImage5=loadImage("\uac8c\uc784 \uc544\uc774\ud15c\\\uc544\uc774\ud0ec5.png");
  ItemImage6=loadImage("\uac8c\uc784 \uc544\uc774\ud15c\\\uc544\uc774\ud0ec6.png");
  ItemImage7=loadImage("\uac8c\uc784 \uc544\uc774\ud15c\\\uc544\uc774\ud0ec7.png");
  ItemImage8=loadImage("\uac8c\uc784 \uc544\uc774\ud15c\\\uc544\uc774\ud0ec8.png");
  ItemImage9=loadImage("\uac8c\uc784 \uc544\uc774\ud15c\\\uc544\uc774\ud0ec9.png");
  ItemImage10=loadImage("\uac8c\uc784 \uc544\uc774\ud15c\\\uc544\uc774\ud0ec10.png");
  ItemImage11=loadImage("\uac8c\uc784 \uc544\uc774\ud15c\\\uc544\uc774\ud0ec11.png");
  ItemImage12=loadImage("\uac8c\uc784 \uc544\uc774\ud15c\\\uc544\uc774\ud0ec12.png");
  ItemImage13=loadImage("\uac8c\uc784 \uc544\uc774\ud15c\\\uc544\uc774\ud0ec13.png");
  ItemImage14=loadImage("\uac8c\uc784 \uc544\uc774\ud15c\\\uc544\uc774\ud0ec14.png");
  ItemImage15=loadImage("\uac8c\uc784 \uc544\uc774\ud15c\\\uc544\uc774\ud0ec15.png");
  ItemImage16=loadImage("\uac8c\uc784 \uc544\uc774\ud15c\\\uc544\uc774\ud0ec16.png");
  
  //\uc0ac\uc6a9\ud560 bgm, \ud6a8\uacfc\uc74c \ub4e4\uc744 \ubd88\ub7ec\uc628\ub2e4.
  minim = new Minim(this);
  am = minim.loadFile("data\\01-title.mp3", 2048);
  bm = minim.loadFile("data\\02-LOGINSKA.mp3", 2048);
  cm = minim.loadFile("data\\03-BRADINSKY.mp3", 2048);
  dm = minim.loadFile("data\\04-KARINKA.mp3", 2048);
  em = minim.loadFile("data\\05-TROIKA.mp3", 2048);
  
  gameBGM=bm;
  am.loop();
  
  //\uc0ac\uc6a9\ud560 \ud3f0\ud2b8\ub97c \ubd88\ub7ec\uc628\ub2e4.
  fontA = loadFont("data\\BodoniMTCondensed-BoldItalic-48.vlw");
  fontB = loadFont("data\\ArialNarrow-Bold-48.vlw");
  fontC = loadFont("data\\HYsupB-48.vlw");

}


public void draw()
{
  switch(state)
  {
   case 0 :  image(a, 0, 0, 600, 600);  break;//\uac8c\uc784 \ucc98\uc74c \ud654\uba74 : \ud14c\ud2b8\ub9ac\uc2a4-\ubc30\uacbd
  
   case 1 :  GameStart(); break;
   
   case 2 : image(b, 0, 0, 600, 600); //\uac8c\uc784 \uc870\uc791\ubc95 \ud654\uba74
            if(mousePressed)
            {
              image(b2, 0, 0, 600, 600);
            }
   
            if(keyPressed)
            {
              state=0;
            }
            break;
            
   case 3 : //image(d, 0, 0, 600, 600); //\uac8c\uc784 \ub7ad\ud0b9
   
   /*
   \uc800\uc7a5\ub41c \ub7ad\ud0b9\ub4e4 \uc815\ub82c\ud6c4 \uc21c\uc704 \ubcf4\uc5ec\uc9c0\uac8c \ud560\uc218 \uc788\uc5b4\uc57c\ud568.
   */
    textFont(fontC,40);
    background(0);
    
    int[][] scores = new int[10][4];
    String[] lines = loadStrings("score.txt");
    for(int i=0; i<lines.length; i++) 
    {
      scores[i] = PApplet.parseInt(split(lines[i],'\t'));
    }
    
    textSize(80);
    fill(255,0,0);
    text("Tetris Rank",70,130);
    
    textSize(30);
    fill(255);
    text("\uc21c\uc704       \uc810\uc218             \uc77c\uc2dc  ",80,240);
    textSize(20);
    for(int i=0;i<10;i++)
    {
      text(i+1+"\uc704  " ,95,275+i*30);
      text(scores[i][0],200,275+i*30);
      text(scores[i][1]+"\ub144",300,275+i*30);
      text(scores[i][2]+"\uc6d4",390,275+i*30);
      text(scores[i][3]+"\uc77c",440,275+i*30);
    } 
    
            if(keyPressed)
            {
              state=0;
            }
            break;
           
   case 4 :    image(e, 0, 0, 600, 600); //\uac8c\uc784 \uc635\uc158
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
   case 5 : //\ud14c\ud2b8\ub9ac\uc2a4 \uac8c\uc784 \uc885\ub8cc
   minim.stop(); 
   exit(); break;
   default: break;
  }
}

////////////////////////////////////////////////////////////////////////////////////////////////////

public void GameStart()
{ 
  if(GameSetup)
  {
    size (600,600);
    gameBGM.loop();
    
    //\uaca9\uc790\ubb34\ub2ac \uac1d\uccb4 \uc0dd\uc131\ud6c4 \ucd08\uae30\ud654
    grid = new int[gridWidth][gridHeight];
    for (int x=0;x<gridWidth;x++) 
    {
      for (int y=0;y<gridHeight;y++) 
      {
        grid[x][y]=0;
      }
    }
    
    rectWidth = PApplet.parseInt(200/10); //20
    rectHeight = PApplet.parseInt(400/20);//20
    queueX = 11+gridStartX+rectWidth*gridWidth;  //11+0+20*20 = 411
    queueY = 10;
    
    //\ud14c\ud2b8\ub9ac\uc2a4 \ub300\uae30\ubaa9\ub85d \uc124\uc815
    tetrisQueue = new int[maxQueue]; //maxQueue=3
    for(int i=0;i<maxQueue;i++) 
    {
      tetrisQueue[i]=round(random(0,6));
    }
    tetrisNew();
  
    tetrisSaved=7;
    tetrisCanSave=true;
    timer = millis(); //\uc2dc\uac04 \uc9c4\ud589
    
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
    
    
    //\uc544\uc774\ud0ec
    for(int i=0;i<ItemList.length;i++)
    {
      ItemList[i]=0;
      ItemImage[i]=null;
    }
    ItemLength=0;
    
    
    GameSetup=false;
  }
  
  if(levelState) //\u2018level \uc544\uc774\ud0ec' \uc0ac\uc6a9\uc2dc
  {
    thread("LEVEL");
  }
  else if(!(levelState) )
  {
    //\ub09c\uc774\ub3c4\uc5d0 \ub530\ub978 \ub808\ubca8 \uc124\uc815 \uad6c\uac04
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
  Background();        //\ucd94\uac00 \ubc30\uacbd\ud654\uba74
  GameImplementation();//\uac8c\uc784 \uad6c\ud604
  
}

////////////////////////////////////////////////////////////////////////////////////////////////////

// \ud14c\ud2b8\ub9ac\ubbf8\ub178 \uacb0\uc815\ud558\uae30
public void tetrisNew() 
{
  int type=tetrisQueue[0];
  for(int i=0;i<maxQueue-1;i++) 
  {
    tetrisQueue[i]=tetrisQueue[i+1];
  }
  tetrisQueue[maxQueue-1]=round(random(0,6));

  tetrisNew(type);  
}

public void tetrisNew(int type) 
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
  tetrisPosX = PApplet.parseInt(gridWidth/2) - (tetrisSize-1);
  
}

////////////////////////////////////////////////////////////////////////////////////////////////////

public void Background()
{
  //\ubc30\uacbd\ud654\uba74 \uc0c9 \uc815\ud558\uae30  
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
  
  //\uc544\uc774\ud0ec \ubcf4\uc5ec\uc8fc\uae30
  fill(000,020,040);  //\ubc24\ud558\ub298\uc0c9 \ubc30\uacbd
  rect(width/2-100, 440, 100, 20);
  
  for(int i=0;i<ItemLength;i++)
  {
   image(ItemImage[i],width/2-100+20*i, 440, 20, 20);
  }
  
  image(c, 300, 0, 300, 600); //\ud14c\ud2b8\ub9ac\uc2a4 \uac8c\uc784\ub0b4 \ud654\uba74 \uc808\ubc18
}

public void GameImplementation()
{
  if(blackState) //\u2018\uc554\ud751 \uc544\uc774\ud0ec' \uc0ac\uc6a9\uc2dc
  {
    thread("BLACK");
  }
  else if(!(blackState) )
  {
    renderGrid();  //\ud14c\ud2b8\ub9ac\uc2a4 \ud310 \ub098\ud0c0\ub0b4\uae30
  }
  
  if(nextState) //\u2018NEXT \ubabb\ubcf4\uac8c\ud558\uae30 \uc544\uc774\ud0ec' \uc0ac\uc6a9
  {
    thread("NEXT");
  }
  else if(!(nextState) )
  {
    renderQueue(); //\ud14c\ud2b8\ub9ac\uc2a4 \uc608\ube44\ubaa9\ub85d, \ud0b5\ud0b9 \ubaa8\uc591 \ub098\ud0c0\ub0b4\uae30
  }
  

  if (!gameOver && (millis()>timer+speedDelay || tetrisDownStep || tetrisDrop))   //\uac8c\uc784\uc624\ubc84\uac00 \uc544\ub2d0\uacbd\uc6b0 && ( \uc2dc\uac04\uc774 0.5\ucd08 \uacbd\uacfc\uc2dc || \u2193\ub20c\ub800\uc744\ub54c  || \u2191\ub20c\ub800\uc744\ub54c )
  {
    if (!tetrisDrop)  //\u2191\uc744 \uc548\ub20c\ub800\uc744\ub54c
    {
      tetrisStepDown(); //\ud14c\ud2b8\ub9ac\ubbf8\ub178 \ub5a8\uc5b4\ud2b8\ub9ac\uae30
    }
    else  //\u2191\uc744 \ub20c\ub800\uc744\ub54c
    {
      while (tetrisDrop) 
      { 
        //\uc804\uccb4\uac00 \ub5a8\uc5b4\uc9c0\uae30 \uc804\uc5d0 \uc67c\ucabd/\uc624\ub978\ucabd\uc73c\ub85c \uc774\ub3d9 \ud560 \uc218 \uc788\uc5b4\uc57c\ud55c\ub2e4.
        tetrisStepDown(); //\ud14c\ud2b8\ub9ac\ubbf8\ub178 \ub5a8\uc5b4\ud2b8\ub9ac\uae30
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
  text("KEEPING", width/2-95, 500); //\ud14c\ud2b8\ub9ac\ubbf8\ub178 \ud0b5\ud551
 
  text("ItemList", width/2-95, 430); //\uc9c0\uae08\uae4c\uc9c0 \uae6c \ubaa8\ub178\uc218
  if(!(ranking<11 && !(gameOver)))
  {
    text("Level"+"    :" +level, width/2-95, 220); //\ub808\ubca8
    text("Score"+"    :" +score, width/2-95, 240); //\uc2a4\ucf54\uc5b4
    text("BreakLine"+":" +totalBreakLine, width/2-95, 260); //\uc9c0\uae08\uae4c\uc9c0 \uae6c \uc904\uc218
    text("tetrisis"+"   :" +tetrisisCount, width/2-95, 280); //\uc9c0\uae08\uae4c\uc9c0 \uae6c \ud14c\ud2b8\ub9ac\uc2a4\uc218
    text("Triple"+"   :" +tripleCount, width/2-95, 300); //\uc9c0\uae08\uae4c\uc9c0 \uae6c \ud2b8\ub9ac\ud50c\uc218
    text("Double"+" :" +doubleCount, width/2-95, 320); //\uc9c0\uae08\uae4c\uc9c0 \uae6c \ub354\ube14\uc218
    text("Mono"+"    :" +monoCount, width/2-95, 340); //\uc9c0\uae08\uae4c\uc9c0 \uae6c \ubaa8\ub178\uc218
  }
  
}

////////////////////////////////////////////////////////////////////////////////////////////////////

//\ud14c\ud2b8\ub9ac\uc2a4 \ud310 \ub098\ud0c0\ub0b4\uae30
public void renderGrid()
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
  
  // \ud14c\ud2b8\ub9ac\ubbf8\ub178 \uadf8\ub9bc\uc790 \ub098\ud0c0\ub0b4\uae30
  renderShadow();
  
  // \ud14c\ud2b8\ub9ac\ubbf8\ub178 \ub0b4\ub824\uc624\ub294\uac83 \ub098\ud0c0\ub0b4\uae30
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

// \ud14c\ud2b8\ub9ac\ubbf8\ub178 \uadf8\ub9bc\uc790 \ub098\ud0c0\ub0b4\uae30
public void renderShadow() 
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

//\ud14c\ud2b8\ub9ac\uc2a4 \uc608\ube44\ubaa9\ub85d, \ud0b5\ud0b9 \ubaa8\uc591 \ub098\ud0c0\ub0b4\uae30
public void renderQueue() 
{
  //\ud14c\ud2b8\ub9ac\uc2a4 \ub300\uae30\ubaa9\ub85d \uccab\ubc88\uc9f8
  for(int y=0;y<4;y++) 
  {
    for(int x=0;x<4;x++) 
    {
      switchFillColor(gridTemplate[ tetrisQueue[0] ] [0] [y] [x] ); 
      rect(queueX + x*rectWidth , queueY + y*rectHeight , rectWidth , rectHeight);
    }
  }
  
  //\ud14c\ud2b8\ub9ac\uc2a4 \ub300\uae30\ubaa9\ub85d \ub450\ubc88\uc9f8,\uc138\ubc88\uc9f8
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
  
  //\ud0b5\ud0b9 \ud14c\ud2b8\ub9ac\ubbf8\ub178
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

//\ud14c\ud2b8\ub9ac\ubbf8\ub178 \ub5a8\uc5b4\ud2b8\ub9ac\uae30 : \uc544\ub798\ub85c tetrisimino\ub97c \ud55c \ub2e8\uacc4 \uc774\ub3d9, \ub9cc\uc57d \uc911\uc9c0\uac00 \ub41c \uacbd\uc6b0 TRUE\ub97c \ubc18\ud658\ud569\ub2c8\ub2e4.
public boolean tetrisStepDown() 
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
      tetrisFixToGrid(); // \uaca9\uc790\ubb34\ub2ac \ud310\uc5d0 \ud14c\ud2b8\ub9ac\ubbf8\ub178\ub97c \uc218\uc815\uc2dc\ud0a4\uae30
      gridCheck();       // \uaca9\uc790\ubb34\ub2ac \ud310\uc5d0 \uac00\ub4dd\ucc2c \uc904\uc774 \uc788\ub294\uc9c0 \ud310\ub2e8\ud558\uae30
      tetrisNew();       // \uc0c8\ub85c\uc6b4 \ud14c\ud2b8\ub9ac\uc2a4 \uc0dd\uc131
      tetrisCanSave=true;
    } 
    else
    {
      gameOver=true;     //\uac8c\uc784\uc624\ubc84
    }
  }
    

  return !moveOK;
}

////////////////////////////////////////////////////////////////////////////////////////////////////

// \uc774\ub3d9\ud558\uae30 \uc804\uc5d0 \uc6c0\uc9c1\uc77c\uc218 \uc788\ub294\uc9c0 \uc77c\ubc18\uc801\uc778 \uac80\uc0ac\ub97c \uc2dc\ud589\ud55c\ub2e4.
public boolean tetrisCheckMove(int xm, int ym)
{
  return tetrisCheckMove(xm, ym, tetrisPosX, tetrisPosY, tetrisRotation);
}

public boolean tetrisCheckMove(int xm, int ym, int tetrisX, int tetrisY, int tetrisR) 
{
  boolean moveOK=true;
  int gridC=0;
  
  for(int y=0;y<tetrisSize;y++)
  {
    for(int x=0;x<tetrisSize;x++)
    {
      if ((tetrisY + y + ym < 0 || tetrisY + y + ym >= gridHeight) || (tetrisX + x + xm < 0 || tetrisX + x + xm >= gridWidth)) 
      {
        gridC=1;  //\ud14c\ud2b8\ub9ac\ubbf8\ub178 4\uc870\uac01\uc911 \ud558\ub098\uac00 \uaca9\uc790\ubb34\ub2ac \ud310\uc911 \ud558\ub098\ub97c \ubc97\uc5b4\ub0a8.
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

// \ub9cc\uc57d \ud68c\uc804\ud558\uba74\uc11c \uc6c0\uc9c1\uc77c\ub54c \ud14c\ud2b8\ub9ac\ubbf8\ub178\uac00 \uaca9\uc790\ubb34\ub2ac \ubc16\uc73c\ub85c \ub098\uac00\ub294\uc9c0 \ud655\uc778\ud569\ub2c8\ub2e4.
public boolean tetrisCheckRotate(int rm)
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

// \uaca9\uc790\ubb34\ub2ac \ud310\uc5d0 \ud14c\ud2b8\ub9ac\ubbf8\ub178\ub97c \uc218\uc815\uc2dc\ud0a4\uae30
public void tetrisFixToGrid()
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

// \uaca9\uc790\ubb34\ub2ac \ud310\uc5d0 \uac00\ub4dd\ucc2c \uc904\uc774 \uc788\ub294\uc9c0 \ud310\ub2e8\ud558\uae30
public void gridCheck() 
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
    
    // \uac00\ub4dd\ucc2c \uc904 \uc81c\uac70
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


public void switchFillColor(int c) 
{
  switch (c) 
  {
    case 0: fill(000,020,040); break;  //\uac80\uc740\uc0c9 \ubc30\uacbd
    case 1: fill(000,255,255); break;  //\ud558\ub298\uc0c9 '\u3161'
    case 2: fill(051,102,255); break;  //\ud30c\ub780\uc0c9 '\u3134'
    case 3: fill(255,102,000); break;  //\uc8fc\ud669\uc0c9 '\u3131'\ubc18\ub300
    case 4: fill(255,255,000); break;  //\ub178\ub780\uc0c9 '\u3141'
    case 5: fill(000,153,051); break;  //\ucd08\ub85d\uc0c9 '\u3139'\ubc18\ub300
    case 6: fill(153,000,204); break;  //\ubcf4\ub77c\uc0c9 '\u3157' 
    case 7: fill(255,000,000); break;  //\ube68\uac04\uc0c9 '\u3139'
    case 8: fill(200,000,050); break;  //\ubd84\ud64d\uc0c9 \ubc29\ud574 \uc544\uc774\ud0ec 
    default: fill(255); break;
  }
}

////////////////////////////////////////////////////////////////////////////////////////////////////


// Save tetrisimino for later
public void tetrisSave() 
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

public void renderGameOver()
{
  textFont(fontA, 32);
  fill(255);
  int x = PApplet.parseInt(((gridWidth*rectWidth)/2)-(textWidth("Game Over")/2));
  text("Game Over", x, 295);
  
  nextState=false;
  holdState=false;
  turnState=false;
  downState=false;
  blackState=false;
  levelState=false;
  
  gameBGM.pause();
  
  /////////////////////////////////////////////////////////////////////////////////////////////////////
  //score \uc800\uc7a5\uacf5\uac04
  // 1. \uc810\uc218 \uc800\uc7a5\ud6c4 \uac8c\uc784 \uc21c\uc704\uc548\uc5d0 \ub4e4\uc5b4\uac00\ub294\uc9c0 \ud310\ub2e8 \ud6c4\uc5d0 \uc21c\uc704\ub0b4\uc5d0 \ub4e4\uba74 \uc810\uc218 \ud30c\uc77c \uc785\ub825\uc744 \uc774\uc6a9\ud574\uc11c text\ud30c\uc77c\ub85c \uc800\uc7a5
  // \ub420\uc218 \uc788\uc73c\uba74 2. \uc131\ud568 + 3.\uc54c\ud30c\ub85c \ub0a0\uc9dc(\ub144,\uc6d4,\uc77c)\ub3c4 \uac19\uc774 \uc800\uc7a5\ub420\uc218 \uc788\ub3c4\ub85d
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
    scores[i] = PApplet.parseInt(split(lines[i],'\t'));
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
    text("\uac8c\uc784 \ub7ad\ud0b9",216,263);
    textSize(20);
    text("\ucd95\ud558\ud569\ub2c8\ub2e4.",253,305);
    textSize(16);
    text("\uc804\uccb4\uc21c\uc704 \uc911\uc5d0\uc11c "+ranking+"\uc704\ub97c \ud558\uc168\uc2b5\ub2c8\ub2e4.",177,347);
    textFont(fontC,30);
    text("\uc131\uba85 : ",211,390);
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
    saveStrings("score.txt",lines); //\uc810\uc218 \uc800\uc7a5
    am.loop(); //\uba54\uc778 bgm \uc7ac\uc0dd
    state=0;   //\uba54\uc778\ud654\uba74
    GameSetup=true; 
    tetrisLocked=false;
    gameOver=false;
  }
}



///////////////////////////////////////////////////////////////////////////////////////////////////


public void mousePressed()
{
  switch(state)
  {
   case 0 :
   
   if(mouseX>180  && mouseX<456 && mouseY>147 && mouseY<202 )
   {
     am.pause();
     state=1;//\uac8c\uc784 \uc2dc\uc791
   }
   else if(mouseX>267  && mouseX<360 && mouseY>244 && mouseY<268 )
   {
     //am.close();
     state=2;//\uac8c\uc784\ubc29\ubc95 \ud654\uba74
   }
   else if(mouseX>278  && mouseX<343 && mouseY>300 && mouseY<327 )
   {
     //am.close();
     state=3;//\uac8c\uc784 \ub7ad\ud0b9
   }
   else if(mouseX>281  && mouseX<341 && mouseY>347 && mouseY<376 )
   {
     am.pause();
     state=4;//\uac8c\uc784 \uc635\uc158
   }
   else if(mouseX>240  && mouseX<375 && mouseY>400 && mouseY<429 )
   {
    //am.close();
     state=5;//\uac8c\uc784 \uc885\ub8cc
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
public void keyPressed() 
{
  if (!tetrisLocked  && state==1) 
  {
    // \uc77c\uc2dc\uc815\uc9c0 
    if(keyCode==ENTER && !(PauseGame))
    {
      textFont(fontA, 32);
      fill(255);
     
      int x = PApplet.parseInt(((gridWidth*rectWidth)/2)-(textWidth("Pause the game")/2));
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
    
    ///////////////////////////////////////////\uc2dc\uc5f0\uc6a9.
    
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
    
    if(downState) //\u2018down \uc544\uc774\ud0ec' \uc0ac\uc6a9\uc2dc
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
    
    if(holdState) //\u2018hold \uc544\uc774\ud0ec' \uc0ac\uc6a9\uc2dc
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
    
    
    if(turnState) //\u2018turn \uc544\uc774\ud0ec' \uc0ac\uc6a9\uc2dc
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

public void ItemEffects(int Item)
{
  switch(Item)
  {
    case 1 : //1~4\uc904 \ud50c\ub7ec\uc2a4 \uc544\uc774\ud0ec
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
    
    case 2 : //\ud604\uc7ac\uae4c\uc9c0 \ubaa8\uc740 \uc544\uc774\ud0ec \uc99d\ubc1c
    for(int i=0;i<ItemLength;i++)//ItemList.length
    {
      ItemList[i]=0;
      ItemImage[i]=null;
    }
    ItemLength=0;
    break;
    
    case 3 : //\uc77c\uc815\uc2dc\uac04\ub3d9\uc548 NEXT \ubabb\ubcf4\uac8c\ud568
    nextState=true;
    break;
    
    case 4 : //\uc77c\uc815\uc2dc\uac04\ub3d9\uc548 HOLD \ubabb\ud558\uac8c\ud568
    holdState=true;
    break;
    
    case 5 : //\uc77c\uc815\uc2dc\uac04\ub3d9\uc548 \ud68c\uc804 \ubabb\ud558\uac8c\ud568
    turnState=true;
    break;
    
    case 6 : //\uc77c\uc815\uc2dc\uac04\ub3d9\uc548 \ud14c\ud2b8\ub9ac\ubbf8\ub178\ub97c \ube68\ub9ac \ubabb\ub5a8\uc5b4\ud2b8\ub9bc
    downState=true;
    break;
      
    case 7 : //\ud3ed\ud0c4 : \uc9c0\uae08\uae4c\uc9c0 \uc313\uc544 \uc62c\ub9b0 \ud0d1\uc5d0 \uad6c\uba4d\uc744 \uc5ec\ub7ec\uac1c \ub0c4
    randomTile=round(random(5,50));
    for(int i=0;i<randomTile;i++)
    {
      int tileX=round(random(0,gridWidth-1));
      int tileY=round(random(0,gridHeight-1));
      
      grid[tileX][tileY]=0;
    }
    break;
    
    case 8 :  //\uc554\ud751 : \uc77c\uc815\uc2dc\uac04\ub3d9\uc548 \ud14c\ud2b8\ub9ac\ubbf8\ub178 \ud654\uba74\uc774 \uc5b4\ub461\uac8c \ub428
    blackState=true;
    break;
    
    case 9 : //\uc77c\uc815\uc2dc\uac04\ub3d9\uc548 \ud14c\ud2b8\ub9ac\ubbf8\ub178 \ub5a8\uc5b4\uc9c0\ub294 \uc18d\ub3c4 20\ub808\ubca8
    levelState=true;
    break;
    
    case 10 : //'\u3161'\uc790 3\uac1c
    for(int i=0;i<maxQueue;i++) 
    {
      tetrisQueue[i]=0;
    }
    break;
    
    case 11 : //\uc88b\uc740 \uc544\uc774\ud0ec\uc73c\ub85c \ud480\ub85c \ub9ac\uc14b
    ItemLength=0;
    for(int i=0;i<5;i++)
    {
      randomItem=PApplet.parseInt(random(10,16));
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
    
    case 12 : //1~4\uc904 \ub9c8\uc774\ub108\uc2a4 \uc544\uc774\ud0ecq
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
    
    case 13 : //'\u3141'\uc790 3\uac1c
    for(int i=0;i<maxQueue;i++) 
    {
      tetrisQueue[i]=3;
    }break;
    
    case 14 : //\ubaa8\ub4e0 \uc0c1\ud0dc\uc774\uc0c1\uc744 \ud488
     nextState=false;
     holdState=false;
     turnState=false;
     downState=false;
     blackState=false;
     levelState=false;
    break;
    
    case 15 : //\ud074\ub9ac\uc5b4 : \ud310\uc4f8
    
    for (int x=0;x<gridWidth;x++) 
    {
      for (int y=0;y<gridHeight;y++) 
      {
        grid[x][y]=0;
      }
    }
    
    break;
    
    case 16 : //\uc810\uc218 \ubb3c\uc57d
    score+=1000*PApplet.parseInt(random(1,4))*PApplet.parseInt(random(1,4));
    break;
    
    default : break;
 
  }
}



public void BLACK()
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

public void NEXT()
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

public void HOLD()
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

public void TURN()
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

public void DOWN()
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

public void LEVEL()
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



  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "tetrisCode" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
