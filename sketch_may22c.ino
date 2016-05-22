#include <Wire.h>

const int temp_addr=0x48;
unsigned char temperature;
unsigned char pre_temperature;

unsigned char read_temp(void)
{
    unsigned char c,d;
     Wire.beginTransmission(temp_addr);
     Wire.write(byte(0x00));
     Wire.requestFrom(temp_addr,2);
     while(Wire.available()<2);
     c=Wire.read();
     d=Wire.read();
     Wire.endTransmission();
     return(c);
}
void setup() {
Serial.begin(9600);
Wire.begin();
//re.begin(); // I2C 활성화
Wire.beginTransmission(temp_addr); // Start (버스 시작) 및 타겟 어드레스 지정
Wire.write(byte(0x01)); // Configuration 레지스터(01) 지정
Wire.write(byte(0x00)); // Configuration 레지스터에 값(00)을 Write : Normal 모드
Wire.endTransmission(); // Stop (버스 종료)

}

void loop() {
  // put your main code here, to run repeatedly:
 Wire.beginTransmission(temp_addr);       // Start (버스 시작) 및 타겟 어드레스 지정
  Wire.write(byte(0x00));                         // Temperature 레지스터(00) 지정 
  Wire.requestFrom(temp_addr, 2);                      // 2 bytes read
  while(Wire.available() < 2)  ;                 // Wating 2 byte available
  byte c = Wire.read();                           // 첫번째 데이터 저장 
  byte d = Wire.read();                           // 두번째 데이터 저장 
Wire.endTransmission();                       // Stop (버스 종료)
word e = (c << 8) | d;                         // 읽어온 2바이트 계산 
  if ((e & 0x8000) == 0x8000)               // MSB는 부호비트 
  {
    Serial.print('-');                              // - 부호이면, print '-' sign
    e = ~e + 1;                                 // 2’s complement값 추출 
  }
  c = ((e & 0x7f00) >> 8);                    // 정수 부분 추출 
  d = ((e & 0x0080) >> 7) ? 5 : 0;          // 소수 부분 추출(0.5인지 0.0인지 결정), 아래 코딩과 같은 의미                                                  // if (e & 0x0080 == 0x0080                                                  //         d = 5                                                     // else
  Serial.print(c,DEC);                           // 정수 부분 온도 출력 
  Serial.print('.');                                 // dot(.) 출력 
  Serial.println(d,DEC);                         // 소수 부분 온도 출력 
  delay(1000);                                   // 다음 디스플레이까지 1초 기다림 
  
}

