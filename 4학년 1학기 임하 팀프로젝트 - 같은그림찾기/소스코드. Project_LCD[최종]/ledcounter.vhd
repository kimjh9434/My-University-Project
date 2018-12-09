----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    19:12:33 06/09/2018 
-- Design Name: 
-- Module Name:    ledcounter - Behavioral 
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
use IEEE.STD_LOGIC_UNSIGNED.ALL;
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity ledcounter is
	Port ( clk : in  STD_LOGIC;
			 gp_en : in STD_LOGIC_VECTOR(1 downto 0);
           dcclk : out STD_LOGIC );
end ledcounter;

architecture Behavioral of ledcounter is

signal cnt_data : STD_LOGIC_VECTOR (11 downto 0);
signal output : STD_LOGIC;

begin

	process(clk,gp_en)
	begin
		if gp_en = "01" then
			if clk'event and clk='1' then -- 클럭이 1로 뛸 때
				if cnt_data = x"351" then -- 남은 절반의 시간동안 dclk 0으로 edge trigger
					cnt_data <= (others=>'0');
					output <= not output;
				else
					cnt_data <= cnt_data + 1; -- trigger 조건이 아닐때는 클럭의 횟수만 체크
					output <= output;
				end if;
			end if;
		else
			cnt_data <= (others=>'0');
			output <= '0';
		end if;
	end process;

	dcclk <= output;
end Behavioral;

