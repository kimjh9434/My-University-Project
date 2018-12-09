----------------------------------------------------------------------------------
-- Create Date:    14:13:09 05/17/2018 
-- Module Name:    clockDividerx4 - Behavioral 
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use ieee.std_logic_unsigned.all; -- 이거 써야만 '+' 쓸 수 있음!

entity clockDividerx4 is
    Port ( clk, rst : in  STD_LOGIC;
			  gp_en : in STD_LOGIC_VECTOR(1 downto 0);
           dclk : out  STD_LOGIC);
end clockDividerx4;

architecture Behavioral of clockDividerx4 is

signal cnt_data:STD_LOGIC_VECTOR(3 downto 0);
signal d_clk : STD_LOGIC;

begin
	process(clk, rst, gp_en)
	begin
		if rst='1' then
			cnt_data<=(others=>'0');
			d_clk<='0';
		elsif clk'event and clk='1' then
			if gp_en = "01" then
				if cnt_data=x"F" then
					cnt_data<=(others=>'0');
					d_clk<=not d_clk;
				else
					d_clk<=d_clk;
					cnt_data<=cnt_data+'1';
				end if;
			end if;
		end if;
	end process;
	
	dclk<=d_clk;
end Behavioral;