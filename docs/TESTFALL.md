#TESTFALL
###Hämta work items med en viss status

#####Setup innan tester
Skapa 3 Workitems som har status UNSTARTED, STARTED och DONE

1. Starta program
2. Öppna Postman
3. Skriv in http://localhost:8080/workitems url input fältet i postman
4. Ange headers: key = Content-Type value =application/json
5.  Skriv in dessa JSON-Objekt nedanför  var försig i bodyn  och välj POST och sen Send

```javascript
{
"description": "Göra projekt del 2",
	"workItemStatus": "unstarted"
}
{
	"description": "Göra saker",
	"workItemStatus": "started"
}
{
	"description": "Göra projekt del 1",
	"workItemStatus": "done"
}
```
#Testfall 1  
Hämta work items med existerande status med små bokstäver

1. Skriv in http://localhost:8080/workitems?status=unstarted  url input fältet i postman
2. Välj GET och sen Send

3.Test data:
status = unstarted

Expected results:
Lista av work items med status UNSTARTED

Actual results:
Som förväntat

Pass/Fail:
Pass

#Testfall 2 
Hämta work items med existerande status med stora bokstäver

1. Skriv in http://localhost:8080/workitems?status=STARTED  url input fältet i postman
2. Välj GET och sen Send
3. Test data: status = STARTED

Expected results:
Lista av work items med status STARTED

Actual results:
Som förväntat

Pass/Fail:
Pass

#Testfall 3
Hämta work items med icke existerande status typ

1. Skriv in http://localhost:8080/workitems?status=test  url input fältet i postman
2. Välj GET och sen Send
3. Test data: status = test

Expected results:
Tom lista av work items

Actual results:
Som förväntat

Pass/Fail:
Pass

#Testfall 4 
Hämta work items utan statusparameter

1. Skriv in http://localhost:8080/workitems url input fältet i postman
2. Välj GET och sen Send
3.Test data: null

Expected results:
Lista av alla work items oberoende status typ

Actual results:
Som förväntat

Pass/Fail:
Pass

#TESTFALL
###Skapa issue till en WorkItem

####Setup innan tester
Skapa ett Issue till en WorkItem som är DONE 

1. Starta program
2. Öppna Postman
3. Skriv in http://localhost:8080/issues url input fältet i postman
4. Ange headers: key = Content-Type value =application/json
5. Skriv in dessa JSON-Objekt nedanför försig i bodyn  och välj POST och sen Send
```javascript
{
"description": "ett projekt",
		"workItemStatus": "Done"
}
{
"description": "ett projekt",
		"workItemStatus": "UNSTARTED"
}
{
"description": "ett projekt",
		"workItemStatus": "STARTED"
}
```
6. Noterna workitemId som returneras. Det kommer användas i nedan i testerna

#Testfall 1
###Skapa ett Issue till en WorkItem som är DONE 

1. Starta Postman 
2. Skriv in http://localhost:8080/issues  url input fältet
3. Mata in issue JSON- objeket  i bodyn:
```javascript
{
"description": “Exempel”
    "workItem": {
        "id": {{workitemId}}  // Workitemet med DONE
    }
}
```
- Skicka POST request 

Test data: 
description: “Exempel”  id= se setup (workitem med status DONE)

Expected results: 
Att Issue har lagts till i WorkItem som angivits samt dess descriptions

Actual results:
Som förväntat

 Pass/ Fail: 
Pass

#Testfall 2
###Skapa ett Issue till en WorkItem som är UNSTARTED 

1. Starta Postman 
1. Skriv in http://localhost:8080/issue url input fältet
3. Mata in issue JSON- objeket  i bodyn:
```javascript
 {
"description": “Exempel”
"workItem": {
"id": {{workitemId}} // Workitemet med UNSTARTED
}
}
```
- Skicka POST request 

Test data:  description: “Exempel”  id= se setup (workitem med status UNSTARTED)
Expected results: 
Att vi får ett error meddelande som säger att “You can't create an issue if the workitem is unstarted or just started”

Actual results:
Som förväntat

 Pass/ Fail: 
Pass

#Testfall 3
###Skapa ett Issue till en WorkItem som är STARTED 
1. Starta Postman
2. Skriv in http://localhost:8080/issue url input fältet
3. Mata in issue JSON- objeket  i bodyn:
```javascript
 {
"description": “Exempel”
"workItem": {
"id": {{workitemId}} // Workitemet med STARTED
}
}
```
- Skicka POST request 

Test data: description: “Exempel”  id= se setup (workitem med status STARTED)

Expected results: 
Att vi får ett error meddelande som säger att “You can't create an issue if the workitem is unstarted or just started”

Actual results:
Som förväntat

 Pass/ Fail: 
Pass



#Testfall 
###Skapa User

1. Öppna Postman
2.  Click POST
3. Type  http://127.0.0.1:8080/users
4. Click raw, JSON
Body
 ```javascript
 {
"firstName":"XXX",
"lastName":"XXX",
"username":"XXXXXXXXXX"
}
 ```
- Click SEND

Test data:
firstName = “XXX ” lastName = “XXX” username = “XXXXXXXXXX”

Expected results:
User är skapad

Actual results: 
Som förväntat

 PASS/FAIL:
Pass

#Testfall 



#####Setup innan tester
Skapa en user 

1. Starta Postman
2. Klicka POST
3. Mata in:
 ```javascript
{
"firstName":"XXX",
"lastName":"XXX",
"username":"XXXXXXXXXX"
}
 ```
- Username kräver minst 10 tecken.
Observera i Url som kommer tillbaka vilket userNumber den nya usern fått(ej id)


###Hämta user med visst usernumber
1. Starta Postman
2. Klicka GET
3. Ange localhost:8080/users/{{userNumber}} // för existerande user
4. Klicka på SEND
5. Test data:  {{usernumber}} = Ange usernumber som slumpats fram vid skapandet av user( se setup)

Expected results:
Usern med  angivet usernumber returneras

Actual Results
Som förväntat

Pass/Fail
Pass



#Testfall 
#####Hämta user med ett obefintligt usernumber

1. Starta Postman
2. Klicka GET
3. Ange localhost:8080/users/{{userNumber}} // Som inte finns
4. Klicka på SEND
5. Test data:  {{userNumber}} = icke existerande userNUmber

Expected results:
Systemet svarar “user not found”

 Actual results:
Som förväntat

 Pass/Faill
Pass
 

#Testfall 
#####Setup innan tester

Förberedande:
OBS: Notera att måsvingar { } inte ska skrivas ut, utan visar bara när annat data ska in. 



1. Öppna Postman och sätt begäran till POST.
2. Skapa ett Team med name och status via "http://127.0.0.1:8080/teams”.
3. Skapa tre workItems där var och ett har description och workItemStatus via "http://127.0.0.1:8080/workitems”.
4. Skapa tre Users med firstName, lastName, username och status via "http://127.0.0.1:8080/users”.
5. Sätt varje User individuellt i Teamet genom att sätta Postmans begäran till PUT och skicka via “http://127.0.0.1:8080/teams/{teamID}/{userNumber}” i adressfältet. Notera att userNumber efterfrågas, alltså inte ID. 


Data:
a. För Team
* name = "America"
* status = "true"
 ```javascript
{
    "name": "America",
    "status": "true"
}
 ```

b. För WorkItems
* description = "Foobar1" / "Foobar2" / "Foobar3"
* workItemStatus = "STARTED"
 ```javascript
{
	"description": "Foobar",
	"workItemStatus": "STARTED"
}
 ```

c. För Users
* firstName = "Donald"
* lastName = "Trump1" / “Trump2” / “Trump3”
* username = "ih8mueller"
* status = "true"


 ```javascript
{
    "firstName": "Donald",
    "lastName": "Trump",
    "username": "ih8mueller",
    "status": true
}

 ```

* Varje test som följer här innefattar att skifta vilken User som äger vilket workItem. 
* För att tilldela ett workItem till en User, sätt begäran på Postman till PUT och skicka via: "http://127.0.0.1:8080/workitems/{workItemID}?user={userNumber}” med en tom body.
* Samma Team används för alla tester. 




#Testfall 1 
 ####Hämta 3 workitems tillhörandes 1 User i ett Team.

Steg:
1. Tilldela alla tre workItems till Trump1.  
2. När alla tre workItems är tilldelade, sätt begäran på Postman till GET och skicka via: "http://127.0.0.1:8080/teams/{TeamID}/workitems"

Förväntat resultat:
Tre WorkItems ska skickas till klienten.

Faktiskt resultat:
Nio WorkItems skickades till klienten.

Pass/fail:
Fail



#Testfall 2
 ####Hämta 3 workitems tillhörandes 2 Users i ett Team.

Steg:
1. Tilldela ett workItem till Trump1.
2. Tilldela de resterande två workItems till Trump2. 
3. När alla tre workItems är tilldelade, sätt begäran på Postman till GET och skicka via: "http://127.0.0.1:8080/teams/{TeamID}/workitems"

Förväntat resultat:
Tre workItems ska skickas till klienten.

Faktiskt resultat:
Fem workItems skickades till klienten.

Pass/fail:
Fail


#Testfall 
### Hämta 3 workitems tillhörandes 3 Users i ett Team.

Steg:
1. Tilldela ett workItem till Trump1.
2. Tilldela ett annat workItem till Trump2.
3. Tilldela det sista workItem till Trump3. 
4. När alla 3 workitems är tilldelade (en till varje), sätt begäran på Postman till GET och skicka via: "http://127.0.0.1:8080/teams/{TeamID}/workitems"

Förväntat resultat:
Tre WorkItems ska skickas till klienten.

Faktiskt resultat:
Tre WorkItems skickades till klienten.

Pass/fail:
Pass



##Dokumentation:   	 

Just när man söker på alla workItems för ett team ("http://127.0.0.1:8080/teams/{TeamID}/workitems") eller söker upp ett specifikt team ("http://127.0.0.1:8080/teams/{TeamID}") så kommer kloner av Users att visas om dessa har fler än ett workItem tilldelat till sig. En User som har fler än ett workItem kommer synas i lika många upplagor som dess antal workItems. 

Det innebär att om en User har tre workItems tilldelade till sig så kommer tre upplagor av denna User synas där varje upplaga fortfarande har sina tre workItems (dvs nio workItems allt som allt). Ifall en User har två workItems så kommer två upplagor att synas där båda har kvar sina två workItems (då fyra workItems allt som allt).

Detta innebär alltså att inget fel kommer uppstå när en User bara har ett (eller inget) WorkItem.
