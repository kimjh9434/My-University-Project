----------------------------------------------------------------------------------
-- Create Date:    11:08:13 05/17/2018 
-- Module Name:    shiftregister - Behavioral 
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity shiftregister is
    Port ( clk, rst : in  STD_LOGIC;
           input : in  STD_LOGIC_VECTOR (3 downto 0);
           shreg0, shreg1, shreg2, shreg3, shreg4, shreg5 : out  STD_LOGIC_VECTOR (3 downto 0));
end shiftregister;

architecture Behavioral of shiftregister is

component regist
	Port ( input : in  STD_LOGIC_VECTOR (3 downto 0);
			 clk, rst, en : in  STD_LOGIC;
			 output : out  STD_LOGIC_VECTOR (3 downto 0)
	);
end component;

signal shift_reg0, shift_reg1, shift_reg2, shift_reg3, shift_reg4, shift_reg5 : STD_LOGIC_VECTOR(3 downto 0);

begin
	REG0 : regist port map(input=>input, clk=>clk, rst=>rst, en=>'1', output=>shift_reg0);
	REG1 : regist port map(input=>shift_reg0, clk=>clk, rst=>rst, en=>'1', output=>shift_reg1);
	REG2 : regist port map(input=>shift_reg1, clk=>clk, rst=>rst, en=>'1', output=>shift_reg2);
	REG3 : regist port map(input=>shift_reg2, clk=>clk, rst=>rst, en=>'1', output=>shift_reg3);
	REG4 : regist port map(input=>shift_reg3, clk=>clk, rst=>rst, en=>'1', output=>shift_reg4);
	REG5 : regist port map(input=>shift_reg4, clk=>clk, rst=>rst, en=>'1', output=>shift_reg5);

	shreg0<=shift_reg0;
	shreg1<=shift_reg1;
	shreg2<=shift_reg2;
	shreg3<=shift_reg3;
	shreg4<=shift_reg4;
	shreg5<=shift_reg5;
end Behavioral;