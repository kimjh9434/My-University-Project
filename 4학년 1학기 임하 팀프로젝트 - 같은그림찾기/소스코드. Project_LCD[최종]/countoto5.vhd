----------------------------------------------------------------------------------
-- Create Date:    14:16:15 05/17/2018 
-- Module Name:    countoto5 - Behavioral 
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use ieee.std_logic_unsigned.all; -- 이거 써야만 '+' 쓸 수 있음!

entity countoto5 is
    Port ( clk : in  STD_LOGIC;
			  gp_en : in STD_LOGIC_VECTOR(1 downto 0);
           cnt : out  STD_LOGIC_VECTOR (2 downto 0));
end countoto5;

architecture Behavioral of countoto5 is

signal cnt_data : STD_LOGIC_VECTOR(2 downto 0);

begin
	process(clk, gp_en)
	begin 
		if clk'event and clk='1' then
			if gp_en = "00" then
				cnt_data<=(others=>'0');
			elsif gp_en = "01" or gp_en = "10" then
				if cnt_data="101" then
					cnt_data<=(others=>'0');
				else
					cnt_data<=cnt_data+1;
				end if;
			end if;
		end if;
	end process;
	
	cnt<= cnt_data;

end Behavioral;