----------------------------------------------------------------------------------
-- Create Date:    14:23:21 05/24/2018 
-- Module Name:    TFT_LCD - Behavioral 
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity TFT_LCD is
   port(
      CLK, RSTB : in std_logic;
		gp_en : in STD_LOGIC_VECTOR(1 downto 0);
      data_out : out std_logic_vector(15 downto 0);
      de : out std_logic;
		position_x : in STD_LOGIC_VECTOR(1 downto 0);
		position_y : in STD_LOGIC_VECTOR(1 downto 0);
		is_one_selected, is_two_selected : in STD_LOGIC;
		c1px, c1py, c2px, c2py : in STD_LOGIC_VECTOR(1 downto 0);
		lc0, lc1, lc2, lc3, lc4, lc5, lc6, lc7, lc8, lc9, lc10, lc11, lc12, lc13, lc14, lc15 : in STD_LOGIC_VECTOR(4 downto 0)
   );
end TFT_LCD;

architecture Behavioral of TFT_LCD is

constant tHP  : integer := 1056;   -- Hsync Period
constant tHW  : integer := 1;   -- Hsync Pulse Width
constant tHBP : integer := 45;   -- Hsync Back Porch
constant tHV  : integer := 800;   -- Horizontal valid data width
constant tHFP : integer := (tHP-tHW-tHBP-tHV);   -- Horizontal Front Port
constant tVP  : integer := 635;   -- Vsync Period
constant tVW  : integer := 1;   -- Vsync Pulse Width
constant tVBP : integer := 22;   -- Vsync Back Portch
constant tW   : integer := 480;   -- Vertical valid data width
constant tVFP : integer := (tVP-tVW-tVBP-tW);   -- Vertical Front Porch

signal hsync_cnt  : std_logic_vector( 11 downto 0 );
signal vsync_cnt  : std_logic_vector( 10 downto 0 );
signal de_i: std_logic;

-- 총 12가지 색깔을 저장하는 값
type COLOR_TYPE is array (0 to 11) of STD_LOGIC_VECTOR(15 downto 0);
signal colors : COLOR_TYPE;

-- LCD 화면에 표시하는 각각의 위치
type COLOR_TYPE2 is array (0 to 15) of STD_LOGIC_VECTOR(15 downto 0);
signal color_position : COLOR_TYPE2;

begin   
	 process(clk,RSTB,gp_en,lc0,lc1,lc2,lc3,lc4,lc5,lc6,lc7,lc8,lc9,lc10,lc11,lc12,lc13,lc14,lc15)
	 begin
		if RSTB = '0' then
			colors(0) <= "1111111111111111"; -- 하양 (배경)
			colors(1) <= "0000000000000000"; -- 검정 (선택전테두리)
			colors(2) <= "1111100000000000"; -- 빨강 (선택후테두리)
			colors(3) <= "1100101100111001"; -- 회색 (카드 뒷면) 
			colors(4) <= "1111100000011111"; -- 핑크
			colors(5) <= "1111110011000000"; -- 주황
			colors(6) <= "1111111111100000"; -- 노랑
			colors(7) <= "0000011111100000"; -- 연두  
			colors(8) <= "1000011011011101"; -- 하늘
			colors(9) <= "1010110101111101"; -- 진녹
			colors(10)<= "0000000000000001"; -- 진파
			colors(11)<= "0000100000000001"; -- 보라
			color_position(0) <= x"0000";
			color_position(1) <= x"0000";
			color_position(2) <= x"0000";
			color_position(3) <= x"0000";
			color_position(4) <= x"0000";
			color_position(5) <= x"0000";
			color_position(6) <= x"0000";
			color_position(7) <= x"0000";
			color_position(8) <= x"0000";
			color_position(9) <= x"0000";
			color_position(10) <= x"0000";
			color_position(11) <= x"0000";
			color_position(12) <= x"0000";
			color_position(13) <= x"0000";
			color_position(14) <= x"0000";
			color_position(15) <= x"0000";
		elsif rising_edge(clk) then
			if gp_en = "01" then
					-- 일단 주석 [추후 적절한 위치에서 풀것]
					if lc0(4) = '1' then -- 배경이면,
						color_position(0) <= colors(0); -- 흰색을 넣는다.
					elsif lc0(3) = '1' then -- 뒷면이면,
						color_position(0) <= colors(3); -- 회색을 넣는다.
					else
						-- Decoder 적용
						CASE lc0(2 downto 0) IS
							WHEN "000" => color_position(0) <= colors(4);
							WHEN "001" => color_position(0) <= colors(5);
							WHEN "010" => color_position(0) <= colors(6);
							WHEN "011" => color_position(0) <= colors(7);
							WHEN "100" => color_position(0) <= colors(8);
							WHEN "101" => color_position(0) <= colors(9);
							WHEN "110" => color_position(0) <= colors(10);
							WHEN "111" => color_position(0) <= colors(11);
							WHEN OTHERS => color_position(0) <= color_position(0);
						END CASE;
					end if;
					
					if lc1(4) = '1' then -- 배경이면,
						color_position(1) <= colors(0); -- 흰색을 넣는다.
					elsif lc1(3) = '1' then -- 뒷면이면,
						color_position(1) <= colors(3); -- 회색을 넣는다.
					else
						-- Decoder 적용
						CASE lc1(2 downto 0) IS
							WHEN "000" => color_position(1) <= colors(4);
							WHEN "001" => color_position(1) <= colors(5);
							WHEN "010" => color_position(1) <= colors(6);
							WHEN "011" => color_position(1) <= colors(7);
							WHEN "100" => color_position(1) <= colors(8);
							WHEN "101" => color_position(1) <= colors(9);
							WHEN "110" => color_position(1) <= colors(10);
							WHEN "111" => color_position(1) <= colors(11);
							WHEN OTHERS => color_position(1) <= color_position(1);
						END CASE;
					end if;
					
					if lc2(4) = '1' then -- 배경이면,
						color_position(2) <= colors(0); -- 흰색을 넣는다.
					elsif lc2(3) = '1' then -- 뒷면이면,
						color_position(2) <= colors(3); -- 회색을 넣는다.
					else
						-- Decoder 적용
						CASE lc2(2 downto 0) IS
							WHEN "000" => color_position(2) <= colors(4);
							WHEN "001" => color_position(2) <= colors(5);
							WHEN "010" => color_position(2) <= colors(6);
							WHEN "011" => color_position(2) <= colors(7);
							WHEN "100" => color_position(2) <= colors(8);
							WHEN "101" => color_position(2) <= colors(9);
							WHEN "110" => color_position(2) <= colors(10);
							WHEN "111" => color_position(2) <= colors(11);
							WHEN OTHERS => color_position(2) <= color_position(2);
						END CASE;
					end if;
					
					if lc3(4) = '1' then -- 배경이면,
						color_position(3) <= colors(0); -- 흰색을 넣는다.
					elsif lc3(3) = '1' then -- 뒷면이면,
						color_position(3) <= colors(3); -- 회색을 넣는다.
					else
						-- Decoder 적용
						CASE lc3(2 downto 0) IS
							WHEN "000" => color_position(3) <= colors(4);
							WHEN "001" => color_position(3) <= colors(5);
							WHEN "010" => color_position(3) <= colors(6);
							WHEN "011" => color_position(3) <= colors(7);
							WHEN "100" => color_position(3) <= colors(8);
							WHEN "101" => color_position(3) <= colors(9);
							WHEN "110" => color_position(3) <= colors(10);
							WHEN "111" => color_position(3) <= colors(11);
							WHEN OTHERS => color_position(3) <= color_position(3);
						END CASE;
					end if;
					
					if lc4(4) = '1' then -- 배경이면,
						color_position(4) <= colors(0); -- 흰색을 넣는다.
					elsif lc4(3) = '1' then -- 뒷면이면,
						color_position(4) <= colors(3); -- 회색을 넣는다.
					else
						-- Decoder 적용
						CASE lc4(2 downto 0) IS
							WHEN "000" => color_position(4) <= colors(4);
							WHEN "001" => color_position(4) <= colors(5);
							WHEN "010" => color_position(4) <= colors(6);
							WHEN "011" => color_position(4) <= colors(7);
							WHEN "100" => color_position(4) <= colors(8);
							WHEN "101" => color_position(4) <= colors(9);
							WHEN "110" => color_position(4) <= colors(10);
							WHEN "111" => color_position(4) <= colors(11);
							WHEN OTHERS => color_position(4) <= color_position(4);
						END CASE;
					end if;
					
					if lc5(4) = '1' then -- 배경이면,
						color_position(5) <= colors(0); -- 흰색을 넣는다.
					elsif lc5(3) = '1' then -- 뒷면이면,
						color_position(5) <= colors(3); -- 회색을 넣는다.
					else
						-- Decoder 적용
						CASE lc5(2 downto 0) IS
							WHEN "000" => color_position(5) <= colors(4);
							WHEN "001" => color_position(5) <= colors(5);
							WHEN "010" => color_position(5) <= colors(6);
							WHEN "011" => color_position(5) <= colors(7);
							WHEN "100" => color_position(5) <= colors(8);
							WHEN "101" => color_position(5) <= colors(9);
							WHEN "110" => color_position(5) <= colors(10);
							WHEN "111" => color_position(5) <= colors(11);
							WHEN OTHERS => color_position(5) <= color_position(5);
						END CASE;
					end if;
					
					if lc6(4) = '1' then -- 배경이면,
						color_position(6) <= colors(0); -- 흰색을 넣는다.
					elsif lc6(3) = '1' then -- 뒷면이면,
						color_position(6) <= colors(3); -- 회색을 넣는다.
					else
						-- Decoder 적용
						CASE lc6(2 downto 0) IS
							WHEN "000" => color_position(6) <= colors(4);
							WHEN "001" => color_position(6) <= colors(5);
							WHEN "010" => color_position(6) <= colors(6);
							WHEN "011" => color_position(6) <= colors(7);
							WHEN "100" => color_position(6) <= colors(8);
							WHEN "101" => color_position(6) <= colors(9);
							WHEN "110" => color_position(6) <= colors(10);
							WHEN "111" => color_position(6) <= colors(11);
							WHEN OTHERS => color_position(6) <= color_position(6);
						END CASE;
					end if;
					
					if lc7(4) = '1' then -- 배경이면,
						color_position(7) <= colors(0); -- 흰색을 넣는다.
					elsif lc7(3) = '1' then -- 뒷면이면,
						color_position(7) <= colors(3); -- 회색을 넣는다.
					else
						-- Decoder 적용
						CASE lc7(2 downto 0) IS
							WHEN "000" => color_position(7) <= colors(4);
							WHEN "001" => color_position(7) <= colors(5);
							WHEN "010" => color_position(7) <= colors(6);
							WHEN "011" => color_position(7) <= colors(7);
							WHEN "100" => color_position(7) <= colors(8);
							WHEN "101" => color_position(7) <= colors(9);
							WHEN "110" => color_position(7) <= colors(10);
							WHEN "111" => color_position(7) <= colors(11);
							WHEN OTHERS => color_position(7) <= color_position(7);
						END CASE;
					end if;
					
					if lc8(4) = '1' then -- 배경이면,
						color_position(8) <= colors(0); -- 흰색을 넣는다.
					elsif lc8(3) = '1' then -- 뒷면이면,
						color_position(8) <= colors(3); -- 회색을 넣는다.
					else
						-- Decoder 적용
						CASE lc8(2 downto 0) IS
							WHEN "000" => color_position(8) <= colors(4);
							WHEN "001" => color_position(8) <= colors(5);
							WHEN "010" => color_position(8) <= colors(6);
							WHEN "011" => color_position(8) <= colors(7);
							WHEN "100" => color_position(8) <= colors(8);
							WHEN "101" => color_position(8) <= colors(9);
							WHEN "110" => color_position(8) <= colors(10);
							WHEN "111" => color_position(8) <= colors(11);
							WHEN OTHERS => color_position(8) <= color_position(8);
						END CASE;
					end if;
					
					if lc9(4) = '1' then -- 배경이면,
						color_position(9) <= colors(0); -- 흰색을 넣는다.
					elsif lc9(3) = '1' then -- 뒷면이면,
						color_position(9) <= colors(3); -- 회색을 넣는다.
					else
						-- Decoder 적용
						CASE lc9(2 downto 0) IS
							WHEN "000" => color_position(9) <= colors(4);
							WHEN "001" => color_position(9) <= colors(5);
							WHEN "010" => color_position(9) <= colors(6);
							WHEN "011" => color_position(9) <= colors(7);
							WHEN "100" => color_position(9) <= colors(8);
							WHEN "101" => color_position(9) <= colors(9);
							WHEN "110" => color_position(9) <= colors(10);
							WHEN "111" => color_position(9) <= colors(11);
							WHEN OTHERS => color_position(9) <= color_position(9);
						END CASE;
					end if;
					
					if lc10(4) = '1' then -- 배경이면,
						color_position(10) <= colors(0); -- 흰색을 넣는다.
					elsif lc10(3) = '1' then -- 뒷면이면,
						color_position(10) <= colors(3); -- 회색을 넣는다.
					else
						-- Decoder 적용
						CASE lc10(2 downto 0) IS
							WHEN "000" => color_position(10) <= colors(4);
							WHEN "001" => color_position(10) <= colors(5);
							WHEN "010" => color_position(10) <= colors(6);
							WHEN "011" => color_position(10) <= colors(7);
							WHEN "100" => color_position(10) <= colors(8);
							WHEN "101" => color_position(10) <= colors(9);
							WHEN "110" => color_position(10) <= colors(10);
							WHEN "111" => color_position(10) <= colors(11);
							WHEN OTHERS => color_position(10) <= color_position(10);
						END CASE;
					end if;
					
					if lc11(4) = '1' then -- 배경이면,
						color_position(11) <= colors(0); -- 흰색을 넣는다.
					elsif lc11(3) = '1' then -- 뒷면이면,
						color_position(11) <= colors(3); -- 회색을 넣는다.
					else
						-- Decoder 적용
						CASE lc11(2 downto 0) IS
							WHEN "000" => color_position(11) <= colors(4);
							WHEN "001" => color_position(11) <= colors(5);
							WHEN "010" => color_position(11) <= colors(6);
							WHEN "011" => color_position(11) <= colors(7);
							WHEN "100" => color_position(11) <= colors(8);
							WHEN "101" => color_position(11) <= colors(9);
							WHEN "110" => color_position(11) <= colors(10);
							WHEN "111" => color_position(11) <= colors(11);
							WHEN OTHERS => color_position(11) <= color_position(11);
						END CASE;
					end if;
					
					if lc12(4) = '1' then -- 배경이면,
						color_position(12) <= colors(0); -- 흰색을 넣는다.
					elsif lc12(3) = '1' then -- 뒷면이면,
						color_position(12) <= colors(3); -- 회색을 넣는다.
					else
						-- Decoder 적용
						CASE lc12(2 downto 0) IS
							WHEN "000" => color_position(12) <= colors(4);
							WHEN "001" => color_position(12) <= colors(5);
							WHEN "010" => color_position(12) <= colors(6);
							WHEN "011" => color_position(12) <= colors(7);
							WHEN "100" => color_position(12) <= colors(8);
							WHEN "101" => color_position(12) <= colors(9);
							WHEN "110" => color_position(12) <= colors(10);
							WHEN "111" => color_position(12) <= colors(11);
							WHEN OTHERS => color_position(12) <= color_position(12);
						END CASE;
					end if;
					
					if lc13(4) = '1' then -- 배경이면,
						color_position(13) <= colors(0); -- 흰색을 넣는다.
					elsif lc13(3) = '1' then -- 뒷면이면,
						color_position(13) <= colors(3); -- 회색을 넣는다.
					else
						-- Decoder 적용
						CASE lc13(2 downto 0) IS
							WHEN "000" => color_position(13) <= colors(4);
							WHEN "001" => color_position(13) <= colors(5);
							WHEN "010" => color_position(13) <= colors(6);
							WHEN "011" => color_position(13) <= colors(7);
							WHEN "100" => color_position(13) <= colors(8);
							WHEN "101" => color_position(13) <= colors(9);
							WHEN "110" => color_position(13) <= colors(10);
							WHEN "111" => color_position(13) <= colors(11);
							WHEN OTHERS => color_position(13) <= color_position(13);
						END CASE;
					end if;
					
					if lc14(4) = '1' then -- 배경이면,
						color_position(14) <= colors(0); -- 흰색을 넣는다.
					elsif lc14(3) = '1' then -- 뒷면이면,
						color_position(14) <= colors(3); -- 회색을 넣는다.
					else
						-- Decoder 적용
						CASE lc14(2 downto 0) IS
							WHEN "000" => color_position(14) <= colors(4);
							WHEN "001" => color_position(14) <= colors(5);
							WHEN "010" => color_position(14) <= colors(6);
							WHEN "011" => color_position(14) <= colors(7);
							WHEN "100" => color_position(14) <= colors(8);
							WHEN "101" => color_position(14) <= colors(9);
							WHEN "110" => color_position(14) <= colors(10);
							WHEN "111" => color_position(14) <= colors(11);
							WHEN OTHERS => color_position(14) <= color_position(14);
						END CASE;
					end if;
					
					if lc15(4) = '1' then -- 배경이면,
						color_position(15) <= colors(0); -- 흰색을 넣는다.
					elsif lc15(3) = '1' then -- 뒷면이면,
						color_position(15) <= colors(3); -- 회색을 넣는다.
					else
						-- Decoder 적용
						CASE lc15(2 downto 0) IS
							WHEN "000" => color_position(15) <= colors(4);
							WHEN "001" => color_position(15) <= colors(5);
							WHEN "010" => color_position(15) <= colors(6);
							WHEN "011" => color_position(15) <= colors(7);
							WHEN "100" => color_position(15) <= colors(8);
							WHEN "101" => color_position(15) <= colors(9);
							WHEN "110" => color_position(15) <= colors(10);
							WHEN "111" => color_position(15) <= colors(11);
							WHEN OTHERS => color_position(15) <= color_position(15);
						END CASE;
					end if;
			elsif gp_en = "00" then -- 초기 리셋만 눌렸을 경우 로고 띄울 부분
			  
			elsif gp_en = "10" then -- 게임종료시 출력할 부분
			  
			else		-- 예외
			  
			end if;
		end if;
	 end process;

    process(CLK, RSTB)         --  sync 계산
    begin
		 if(RSTB = '0')then
				-- 이놈은 변경하지 말것
				hsync_cnt<= (others=>'0');
				vsync_cnt<= (others=>'0');
		 elsif(rising_edge(CLK)) then 
				if(hsync_cnt=tHP)then
					 hsync_cnt<= (others=>'0');
				else
					 hsync_cnt<= hsync_cnt + '1';
				end if;
				if(hsync_cnt=tHP)then
					 if(vsync_cnt=tVP)then
						  vsync_cnt<= (others=>'0');
					 else
						  vsync_cnt<= vsync_cnt + '1';
					 end if;
				end if;
		 end if;
    end process;
	
    process(CLK, RSTB,vsync_cnt,hsync_cnt)         --Data Enable
    begin
        if(RSTB = '0')then
            de_i<='0';
        elsif(rising_edge(CLK)) then
            if ((vsync_cnt >= (tVW + tVBP)) and (vsync_cnt <= (tVW + tVBP + tW ))) then
                if(hsync_cnt=(tHBP)) then
                    de_i<='1';
                elsif(hsync_cnt=(tHV+tHBP)) then
                    de_i<='0';
                else
                    de_i<=de_i;
                end if;
            else
                de_i<='0';
            end if;
        end if;
    end process;

    -- 가로 세로 4x4 카드를 출력하는 코드
    process(CLK, RSTB, hsync_cnt, vsync_cnt,colors, gp_en)         --출력할 이미지. R,G,B가 화면상에 번갈아 출력
	     variable pos_x : integer range 0 to 3;
	     variable pos_y : integer range 0 to 3;
		  variable selectCard1_x : integer range 0 to 3;
	     variable selectCard1_y : integer range 0 to 3;
		  variable selectCard2_x : integer range 0 to 3;
	     variable selectCard2_y : integer range 0 to 3;
    begin
	     pos_x := conv_integer(position_x);
		  pos_y := conv_integer(position_y);
		  selectCard1_x := conv_integer(c1px);
		  selectCard1_y := conv_integer(c1py);
		  selectCard2_x := conv_integer(c2px);
		  selectCard2_y := conv_integer(c2py);

		  if gp_en = "01" then
			  if (RSTB='0')then
					  data_out <= colors(1); --검정
			  elsif (rising_edge(CLK)) then
					if ( (vsync_cnt >= (tVW + tVBP)) and (vsync_cnt <= (tVW + tVBP + tW))) then
						 data_out <= colors(0); --하양
						 if ( (hsync_cnt >= (tHW + tHBP + 20 + 190 * pos_x )) and ( hsync_cnt <= (tHW + tHBP + 20 + 190 * pos_x + 189))) then
							  if( ( vsync_cnt >= (tVW + tVBP + 10 + 115 * pos_y )) and ( vsync_cnt <= (tVW + tVBP + 10 + 115 * pos_y + 114))) then
									data_out <= colors(1); --검정
							  end if;
						 end if;
						 
						 -- 추후에 이것만 남기면 됨. (문서의 state 제어변수에 따라 동작할 것임)
						 -- if state 에 따라 선택적으로 추가 적용 [select0이면 적용 x, select1이며 1개만, show 면 2개 다 적용]
						 if is_one_selected = '1' then
						     if ( (hsync_cnt >= (tHW + tHBP + 20 + 190 * selectCard1_x + 10)) and ( hsync_cnt <= (tHW + tHBP + 20 + 190 * selectCard1_x + 179))) then
						         if( ( vsync_cnt >= (tVW + tVBP + 10 + 115 * selectCard1_y + 5)) and ( vsync_cnt <= (tVW + tVBP + 10 + 115 * selectCard1_y + 109))) then
	                            data_out <= colors(2); --빨강
							      end if;
						     end if;
						 end if;
						 if is_two_selected = '1' then
							  if ( (hsync_cnt >= (tHW + tHBP + 20 + 190 * selectCard2_x + 10)) and ( hsync_cnt <= (tHW + tHBP + 20 + 190 * selectCard2_x + 179))) then
									if( ( vsync_cnt >= (tVW + tVBP + 10 + 115 * selectCard2_y + 5)) and ( vsync_cnt <= (tVW + tVBP + 10 + 115 * selectCard2_y + 109))) then
										 data_out <= colors(2); --빨강
									end if;
							  end if;
						 end if;
						 
						 if ( (hsync_cnt >= (tHW + tHBP + 40)) and ( hsync_cnt <= (tHW + tHBP + 189))) then
							  if( ( vsync_cnt >= (tVW + tVBP + 20)) and ( vsync_cnt <= (tVW + tVBP +114))) then
									  data_out <= color_position(0);
							  elsif ( ( vsync_cnt >= (tVW + tVBP + 135)) and ( vsync_cnt <= (tVW + tVBP +229))) then
									  data_out <= color_position(1);
							  elsif ( ( vsync_cnt >= (tVW + tVBP +250)) and ( vsync_cnt <= (tVW + tVBP + 344))) then
									  data_out <= color_position(2);
							  elsif ( ( vsync_cnt >= (tVW + tVBP +365)) and ( vsync_cnt <= (tVW + tVBP + 459))) then
									  data_out <= color_position(3);
							  end if;
						 elsif ( (hsync_cnt >= (tHW + tHBP + 230)) and ( hsync_cnt <= (tHW + tHBP + 379))) then
							  if( ( vsync_cnt >= (tVW + tVBP + 20)) and ( vsync_cnt <= (tVW + tVBP +114))) then
									  data_out <= color_position(4);
							  elsif ( ( vsync_cnt >= (tVW + tVBP + 135)) and ( vsync_cnt <= (tVW + tVBP +229))) then  
									  data_out <= color_position(5);
							  elsif ( ( vsync_cnt >= (tVW + tVBP +250)) and ( vsync_cnt <= (tVW + tVBP + 344))) then
									  data_out <= color_position(6);
							  elsif ( ( vsync_cnt >= (tVW + tVBP +365)) and ( vsync_cnt <= (tVW + tVBP + 459))) then
									  data_out <= color_position(7);
							  end if;
						 elsif ( (hsync_cnt >= (tHW + tHBP + 420)) and ( hsync_cnt <= (tHW + tHBP + 569))) then
							  if( ( vsync_cnt >= (tVW + tVBP + 20)) and ( vsync_cnt <= (tVW + tVBP +114))) then
									  data_out <= color_position(8);
							  elsif ( ( vsync_cnt >= (tVW + tVBP + 135)) and ( vsync_cnt <= (tVW + tVBP +229))) then
									  data_out <= color_position(9);
							  elsif ( ( vsync_cnt >= (tVW + tVBP +250)) and ( vsync_cnt <= (tVW + tVBP + 344))) then
									  data_out <= color_position(10);
							  elsif ( ( vsync_cnt >= (tVW + tVBP +365)) and ( vsync_cnt <= (tVW + tVBP + 459))) then
									  data_out <= color_position(11);
							  end if;
						 elsif ( (hsync_cnt >= (tHW + tHBP + 610)) and ( hsync_cnt <= (tHW + tHBP + 759))) then
							  if( ( vsync_cnt >= (tVW + tVBP + 20)) and ( vsync_cnt <= (tVW + tVBP + 114))) then
									  data_out <= color_position(12);
							  elsif ( (vsync_cnt >= (tVW + tVBP + 135)) and ( vsync_cnt <= (tVW + tVBP + 229))) then
									  data_out <= color_position(13);
							  elsif ( (vsync_cnt >= (tVW + tVBP + 250)) and ( vsync_cnt <= (tVW + tVBP + 344))) then
									  data_out <= color_position(14);
							  elsif ( (vsync_cnt >= (tVW + tVBP + 365)) and ( vsync_cnt <= (tVW + tVBP + 459))) then
									  data_out <= color_position(15);
							  end if;
						 end if;                  -- 화면상의 출력될 실질적인 이미지 
					end if; 
			   end if; -- rstb
			elsif gp_en = "00" then
			elsif gp_en = "10" then
			else
			end if; -- gp_en
    end process;
		
--    data_out<= r_data & g_data & b_data;
    de<=de_i;

end Behavioral;