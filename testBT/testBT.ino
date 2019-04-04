#include <SoftwareSerial.h>
#include <Adafruit_NeoPixel.h>
#include <stdlib.h>

#define BLED 12
Adafruit_NeoPixel strip = Adafruit_NeoPixel(60, BLED, NEO_GRB + NEO_KHZ800);

int i = 0;
int rvb[3] = {0, 0, 0};
char buf;
int n;
int b;

/*void OnOff()
{
  if(Serial.available() > 0)  
  {
    Incoming_value = Serial.read();      //Read the incoming data and store it into variable Incoming_value
    Serial.print(Incoming_value);        //Print Value of Incoming_value in Serial monitor
    Serial.print("\n");        //New line 
    if(Incoming_value == '1')            //Checks whether value of Incoming_value is equal to 1 
      digitalWrite(13, HIGH);  //If value is 1 then LED turns ON
    else if(Incoming_value == '0')       //Checks whether value of Incoming_value is equal to 0
      digitalWrite(13, LOW);   //If value is 0 then LED turns OFF
  }     
}*/

int charToInt(char a) {
  int b = -1;
  switch (a) {
    case '0':
      b = 0;
      break;
    case '1':
      b = 1;
      break;
    case '2':
      b = 2;
      break;
    case '3':
      b = 3;
      break;
    case '4':
      b = 4;
      break;
    case '5':
      b = 5;
      break;
    case '6':
      b = 6;
      break;
    case '7':
      b = 7;
      break;
    case '8':
      b = 8;
      break;
    case '9':
      b = 9;
      break;
  }
  return b;
}

int coef(int n){
    int co=0;
    switch(n){
      case 0 : 
        co = 1;
      break;
      case 1 :
        co = 10;
      break;
      case 2 : 
        co =100;
      break;
    }
    return co;
}
  
void setup()
{
  // define pin modes for tx, rx pins:
  strip.begin();
  //strip.setBrightness(50);
  Serial.begin(9600);
}
void loop()
{
  while (Serial.available())
  {
    buf = (char)Serial.read();
    if (buf == 'A') {
      digitalWrite(12, HIGH);
    }
    if (buf == 'E') {
       digitalWrite(12, LOW);
    }
    if (buf == 'r') {
      i = 0;
      n = 2;
      rvb[i]=0;
    }
    
    if (buf == 'v') {
      i = 1;
      n = 2;
      rvb[i]=0;
    }
    if (buf == 'b') {
      i = 2;
      n = 2;
      rvb[i]=0;
    }
    if (buf == ';') {
      rvb[i] = rvb[i] / coef(n+1);
      for (int j = 0; j < 60; j++)
      {
        strip.setPixelColor(j, rvb[0], rvb[1], rvb[2]);
      }
      strip.show();
      delay(20);
      Serial.flush();
      Serial.println(rvb[0]);
      Serial.println(rvb[1]);
      Serial.println(rvb[2]);
      //delay(100);
    }
    if (charToInt(buf) != -1) {
      b = charToInt(buf);
      rvb[i] += b *coef(n);
      //delay(10);
      n--;
    }
    //delay(100);


  
  }

}
