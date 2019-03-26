#include <Adafruit_NeoPixel.h>
#include <TCS3200.h>
#define LED 13
#define BLED 12
// CS ( S2, S3, OUT, S0, S1, LED )


colorData current;
TCS3200 CS(4,3,2,6,5,13);
Adafruit_NeoPixel strip = Adafruit_NeoPixel(60, BLED, NEO_GRB + NEO_KHZ800);

 
void setup() {
    Serial.begin(9600);
    CS.begin();
    strip.begin();
    CS.LEDON(false);
    CS.nSamples(40);
    CS.setRefreshTime(200);
    CS.setFrequency(TCS3200_FREQ_HI);
    CS.calibration(0);
}
 
void loop() {
  
  current = CS.readRGB();
  CS.read( true );
  CS.getRGB(&current);
  
  for (int j = 0; j<60;j++)
  {
    strip.setPixelColor(j,current.value[0],current.value[1],current.value[2]);
  }
  strip.show();
}  
