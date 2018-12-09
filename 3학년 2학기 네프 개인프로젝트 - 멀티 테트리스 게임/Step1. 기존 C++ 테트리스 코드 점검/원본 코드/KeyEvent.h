#pragma once
#include <windows.h>

using namespace std;

class KeyEvent {
private:
	HANDLE hln;
	COORD KeyWhere;
	DWORD EventConut;
	INPUT_RECORD InRec;
	DWORD NumRead;
	int downKey;
public:
	KeyEvent() {
		hln = GetStdHandle(STD_INPUT_HANDLE);
		EventConut = 1;
	}
	//아스키 코드 값을 받아온다. 
	int getkey() {
		ReadConsoleInput(hln, &InRec, 1, &NumRead);
		if (InRec.EventType == KEY_EVENT) {
			if (InRec.Event.KeyEvent.bKeyDown) {
				downKey = InRec.Event.KeyEvent.wVirtualKeyCode;
				return downKey;
			}
			else {
				return -1;
			}
		}
		return -1;
	}
};