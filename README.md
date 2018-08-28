## ProjectTask-application

**Källkoden går att fina på https://github.com/elinafurst/Project_Task**

ProjectTask-application är en applikation som gör det möjligt att hantera olika ärenden för olika användare samt att lista olika ärenden med olika status.


Komponenterna som används i projektet:

* **Spring Boot baserad applikation skriven i Java**  
* Jersey för Rest
* mysql och h2 för db-hantering


### Förberedelser

För att kunna köra applikationen, behövs:
* Java 8
* postman 6.0.0
*IDE

### H2
 In-memory datatabas som används för att underlätta testning och utveckling. Spring Boot applikationen skapar upp databasen vid uppstart och slänger den så fort applikationen avslutas.
 
 
 ### mysql
 * MySQL är en databashanterare. Den använder sig av frågespråket SQL. 
 - Går att korrigera till denna DB under "application.properties". OBS! Används inte som standard i denna version av projektet.

### Postman
*Postman är en REST-klient för att göra REST-anrop.

### Observera...
Att databasen töms varje gång applikationen startas om

##### Starta aplikationen med jar-filen.
Ställ dig i mappen där jar.-filen är placerade i en terminal.
För att köra filen i windows ange korrekt mappstruktur och skriv :
```bash
java -jar *mappstruktur* ProjectTask-0.0.1-SNAPSHOT.jar
```

##### Verifiera att applikationen är igång
Ett lätt sätt att kontrolleraatt allting fungerar korrekt är att knappa in "http://localhost:8080/users" i webbläsaren efter att någon form av inmatning i DB utförs.
Börja med att utföra följande:

1.  Användaren väljer POST fältet och skriver http://127.0.0.1:8080/users
2.    Användaren väljer Body fältet, väljer fälten ’raw’ och ’JSON(application/json)’ och skriver i det undre fältet firstName, lastName, username (som bör vara minst 10 tecken), och team (med ett befintligt team id) inom klammerparenteser t.ex. :
```javascript
{
	"firstName":"XXX",
	"lastName":"XXX",
	"username":"XXXXXXXXXX"
	
	}
```	

Om inmatningen av datan lyckas bör postman svara med "201 Created" och du kan mata in "http://127.0.0.1:8080/users" i webbläsaren och bör se användaren du skapade.

Outputen borde se ut något som liknar:
```javascript
[
{
id: 1,
firstName: "XXX",
lastName: "XXX",
username: "XXXXXXXXXX",
userNumber: 713,
status: false,
workitems: [ ]
}
]
 ```
 

 
##### Starta applikationen i utvecklingsmiljö
Öppna vald IDE och öppna projektet "Project_task".
Starta applikationen genom att köra "projectTaskApplication".
Mata in datan enligt instruktionerna i postman ovan (starta med jar-fil). 
Kontrollera att allt är igång och fungerar korrekt genom att mata in "http://localhost:8080/h2" i webbläsaren.
Säkerställ att du har rätt JDBC URL, som ska vara: jdbc:h2:mem:testdb
Standard är satt till username "sa" och inget password, korrigeras i "application.properties".
Logga in och klicka på "user" och sedan "Run".
Du bör få upp användaren du skapade.


Outputen borde se ut något som liknar:
```javascript

ID  FIRST_NAME  	LAST_NAME  	STATUS  	USER_NUMBER  	USERNAME  	TEAM_ID  
1	XXX  	         XXX	     FALSE	     713	        XXXXXXXXXX	  null

```

