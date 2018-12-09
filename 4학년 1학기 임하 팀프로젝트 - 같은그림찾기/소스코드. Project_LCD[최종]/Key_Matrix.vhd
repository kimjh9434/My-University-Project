----------------------------------------------------------------------------------
-- Create Date:    14:11:39 05/17/2018 
-- Module Name:    Key_Matrix - Behavioral 
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity Key_Matrix is
    Port ( clk, rst : in  STD_LOGIC;
			  gp_en : in STD_LOGIC_VECTOR(1 downto 0);
           key_in : in  STD_LOGIC_VECTOR (3 downto 0);
           key_scan : out  STD_LOGIC_VECTOR (3 downto 0);
           key_data : out  STD_LOGIC_VECTOR (3 downto 0);
			  key_event: out STD_LOGIC);
end Key_Matrix;

architecture Behavioral of Key_Matrix is

component clockDividerx4  is
	port ( clk, rst : in STD_LOGIC;
			 gp_en : in STD_LOGIC_VECTOR(1 downto 0);
	       dclk : out STD_LOGIC
	);
end Component ;

signal scan_cnt : STD_LOGIC_VECTOR(3 downto 0);
signal key_data_int : STD_LOGIC_VECTOR(3 downto 0);
signal key_in_int : STD_LOGIC_VECTOR(3 downto 0);
signal seg_clk : STD_LOGIC;
signal key_temp : STD_LOGIC_VECTOR(15 downto 0);

begin
	DVD0 : clockDividerx4 port map(clk=>clk, rst=>rst, gp_en=>gp_en, dclk=>seg_clk);

	-- scan_count 값을 계속 돌리면서, 몇번째 key_scan(행)에서 값을 눌렀는지 검사한다.
	process(rst, seg_clk, gp_en)
	begin
		if rst = '1' then
			scan_cnt <= "1110";
		elsif rising_edge(seg_clk) then
			if gp_en = "01" then
				scan_cnt <= scan_cnt(2 downto 0) & scan_cnt(3);
			end if;
		end if;
	end process;

	-- clk 값이 뛸때마다 key_in_int값을 갱신한다.
	process(rst, clk, gp_en)
	begin
		if rst = '1' then
			key_in_int <= (others => '1');
		elsif rising_edge (clk) then
			if gp_en = "01" then
				key_in_int <= key_in;
			end if;
		end if;
	end process;
	
	process(scan_cnt, key_in_int, seg_clk, gp_en)
	begin
		if rising_edge(seg_clk) then
			if gp_en = "01" then 
				case scan_cnt is
					when "1110" => if 	key_in_int = "1110" then
												key_data_int <= X"1";
										elsif key_in_int = "1101" then
												key_data_int <= X"4";
										elsif key_in_int = "1011" then
												key_data_int <= X"7";
										elsif key_in_int = "0111" then
												key_data_int <= X"0";
										end if;
					when "1101" => if 	key_in_int = "1110" then
												key_data_int <= X"2";
										elsif key_in_int = "1101" then
												key_data_int <= X"5";
										elsif key_in_int = "1011" then
												key_data_int <= X"8";
										elsif key_in_int = "0111" then
												key_data_int <= X"A";
										end if;
					when "1011" => if 	key_in_int = "1110" then
												key_data_int <= X"3";
										elsif key_in_int = "1101" then
												key_data_int <= X"6";
										elsif key_in_int = "1011" then
												key_data_int <= X"9";
										elsif key_in_int = "0111" then
												key_data_int <= X"B";
										end if;
					when "0111" => if		key_in_int = "1110" then
												key_data_int <= X"F";
										elsif key_in_int = "1101" then
												key_data_int <= X"E";
										elsif key_in_int = "1011" then
												key_data_int <= X"D";
										elsif key_in_int = "0111" then
												key_data_int <= X"C";
										end if;
					when others => key_data_int <= key_data_int;
				end case;
			end if;
		end if;
	end process;
	
	
	--keypad push check
	process(rst, key_in_int, scan_cnt, seg_clk, gp_en)
	begin
		if rst = '1' then
			key_temp <= (others=>'1');
		elsif rising_edge(seg_clk) then
			if gp_en = "01" then
				case scan_cnt is
					when "1110" => if 	key_in_int = "1101" then
												key_temp(15 downto 12) <= key_in_int;
										else
											key_temp(15 downto 12) <= X"F";
										end if;
					when "1101" => if 	key_in_int = "0111" then
												key_temp(11 downto 8) <= X"F";
										else
											key_temp(11 downto 8) <= key_in_int;
										end if;
					when "1011" => if 	key_in_int = "1101" then
												key_temp(7 downto 4) <= key_in_int;
										else
												key_temp(7 downto 4) <= X"F";
										end if;
					when "0111" => key_temp(3 downto 0) <= X"F";
					when others => key_temp <= key_temp;
				end case;
			end if;
		end if;
	end process;
	
	--key_event 변경
	process(key_temp, gp_en)
	begin
		if gp_en = "01" then
			if key_temp = X"FFFF" then
				key_event <= '0';
			else
				key_event <= '1';
			end if;
		end if;
	end process;
	
	key_scan <= scan_cnt;
	key_data <= key_data_int;
	
	
end Behavioral;