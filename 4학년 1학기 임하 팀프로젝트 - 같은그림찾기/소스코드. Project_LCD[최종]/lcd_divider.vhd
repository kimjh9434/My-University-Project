library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use ieee.std_logic_unsigned.all; -- 이거 써야만 '+' 쓸 수 있음!

entity lcd_divider is
    Port ( clk, rst : in  STD_LOGIC;
			  gp_en : in STD_LOGIC_VECTOR(1 downto 0);
           dclk : out  STD_LOGIC);
end lcd_divider;

architecture Behavioral of lcd_divider is

signal cnt_data:STD_LOGIC_VECTOR(1 downto 0);

begin
	process(clk, rst, gp_en)
	begin
		if rst='1' then
			cnt_data<=(others=>'0');
			dclk<='0';
		elsif clk'event and clk='1' then
			if gp_en = "11" then
			else
				if cnt_data="11" then

					dclk<='1';	
					cnt_data<=(others=>'0');
				elsif cnt_data="01" then
					dclk<='0';
					cnt_data<=cnt_data+'1';
				else
					cnt_data<=cnt_data+'1';
				end if;
			end if;
		end if;
	end process;
end Behavioral;

