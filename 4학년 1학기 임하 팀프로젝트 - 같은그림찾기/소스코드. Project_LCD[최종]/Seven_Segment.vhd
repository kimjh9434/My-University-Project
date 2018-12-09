----------------------------------------------------------------------------------
-- Create Date:    20:59:46 06/12/2018 
-- Module Name:    Seven_Segment - Behavioral 
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity Seven_Segment is
    Port ( clk, rst, turn : in  STD_LOGIC;
			  gp_en : in STD_LOGIC_VECTOR(1 downto 0);
			  is_right : in STD_LOGIC_VECTOR(1 downto 0);
           segment : out  STD_LOGIC_VECTOR (7 downto 0);
           digit : out  STD_LOGIC_VECTOR (5 downto 0));
end Seven_Segment;

architecture Behavioral of Seven_Segment is

COMPONENT countoto5 is
	port( clk : in STD_LOGIC;
			gp_en : in STD_LOGIC_VECTOR(1 downto 0);
		  cnt : out STD_LOGIC_VECTOR(2 downto 0)
		);
END COMPONENT;

component count0to9_end is
    Port ( clk, rst, en : in  STD_LOGIC;
			  ir : in STD_LOGIC_VECTOR(1 downto 0);
           cnt : out  STD_LOGIC_VECTOR (3 downto 0));
end component;

type seg_in is array(0 to 5) of STD_LOGIC_VECTOR(3 downto 0); -- 사용자 정의. 2차원 배열 선언
signal seginput : seg_in;
signal player1,player2 : STD_LOGIC_VECTOR(3 downto 0);
signal clk_count : STD_LOGIC_VECTOR(2 downto 0);
signal dig : STD_LOGIC_VECTOR(5 downto 0);
signal display_data : STD_LOGIC_VECTOR(3 downto 0);
signal player : STD_LOGIC;
signal player1_en : STD_LOGIC;
signal player2_en : STD_LOGIC;

begin

	C5 : countoto5 port map(clk=>clk, gp_en=>gp_en, cnt=>clk_count);
	P1C9 : count0to9_end port map(clk=>clk, rst=>rst, en=>player1_en, ir=>is_right, cnt=>player1);
	P2C9 : count0to9_end port map(clk=>clk, rst=>rst, en=>player2_en, ir=>is_right, cnt=>player2);
	
--	process(clk, rst, turn)
--   begin
--		if rst = '1' then
--			player1_en<='1';
--			player2_en<='0';
--		elsif rising_edge(clk) then
--			if turn = '0' then
--				player1_en <= '1';
--				player2_en <= '0';
--			else
--				player1_en<='0';
--				player2_en<='1';
--			end if;
--		end if;
--	end process;

	process(clk,rst,player)
	begin
		if rst = '1' then
			player1_en<='1';
			player2_en<='0';
		elsif rising_edge(clk) then
			if player = '1' then
				player1_en<='0';
				player2_en<='1';
			else
				player1_en <= '1';
				player2_en <= '0';
			end if;
		end if;
	end process;
	
	process(clk,rst,turn)
	begin
		if rst = '1' then
			player <= '0';
		elsif rising_edge(clk) then
			if turn = '1' then
				player <= not player;
			else
				player <= player;
			end if;
		end if;
	end process;
	
	process(clk, gp_en)
	begin
		if rising_edge(clk) then
			if gp_en = "00" then
				dig <= "000000";
			elsif gp_en = "01" or gp_en = "10" then
				case clk_count is
					when "000" => dig <= "000001";
					when "001" => dig <= "000010";
					when "010" => dig <= "000100";
					when "011" => dig <= "001000";
					when "100" => dig <= "010000";
					when "101" => dig <= "100000";
					when others => dig <= "000000";
				end case;
			end if;
		end if;
	end process;
	
	process(clk,gp_en,player,is_right,rst)
	begin
		if rst = '1' then
			seginput(5) <= x"1";
			seginput(4) <= x"9";
			seginput(3) <= x"a";
			seginput(2) <= x"0";
			seginput(1) <= x"0";
			seginput(0) <= x"0";
		elsif rising_edge(clk) then
			if gp_en = "00" then
--				player1(5) <= x"1"; -- 1
--				player1(4) <= x"9"; -- P.
--				player1(3) <= x"a"; -- ' '
--				player1(2) <= x"0";
--				player1(1) <= x"0";
--				player1(0) <= x"0";
--				player2(5) <= x"2"; -- 2
--				player2(4) <= x"9"; -- P.
--				player2(3) <= x"a"; -- ' '
--				player2(2) <= x"0";
--				player2(1) <= x"0";
--				player2(0) <= x"0";
				seginput(5) <= x"1";
				seginput(4) <= x"9";
				seginput(3) <= x"a";
				seginput(2) <= x"0";
				seginput(1) <= x"0";
				seginput(0) <= x"0";
			elsif gp_en = "01" then
				if player = '0' then
					seginput(5) <= x"1";
					seginput(4) <= x"9";
					seginput(3) <= x"a";
					seginput(2) <= player1;
					seginput(1) <= x"0";
					seginput(0) <= x"0";
--					player1(5) <= x"1"; -- 1
--					player1(4) <= x"9"; -- P.
--					player1(3) <= x"a"; -- ' '
--					player1(2) <= player1(2);
--					player1(1) <= x"0";
--					player1(0) <= x"0";
				else
					seginput(5) <= x"2"; -- 2
					seginput(4) <= x"9"; -- P.
					seginput(3) <= x"a";
					seginput(2) <= player2;
					seginput(1) <= x"0"; 
					seginput(0) <= x"0";
--					player2(5) <= x"2"; -- 2
--					player2(4) <= x"9"; -- P.
--					player2(3) <= x"a"; -- ' '
--					player2(2) <= player2(2);
--					player2(1) <= x"0";
--					player2(0) <= x"0";
				end if;
			elsif gp_en = "10" then
				if player1 > player2 then
					seginput(5) <= x"1"; -- 1
					seginput(4) <= x"9"; -- P.
					seginput(3) <= x"b"; -- u
					seginput(2) <= x"b"; -- u
					seginput(1) <= x"1"; -- 1
					seginput(0) <= x"c"; -- n
				elsif player1 < player2 then
					seginput(5) <= x"2"; -- 2
					seginput(4) <= x"9"; -- P.
					seginput(3) <= x"b"; -- u
					seginput(2) <= x"b"; -- u
					seginput(1) <= x"1"; -- 1
					seginput(0) <= x"c"; -- n
				else -- draw
					seginput(5) <= x"d"; -- d
					seginput(4) <= x"e"; -- r
					seginput(3) <= x"f"; -- a
					seginput(2) <= x"b"; -- u
					seginput(1) <= x"b"; -- u
					seginput(0) <= x"a"; --' '
				end if;
			end if;
		end if;
	end process;
	
--	process(is_right)
--	begin
--		if is_right = "11" then
--			ISbit <= '1';
--		else
--			ISbit <= '0';
--		end if;
--	end process;
	
	process(dig, seginput(0), seginput(1),seginput(2),seginput(3),seginput(4),seginput(5))
	begin
		case dig is
			when "000001" => display_data <=seginput(0);
			when "000010" => display_data <=seginput(1);
			when "000100" => display_data <=seginput(2);
			when "001000" => display_data <=seginput(3);
			when "010000" => display_data <=seginput(4);
			when "100000" => display_data <=seginput(5);
			when others => display_data <= "0000";
		end case;
	end process;

	process(display_data)
	begin
		case display_data is
			when X"0" => segment <= "11111100";
			when X"1" => segment <= "01100000";
			when X"2" => segment <= "11011010";
			when X"3" => segment <= "11110010";
			when X"4" => segment <= "01100110";
			when X"5" => segment <= "10110110";
			when X"6" => segment <= "10111110";
			when X"7" => segment <= "11100100";
			when X"8" => segment <= "11111110";
			when X"9" => segment <= "11001111"; -- P.
			when X"a" => segment <= "00000000"; --' '
			when X"b" => segment <= "01111100"; -- u => 2개면 uu로 w처럼 보임
			when X"c" => segment <= "11101100"; -- n
			when X"d" => segment <= "01111010"; -- d
			when X"e" => segment <= "00001110"; -- r
			when X"f" => segment <= "11111010"; -- a
			when others => segment <= (others => '0');
		end case;
	end process;
	
	digit <= dig;
--
--	player1(5) <= x"1"; -- 1
--	player1(4) <= x"9"; -- P.
--	player1(3) <= x"a"; -- ' '
--	CNT1 : count0to9_end port map(clk=>ISbit, rst=>rst, en=>player1_en, cnt=>player1(2));
--	player1(1) <= x"0"; -- 0
--	player1(0) <= x"0"; -- 0
--	
--	player2(5) <= x"2"; -- 2
--	player2(4) <= x"9"; -- P.
--	player2(3) <= x"a"; -- ' '
--	CNT2 : count0to9_end port map(clk=>ISbit, rst=>rst, en=>player2_en, cnt=>player2(2));
--	player2(1) <= x"0"; -- 0
--	player2(0) <= x"0"; -- 0
	
--	process(clk, rst, player1_en, player2_en, end_bit)
--   begin
--		if rising_edge(clk) then
--			if rst = '1' then
--				seginput(5) <= x"a"; 
--				seginput(4) <= x"a";
--				seginput(3) <= x"a"; 
--				seginput(2) <= x"a";
--				seginput(1) <= x"a"; 
--				seginput(0) <= x"a";
--			elsif end_bit = "01" then
--				if player1_en = '1' then
--					seginput <= player1;
--				else
--					seginput <= player2;
--				end if;
--			elsif end_bit = "11" then
--				if player1(2) > player2(2) then
--					seginput(5) <= x"1"; -- 1
--					seginput(4) <= x"9"; -- P.
--					seginput(3) <= x"b"; -- u
--					seginput(2) <= x"b"; -- u
--					seginput(1) <= x"1"; -- 1
--					seginput(0) <= x"c"; -- n
--				elsif player1(2) < player2(2) then
--					seginput(5) <= x"2"; -- 2
--					seginput(4) <= x"9"; -- P.
--					seginput(3) <= x"b"; -- u
--					seginput(2) <= x"b"; -- u
--					seginput(1) <= x"1"; -- 1
--					seginput(0) <= x"c"; -- n
--				else -- draw
--					seginput(5) <= x"d"; -- d
--					seginput(4) <= x"e"; -- r
--					seginput(3) <= x"f"; -- a
--					seginput(2) <= x"b"; -- u
--					seginput(1) <= x"b"; -- u
--					seginput(0) <= x"a"; --' '
--				end if;
--			end if;
--		end if;
--	end process;
--	

	
--	
--	seg : segment7 port map(clk=>clk, rst=>rst,
--		SEG_INPUT1=>seginput(0), 
--		SEG_INPUT2=>seginput(1), 
--		SEG_INPUT3=>seginput(2), 
--		SEG_INPUT4=>seginput(3), 
--		SEG_INPUT5=>seginput(4), 
--		SEG_INPUT6=>seginput(5),
--		digit=>digit,
--		segment=>segment
--		);

end Behavioral;

