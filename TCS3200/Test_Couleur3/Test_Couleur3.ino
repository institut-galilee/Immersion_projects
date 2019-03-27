#include <Adafruit_NeoPixel.h>
#include <TCS3200.h>
#include <SoftwareSerial.h>
#define rxPin 7 // Broche 11 en tant que RX, à raccorder sur TX du HC-05
#define txPin 8 // Broche 10 en tant que TX, à raccorder sur RX du HC-05
SoftwareSerial mySerial(rxPin, txPin);
#define LED 13
#define BLED 12
// CS ( S2, S3, OUT, S0, S1, LED )

char r = NULL;
colorData current;
colorData *data;
TCS3200 CS(4,3,2,6,5,13);
Adafruit_NeoPixel strip = Adafruit_NeoPixel(60, BLED, NEO_GRB + NEO_KHZ800);


void Calibration()
{
      Serial.println("Debut du calibrage !!");
      CS.nSamples(40);
      CS.setRefreshTime(200);
      CS.setFrequency(TCS3200_FREQ_HI);
      CS.calibration(0);
}

void Init(){
  bool done = false;
  Serial.println("Voulez vous calibrer le capteur : Y/N");
  while(!done)
  {
      if(Serial.available())
      {
        r = Serial.read();
        delay(10);
        
        if(r == 'N')
        {
          CS.loadCal(0);
          done = true;
        }
        if(r == 'Y')
        {
          Calibration();
          done = true;
  
        } 
        Serial.read();
      }
   }
   
}

void setup() {
    pinMode(rxPin, INPUT);
    pinMode(txPin, OUTPUT);
    mySerial.begin(9600);
    Serial.begin(9600);
    CS.begin();
    CS.LEDON(false);
    strip.begin();
    strip.setBrightness(50);
    Init();
    
    
}
 
void loop() {
  
    while(mySerial.available())
    {
      Serial.write(mySerial.read());
      delay(200);
    }
    
    while(Serial.available())
    {
      mySerial.write(Serial.read());
    }
    current = CS.readRGB();
    //CS.read( true );
    CS.getRGB(&current);
    
    for (int j = 0; j<60;j++)
    {
      strip.setPixelColor(j,current.value[0],current.value[1],current.value[2]);
    }
    strip.show();
}  
