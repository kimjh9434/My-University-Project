
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use ieee.std_logic_unsigned.all; -- 이거 써야만 '+' 쓸 수 있음!

entity count0to9_end is
    Port ( clk, rst, en : in  STD_LOGIC;
			  ir : in STD_LOGIC_VECTOR(1 downto 0);
           cnt : out  STD_LOGIC_VECTOR (3 downto 0));
end count0to9_end;

architecture Behavioral of count0to9_end is

signal cnt_data : STD_LOGIC_VECTOR(3 downto 0);

begin
	process(clk, rst, en, ir, cnt_data)
	begin 
		if rst='1' then
			cnt_data<=(others=>'0');
		elsif rising_edge(clk) then
		   if en = '1' then
				if ir = "11" then
					if cnt_data="1001" then
						cnt_data<=(others=>'0');
					else
						cnt_data<=cnt_data+1;
					end if;
				else
					cnt_data <= cnt_data;
				end if;
			end if;
		end if;
	end process;
	cnt<= cnt_data;
end Behavioral;