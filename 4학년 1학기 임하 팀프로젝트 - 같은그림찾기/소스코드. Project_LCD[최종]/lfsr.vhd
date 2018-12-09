----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    18:41:38 06/09/2018 
-- Design Name: 
-- Module Name:    lfsr - Behavioral 
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

entity lfsr is
  port (clk : in std_logic;
			gp : in std_logic_vector(1 downto 0);
        out1,out2 : out std_logic_vector(3 downto 0)
        );
end lfsr;

architecture Behavioral of lfsr is

signal lfsr : std_logic_vector(23 downto 0);

begin

process(clk, gp)
  begin
	if rising_edge(clk) then
     if gp = "00" then
		lfsr <= lfsr(22 downto 0) & (lfsr(23) xnor lfsr(0)); 
     else 
		lfsr <= x"123ABC";
     end if;
	 end if;
  end process;
  
  out1 <= lfsr(3 downto 0);
  out2 <= lfsr(7 downto 4);
--  out3 <= lfsr(11 downto 8);
--  out4 <= lfsr(15 downto 12);
--  out5 <= lfsr(19 downto 16);
--  out6 <= lfsr(23 downto 20);
end Behavioral;