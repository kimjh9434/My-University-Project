----------------------------------------------------------------------------------

-- Create Date:    14:30:00 05/31/2018 
-- Module Name:    Top_module - Behavioral 
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity Top_module is 
    Port ( clk, RSTB : in  STD_LOGIC;
	 		  rst,start : in STD_LOGIC;
           key_in : in  STD_LOGIC_VECTOR (3 downto 0);
           lcd_clk : out  STD_LOGIC;
           de : out  STD_LOGIC;
           data_out : out  STD_LOGIC_VECTOR (15 downto 0);
           key_scan : out  STD_LOGIC_VECTOR (3 downto 0);
           segment : out  STD_LOGIC_VECTOR (7 downto 0);
           dig : out  STD_LOGIC_VECTOR (5 downto 0);
			  LEDs : out STD_LOGIC_VECTOR(23 downto 0)
			  );
end Top_module;

architecture Behavioral of Top_module is

component lcd_divider 
    Port ( clk, rst : in  STD_LOGIC;
			  gp_en : in STD_LOGIC_VECTOR(1 downto 0);
           dclk : out  STD_LOGIC );
end component;

component TFT_LCD
    Port ( CLK, RSTB : in  STD_LOGIC;
			  gp_en : in STD_LOGIC_VECTOR(1 downto 0);
           data_out  : out  STD_LOGIC_VECTOR (15 downto 0);
           de : out  STD_LOGIC;
			  position_x, position_y : in STD_LOGIC_VECTOR(1 downto 0);
			  is_one_selected, is_two_selected : in STD_LOGIC;
			  c1px, c1py, c2px, c2py : in STD_LOGIC_VECTOR(1 downto 0);
		     lc0, lc1, lc2, lc3, lc4, lc5, lc6, lc7, lc8, lc9, lc10, lc11, lc12, lc13, lc14, lc15 : in STD_LOGIC_VECTOR(4 downto 0)
	 );
end component;

component fisrtdvd is
	Port ( clk, rst : in  STD_LOGIC;
           dclk : out  STD_LOGIC);
end component;

component keypad_divider
	port ( clk, rst : in  STD_LOGIC;
		    gp_en : in STD_LOGIC_VECTOR(1 downto 0);
          dclk : out  STD_LOGIC
	);
end component;

component Key_Matrix
	port ( clk, rst : in  STD_LOGIC;
			 gp_en : in STD_LOGIC_VECTOR(1 downto 0);
			 key_in : in STD_LOGIC_VECTOR (3 downto 0);
			 key_scan : out STD_LOGIC_VECTOR (3 downto 0);
			 key_data : out STD_LOGIC_VECTOR (3 downto 0);
			 key_event: out STD_LOGIC
	);
end component;

component FSM is
	port (clk : in STD_LOGIC; 
		push_inv_reset : in STD_LOGIC;
		push_inv_start : in STD_LOGIC;
		is_card_empty : in STD_LOGIC_VECTOR(1 downto 0);
		gamephase : out STD_LOGIC_VECTOR(1 downto 0));
end component;

component led is
	Port ( rst,turn,clk : in  STD_LOGIC;
			  gp_en : in STD_LOGIC_VECTOR(1 downto 0);
           LED : out  STD_LOGIC_VECTOR (23 downto 0);
			  timeover : out STD_LOGIC );
end component;

component ledcounter is
	Port ( clk : in  STD_LOGIC;
			 gp_en : in STD_LOGIC_VECTOR(1 downto 0);
           dcclk : out  STD_LOGIC );
end component;

component TurnFSM is
	port ( rst,clk : in STD_LOGIC;
			gp_en : in STD_LOGIC_VECTOR(1 downto 0);
			timeover : in STD_LOGIC;
			is_right : in STD_LOGIC_VECTOR(1 downto 0); -- 1이면 맞은거, 0이면 틀린거 // 여기서는 0일 때 턴 변경
			turnbit : out STD_LOGIC -- 0이면 1p 턴, 1이면 2p 턴
		);
end component;

component Shuffle is
   port( clk : in std_logic;
			gamephase : in std_logic_vector(1 downto 0);
         OUTPUT : out std_logic_vector(47 downto 0));
end component;

component changecard is
	port ( clk : in STD_LOGIC;
		gp_en : in STD_LOGIC_VECTOR(1 downto 0);
		Ocard1 : in STD_LOGIC_VECTOR(47 downto 0);
		its : in STD_LOGIC;
		c1px,c1py,c2px,c2py : in STD_LOGIC_VECTOR(1 downto 0);
		reset_selected : out STD_LOGIC;
		Pair_Right : out STD_LOGIC_VECTOR(1 downto 0);
		ending : out STD_LOGIC_VECTOR(1 downto 0);
		keyblock : out STD_LOGIC;
		Lcard0,Lcard1,Lcard2,Lcard3,Lcard4,Lcard5,Lcard6,Lcard7 : out STD_LOGIC_VECTOR(4 downto 0);
		Lcard8,Lcard9,Lcard10,Lcard11,Lcard12,Lcard13,Lcard14,Lcard15 : out STD_LOGIC_VECTOR(4 downto 0) );
end component;

component Seven_Segment is
    Port ( clk, rst, turn : in  STD_LOGIC;
			  gp_en : in STD_LOGIC_VECTOR(1 downto 0);
			  is_right : in STD_LOGIC_VECTOR(1 downto 0);
           segment : out  STD_LOGIC_VECTOR (7 downto 0);
           digit : out  STD_LOGIC_VECTOR (5 downto 0));
end component;

-----------------------------------------------------------------------------------
-- 여기서부터 Signal 선언
signal LCD_RESET : std_logic; -- LCD RSET 용
signal lcd_25m_clk : std_logic;
signal lcd_den : std_logic;
-----------------------------------------------------------------------------------
signal rst_inv, start_inv : STD_LOGIC;
signal f_clk, key_clk, key_event : STD_LOGIC; -- rst_inv : ucf H5
signal key_pad_out : STD_LOGIC_VECTOR(3 downto 0); -- Key_Matrix 모듈에서 나온 출력값

type LC is array (0 to 15) of STD_LOGIC_VECTOR(4 downto 0);
signal lcdCard : LC; -- 현재 상태의 LCD 출력 값 [5비트 16개]

signal position_x : STD_LOGIC_VECTOR(1 downto 0); -- 현재 커서의 x좌표 [2비트 1개]
signal position_y : STD_LOGIC_VECTOR(1 downto 0); -- 현재 커서의 y좌표 [2비트 1개]
signal card1_position_x : STD_LOGIC_VECTOR(1 downto 0); -- 첫 번째 선택된 카드의 x좌표 [2비트 1개] 
signal card1_position_y : STD_LOGIC_VECTOR(1 downto 0); -- 첫 번째 선택된 카드의 y좌표 [2비트 1개]
signal card2_position_x : STD_LOGIC_VECTOR(1 downto 0); -- 두 번째 선택된 카드의 x좌표 [2비트 1개]
signal card2_position_y : STD_LOGIC_VECTOR(1 downto 0); -- 두 번째 선택된 카드의 y좌표 [2비트 1개]


signal gamephase : STD_LOGIC_VECTOR(1 downto 0) := "00";

signal LEDsignal : STD_LOGIC;
signal timeover : STD_LOGIC;

signal checkturn : STD_LOGIC;

signal is_one_selected : STD_LOGIC; -- first Selected
signal is_two_selected : STD_LOGIC; -- second Selected

signal OneSet : STD_LOGIC_VECTOR(47 downto 0);

signal RS : STD_LOGIC;
signal PR : STD_LOGIC_VECTOR(1 downto 0);

signal endbit : STD_LOGIC_VECTOR(1 downto 0);
signal key_block : STD_LOGIC;

begin
	 -- LCD 관련해서 건들면 안됨------------------------------------------------------
	 -- Signal 추가는 괜찮음
	 
    de <= lcd_den;
    lcd_clk <= lcd_25m_clk;
    LCD_RESET <= not RSTB;
	 
	 rst_inv <= not rst; -- 푸시 버튼1 H5 
	 start_inv <= not start; -- 푸시 버튼2 G5
	 
	 FDVD : fisrtdvd port map (clk=>clk, rst=>not rst, dclk=>f_clk);
	 
	 FSM1 : FSM port map (clk=>clk, push_inv_reset=>rst_inv, push_inv_start=>start_inv,
								is_card_empty=>endbit, gamephase=>gamephase);
							
    -- Clock divider : 그냥 베이스로 Clock divider은 돌아가고 있으며, dclk를 만들어 낸다.
    LCD_DVD : lcd_divider port map (clk=>clk, rst=>LCD_RESET, gp_en=>gamephase, dclk=>lcd_25m_clk);

    u_tft_lcd : TFT_LCD port map(CLK => lcd_25m_clk,
						 				   RSTB => RSTB,
											gp_en=>gamephase,
										   data_out => data_out,
										   de => lcd_den,
											position_x => position_x,
											position_y => position_y,
											is_one_selected => is_one_selected,
											is_two_selected => is_two_selected,
											c1px => card1_position_x, c1py => card1_position_y, 
											c2px => card2_position_x, c2py => card2_position_y,
											lc0=>lcdCard(0),lc1=>lcdCard(1),lc2=>lcdCard(2),lc3=>lcdCard(3),lc4=>lcdCard(4),lc5=>lcdCard(5),lc6=>lcdCard(6),lc7=>lcdCard(7),
											lc8=>lcdCard(8),lc9=>lcdCard(9),lc10=>lcdCard(10),lc11=>lcdCard(11),lc12=>lcdCard(12),lc13=>lcdCard(13),lc14=>lcdCard(14),lc15=>lcdCard(15)
											);

	------------------------------------------------------------------
	S1 : Shuffle port map (clk=>clk, gamephase => gamephase, OUTPUT=>OneSet);
--	-----------------------------------------------------------------------------
	CC : changecard port map ( clk=>f_clk, gp_en=>gamephase, Ocard1=>OneSet, its=>is_two_selected,
										 c1px=>card1_position_x, c1py=>card1_position_y, c2px=>card2_position_x, c2py=>card2_position_y, reset_selected=>RS, Pair_Right=>PR, ending=>endbit, keyblock=>key_block,
										 Lcard0=>lcdCard(0),Lcard1=>lcdCard(1),Lcard2=>lcdCard(2),Lcard3=>lcdCard(3),Lcard4=>lcdCard(4),Lcard5=>lcdCard(5),Lcard6=>lcdCard(6),Lcard7=>lcdCard(7),
										 Lcard8=>lcdCard(8),Lcard9=>lcdCard(9),Lcard10=>lcdCard(10),Lcard11=>lcdCard(11),Lcard12=>lcdCard(12),Lcard13=>lcdCard(13),Lcard14=>lcdCard(14),Lcard15=>lcdCard(15));
										 
	TFSM : TurnFSM port map(rst=>rst_inv, clk=>f_clk, gp_en=>gamephase, timeover=>timeover, is_right=>PR, turnbit=>checkturn);
	-----------------------------------------------------------------------------
	LEDc : ledcounter port map(clk=>f_clk, gp_en=>gamephase, dcclk=>LEDsignal);
	LEDmain : led port map(rst=>start_inv, turn=>checkturn, clk=>LEDsignal,--is_card_empty,
									gp_en=>gamephase, LED=>LEDs, timeover=>timeover);
	----------------------------------------------------------------------------
	KEY_DVD : keypad_divider port map(clk=>clk, rst=>rst_inv, gp_en=>gamephase, dclk=>key_clk);

	KEY_PAD : Key_Matrix port map(clk=>key_clk, rst=>rst_inv, gp_en=>gamephase, key_in=>key_in, key_scan=>key_scan, 
											 key_data=>key_pad_out, key_event=>key_event);
 
  --	key_envet가 뛰었을 때, key_pad_out값을 받아서, 현재 커서의 x,y 좌표를 설정해주는 프로세스
	process(gamephase,key_event, rst_inv, key_pad_out, checkturn, RS, position_x, position_y,is_two_selected, key_block)
       variable current_x : integer range 0 to 3;
       variable current_y : integer range 0 to 3;
   begin
      current_x := conv_integer(position_x);
      current_y := conv_integer(position_y);
      if rst_inv = '1' then
          position_x <= (others=>'0');
          position_y <= (others=>'0');
          card1_position_x <= (others=>'0');
          card1_position_y <= (others=>'0');
          card2_position_x <= (others=>'0');
          card2_position_y <= (others=>'0');
          is_one_selected <= '0';
          is_two_selected <= '0';
		elsif gamephase = "01" then
			if RS = '1' or checkturn = '1' then
				 is_one_selected <= '0';
				 is_two_selected <= '0';
				 card1_position_x <= (others=>'0');
				 card1_position_y <= (others=>'0');
				 card2_position_x <= (others=>'0');
				 card2_position_y <= (others=>'0');
			elsif key_block = '1' then
			elsif rising_edge(key_event) then
				if key_pad_out = x"2" then
					if position_y = "00" then
						 position_y <= "11";
					else
						 position_y <= position_y - 1;
					end if;
				elsif key_pad_out = x"4" then
					if position_x = "00" then
						 position_x <= "11";
					else
						 position_x <= position_x - 1;
					end if;
				elsif key_pad_out = x"6" then
					if position_x = "11" then
						 position_x <= "00";
					else
						 position_x <= position_x + 1;
					end if;
				elsif key_pad_out = x"8" then
					if position_y = "11" then
						 position_y <= "00";
					else
						 position_y <= position_y + 1;
					end if;
				elsif key_pad_out = x"5" then
					if lcdCard(4*current_x + current_y)(4) = '1' then -- 현재 커서 위치가 이미 사라진 값
					elsif is_one_selected = '0' then -- 1. 첫 번째 선택인가? [아직 선택된 적이 없는 경우] [Select0상태인 경우]
						card1_position_x <= position_x;
						card1_position_y <= position_y;
						is_one_selected <= '1';
					elsif is_one_selected = '1' then -- 2. 두 번째 선택인가? [한번 선택된 적이 있는 경우] [Select1상태인 경우]
						if card1_position_x = position_x and card1_position_y = position_y then
						else
							card2_position_x <= position_x;
							card2_position_y <= position_y;
							is_two_selected <= '1';
						end if;
					end if;
				end if;
			end if;
      end if;
   end process;
	

	SS : Seven_Segment port map(clk=>f_clk, rst=>rst_inv, gp_en=>gamephase, turn=>checkturn, is_right=>PR,
										 segment=>segment, digit=>dig);
	
end Behavioral;