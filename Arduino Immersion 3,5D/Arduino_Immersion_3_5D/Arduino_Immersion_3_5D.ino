#include <Adafruit_NeoPixel.h>
#include <SoftwareSerial.h>
#include <stdlib.h>
#include <TCS3200.h>
#define BLED 12
#define LED 13
#define CALIBRAGE 1
#define IMMERSION 2
#define THEME 3
#define GUIRELAND 4
#define FONDU 5
#define OFF 0
Adafruit_NeoPixel strip = Adafruit_NeoPixel(60, BLED, NEO_GRB + NEO_KHZ800);
// CS ( S2, S3, OUT, S0, S1, LED )
TCS3200 CS(4, 3, 2, 6, 5, 13);
char r = NULL;
int n, b;
int i = 0;
int rvb[4] = {0, 0, 0, 200};
char buf;

int mode = OFF;
colorData current;
colorData *data;
///////////////////////////////////CAPTEUR///////////////////////////

void increaseRed(){
  while(rvb[0] <255 && mode == FONDU){
    getMode();
    for (int j = 0; j < 60; j++)
    {
      strip.setPixelColor(j, rvb[0],rvb[1], rvb[2]);
    }
    strip.show();
    delay(25);
    rvb[0]++;
  }
  rvb[0]=255;
}

void increaseGreen(){
  while(rvb[1] <255 && mode == FONDU){
    getMode();
    for (int j = 0; j < 60; j++)
    {
      strip.setPixelColor(j, rvb[0],rvb[1], rvb[2]);
    }
    strip.show();
    delay(25);
    rvb[1]++;
  }
  rvb[1]=255;
}

void increaseBlue(){
  while(rvb[2] <255 && mode == FONDU){
    getMode();
    for (int j = 0; j < 60; j++)
    {
      strip.setPixelColor(j, rvb[0],rvb[1], rvb[2]);
    }
    strip.show();
    delay(25);
    rvb[2]++;
  }
  rvb[2]=255;
}

void decreaseRed(){
  while(rvb[0] <255 && mode == FONDU){
    getMode();
    for (int j = 0; j < 60; j++)
    {
      strip.setPixelColor(j, rvb[0],rvb[1], rvb[2]);
    }
    strip.show();
    delay(25);
    rvb[0]--;
  }
  rvb[0]=0;
}

void decreaseGreen(){
  while(rvb[1] >0 && mode == FONDU){
    getMode();
    for (int j = 0; j < 60; j++)
    {
      strip.setPixelColor(j, rvb[0],rvb[1], rvb[2]);
    }
    strip.show();
    delay(25);
    rvb[1]--;
  }
  rvb[1]=0;
}

void decreaseBlue(){
  while(rvb[2] > 0 && mode == FONDU){
    getMode();
    for (int j = 0; j < 60; j++)
    {
      strip.setPixelColor(j, rvb[0],rvb[1], rvb[2]);
    }
    strip.show();
    delay(25);
    rvb[2]--;
  }
  rvb[2]=0;
}

void getMode(){
   if (Serial.available())
  {
    buf = (char)Serial.read();
    
    if (buf == 'C') {
      mode = CALIBRAGE;

    }
    if (buf == 'G'){
      mode = GUIRELAND;
    }
    if (buf == 'I') {
      mode = IMMERSION;
    }
    if (buf == 'F'){
      mode = FONDU;
    }
    if (buf == 'T') {
      mode = THEME;
    }
     if (buf == 'A') {
      for (int j = 0; j < 60; j++)
      {
        strip.setPixelColor(j, rvb[0], rvb[1], rvb[2]);
      }
      strip.show();
    }
    if (buf == 'E') {
      for (int j = 0; j < 60; j++)
      {
        strip.setPixelColor(j, 0, 0, 0);
      }
      strip.show();
    }
  }
}


void calibrage()
{
  Serial.println("Debut du calibrage !!");
  CS.nSamples(40);
  CS.setRefreshTime(200);
  CS.setFrequency(TCS3200_FREQ_HI);
  CS.calibration(0);
}

void cali() {
  bool done = false;
  delay(75);
  Serial.println("Voulez vous calibrer le capteur : ");
   Serial.println("Oui pour commencer, OK pour quitter");
  while (!done)
  {
    if (Serial.available())
    {
      r = Serial.read();
      delay(10);

      if (r == 'O')
      {
        CS.loadCal(0);
        done = true;
      }
      if (r == 'Y')
      {
        calibrage();
        done = true;
      }
      Serial.flush();
    }
  }
  mode = IMMERSION;
}

void guireland()
{
  getMode();
  while(Serial.available()>0)
  {
    char trash = Serial.read();
  }
  Serial.println(buf);
  if(!(buf == 'E')){
  for (int j = 0; j < 60; j++)
  {
    strip.setPixelColor(j, random(j), random(j + 1), random(j + 2));
  }
  strip.show();
  delay(400);
  }
}

void fondu()
{
  getMode();
  strip.setBrightness(rvb[3]);
  increaseGreen();
  increaseBlue();
  decreaseGreen();
  increaseRed();
  decreaseBlue();
  increaseGreen();
  increaseBlue();
  decreaseBlue();
  decreaseGreen();
  decreaseRed();
  delay(25); 
}

void immersion_3_5_D()
{
  getMode();
  
  current = CS.readRGB();
  CS.getRGB(&current);

  for (int j = 0; j < 60; j++)
  {
    strip.setPixelColor(j, current.value[0], current.value[1], current.value[2]);
  }
  strip.show();


}
////////////////////////////////Pour la communication Bluetooth bande LED///////////////////////
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

int coef(int n) {
  int co = 0;
  switch (n) {
    case 0 :
      co = 1;
      break;
    case 1 :
      co = 10;
      break;
    case 2 :
      co = 100;
      break;
  }
  return co;
}

void choisir_Theme()
{
  while (Serial.available() && mode == THEME)
  {
    getMode();

    if (buf == 'r') {
      i = 0;
      n = 2;
      rvb[i] = 0;
    }

    if (buf == 'v') {
      i = 1;
      n = 2;
      rvb[i] = 0;
    }
    if (buf == 'b') {
      i = 2;
      n = 2;
      rvb[i] = 0;
    }
    if (buf == 'l') {
      i = 3;
      n = 2;
    }
    if (buf == ';') {
      rvb[i] = rvb[i] / coef(n + 1);
      for (int j = 0; j < 60; j++)
      {
        strip.setPixelColor(j, rvb[0], rvb[1], rvb[2]);
      }
      strip.setBrightness(rvb[3]);
      strip.show();
      delay(20);
      Serial.flush();
    }
    if (charToInt(buf) != -1) {
      b = charToInt(buf);
      rvb[i] += b * coef(n);
      n--;
    }
  }
}

void onOff() {
  if (Serial.available() && mode == OFF)
  {
    buf = (char)Serial.read();
     Serial.println(buf);
    if (buf == 'A') {
      for (int j = 0; j < 60; j++)
      {
        strip.setPixelColor(j, 255, 255, 255);
      }
      strip.show();
    }
    if (buf == 'E') {
      for (int j = 0; j < 60; j++)
      {
        strip.setPixelColor(j, 0, 0, 0);
      }
      strip.show();
      mode = OFF;
    }
    if (buf == 'T') {
      mode = THEME;
    }
    if (buf == 'C') {
      mode = CALIBRAGE;
    }
    if (buf == 'I') {
      mode = IMMERSION;
    }
    if (buf == 'G') {
      mode = GUIRELAND;
    }
    if (buf == 'F'){
      mode = FONDU;
    }
  }
}

/////////////////////////////SETUP//////////////////////////////



void setup()
{
  Serial.begin(9600);
  CS.begin();
  CS.LEDON(false);
  strip.begin();
  randomSeed(1996);

}

void loop()
{
  if (mode == OFF)
    onOff();
  if (mode == CALIBRAGE)
    cali();
  if (mode == IMMERSION)
    immersion_3_5_D();
  if (mode == THEME)
    choisir_Theme();
  if (mode == GUIRELAND)
    guireland();
  if (mode == FONDU)
    fondu();

}
