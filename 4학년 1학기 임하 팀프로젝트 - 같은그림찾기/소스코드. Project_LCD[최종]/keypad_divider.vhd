
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity keypad_divider is
    Port ( clk, rst : in  STD_LOGIC;
		     gp_en : in STD_LOGIC_VECTOR(1 downto 0);
           dclk : out  STD_LOGIC);
end keypad_divider;

architecture Behavioral of keypad_divider is

signal cnt_data:STD_LOGIC_VECTOR(15 downto 0);

begin
	process(clk, rst, gp_en)
	begin
		if rst='1' then
			cnt_data<=(others=>'0');
			dclk<='0';
		elsif clk'event and clk='1' then
			if gp_en = "01" then -- 게임 진행중일 때만
				if cnt_data=X"1111" then
					dclk<='1';	
					cnt_data<=(others=>'0');
				elsif cnt_data=X"0111" then
					dclk<='0';
					cnt_data<=cnt_data+'1';
				else
					cnt_data<=cnt_data+'1';
				end if;
			end if;
		end if;
	end process;
end Behavioral;