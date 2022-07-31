# FE0222B-BE_Progetto_Settimana8
Questo è un semplice progetto per prendere dimestichezza con le tecnologie REST fornite da JBOSS in ausilio dell'application server Wildfly.
Alcuni metodi di questo progetto sono stati testati utilizzando il framework di testing Junit.
Alcune scelte sono state prese per puro scopo didattico.

# Contenuti presenti
- [Quadro generale](#Quadro-generale)
- [Funzionalità principali](#Funzionalità-principali)
- [Utilizzo API](#Utilizzo-API)
- [Risorse Aggiuntive](#Risorse-Aggiuntive)

## Quadro generale
L'applicazione espone metodi REST all'utente per la gestione di conti corrente

## Funzionalità principali
- Creazione di un conto corrente
- Modifica di un conto corrente
- Cancellazione di un conto corrente
- Visualizzazione di tutti i conti corrente
- Visualizzazione di un conto corrente attraverso l'iban
- Prelievi
- Versamenti
- Lista movimenti

Ciascuna di queste chiamate, a puro scopo didattico, non rende persistenti i dati su alcun supporto ne locale ne remoto, da ciò consegue che i dati saranno "resettati" ad ogni avvio/riavvio del server Wildfly

## Utilizzo API
### Metodi GET
- getByIban   /rest/ewallet/getbyiban?iban=IT000111222333444
- getAllContiCorrente   /rest/ewallet/getall
- listaMovimentiByIban    /rest/ewallet/listamovimenti?iban=IT000111222333444

### Metodi POST
- createContoCorrente   /rest/ewallet/create
```
//request body
{
    "iban": "IT000111222333444",
    "saldo": "525.50",
    "intestatario": "Mario Rossi"
}
```
- prelievo   /rest/ewallet/prelievo
```
//request body
{
    "iban": "IT000111222333444",
    "importo": 10
}
```
- versamento    /rest/ewallet/versamento
```
//request body
{
    "iban": "IT000111222333444",
    "importo": 10
}
```

### Metodi DELETE
- deleteContoCorrente   /rest/ewallet/delete?iban=IT000111222333444

### Metodi PUT
- updateContoCorrente   /rest/ewallet/update
 ```
 {
     "intestatario": "Mario Vitale"
 }
 ```
 
 ## Risorse Aggiuntive
 E' fornito il file di esportazione di Postman contentente già la configurazione corretta dei metodi per poterli testare
