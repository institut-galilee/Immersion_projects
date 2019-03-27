#include <SoftwareSerial.h>
#include <Adafruit_NeoPixel.h>

#define BLED 12
Adafruit_NeoPixel strip = Adafruit_NeoPixel(60, BLED, NEO_GRB + NEO_KHZ800);

int i = 0;
int rvb[3]={0,0,0};
char buf;
int n;



void setup()
{
 // define pin modes for tx, rx pins:
     strip.begin();
     //strip.setBrightness(50);
     Serial.begin(9600);
}
void loop()
{
     while(Serial.available())
     { 
        buf = (char)Serial.read();
        if(buf=='r'){
          i=0;
          n = 2;
          int rvb[3]={0,0,0};
        }
        if(buf=='v'){
          i=1;
          rvb[0] = rvb[0]/10^(n+1);
          n = 2;
        }
        if(buf=='b'){
          i=2;
          rvb[1] = rvb[0]/10^(n+1);
          n = 2;
        }
        if(buf==';'){
          i=0;
          rvb[2] = rvb[0]/10^(n+1);
          for (int j = 0; j<60;j++)
          {
          strip.setPixelColor(j,rvb[0],rvb[1],rvb[2]);
          }
          strip.show();
          Serial.println(rvb[0]);
          Serial.println(rvb[1]);
          Serial.println(rvb[2]);
        }
        if(buf!='r'||buf!='v'||buf!='b'||buf!=';'){
        rvb[i]+=((int)buf) * 10^n;
        n--;
        } 
        
        
  
     }


     

}
