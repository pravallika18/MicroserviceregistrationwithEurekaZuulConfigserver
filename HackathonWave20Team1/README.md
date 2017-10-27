# HackathonWave20Team1

clone the project using https://github.com/pravallika18/HackathonWave20Team1

install mysql in your system and add postman to your chrome.

download Spring tool suite.

import this application into Spring tool suite .

open properties file(src/main/resource) and change mysql credentials. 

right click on project and select run as spring boot application oprtion.it will create a table as a user in database.

finally do operations through postman.


How to do postman oprations .

to create a user profile fallow the steps 

  1.select post and give url as "/v1.0/UserProfile/user"(find it in controller class)

  2.select "body" in second row,select "raw" in third row in the same row change text to JSON in drop down.
 
  3.give data in body example like '{ "id":"1","name":"inhiyaz","email":"insk@gmail"}'.
  
  4.click on send.
  
to gteAll>> change post to get method

to update>> change get to put                * Parlally check in mysql databse. 

to delete>> change put to delete.
   
 
