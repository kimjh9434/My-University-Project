----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    16:31:59 06/09/2018 
-- Design Name: 
-- Module Name:    SMreg - Behavioral 
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

entity SMreg is
	port (input : in STD_LOGIC_VECTOR (1 downto 0);
			clk, en : in STD_LOGIC;
			output : out STD_LOGIC_VECTOR (1 downto 0));
end SMreg;

architecture Behavioral of SMreg is

component pushdff
	port (d, clk, en: in STD_LOGIC;
			q: out STD_LOGIC);
end component;

begin

	reg0: for n in 1 downto 0 generate
		reg : pushdff port map(d => input(n), clk => clk, en => en, q => output(n));
	end generate;

end Behavioral;

