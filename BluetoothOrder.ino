
#include <SoftwareSerial.h>

SoftwareSerial mySerial(2, 3); // RX, TX


void setup()
{
  // Open serial communications and wait for port to open:
  pinMode(7,OUTPUT);
  pinMode(8,OUTPUT);
  Serial.begin(9600);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for Leonardo only
  }
  Serial.println("Goodnight moon!");
  // set the data rate for the SoftwareSerial port
  mySerial.begin(9600);

}

char buffer[64];
int buffer_position = 0; 

void buffer_clear()
{
  int i= 0; 
  for(i = 0; i< 64; i++)
    buffer[i] = 0x0;
  buffer_position = 0; 
}
void loop() // run over and over
{
  if (mySerial.available()){
    char c = mySerial.read();
    if(c == '\n'){
      Serial.println(buffer);
      switch(buffer[0]){
        case 'K':
         {
            switch(buffer[1]) {
              case '0':
                Serial.print("LED 0:");
                Serial.println(buffer[5]);
                if(buffer[5] == '0') 
                  digitalWrite(8, LOW);
                else 
                  digitalWrite(8, HIGH);
              break;
              default:
              break;             
            }
            mySerial.write("R:OK\n");
          }
        break;
        case 'L':
          {
            switch(buffer[1]) {
              case '0':
                Serial.print("LED 1:");
                Serial.println(buffer[5]);
                if(buffer[5] == '0') 
                  digitalWrite(7, LOW);
                else 
                  digitalWrite(7, HIGH);
              break;
              default:
              break;             
            }
            mySerial.write("R:OK\n");
          }
        break;
        default:
          mySerial.write("R:FAIL\n");
        break;
      }
      buffer_clear(); 
    } else {      
      buffer[buffer_position++] = c; 
    }
  }
  
  if (Serial.available()){
    mySerial.write(Serial.read());
  }
}



