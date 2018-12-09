----------------------------------------------------------------------------------
-- Create Date:    01:57:02 05/29/2018 
-- Module Name:    clk_divider - Behavioral 
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use ieee.std_logic_unsigned.all; -- 이거 써야만 '+' 쓸 수 있음!

entity clk_divider is
    Port ( clk, rst : in  STD_LOGIC;
           dclk : out  STD_LOGIC);
end clk_divider;

architecture Behavioral of clk_divider is

signal cnt_data : STD_LOGIC_VECTOR (19 downto 0);
signal d_clk : STD_LOGIC;

begin

		process(clk,rst)
		begin
			if rst = '1' then
				cnt_data <= (others=>'0');
				d_clk <= '0';
			elsif clk'event and clk='1' then -- 클럭이 1로 뛸 때
				if cnt_data = x"22222" then -- 절반의 시간동안 dclk 1로 edge trigger
					cnt_data <= (others=>'0');
					d_clk <= '1';
				elsif cnt_data = x"11111" then -- 남은 절반의 시간동안 dclk 0으로 edge trigger
					cnt_data <= cnt_data + 1;
					d_clk <= '0';
				else
					cnt_data <= cnt_data + 1; -- trigger 조건이 아닐때는 클럭의 횟수만 체크
					d_clk <= d_clk;
				end if;
			end if;
		end process;
	
	dclk <= d_clk;

end Behavioral;