#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "Adjust.h"


int main(){
	int tick=0;
	int i=0;
	time_t start;   //시스템이 처음시작했을때의 시간.
	struct tm st;
	time(&start);
	st=*localtime(&start);
	printf("정산이 시작되지 않았습니다.\n");
	while(1)
	{
		tick=Tick(st);
		if(tick==0){  //3분일 경우
			if(i==0){
			//system("clear");
			printf("SoftwareEngineering Team Project \n");
			printf("FCS - Fee Calculation System \n");
                	printf("\n\n\n");
			Fee_Calculation_Controller(); //모든 단말기파일에 대하여 정산한다.
			i=1;			
	                printf("\n\n\n");
			
			printf("정산이 시작되지 않았습니다.\n");
			//break;
			}
		}
		else if(tick==1){//3분이 아닐 경우
		i=0;
		}
	}
	return 0;
}

void Fee_Calculation_Controller(){
	float bus_fee=0,metro_fee=0;
	int c_well=1;
	Card_Reader_Info_Loader();	  //모든 단말기파일의 데이터를 불러온다.
	c_well=All_Adjust(&bus_fee,&metro_fee);
	//bus_fee=10.5;
	//metro_fee=11.4;
	if((bus_fee-(int)bus_fee)*2>=1)
	bus_fee=((int)bus_fee)+1;
	else
	bus_fee=(int)bus_fee;
	if((metro_fee-(int)metro_fee)*2>=1)
	metro_fee=((int)metro_fee)+1;
	else
	metro_fee=(int)metro_fee;
	Display(bus_fee,metro_fee);
	Send(c_well,bus_fee,metro_fee);
	All_Reset();

}

void Card_Reader_Info_Loader(){
	CID_Merge(); //단말기 파일에 저장된 데이터를 CID 별로 분류한다.
}


int All_Adjust(float* bus_fee, float* metro_fee){
	Card_info* temp;
	int size=0;

	AdjustStart();
	GuestOut();

	temp=CID_Sort("CID_1000",&size);// CID를 시간순으로 정렬한다.
	Adjust(temp,size,bus_fee,metro_fee);

	temp=CID_Sort("CID_1001",&size);
	Adjust(temp,size,bus_fee,metro_fee);

	temp=CID_Sort("CID_1002",&size);
	Adjust(temp,size,bus_fee,metro_fee);

	temp=CID_Sort("CID_1003",&size);
	Adjust(temp,size,bus_fee,metro_fee);

	temp=CID_Sort("CID_1004",&size);
	Adjust(temp,size,bus_fee,metro_fee);

	temp=CID_Sort("CID_1005",&size);
	Adjust(temp,size,bus_fee,metro_fee);

	temp=CID_Sort("CID_1006",&size);
	Adjust(temp,size,bus_fee,metro_fee);

	return 0;
};
void CID_Merge()
{
	FILE *file_CRID_0,*file_CRID_1,*file_CRID_2,*file_CRID_3,*file_CRID_4,*file_CRID_5;
	FILE *file_CID_1000,*file_CID_1001,*file_CID_1002;
	FILE *file_CID_1003,*file_CID_1004,*file_CID_1005,*file_CID_1006;
	char buff[1024];


	Card_info temp_card_info;

	file_CRID_0=fopen("./../Card_Reader_0.txt","r");//버스.txt
	file_CRID_1=fopen("./../Card_Reader_1.txt","r");//지하철 건대입구역.txt
	file_CRID_2=fopen("./../Card_Reader_2.txt","r");//지하철 강남역.txt
	file_CRID_3=fopen("./../Card_Reader_3.txt","r");//지하철 신림역.txt
	file_CRID_4=fopen("./../Card_Reader_4.txt","r");//지하철 합정역.txt
	file_CRID_5=fopen("./../Card_Reader_5.txt","r");//지하철 동대문역사문화공원역.txt

	file_CID_1000=fopen("CID_1000.txt","w");
	file_CID_1001=fopen("CID_1001.txt","w");
	file_CID_1002=fopen("CID_1002.txt","w");
	file_CID_1003=fopen("CID_1003.txt","w");
	file_CID_1004=fopen("CID_1004.txt","w");
	file_CID_1005=fopen("CID_1005.txt","w");
	file_CID_1006=fopen("CID_1006.txt","w");
	/*
	if(!fgets(buff,1024,file_CID_1000))
	{
		perror("CID_1000 파일 읽기 에러");
	}
	if(!fgets(buff,1024,file_CID_1001))
	{
		perror("CID_1001 파일 읽기 에러");
	}

	for(i=0;i<6;i++){
		strcpy(fname,"CRID_");
		itoa(i,fname_temp,10);
		strcat(fname,fname_temp);
	}
	*/
	if(!fgets(buff,57,file_CRID_0))
	{
		perror("버스 파일 읽기 에러");
	}
	//printf("%s\n",buff);

	fprintf(file_CID_1000,"%s",buff);
	fprintf(file_CID_1001,"%s",buff);
	fprintf(file_CID_1002,"%s",buff);
	fprintf(file_CID_1003,"%s",buff);
	fprintf(file_CID_1004,"%s",buff);
	fprintf(file_CID_1005,"%s",buff);
	fprintf(file_CID_1006,"%s",buff);


	fscanf(file_CRID_0,"%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
			&temp_card_info.tagTime.tm_year, &temp_card_info.tagTime.tm_mon, &temp_card_info.tagTime.tm_mday, &temp_card_info.tagTime.tm_hour,
			&temp_card_info.tagTime.tm_min, &temp_card_info.tagTime.tm_sec, 
			&temp_card_info.tp, &temp_card_info.state, &temp_card_info.price, &temp_card_info.transfer, &temp_card_info.getout);
		

	while(!feof(file_CRID_0))
	{
		switch(temp_card_info.CID)
		{
		case 1000:
			fprintf(file_CID_1000,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;

		case 1001:
			fprintf(file_CID_1001,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1002:
			fprintf(file_CID_1002,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1003:
			fprintf(file_CID_1003,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1004:
			fprintf(file_CID_1004,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1005:
			fprintf(file_CID_1005,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1006:
			fprintf(file_CID_1006,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;


		}

		fscanf(file_CRID_0,"%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
			&temp_card_info.tagTime.tm_year, &temp_card_info.tagTime.tm_mon, &temp_card_info.tagTime.tm_mday, &temp_card_info.tagTime.tm_hour,
			&temp_card_info.tagTime.tm_min, &temp_card_info.tagTime.tm_sec, 
			&temp_card_info.tp, &temp_card_info.state, &temp_card_info.price, &temp_card_info.transfer, &temp_card_info.getout);
		
	}

	fclose(file_CRID_0);

	//CRID_1 fopen
	if(!fgets(buff,57,file_CRID_1))
	{
		perror("버스 파일 읽기 에러");
	}

	fscanf(file_CRID_1,"%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
			&temp_card_info.tagTime.tm_year, &temp_card_info.tagTime.tm_mon, &temp_card_info.tagTime.tm_mday, &temp_card_info.tagTime.tm_hour,
			&temp_card_info.tagTime.tm_min, &temp_card_info.tagTime.tm_sec, 
			&temp_card_info.tp, &temp_card_info.state, &temp_card_info.price, &temp_card_info.transfer, &temp_card_info.getout);
		

	while(!feof(file_CRID_1))
	{
		switch(temp_card_info.CID)
		{
		case 1000:
			fprintf(file_CID_1000,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;

		case 1001:
			fprintf(file_CID_1001,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1002:
			fprintf(file_CID_1002,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1003:
			fprintf(file_CID_1003,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1004:
			fprintf(file_CID_1004,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1005:
			fprintf(file_CID_1005,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1006:
			fprintf(file_CID_1006,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		}

		fscanf(file_CRID_1,"%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
			&temp_card_info.tagTime.tm_year, &temp_card_info.tagTime.tm_mon, &temp_card_info.tagTime.tm_mday, &temp_card_info.tagTime.tm_hour,
			&temp_card_info.tagTime.tm_min, &temp_card_info.tagTime.tm_sec, 
			&temp_card_info.tp, &temp_card_info.state, &temp_card_info.price, &temp_card_info.transfer, &temp_card_info.getout);
		
	}

	fclose(file_CRID_1);

	//CRID_2 fopen
	if(!fgets(buff,57,file_CRID_2))
	{
		perror("버스 파일 읽기 에러");
	}

	fscanf(file_CRID_2,"%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
			&temp_card_info.tagTime.tm_year, &temp_card_info.tagTime.tm_mon, &temp_card_info.tagTime.tm_mday, &temp_card_info.tagTime.tm_hour,
			&temp_card_info.tagTime.tm_min, &temp_card_info.tagTime.tm_sec, 
			&temp_card_info.tp, &temp_card_info.state, &temp_card_info.price, &temp_card_info.transfer, &temp_card_info.getout);
		

	while(!feof(file_CRID_2))
	{
		switch(temp_card_info.CID)
		{
		case 1000:
			fprintf(file_CID_1000,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;

		case 1001:
			fprintf(file_CID_1001,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1002:
			fprintf(file_CID_1002,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1003:
			fprintf(file_CID_1003,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1004:
			fprintf(file_CID_1004,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1005:
			fprintf(file_CID_1005,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1006:
			fprintf(file_CID_1006,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		}

		fscanf(file_CRID_2,"%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
			&temp_card_info.tagTime.tm_year, &temp_card_info.tagTime.tm_mon, &temp_card_info.tagTime.tm_mday, &temp_card_info.tagTime.tm_hour,
			&temp_card_info.tagTime.tm_min, &temp_card_info.tagTime.tm_sec, 
			&temp_card_info.tp, &temp_card_info.state, &temp_card_info.price, &temp_card_info.transfer, &temp_card_info.getout);
		
	}

	fclose(file_CRID_2);

	//CRID_3 fopen
	if(!fgets(buff,57,file_CRID_3))
	{
		perror("버스 파일 읽기 에러");
	}

	fscanf(file_CRID_3,"%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
			&temp_card_info.tagTime.tm_year, &temp_card_info.tagTime.tm_mon, &temp_card_info.tagTime.tm_mday, &temp_card_info.tagTime.tm_hour,
			&temp_card_info.tagTime.tm_min, &temp_card_info.tagTime.tm_sec, 
			&temp_card_info.tp, &temp_card_info.state, &temp_card_info.price, &temp_card_info.transfer, &temp_card_info.getout);
		

	while(!feof(file_CRID_3))
	{
		switch(temp_card_info.CID)
		{
		case 1000:
			fprintf(file_CID_1000,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;

		case 1001:
			fprintf(file_CID_1001,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1002:
			fprintf(file_CID_1002,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1003:
			fprintf(file_CID_1003,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1004:
			fprintf(file_CID_1004,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1005:
			fprintf(file_CID_1005,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1006:
			fprintf(file_CID_1006,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		}

		fscanf(file_CRID_3,"%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
			&temp_card_info.tagTime.tm_year, &temp_card_info.tagTime.tm_mon, &temp_card_info.tagTime.tm_mday, &temp_card_info.tagTime.tm_hour,
			&temp_card_info.tagTime.tm_min, &temp_card_info.tagTime.tm_sec, 
			&temp_card_info.tp, &temp_card_info.state, &temp_card_info.price, &temp_card_info.transfer, &temp_card_info.getout);
		
	}

	fclose(file_CRID_3);

	//CRID_4 fopen
	if(!fgets(buff,57,file_CRID_4))
	{
		perror("버스 파일 읽기 에러");
	}

	fscanf(file_CRID_4,"%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
			&temp_card_info.tagTime.tm_year, &temp_card_info.tagTime.tm_mon, &temp_card_info.tagTime.tm_mday, &temp_card_info.tagTime.tm_hour,
			&temp_card_info.tagTime.tm_min, &temp_card_info.tagTime.tm_sec, 
			&temp_card_info.tp, &temp_card_info.state, &temp_card_info.price, &temp_card_info.transfer, &temp_card_info.getout);
		

	while(!feof(file_CRID_4))
	{
		switch(temp_card_info.CID)
		{
		case 1000:
			fprintf(file_CID_1000,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;

		case 1001:
			fprintf(file_CID_1001,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1002:
			fprintf(file_CID_1002,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1003:
			fprintf(file_CID_1003,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1004:
			fprintf(file_CID_1004,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1005:
			fprintf(file_CID_1005,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1006:
			fprintf(file_CID_1006,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		}

		fscanf(file_CRID_4,"%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
			&temp_card_info.tagTime.tm_year, &temp_card_info.tagTime.tm_mon, &temp_card_info.tagTime.tm_mday, &temp_card_info.tagTime.tm_hour,
			&temp_card_info.tagTime.tm_min, &temp_card_info.tagTime.tm_sec, 
			&temp_card_info.tp, &temp_card_info.state, &temp_card_info.price, &temp_card_info.transfer, &temp_card_info.getout);
		
	}

	fclose(file_CRID_4);	

	//CRID_5 fopen
	if(!fgets(buff,57,file_CRID_5))
	{
		perror("버스 파일 읽기 에러");
	}

	fscanf(file_CRID_5,"%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
			&temp_card_info.tagTime.tm_year, &temp_card_info.tagTime.tm_mon, &temp_card_info.tagTime.tm_mday, &temp_card_info.tagTime.tm_hour,
			&temp_card_info.tagTime.tm_min, &temp_card_info.tagTime.tm_sec, 
			&temp_card_info.tp, &temp_card_info.state, &temp_card_info.price, &temp_card_info.transfer, &temp_card_info.getout);
		

	while(!feof(file_CRID_5))
	{
		switch(temp_card_info.CID)
		{
		case 1000:
			fprintf(file_CID_1000,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;

		case 1001:
			fprintf(file_CID_1001,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1002:
			fprintf(file_CID_1002,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1003:
			fprintf(file_CID_1003,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1004:
			fprintf(file_CID_1004,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1005:
			fprintf(file_CID_1005,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		case 1006:
			fprintf(file_CID_1006,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
				temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
				temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
				temp_card_info.tp, temp_card_info.state, temp_card_info.price, temp_card_info.transfer, temp_card_info.getout);
			break;
		}

		fscanf(file_CRID_5,"%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
			&temp_card_info.tagTime.tm_year, &temp_card_info.tagTime.tm_mon, &temp_card_info.tagTime.tm_mday, &temp_card_info.tagTime.tm_hour,
			&temp_card_info.tagTime.tm_min, &temp_card_info.tagTime.tm_sec, 
			&temp_card_info.tp, &temp_card_info.state, &temp_card_info.price, &temp_card_info.transfer, &temp_card_info.getout);
		
	}

	fclose(file_CRID_5);



	fclose(file_CID_1000);
	fclose(file_CID_1001);
	fclose(file_CID_1002);
	fclose(file_CID_1003);
	fclose(file_CID_1004);
	fclose(file_CID_1005);
	fclose(file_CID_1006);
}

Card_info* CID_Sort(char* name, int* size){
	FILE *file,*temp;
	Card_info *temp_card_info;
	Card_info *real_card_info;
	
	Card_info t_temp_card_info[2];
	Card_info t_real_card_info[2];
		

	Card_info line_card_info[2];

	char buff[1024];
	int line=0;
	int i=0,j=0,k=0;
	int real_fee=0,temp_fee=0;
	char fname[100]={0};
	char fname_temp[100]={0};

	strcpy(fname,name);
	strcpy(fname_temp,fname);
	strcat(fname_temp,"_Sorted.txt");
	strcat(fname,".txt");

	printf("\nfname: %s\n",fname);
	file=fopen(fname,"r");
	if(!fgets(buff,57,file))
		perror("파일읽기 오류");

	while(!feof(file)){
		fscanf(file,"%d %d %d %d %d %d %d %d %d %d %d %d %d", &line_card_info[0].CID, &line_card_info[0].CRID, 
			&line_card_info[0].tagTime.tm_year, &line_card_info[0].tagTime.tm_mon, &line_card_info[0].tagTime.tm_mday, &line_card_info[0].tagTime.tm_hour,
			&line_card_info[0].tagTime.tm_min, &line_card_info[0].tagTime.tm_sec, 
			&line_card_info[0].tp, &line_card_info[0].state, &line_card_info[0].price, &line_card_info[0].transfer, &line_card_info[0].getout);
		line++;
	}
	printf("line : %d\n",line);
	line--;
	fclose(file);

	temp_card_info=(Card_info*)malloc(sizeof(Card_info)*line);
	real_card_info=(Card_info*)malloc(sizeof(Card_info)*line);



	temp=fopen(fname_temp,"w");
	file=fopen(fname,"r");


	if(!fgets(buff,57,file))
	{
		perror("파일읽기 오류");
	}
	fputs(buff,temp);
	

	for(i=0;i<line;i++){
		fscanf(file,"%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info[i].CID, &temp_card_info[i].CRID, 
			&temp_card_info[i].tagTime.tm_year, &temp_card_info[i].tagTime.tm_mon, &temp_card_info[i].tagTime.tm_mday, &temp_card_info[i].tagTime.tm_hour,
			&temp_card_info[i].tagTime.tm_min, &temp_card_info[i].tagTime.tm_sec, 
			&temp_card_info[i].tp, &temp_card_info[i].state, &temp_card_info[i].price, &temp_card_info[i].transfer, &temp_card_info[i].getout);

	}

	for(i=0;i<line;i++){
	temp_card_info[i].tagTime.tm_year-=1900;
	temp_card_info[i].tagTime.tm_mon-=1;

	}

	fclose(file);
	
	for(i=0;i<line;i++){
		if(i==0)
		{
			real_card_info[0]=temp_card_info[i];
		}
        else
		{
			for(j=0;j<i+1;j++)
			{	

				if((j<i)&&(maketime(&temp_card_info[i].tagTime)<=maketime(&real_card_info[j].tagTime)))
				{
					for(k=line-2;k>=j;k--)
					{
						real_card_info[k+1]=real_card_info[k];
					}
					real_card_info[j]=temp_card_info[i];
					break;
				}
				else if(j==i)
				{
					real_card_info[j]=temp_card_info[i];
				}
			
			}
		}
	}



	for(i=0;i<line;i++){
		real_card_info[i].tagTime.tm_year+=1900;
		real_card_info[i].tagTime.tm_mon+=1;
		fprintf(temp,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n", real_card_info[i].CID, real_card_info[i].CRID, 
			real_card_info[i].tagTime.tm_year, real_card_info[i].tagTime.tm_mon, real_card_info[i].tagTime.tm_mday, real_card_info[i].tagTime.tm_hour,
			real_card_info[i].tagTime.tm_min, real_card_info[i].tagTime.tm_sec, 
			real_card_info[i].tp, real_card_info[i].state, real_card_info[i].price, real_card_info[i].transfer, real_card_info[i].getout);
	
	}
	fclose(temp);

	*size=line;
	return real_card_info;

}

float maketime(struct tm *tagtime){
	float i=0;
	i+=tagtime->tm_sec;
	i+=tagtime->tm_min*60;
	i+=tagtime->tm_hour*3600;
	i+=tagtime->tm_mday*3600*24;
	i+=tagtime->tm_mon*3600*24*30;		
	
	return i;
}

//정렬된 데이터를 정산한다.
void Adjust(Card_info* real_card_info, int line, float* bus_fee,float* metro_fee){
	int real_fee=0,temp_fee=0;
	float total_fee=0;
	int i=0,j=0;
	int index=0;

	for(i=0;i<line;i++){
		if((i>0)&&(real_card_info[i].price>1050)){
			real_card_info[i-1].price+=(real_card_info[i].price-1050);
			real_card_info[i].price=1050;
		}
	}



	index=line-1;
	for(i=line-1;i>=0;i--)
	{
		if((real_card_info[i].state==1)&&(real_card_info[i].transfer==0))
		{
			for(j=index;j>=i;j--)
			{
				real_fee+=real_card_info[j].price;
				if(j==i || (real_card_info[j-1].tp!=real_card_info[j].tp))
				{
					total_fee+=real_fee;
					//printf("total_fee:%f, real_fee:%d\n",total_fee,real_fee);
				}
			}
			for(j=index;j>=i;j--)
			{
				temp_fee+=real_card_info[j].price;
				//printf("total_fee:%f, real_fee:%d, real_card_info[%d].price: %d\n",total_fee,real_fee,j,real_card_info[j].price);
				//printf("temp_fee: %d, bus_fee: %f, metro_fee: %f\n",temp_fee,bus_fee,metro_fee);
				
				if(j==i || (real_card_info[j-1].tp!=real_card_info[j].tp))
				{
					if(real_card_info[j].tp==0)
					{
						*bus_fee+=temp_fee*(real_fee/total_fee);
						//printf("+bus_fee : %f\n",temp_fee*(real_fee/total_fee));
						//printf("bus_fee : %f\n",bus_fee);
					}
					else if(real_card_info[j].tp==1)
					{
						*metro_fee+=temp_fee*(real_fee/total_fee);
						//printf("metro_fee : %f\n",metro_fee);
					}
				}

			}
			index=i-1;
			total_fee=0;
			real_fee=0;
			temp_fee=0;
		}
	}

	printf("metro_fee : %f\n",*metro_fee);
	printf("bus_fee : %f\n",*bus_fee);
}

void All_Reset(){
	Reset("./../Card_Reader_0.txt");
	Reset("./../Card_Reader_1.txt");
	Reset("./../Card_Reader_2.txt");
	Reset("./../Card_Reader_3.txt");
	Reset("./../Card_Reader_4.txt");
	Reset("./../Card_Reader_5.txt");
}
void Reset(char* fname){
	FILE *file;
	
	file=fopen(fname,"w");
	fputs("CID		CRID	tagTime			    tp	state   cash	transfer  getout",file);
	fclose(file);
}
void AdjustStart(){//정산시 c_well을 1로 한다.
	FILE *file_bus,*file_metro;
	file_bus=fopen("./../send_bus.txt","r+");
	file_metro=fopen("./../send_metro.txt","r+");
	fprintf(file_bus,"%d\n",1);
	fprintf(file_metro,"%d\n",1); 

	//Sleep(5000);
	fclose(file_bus);
	fclose(file_metro);
}	  
void GuestOut(){ //정산시작시 손님들을 내쫒는다. ....승차중인 사람들에 한해서 쫒겨났다라는값을넣는다.
	FILE *file,*file_temp;
	char buff[1024];
	int temp=0;
	Card_info temp_card_info;
	file=fopen("./../Card.txt","r+");
	file_temp=fopen("./../Card_temp.txt","w");
	
	if(!fgets(buff,1024,file))
	{
		perror("파일읽기 에러");
	}	

	fscanf(file,"%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
		&temp_card_info.tagTime.tm_year, &temp_card_info.tagTime.tm_mon, &temp_card_info.tagTime.tm_mday, &temp_card_info.tagTime.tm_hour,
		&temp_card_info.tagTime.tm_min, &temp_card_info.tagTime.tm_sec, 
		&temp_card_info.tp, &temp_card_info.state, &temp_card_info.cash, &temp_card_info.transfer, &temp_card_info.getout);
		
	
	while(!feof(file))
	{		
		temp_card_info.getout=1;
		
		
		if(temp==0){
			fprintf(file_temp,"CID		CRID	tagTime			    tp	state   cash	transfer  getout\n");
			temp++;
		}
		fprintf(file_temp,"%d\t%d\t\t%d %d %d %d %d %d\t%d\t\t%d\t%d\t%d\t\t\t%d\n",temp_card_info.CID, temp_card_info.CRID, 
			temp_card_info.tagTime.tm_year, temp_card_info.tagTime.tm_mon, temp_card_info.tagTime.tm_mday, temp_card_info.tagTime.tm_hour,
			temp_card_info.tagTime.tm_min, temp_card_info.tagTime.tm_sec, 
			temp_card_info.tp, temp_card_info.state, temp_card_info.cash, temp_card_info.transfer, temp_card_info.getout);

		fscanf(file,"%d %d %d %d %d %d %d %d %d %d %d %d %d", &temp_card_info.CID, &temp_card_info.CRID, 
			&temp_card_info.tagTime.tm_year, &temp_card_info.tagTime.tm_mon, &temp_card_info.tagTime.tm_mday, &temp_card_info.tagTime.tm_hour,
			&temp_card_info.tagTime.tm_min, &temp_card_info.tagTime.tm_sec, 
			&temp_card_info.tp, &temp_card_info.state, &temp_card_info.cash, &temp_card_info.transfer, &temp_card_info.getout);
		
		
	}

	fclose(file);
	fclose(file_temp);	
	remove("./../Card.txt");
	rename("./../Card_temp.txt","./../Card.txt");

}

void Send(int c_well,int bus_fee,int metro_fee){
	FILE *file_bus,*file_metro;

	file_bus=fopen("./../send_bus.txt","w");
	fprintf(file_bus,"%d\n",c_well);
	fprintf(file_bus,"정산금액: %d\n",bus_fee);

	file_metro=fopen("./../send_metro.txt","w");
	fprintf(file_metro,"%d\n",c_well);
	fprintf(file_metro,"총 정산금액: %d",metro_fee);
	
	fclose(file_bus);
	fclose(file_metro);

}
void Display(int bus_fee, int metro_fee){
	time_t now; 
	struct tm t_now;

	time(&now);
	t_now=*localtime(&now);
	printf("현재 시간: %d/%02d/%02d %02d:%02d:%02d\n",
		t_now.tm_year+1900, t_now.tm_mon+1, t_now.tm_mday, t_now.tm_hour, t_now.tm_min, t_now.tm_sec);

	printf("버스 정산 금액: %d\n",bus_fee);
	printf("지하철 정산 금액: %d\n",metro_fee);

}

int Tick(struct tm st){ //st는 start time
	int tick=0;			//현재 시각과 처음 시각의 차이의 초 값.
	int st_sec=0;		//시스템 처음 시작 시간을 초로 바꾼 값.
	int now_sec=0;		//현재 시각을 초로 바꾼 값.

	time_t now;			//현재 시각
	struct tm t_now;
	time(&now);
	t_now=*localtime(&now);
	//t_now-st의 시간 차이를 tick에 넣는다.
	st_sec=mktime(&st);			//처음시간을 초로 바꾼다.
	now_sec=mktime(&t_now);		//현재시간을 초로 바꾼다.
	tick=now_sec-st_sec;

	
	if((st_sec!=now_sec)&&(tick%180==0)){//30초마다
		//printf("st_sec: %d, now_sec: %d, Tick: %d \n",st_sec,now_sec,tick%180);
		return 0;
	}
	else
		return 1;
}