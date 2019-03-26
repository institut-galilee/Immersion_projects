#include <Adafruit_NeoPixel.h>
#include <TimerOne.h>

#define S0 6
#define S1 5
#define S2 4
#define S3 3
#define OUT 2
#define LED 13
#define BLED 12


int g_count = 0;
int g_array[3];
int g_flag = 0;
float g_SF[3];
int red = 0;
int grn = 0;
int blu = 0;
String color = "";
Adafruit_NeoPixel strip = Adafruit_NeoPixel(60, BLED, NEO_GRB + NEO_KHZ800);



void TSC_Init()
{
  pinMode(S0, OUTPUT);
  pinMode(S1, OUTPUT);
  pinMode(S2, OUTPUT);
  pinMode(S3, OUTPUT);
  pinMode(OUT, INPUT);
  pinMode(LED, OUTPUT);
  digitalWrite(S0, LOW);
  digitalWrite(S1, HIGH);
  strip.begin();
  strip.show();
  
}

void TSC_FilterColor(int Level01, int Level02)
{
  if (Level01 != 0)
    Level01 = HIGH;
  if (Level02 != 0)
    Level02 = HIGH;
  digitalWrite(S2, Level01);
  digitalWrite(S3, Level02);
}

void TSC_Count()
{
  g_count ++;
}

void TSC_WB(int Level0, int Level1) // Balance des blancs
{
  g_count = 0;
  g_flag ++;
  TSC_FilterColor(Level0, Level1);
  Timer1.setPeriod(4000000);
}

void TSC_Callback()
{
  switch (g_flag)
  {
    case 0:
      Serial.println("->WB Start");
      TSC_WB(LOW, LOW); // Filtre sans rouge
      break;
    case 1:
      Serial.print("->Frequency R=");
      Serial.println(g_count);
      g_array[0] = g_count;
      TSC_WB(HIGH, HIGH);
      // Filtre sans vert
      break;
    case 2:
      Serial.print("->Frequency G=");
      Serial.println(g_count);
      g_array[1] = g_count;
      TSC_WB(LOW, HIGH); // Filtre sans bleu
      break;
    case 3:
      Serial.print("->Frequency B=");
      Serial.println(g_count);
      Serial.println("->WB End");
      g_array[2] = g_count;
      TSC_WB(HIGH, LOW); // Pas de filtre
      break;
    default:
      g_count = 0;
      break;

  }
}


void setup() {
  TSC_Init();
  Serial.begin(9600);
  Timer1.initialize();
  Timer1.attachInterrupt(TSC_Callback);
  attachInterrupt(0, TSC_Count, RISING);
  delay(4000);
  for (int i = 0; i < 3; i++)
    Serial.println(g_array[i]);
  g_SF[0] = 255.0 / g_array[0]; // valeur R
  g_SF[1] = 255.0 / g_array[1] ; // valeur G
  g_SF[2] = 255.0 / g_array[2] ; // valeur B
  Serial.println(g_SF[0]);
  Serial.println(g_SF[1]);
  Serial.println(g_SF[2]);
}


void loop() {
  
  g_flag = 0;
  for (int i = 0; i < 3; i++){
    if (i==0)
      red = g_array[i] * g_SF[i];
    if (i==1)
      grn = g_array[i] * g_SF[i];
    if (i==2)
      blu =  g_array[i] * g_SF[i];
    Serial.println(int(g_array[i] * g_SF[i]));
  }

  for (int j = 0; j<60;j++)
  {
    strip.setPixelColor(j,red,grn,blu);
  }
  strip.show();
  
  if (red > 225 && red < 260 && grn > 225 && grn < 260 && blu > 225 && blu < 260) color = "WHITE";
  else if (red >= 0 && red <= 15 && grn >= 0 && grn <= 15 && blu >= 0 && blu <= 15) color = "BLACK";
  else if (red > 160 && red < 260 && grn >= 0 && grn <= 45 && blu >= 0 && blu <= 55) color = "RED";
  else if (red >=0 && red <= 85 && grn >= 155 && grn < 260 && blu >= 0 && blu < 80) color = "GREEN";
  else if (red >= 0 && red <= 30 && grn >=0 && grn <= 85 && blu >= 145 && blu <= 260) color = "BLUE";
  else if (red >= 175 && red <= 255 && grn >= 175 && grn <= 250 && blu >=85 && blu <= 115) color = "YELLOW";
  else if (red >= 75 && red <= 105 && grn >= 200 && grn <= 230 && blu >=120 && blu <= 230) color = "CYAN";
  else if (red >= 160 && red <= 215 && grn >= 80 && grn <= 115 && blu >=175 && blu <= 230) color = "MAGENTA";
  else if (red >= 170 && red <= 215 && grn >= 50 && grn <= 95 && blu >=40 && blu <= 80) color = "ORANGE";
  else if (red >= 40 && red <= 60 && grn >= 25 && grn <= 55 && blu >=40 && blu <= 60) color = "PURPLE";
  else if (red >= 20 && red <= 35 && grn >= 45 && grn <= 60 && blu >=35 && blu <= 60) color = "TURQUOISE";

  else  color = "NO_COLOR";
  Serial.println(color);
  delay(4000);
}
