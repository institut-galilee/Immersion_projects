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
  else if (red >= 0 && red <= 10 && grn >= 0 && grn <= 10 && blu >= 0 && blu <= 10) color = "BLACK";
  else if (red > 160 && red < 260 && grn >= 0 && grn <= 50 && blu >= 0 && blu <= 50) color = "RED";
  else if (red >=0 && red <= 70 && grn >= 142 && grn < 260 && blu >= 0 && blu < 60) color = "GREEN";
  else if (red >= 0 && red <= 20 && grn >=0 && grn <= 70 && blu >= 150 && blu <= 260) color = "BLUE";
  else if (red >= 220 && red <= 260 && grn >= 170 && grn <= 260 && blu >=0 && blu <= 100) color = "YELLOW";
  else if (red >= 0 && red <= 80 && grn >= 170 && grn <= 260 && blu >=180 && blu <= 260) color = "CYAN";
  else if (red >= 170 && red <= 260 && grn >= 0 && grn <= 100 && blu >=180 && blu <= 260) color = "MAGENTA";
  else  color = "NO_COLOR";
  Serial.println(color);
  delay(4000);
}
