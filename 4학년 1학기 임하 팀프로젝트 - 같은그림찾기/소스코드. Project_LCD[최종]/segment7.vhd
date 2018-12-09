library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity segment7 is
    Port ( clk, rst : in  STD_LOGIC;
           SEG_INPUT1, SEG_INPUT2, SEG_INPUT3, SEG_INPUT4, SEG_INPUT5, SEG_INPUT6  : in  STD_LOGIC_VECTOR (3 downto 0);
			  digit : out STD_LOGIC_VECTOR (5 downto 0);
           segment : out  STD_LOGIC_VECTOR (7 downto 0)
	 );
end segment7;

architecture Behavioral of segment7 is

component countoto5 is
    Port ( clk, rst : in  STD_LOGIC;
           cnt : out  STD_LOGIC_VECTOR (2 downto 0));
end component;


signal seg_int : STD_LOGIC_VECTOR(7 downto 0);
signal display_data : STD_LOGIC_VECTOR(3 downto 0);
signal clk_count : STD_LOGIC_VECTOR(2 downto 0);
signal dig : STD_LOGIC_VECTOR(5 downto 0);

begin
	C5 : countoto5 port map(clk=>clk, rst=>rst, cnt=>clk_count);

	process(clk, rst)
	begin
		if rst='1' then
			dig<="000000";
		elsif rising_edge(clk) then
			case clk_count is
				when "000" => dig <= "000001";
				when "001" => dig <= "000010";
				when "010" => dig <= "000100";
				when "011" => dig <= "001000";
				when "100" => dig <= "010000";
				when "101" => dig <= "100000";
				when others => dig <= "000000";
			end case;
		end if;
	end process;

	process(dig, rst, SEG_INPUT1, SEG_INPUT2, SEG_INPUT3, SEG_INPUT4, SEG_INPUT5, SEG_INPUT6)
	begin
		case dig is
			when "000001" => display_data <=SEG_INPUT1;
			when "000010" => display_data <=SEG_INPUT2;
			when "000100" => display_data <=SEG_INPUT3;
			when "001000" => display_data <=SEG_INPUT4;
			when "010000" => display_data <=SEG_INPUT5;
			when "100000" => display_data <=SEG_INPUT6;
			when others => display_data <= "0000";
		end case;
	end process;

	process(display_data)
	begin
		case display_data is
			when X"0" => seg_int <= "11111100";
			when X"1" => seg_int <= "01100000";
			when X"2" => seg_int <= "11011010";
			when X"3" => seg_int <= "11110010";
			when X"4" => seg_int <= "01100110";
			when X"5" => seg_int <= "10110110";
			when X"6" => seg_int <= "10111110";
			when X"7" => seg_int <= "11100100";
			when X"8" => seg_int <= "11111110";
			when X"9" => seg_int <= "11001111"; -- P.
			when X"a" => seg_int <= "00000000"; --' '
			when X"b" => seg_int <= "01111100"; -- u => 2개면 uu로 w처럼 보임
			when X"c" => seg_int <= "11101100"; -- n
			when X"d" => seg_int <= "01111010"; -- d
			when X"e" => seg_int <= "00001110"; -- r
			when X"f" => seg_int <= "11111010"; -- a
			when others => seg_int <= (others => '0');
		end case;
	end process;

	digit<=dig;
	segment<=seg_int;
	
end Behavioral;