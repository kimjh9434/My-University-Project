----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    16:29:51 06/09/2018 
-- Design Name: 
-- Module Name:    regist - Behavioral 
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

entity regist is
	port (input : in STD_LOGIC_VECTOR (3 downto 0);
			clk, rst, en : in STD_LOGIC;
			output : out STD_LOGIC_VECTOR (3 downto 0));
end regist;

architecture Behavioral of regist is

component FlipFlop
	port (d, clk, reset, en: in STD_LOGIC;
			q: out STD_LOGIC);
end component;

begin
	reg0: for n in 3 downto 0 generate
		reg :FlipFlop port map(d => input(n), clk => clk, reset => rst , en => en, q => output(n));
	end generate;

end Behavioral;

