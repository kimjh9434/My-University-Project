----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    21:39:58 06/09/2018 
-- Design Name: 
-- Module Name:    check - Behavioral 
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

entity check is
	port ( clk, rst : in STD_LOGIC;
			 gp_en : in STD_LOGIC_VECTOR(1 downto 0);
			 cardposition1_x : in STD_LOGIC_VECTOR(1 downto 0);
			 cardposition1_y : in STD_LOGIC_VECTOR(1 downto 0);
			 cardposition2_x : in STD_LOGIC_VECTOR(1 downto 0);
			 cardposition2_y : in STD_LOGIC_VECTOR(1 downto 0);
			 is_two_selected : in STD_LOGIC;
			 oc0,oc1,oc2,oc3,oc4,oc5,oc6,oc7,oc8,oc9,oc10,oc11,oc12,oc13,oc14,oc15 : in STD_LOGIC_VECTOR(3 downto 0);
			 lc0,lc1,lc2,lc3,lc4,lc5,lc6,lc7,lc8,lc9,lc10,lc11,lc12,lc13,lc14,lc15 : out STD_LOGIC_VECTOR(4 downto 0)
	);
end check;

architecture Behavioral of check is

begin

	process(clk,rst,gp_en,is_two_selected)
	begin
		if rst = '1' then
		
		elsif rising_edge(clk) then
			if is_two_selected = '1' then
				
			else
		
			end if;
		end if;
	end process;

end Behavioral;

