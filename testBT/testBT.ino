#include <SoftwareSerial.h>
#define rxPin 2 // Broche 11 en tant que RX, à raccorder sur TX du HC-05
#define txPin 3 // Broche 10 en tant que TX, à raccorder sur RX du HC-05
SoftwareSerial mySerial(rxPin, txPin);
void setup()
{
 // define pin modes for tx, rx pins:
 pinMode(rxPin, INPUT);
 pinMode(txPin, OUTPUT);
 mySerial.begin(9600);
 Serial.begin(9600);
}
void loop()
{
 while(mySerial.available())
 {
  Serial.write(mySerial.read());
  delay(200);
 }

 while(Serial.available())
 {
  mySerial.write(Serial.read());
 }
}
