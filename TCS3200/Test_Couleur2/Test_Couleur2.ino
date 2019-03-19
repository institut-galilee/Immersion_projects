#include <TimerOne.h>

#define S0 6
#define S1 5
#define S2 4
#define S3 3
#define OUT 2
#define LED 13


int g_count = 0;
int g_array[3];
int g_flag = 0;
float g_SF[3];
int red = 0;
int grn = 0;
int blu = 0;
String color = "";
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
  Timer1.setPeriod(1000000);
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
  if (red > 225 && red < 260 && grn > 225 && grn < 260 && blu > 225 && blu < 260) color = "WHITE";
  else if (red >= 0 && red <= 15 && grn >= 0 && grn <= 15 && blu >= 0 && blu <= 15) color = "BLACK";
  else if (red > 190 && red < 260 && grn >= 35 && grn <= 45 && blu >= 45 && blu <= 55) color = "RED";
  else if (red >=75 && red <= 85 && grn >= 160 && grn < 260 && blu >= 60 && blu < 70) color = "GREEN";
  else if (red >= 20 && red <= 30 && grn >=75 && grn <= 85 && blu >= 180 && blu <= 260) color = "BLUE";
  else if (red >= 240 && red <= 255 && grn >= 195 && grn <= 210 && blu >=95 && blu <= 105) color = "YELLOW";
  else if (red >= 85 && red <= 95 && grn >= 220 && grn <= 230 && blu >=120 && blu <= 230) color = "CYAN";
  else if (red >= 195 && red <= 205 && grn >= 105 && grn <= 115 && blu >=215 && blu <= 230) color = "MAGENTA";
  else if (red >= 205 && red <= 215 && grn >= 70 && grn <= 85 && blu >=65 && blu <= 80) color = "ORANGE";
  else if (red >= 40 && red <= 50 && grn >= 25 && grn <= 35 && blu >=45 && blu <= 55) color = "PURPLE";
  else if (red >= 20 && red <= 35 && grn >= 45 && grn <= 55 && blu >=45 && blu <= 55) color = "TURQUOISE";

  else  color = "NO_COLOR";
  Serial.println(color);
  delay(4000);
}
