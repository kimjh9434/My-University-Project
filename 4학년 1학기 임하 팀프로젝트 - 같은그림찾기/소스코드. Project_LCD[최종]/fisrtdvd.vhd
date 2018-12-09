----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    17:14:45 06/09/2018 
-- Design Name: 
-- Module Name:    fisrtdvd - Behavioral 
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

entity firstdvd is
	Port ( clk, rst : in  STD_LOGIC;
           dclk : out  STD_LOGIC);
end firstdvd;

architecture Behavioral of firstdvd is

signal cnt_data : STD_LOGIC_VECTOR (19 downto 0) := x"00000";

begin
	process(clk,rst)
	begin
		if rst = '1' then
			cnt_data <= (others=>'0');
			dclk<='0';
		elsif clk'event and clk='1' then -- 클럭이 1로 뛸 때
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
	end process;

end Behavioral;

