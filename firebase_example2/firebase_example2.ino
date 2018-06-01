//
// Copyright 2015 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

// FirebaseDemo_ESP8266 is a sample that demo the different functions
// of the FirebaseArduino API.

#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>
#include <Wire.h>
#include <BH1750.h>
#include <SHT1x.h>

// Cấu hình.
#define led D0
#define fan D5
#define dataPin D1
#define clockPin D2

#define FIREBASE_HOST "arduinodemo-c1c05.firebaseio.com"  
#define FIREBASE_AUTH ""
#define WIFI_SSID "Wifi-free"
#define WIFI_PASSWORD "88888880"

BH1750 lightMeter;
SHT1x sht1x(dataPin, clockPin);

void setup() {
  Serial.begin(115200);
  
  // Két nối wifi.
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());

  pinMode(led, OUTPUT);
  pinMode(fan, OUTPUT);
  Wire.begin(D4, D3);
  
  lightMeter.begin();
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
}

int n = 0;


void loop() {
  uint16_t lux = lightMeter.readLightLevel();
  float temp_c;
  float humidity;
  String ledstate,fanstate;

  //read values from sht10
  temp_c = sht1x.readTemperatureC();
  humidity = sht1x.readHumidity();
  
  
  /*// Set giá trị lưu vào db
  Firebase.setString("led", state);
  // Kiểm tra lỗi
  if (Firebase.failed()) {
      Serial.print("setting /number failed:");
      Serial.println(Firebase.error());  
      return;
  }
  delay(500);*/
  
  // Cập nhật giá trị
  /*Firebase.setString("led", ledstate);
  // Kiểm tra lỗi
  if (Firebase.failed()) {
      Serial.print("setting /number failed:");
      Serial.println(Firebase.error());  
      return;
  }
  delay(100);

  Firebase.setString("led", fanstate);
  // Kiểm tra lỗi
  if (Firebase.failed()) {
      Serial.print("setting /number failed:");
      Serial.println(Firebase.error());  
      return;
  }
  delay(100);*/

  Firebase.setInt("light", lux);
  // Kiểm tra lỗi
  if (Firebase.failed()) {
      Serial.print("setting /number failed:");
      Serial.println(Firebase.error());  
      return;
  }
  delay(100);

  Firebase.setFloat("humidity", humidity);
  // Kiểm tra lỗi
  if (Firebase.failed()) {
      Serial.print("setting /number failed:");
      Serial.println(Firebase.error());  
      return;
  }
  delay(100);

  Firebase.setFloat("temperature", temp_c);
  // Kiểm tra lỗi
  if (Firebase.failed()) {
      Serial.print("setting /number failed:");
      Serial.println(Firebase.error());  
      return;
  }
  delay(100);

  // Lấy giá trị
  Serial.print("led: ");
  ledstate = Firebase.getString("led");
  Serial.println(ledstate);
  digitalWrite(led, ledstate.toInt());
  Serial.print("fan: ");
  fanstate = Firebase.getString("fan");
  Serial.println(fanstate);
  digitalWrite(fan, fanstate.toInt());

  Serial.print("light: ");
  Serial.println(Firebase.getInt("light"));
  Serial.print("temperature: ");
  Serial.println(Firebase.getFloat("temperature"));
  Serial.print("humidity: ");
  Serial.println(Firebase.getFloat("humidity"));
  //delay(1000);
  

  /*// Xóa giá trị
  Firebase.remove("light");
  delay(1000);*/

  /*// Thêm chuỗi
  Firebase.setString("message", "hello world");
  // Kiểm tra lỗi
  if (Firebase.failed()) {
      Serial.print("setting /message failed:");
      Serial.println(Firebase.error());  
      return;
  }
  delay(1000);*/
  
  /*// Thêm giá trị bool
  Firebase.setBool("truth", false);
  // Kiểm tra lỗi
  if (Firebase.failed()) {
      Serial.print("setting /truth failed:");
      Serial.println(Firebase.error());  
      return;
  }
  delay(1000);*/

  /*// Lưu log
  String name = Firebase.pushInt("logs", n++);
  // Kiểm tra lỗi
  if (Firebase.failed()) {
      Serial.print("pushing /logs failed:");
      Serial.println(Firebase.error());  
      return;
  }
  
  Serial.print("pushed: /logs/");
  Serial.println(name);
  //delay(1000);*/
}
