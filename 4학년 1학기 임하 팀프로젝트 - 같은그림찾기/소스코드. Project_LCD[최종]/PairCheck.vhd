----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    14:59:11 06/12/2018 
-- Design Name: 
-- Module Name:    PairCheck - Behavioral 
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

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity PairCheck is
	port( clk,rst : in STD_LOGIC;
		gp_en : in STD_LOGIC_VECTOR(1 downto 0);
		its : in STD_LOGIC;
		c1px, c1py, c2px, c2py : in STD_LOGIC_VECTOR(1 downto 0);
		reset_selected : out STD_LOGIC;
		Pair_Right : out STD_LOGIC_VECTOR(1 downto 0) -- 앞비트가 x좌표가 맞는지 뒷비트가 y좌표가 맞는지 의미
	);
end PairCheck;

architecture Behavioral of PairCheck is

	signal PR : STD_LOGIC_VECTOR(1 downto 0);
	signal RS : STD_LOGIC;
	
begin
	
	process(rst,clk,gp_en,its,c1px,c1py,c2px,c2py)
	begin
		if rst = '1' then
			PR <= "00";
			RS <= '0';
		elsif rising_edge(clk) then
			if gp_en = "01" then
				if its = '1' then
					if c1px = c2px then
						PR(1) <= '1';
					else
						PR(1) <= '0';
					end if;
					if c1py = c2py then
						PR(0) <= '1';
					else
						PR(0) <= '0';
					end if;
					RS <= '1';
				end if;
			else
				PR <= "00";
				RS <= '0';
			end if;
		end if;
	end process;
	
	Pair_Right <= PR;
	reset_selected <= RS;
	
end Behavioral;

