library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use ieee.std_logic_unsigned.all; -- �̰� ��߸� '+' �� �� ����!

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
		elsif clk'event and clk='1' then -- Ŭ���� 1�� �� ��
			if gp_en = "00" then
			elsif gp_en = "11" then
			else
				if cnt_data = x"22222" then -- ������ �ð����� dclk 1�� edge trigger
					cnt_data <= (others=>'0');
					dclk <= '1';
				elsif cnt_data = x"11111" then -- ���� ������ �ð����� dclk 0���� edge trigger
					cnt_data <= cnt_data + 1;
					dclk <= '0';
				else
					cnt_data <= cnt_data + 1; -- trigger ������ �ƴҶ��� Ŭ���� Ƚ���� üũ
				end if;
			end if;
		end if;
	end process;
end Behavioral;