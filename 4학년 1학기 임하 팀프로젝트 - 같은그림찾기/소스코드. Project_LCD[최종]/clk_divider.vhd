----------------------------------------------------------------------------------
-- Create Date:    01:57:02 05/29/2018 
-- Module Name:    clk_divider - Behavioral 
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use ieee.std_logic_unsigned.all; -- �̰� ��߸� '+' �� �� ����!

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
			elsif clk'event and clk='1' then -- Ŭ���� 1�� �� ��
				if cnt_data = x"22222" then -- ������ �ð����� dclk 1�� edge trigger
					cnt_data <= (others=>'0');
					d_clk <= '1';
				elsif cnt_data = x"11111" then -- ���� ������ �ð����� dclk 0���� edge trigger
					cnt_data <= cnt_data + 1;
					d_clk <= '0';
				else
					cnt_data <= cnt_data + 1; -- trigger ������ �ƴҶ��� Ŭ���� Ƚ���� üũ
					d_clk <= d_clk;
				end if;
			end if;
		end process;
	
	dclk <= d_clk;

end Behavioral;