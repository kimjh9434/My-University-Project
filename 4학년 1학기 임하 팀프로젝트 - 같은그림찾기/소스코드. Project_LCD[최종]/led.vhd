----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    19:10:05 06/09/2018 
-- Design Name: 
-- Module Name:    led - Behavioral 
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

entity led is
	 Port ( rst,turn,clk : in  STD_LOGIC;
			  gp_en : in STD_LOGIC_VECTOR(1 downto 0);
           LED : out  STD_LOGIC_VECTOR (23 downto 0);
			  timeover : out STD_LOGIC );
end led;

architecture Behavioral of led is

signal LEDbit : STD_LOGIC_VECTOR(23 downto 0);
signal start_control : STD_LOGIC;

begin

	process(rst,turn,clk,gp_en,start_control)
	begin
		if rst = '1' then
			if start_control = '0' then	
				LEDbit <= x"FFFFFF";
				timeover <= '0';
				start_control <= '1';
			else
				LEDbit <= LEDbit;
				timeover <= '0';
			end if;
		elsif gp_en = "01" then
			if turn = '1' then
				LEDbit <= x"FFFFFF";
				timeover <= '0';
			elsif rising_edge(clk) then
				if LEDbit = "000000" then
					timeover <= '1';
				else
					LEDbit <= '0' & LEDbit(23 downto 1);
					timeover <= '0';
				end if;
			end if;
		elsif gp_en = "10" then
			LEDbit <= LEDbit;
			timeover <= '0';
			start_control <= '0';
		else
			LEDbit <= x"000000";
			timeover <= '0';
		end if;
	end process;

	LED <= LEDbit;


end Behavioral;

	