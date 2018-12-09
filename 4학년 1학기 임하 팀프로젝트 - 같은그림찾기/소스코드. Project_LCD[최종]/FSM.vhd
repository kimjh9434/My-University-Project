----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    14:33:08 06/09/2018 
-- Design Name: 
-- Module Name:    FSM - Behavioral 
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

entity FSM is
	port (clk : in STD_LOGIC; 
			push_inv_reset : in STD_LOGIC;
			push_inv_start : in STD_LOGIC;
			is_card_empty : in STD_LOGIC_VECTOR(1 downto 0);
			gamephase : out STD_LOGIC_VECTOR(1 downto 0)
			);
end FSM;

architecture Behavioral of FSM is

type state_type is (logo,gaming,gameover);
signal curr_state, next_state : state_type;

begin

	process(push_inv_reset,clk)
	begin
		if push_inv_reset = '1' then
			curr_state <= logo;
		elsif rising_edge(clk) then
			curr_state<=next_state;
		end if;
	end process;
	
	process(curr_state,push_inv_reset,push_inv_start,is_card_empty)
	begin
		case curr_state is
			when logo=>
				if push_inv_start = '1' then
					next_state <= gaming;
				else
					next_state <= logo;
				end if;
			when gaming=>
				if is_card_empty = "11" then
					next_state <= gameover;
				elsif push_inv_reset = '1' then
					next_state <= logo;
				else
					next_state <= gaming;
				end if;
			when gameover=>
				if push_inv_reset = '1' then
					next_state <= logo;
				else
					next_state <= gameover;
				end if;
			when others => next_state <= logo;
		end case;
	end process;
	
	process(curr_state)
	begin
		if curr_state = logo then
			gamephase<="00";
		elsif curr_state = gaming then
			gamephase<="01";
		elsif curr_state = gameover then
			gamephase<="10";
		else
			gamephase<="11";
		end if;
	end process;
	
end Behavioral;

