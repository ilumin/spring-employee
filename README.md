# Spring Employee API 

## Required SW
- JAVA8
- SDKMan
- Spring Cli (install by SDKMan)
- Example Data 

### Install JAVA8

### Install SDKMan

### Install Spring Cli

### Prepare Example Data 
URL: https://github.com/datacharmer/test_db  

## DB Structure
![DB Structure](https://dev.mysql.com/doc/employee/en/images/employees-schema.png)

## API Description

- API แสดงข้อมูลของ Employee 
- API[Employee] แสดงหลาย titles (ตำแหน่งงาน)
- API[Employee] ทำ CRUD ข้อมูล titles ผ่านทาง `/employees/{id}/titles`
- Department has many Employees
- Employee may has many Departments

## Create a New Project With Spring Cli
เราสามารถสร้างโปรเจคจาก UI ของ IntelliJ หรือจาก http://start.spring.io/ ก็ได้  
แต่นี่ไม่ใช่ยุคสมัยที่เราจะต้องมานั่งสร้างโปรเจคจาก UI หรือเว็บแล้ว เราต้องสร้างจาก cli  
สำหรับ Spring Boot Project เราสามารถสร้างโดยใช้ Spring Cli   
ซึ่งจริงๆแล้วมันจะยิง api ไปหา start.spring.io นั่นแหละ   

สำหรับโปรเจคนี้ เราจะใช้คำสั่ง

    $ spring init \
        --dependencies=web,data-jpa,mysql,lombok,actuator \
        --type=gradle-project \
      	--build=gradle \
      	--java-version=1.8 \
      	--groupId=com.ilumin.lab \
      	--packaging=war \
      	--package-name=com.ilumin.lab \
      	--artifactId=employee \
      	--version=0.0.1-LAB \
      	api-employee
      	
อธิบายคำสั่ง

- `spring init` เป็นคำสั่งที่ใช้สร้าง spring boot project
- `--dependencies=web,data-jpa,mysql,lombok,actuator` กำหนดให้ spring install plugin 
  `web` หรือ `spring-boot-starter-web` สำหรับทำ `Request` และ `Response`
  `data-jpa` หรือ `spring-boot-starter-data-jpa` สำหรับทำ `Object Relational Mapping`
  `mysql` หรือ `mysql-connector-java` สำหรับต่อกับ MySQL
  `lombok` สำหรับใช้ annotation ที่ทำให้ชีวิตง่ายๆ เช่น `@Data`, `@Getter`, `@Setter` 
  `actuator` หรือ `spring-boot-starter-actuator` สำหรับใช้ทำ health check
- `--type=gradle-project` และ `--build=gradle` กำหนดให้ build project ด้วย gradle
- `--java-version=1.8` ที่กำหนดให้ใช้ JAVA8 ในการ compile
- `--groupId=com.ilumin.lab` กำหนด group id ของ JAVA project
- `--packaging=war` กำหนดให้ build package เป็นไฟล์นามสกุล war
- `--package-name=com.ilumin.lab` กำหนด default package
- `--artifactId=employee` กำหนดชื่อ Project
- `--version=0.0.1-LAB` กำหนด version ของ Project 
- `api-employee` ตัวสุดท้าย กำหนดให้สร้าง Project ใน folder ชื่อ `api-employee` 
  ซึ่งถ้าไม่ได้กำหนดไว้ มันจะสร้างเป็น zip file เหมือนตอนที่สร้างใน start.spring.io
  
## NOTE

- ใช้ OneToMany สำหรับการดึง Employee's title 
  ให้ per page = 5
  ผลลัพท์
  ทำ query 7 ครั้ง คือ 1. CHECK EMPTY employee 1 ครั้ง 2. SELECT LIMIT employee 1 ครั้ง 3. SELECT title จาก employee 5 ครั้ง
  ถ้าเขียนปกติ ก็คงเขียนวนลูปแบบนี้
  ถ้าเขียนเทพๆหน่อย ก็คงใช้ SELECT title IN employee 1 ครั้ง แล้วลูปเก็บ resource