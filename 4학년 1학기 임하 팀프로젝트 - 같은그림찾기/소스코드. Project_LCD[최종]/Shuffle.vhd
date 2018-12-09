----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    18:48:21 06/09/2018 
-- Design Name: 
-- Module Name:    Top_module - Behavioral 
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
use ieee.std_logic_unsigned.all;
use ieee.std_logic_arith.all;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity Shuffle is
   port( clk : in std_logic;
			gamephase : in std_logic_vector(1 downto 0);
         OUTPUT : out std_logic_vector(47 downto 0));
end Shuffle;

architecture Behavioral of Shuffle is

component lfsr
  port (clk : in std_logic;
		  gp : in std_logic_vector(1 downto 0);
        out1,out2 : out std_logic_vector(3 downto 0)
        );
end component;

signal out_card : std_logic_vector(47 downto 0) := "000001010011100101110111000001010011100101110111";
signal key1, key2 : std_logic_vector(3 downto 0) := "0000";

begin
LFS0 : lfsr port map(clk => clk, gp => gamephase, out1 => key1, out2 => key2);

process(clk,gamephase)
   type card_type is array (0 to 15) of std_logic_vector(2 downto 0);
   variable a_card : card_type;
   variable temp_card1, temp_card2, temp_card3, temp_card4 : std_logic_vector(2 downto 0);
begin
   if gamephase = "00" and rising_edge(clk) then
         a_card(0) := out_card(47 downto 45);
         a_card(1) := out_card(44 downto 42);
         a_card(2) := out_card(41 downto 39);
         a_card(3) := out_card(38 downto 36);
         a_card(4) := out_card(35 downto 33);
         a_card(5) := out_card(32 downto 30);
         a_card(6) := out_card(29 downto 27);
         a_card(7) := out_card(26 downto 24);
			a_card(8) := out_card(23 downto 21);
         a_card(9) := out_card(20 downto 18);
         a_card(10) := out_card(17 downto 15);
         a_card(11) := out_card(14 downto 12);
         a_card(12) := out_card(11 downto 9);
         a_card(13) := out_card(8 downto 6);
         a_card(14) := out_card(5 downto 3);
         a_card(15) := out_card(2 downto 0);
         temp_card1 := a_card(conv_integer(key1));
         a_card(conv_integer(key1)) := a_card(conv_integer(key2));
         a_card(conv_integer(key2)) := temp_card1;
         
         out_card <= a_card(0) & a_card(1) & a_card(2) & a_card(3)
                     & a_card(4) & a_card(5) & a_card(6) & a_card(7)
							& a_card(8) & a_card(9) & a_card(10) & a_card(11)
							& a_card(12) & a_card(13) & a_card(14) & a_card(15);   
   end if;
end process;
OUTPUT <= out_card;
end Behavioral;
