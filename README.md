# TP API REST:
Faire une API REST de gestion de contact

**Fonctionnalités :**
- Récupérer tous les contacts ✅
- Récupérer un contact par son email ou numéro de téléphone ✅
- Ajouter un contact ✅
- Supprimer un contact (par ID) ✅
- Rechercher un ou plusieurs contact par le nom et prénom ✅

:warning:  RESPECTER LES STATUS HTTP :warning:

```
### Créer un contact

POST http://localhost:8080/api/contact
Content-Type: application/json

{
"firstname": "Ludovic",
"lastname": "Anselin",
"phoneNumber": "0670673885",
"email": "ludovicanselin@gmail.com"
}

### Obtenir tous les contacts

GET http://localhost:8080/api/contacts

### Rechercher un contact par nom et prénom

GET http://localhost:8080/api/contacts/search?lastname=anselin&firstname=ludovic

### Rechercher un contact par numéro de téléphone

GET http://localhost:8080/api/contact?phone-number=0670673885

### Rechercher un contact par adresse e-mail

GET http://localhost:8080/api/contact?email=ludovicanselin@gmail.com

### Supprimer un contact

DELETE http://localhost:8080/api/contact/1
```
