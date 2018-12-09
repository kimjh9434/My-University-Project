float pulse2ugm3(unsigned long pulse){
  float value = (pulse-1400)/14.0;
  if(value > 300){
    value = 0;
  }
  return value;
}

void setup() {
  pinMode(8,INPUT);
  Serial.begin(115200);
}

int i = 0;
unsigned long pulse = 0;
float ugm3 = 0;
void loop() {
  pulse = pulseIn(8,LOW,20000);

  ugm3 = pulse2ugm3(pulse);
  //Serial.print(i);
  //Serial.print("\t:\t");
  //Serial.print(ugm3,4);
  //Serial.println("\tug/m3");
  //i++;
  //delay(1000);

  Serial.println(ugm3,4);
  //Serial.println(100);
  //delay(60000); // 본래 1분
  delay(5000); //시연용 5초
}
