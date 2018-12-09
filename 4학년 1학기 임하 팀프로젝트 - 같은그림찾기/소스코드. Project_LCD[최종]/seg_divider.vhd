library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use ieee.std_logic_unsigned.all; -- 이거 써야만 '+' 쓸 수 있음!

entity seg_divider is
    Port ( clk, rst : in  STD_LOGIC;
			  gp_en : in STD_LOGIC_VECTOR(1 downto 0);
           dclk : out  STD_LOGIC);
end seg_divider;

architecture Behavioral of seg_divider is

signal cnt_data : STD_LOGIC_VECTOR (19 downto 0) := x"00000";

begin
	process(clk,rst,gp_en)
	begin
		if rst = '1' then
			cnt_data <= (others=>'0');
			dclk<='0';
		elsif clk'event and clk='1' then -- 클럭이 1로 뛸 때
			if gp_en = "00" then
			elsif gp_en = "11" then
			else
				if cnt_data = x"22222" then -- 절반의 시간동안 dclk 1로 edge trigger
					cnt_data <= (others=>'0');
					dclk <= '1';
				elsif cnt_data = x"11111" then -- 남은 절반의 시간동안 dclk 0으로 edge trigger
					cnt_data <= cnt_data + 1;
					dclk <= '0';
				else
					cnt_data <= cnt_data + 1; -- trigger 조건이 아닐때는 클럭의 횟수만 체크
				end if;
			end if;
		end if;
	end process;
end Behavioral;