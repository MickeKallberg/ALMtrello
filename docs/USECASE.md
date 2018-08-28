#Krav
*Skapa en User
*Hämta en User baserat på UserId
*Hämta alla Workitem för status
*Skapa ett Issue och tilldela det till WorkItem 
*Hämta alla Workitem för team

#Icke-funktionella krav
* Systemet ska kunna hantera minst 3000 användare samtidigt
* Systemet ska fungera i klienten Postman
* Systemet ska ha en svarstid till användaren på 3 sek


#USE CASE 1
###Hämta work items med en viss status

|Scope: Postman | Agent: Användare av systemet |

Förutsättningar (preconditions):
- Det finns minst ett work item i systemet som har status UNSTARTED, STARTED eller DONE
Resulterande tillstånd (postconditions):
- En lista av work items som har en viss status visas(UNSTARTED, STARTED eller DONE)
Trigger
- Användaren skickar en GET request för hämta work items med en viss status

Flöde: 
1. Användaren skickar en GET request för work items tillsammans med en statusparameter och ett sökord som motsvarar existerande status i systemet.

2. Användaren får tillbaka en lista av work items som JSON-objekt. Listan av work 
items innehåller endast statusar motsvarande det som användes som sökord

Alternativa flöden(Extensions):

1. Användaren skickar en GET request för work items utan en statusparamter
2. Användaren får tillbaka en lista av work items som JSON-objekt. Listan av work items består av alla som finns i systemet, oberoende status.

Felflöden:
1. Användaren skickar en GET request för work items tillsammans med en statusparameter och ett sökord som inte motsvarar existerande status i systemet.
2. Användare får tillbaka en tom lista.

#USE CASE 2
###Skapa en issue och lägg till i en work item

|Scope: Postman | Agent: Användare av systemet |

Förutsättningar (preconditions):
- Att tabellerna workitem och issue finns i databasen. Att det finns ett WorkItem med status DONE
 Resulterande tillstånd (postconditions) - 
- Att ett issue läggs till i en WorkItem och  Workitemet får status UNSTARTED

Trigger
- Att användare skickar POST request i Postman. 

Flöde:  
1. Användaren skapar ett Issue med ett Workitem id.
2. Användaren får tillbaka den nyskapade issuens url.

Alternativa flöden(Extensions)  

1. Om användaren försöker lägga till issue till work item  med status UNSTARTED  eller STARTED kommer inget issue skapas.
2. Om användaren inte inkluderar workItemid i issue JSON-objeket blir de error.

#USE CASE 3
###Skapa user

|Scope: Postman | Agent: Användare av systemet |

Förutsättningar (preconditions): 
- Inga
 
Resulterande tillstånd (postconditions):
- User är skapad i systemet och tillhör antingen ett existerande team eller inte.

Trigger: 

- Användaren gör en POST User i Postman.

Flöde :
1. Öppna Postman.
2. Välj POST fältet och skriv in http://127.0.0.1:8080/users
3. Välj fälten Body, ’raw’ och ’JSON(application/json)’ och skriver i det undre fältet firstName, lastName, username (som bör vara minst 10 tecken) inom klammerparenteser.
4. En ny User är skapad.

Felflöden:

1. Om användaren väljer fält Text istället för JSON, så visar Postman status: 415: Unsupported Media Type.
2. Om användaren skriver en username med färre tecken än 10 så visar Postman status: 400 Bad request och i Body nedanför visar den
```javascript
{
	"error": "username must be 10 characters or more"
}
```
3. Om användaren skriver ett team ’id’ som inte finns i databasen så visar Postman status: 500 Internal Server Error.
4. Om användaren inte skriver någon text i Body fältet och trycker på SEND, så visar Postman status: 500 Internal Server Error.


#USE CASE 4
 ###Hämta en user baserat på usernumber

|Scope: Postman | Agent: Användare av systemet |

 Förutsättningar (preconditions)  :
 
- Att det finns objekt i tabbellen “user” .
- Att användaren har ett usernumber att söka efter.

Trigger

-Att användaren klickar GET i Postman

Flöde(Basic Flow):
1. Användaren uppger ett usernumber för en existerande användare.
2. Användaren får tillbaka den användare som matchar “usernumber”.

Alternativt flöde:
1. Om användaren angett ett usernumber som inte är befintligt återfås ett meddelande om att user/användare inte är funnen.


#USE CASE 5

|Scope: Postman | Agent: Användare av systemet |

 Förutsättningar: 
 - Det finns minst ett existerande team.
 
  Resulterande tillstånd:
   Samtliga workitems från varje medlem i teamet skickas till klienten som JSON-objekt.
   
   Flöde: 
1. Användaren skriver "http://127.0.0.1:8080/teams/[id för specifikt team]/workitems" i adressfältet i Postman. 
2. Användaren skickar begäran som GET. 
3. Systemet kontrollerar att teamet existerar. 
4. Systemet skapar en lista med alla workitems från varje medlem som ingår i teamet. 
5. Listan skickas till klienten.

Alternativa flöden:
 4-5. a. Tom lista. 
 1. Systemet hittar inga workitems i teamet. 
 2. Systemet skapar en tom lista som inte fylls. 
 3. Den tomma listan skickas till klienten.
 
 Felflöden:
  1-3. a. Felaktigt team. 
  1. Användaren försöker hämta workitems för ett ogiltigt team. 
  2. Systemet hittar inte teamet. 
  3. Systemet skickar ett errormeddelande till klienten med statuskod "404 Not Found".
  
   b. Felaktig begäran.
1. Användaren skickar begäran från Postman via något annat än GET (exempelvis PUT, POST, DELETE). 
2. Klienten får statuskod "405 Method Not Allowed".

 4-5. a. Listan innehåller dubletter. 
 1. Teamet råkar innehålla kloner av en eller flera medlemmar. 
 2. Samtliga workitems, inklusive de från respektive klon, sparas i listan. 
 3. Listan skickas till klienten med statuskod "200 OK". 
 - Förberedande: 
 * Öppna Postman, skriv "http://127.0.0.1:8080" i adressfältet och sätt begäran till POST. 
 
 * Skapa 1 Team med name och status och tryck Send. 
 
 * Skapa 3 Users med firstName, lastName, username, userNumber, status och team och tryck Send.
