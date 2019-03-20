#include <TCS3200.h>
#define LED 13
// CS ( S2, S3, OUT, S0, S1, LED )
TCS3200 CS(4,3,2,6,5,13);
 
void setup() {
    Serial.begin(9600);
    CS.begin();
    CS.LEDON(false);
    CS.nSamples(40);
    CS.setRefreshTime(200);
    CS.setFrequency(TCS3200_FREQ_HI);
    CS.calibration(0);
}
 
void loop() {
  CS.read( true );
}  
