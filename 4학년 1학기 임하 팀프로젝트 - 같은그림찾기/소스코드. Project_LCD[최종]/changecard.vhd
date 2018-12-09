----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    15:50:16 06/12/2018 
-- Design Name: 
-- Module Name:    changecard - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity changecard is
	port ( clk : in STD_LOGIC;
		gp_en : in STD_LOGIC_VECTOR(1 downto 0);
		Ocard1 : in STD_LOGIC_VECTOR(47 downto 0);
		its : in STD_LOGIC;
		c1px,c1py,c2px,c2py : in STD_LOGIC_VECTOR(1 downto 0);
		reset_selected : out STD_LOGIC;
		Pair_Right : out STD_LOGIC_VECTOR(1 downto 0);
		ending : out STD_LOGIC_VECTOR(1 downto 0);
		keyblock : out STD_LOGIC;
		Lcard0,Lcard1,Lcard2,Lcard3,Lcard4,Lcard5,Lcard6,Lcard7,Lcard8,Lcard9,Lcard10,Lcard11,Lcard12,Lcard13,Lcard14,Lcard15 : out STD_LOGIC_VECTOR(4 downto 0)
	);
end changecard;

architecture Behavioral of changecard is

type LC is array (0 to 15) of STD_LOGIC_VECTOR(4 downto 0);
signal lcdCard : LC; -- 현재 상태의 LCD 출력 값 [5비트 16개]

signal OcardS1 : STD_LOGIC_VECTOR(47 downto 0);

signal waiting : STD_LOGIC_VECTOR(11 downto 0);
signal RS : STD_LOGIC;
signal PR : STD_LOGIC_VECTOR(1 downto 0);

signal card_num : STD_LOGIC_VECTOR(3 downto 0); -- 남은 카드 쌍의 개수 [4비트. 0~8]
signal endbit : STD_LOGIC_VECTOR(1 downto 0);

signal key_block : STD_LOGIC;

begin

	OcardS1 <= Ocard1;
	
	process(OcardS1,gp_en,c1px,c1py,c2px,c2py,clk)
		  variable selectCard1_x : integer range 0 to 3;
	     variable selectCard1_y : integer range 0 to 3;
		  variable selectCard2_x : integer range 0 to 3;
	     variable selectCard2_y : integer range 0 to 3;
--		  variable current_x : integer range 0 to 3;
--		  variable current_y : integer range 0 to 3;
	begin
--		  current_x := conv_integer(px);
--		  current_y := conv_integer(py);
		  selectCard1_x := conv_integer(c1px);
		  selectCard1_y := conv_integer(c1py);
		  selectCard2_x := conv_integer(c2px);
		  selectCard2_y := conv_integer(c2py);
		if rising_edge(clk) then
			if gp_en = "00" then
				lcdCard(0) <= '0' & '1' & OcardS1(2 downto 0);
				lcdCard(1) <= '0' & '1' & OcardS1(5 downto 3);
				lcdCard(2) <= '0' & '1' & OcardS1(8 downto 6);
				lcdCard(3) <= '0' & '1' & OcardS1(11 downto 9);
				lcdCard(4) <= '0' & '1' & OcardS1(14 downto 12);
				lcdCard(5) <= '0' & '1' & OcardS1(17 downto 15);
				lcdCard(6) <= '0' & '1' & OcardS1(20 downto 18);
				lcdCard(7) <= '0' & '1' & OcardS1(23 downto 21);
				lcdCard(8) <= '0' & '1' & OcardS1(26 downto 24);
				lcdCard(9) <= '0' & '1' & OcardS1(29 downto 27);
				lcdCard(10) <= '0' & '1' & OcardS1(32 downto 30);
				lcdCard(11) <= '0' & '1' & OcardS1(35 downto 33);
				lcdCard(12) <= '0' & '1' & OcardS1(38 downto 36);
				lcdCard(13) <= '0' & '1' & OcardS1(41 downto 39);
				lcdCard(14) <= '0' & '1' & OcardS1(44 downto 42);
				lcdCard(15) <= '0' & '1' & OcardS1(47 downto 45);
				waiting <= x"000";
				RS <= '0';
				PR <= "00";
				card_num <= "0000";
			elsif gp_en = "01" then
				if its = '1' then
					if waiting = x"7BD" then
						if lcdCard(4*selectCard1_x + selectCard1_y)(2 downto 0) = lcdCard(4*selectCard2_x + selectCard2_y)(2 downto 0) then
							lcdCard(4*selectCard1_x + selectCard1_y)(4) <= '1';
							lcdCard(4*selectCard2_x + selectCard2_y)(4) <= '1';
							PR <= "11";
							card_num <= card_num + 1;
						else
							lcdCard(4*selectCard1_x + selectCard1_y)(3) <= '1';
							lcdCard(4*selectCard2_x + selectCard2_y)(3) <= '1';
							PR <= "01";
						end if;
						waiting <= (others=>'0');
						RS <= '1';
						key_block <= '0';
					else
						lcdCard(4*selectCard1_x + selectCard1_y)(3) <= '0';
						lcdCard(4*selectCard2_x + selectCard2_y)(3) <= '0';
						waiting <= waiting + 1;
						RS <= '0';
						PR <= "00";
						key_block <= '1';
					end if;
				else
					RS <= '0';
					PR <= "00";
					key_block <= '0';
					waiting <= (others=>'0');
				end if;
			else
				lcdCard(0) <= lcdCard(0);
				lcdCard(1) <= lcdCard(1);
				lcdCard(2) <= lcdCard(2);
				lcdCard(3) <= lcdCard(3);
				lcdCard(4) <= lcdCard(4);
				lcdCard(5) <= lcdCard(5);
				lcdCard(6) <= lcdCard(6);
				lcdCard(7) <= lcdCard(7);
				lcdCard(8) <= lcdCard(8);
				lcdCard(9) <= lcdCard(9);
				lcdCard(10) <= lcdCard(10);
				lcdCard(11) <= lcdCard(11);
				lcdCard(12) <= lcdCard(12);
				lcdCard(13) <= lcdCard(13);
				lcdCard(14) <= lcdCard(14);
				lcdCard(15) <= lcdCard(15);
				RS <= '0';
				PR <= "00";
			end if;
		end if;
	end process;
	
	process(card_num)
	begin
		if card_num = "1000" then
			endbit <= "11";
		else
			endbit <= "01";
		end if;
	end process;
	
	Lcard0 <= lcdCard(0);
	Lcard1 <= lcdCard(1);
	Lcard2 <= lcdCard(2);
	Lcard3 <= lcdCard(3);
	Lcard4 <= lcdCard(4);
	Lcard5 <= lcdCard(5);
	Lcard6 <= lcdCard(6);
	Lcard7 <= lcdCard(7);
	Lcard8 <= lcdCard(8);
	Lcard9 <= lcdCard(9);
	Lcard10 <= lcdCard(10);
	Lcard11 <= lcdCard(11);
	Lcard12 <= lcdCard(12);
	Lcard13 <= lcdCard(13);
	Lcard14 <= lcdCard(14);
	Lcard15 <= lcdCard(15);
	
	reset_selected <= RS;
	Pair_Right <= PR;
	keyblock <= key_block;
	ending <= endbit;
	
end Behavioral;

