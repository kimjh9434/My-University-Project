----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    16:32:20 06/09/2018 
-- Design Name: 
-- Module Name:    pushdff - Behavioral 
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

entity pushdff is
	port(d, clk, en: in STD_LOGIC;
			q: out STD_LOGIC);
end pushdff;

architecture Behavioral of pushdff is

signal data: STD_LOGIC;

begin

	process(clk, en)
	begin
		if en = '1' then
			if clk'event and clk = '1' then -- edge triggered flip-flop
				data <= d;
			else
				data <= data;
			end if;
		end if;
	end process;
	
	q <= data;

end Behavioral;

