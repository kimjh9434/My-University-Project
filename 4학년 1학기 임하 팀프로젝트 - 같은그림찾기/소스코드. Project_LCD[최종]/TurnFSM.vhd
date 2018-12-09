----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    14:32:08 06/12/2018 
-- Design Name: 
-- Module Name:    TurnFSM - Behavioral 
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

entity TurnFSM is
	port ( rst,clk : in STD_LOGIC;
			gp_en : in STD_LOGIC_VECTOR(1 downto 0);
			timeover : in STD_LOGIC;
			is_right : in STD_LOGIC_VECTOR(1 downto 0); -- 11이면 맞은거, 그 외에는 틀린거 // 틀릴 때 턴 변경
			turnbit : out STD_LOGIC -- 0이면 1p 턴, 1이면 2p 턴
		);
end TurnFSM;

architecture Behavioral of TurnFSM is

signal turn : STD_LOGIC;

begin

	process(clk,gp_en,timeover,is_right,rst)
	begin
		if rst = '1' then
			turn <= '0';
		elsif rising_edge(clk) then
			if gp_en = "01" then
				if timeover = '1' then
					turn <= '1';
				elsif is_right = "01" then
					turn <= '1';
				else
					turn <= '0';
				end if;
			else
				turn <= '0';
			end if;
		end if;
	end process;
	
	turnbit <= turn;

end Behavioral;

