## Sujet – Exercice JDBC : Application de gestion bancaire

### Résultat attendu

Vous devez implémenter une application de gestion bancaire connectée à une base de données relationnelle via JDBC. 

Votre application doit permettre :

1. **Création d’un client et d’un compte bancaire associé**
   - Saisie du nom, prénom, téléphone du client.
   - Création automatique d’un compte lié.

2. **Dépôt d’argent sur un compte existant**
   - Saisie de l’ID du compte et du montant.
   - Ajout d’une opération de type "DÉPÔT" liée à ce compte.

3. **Retrait d’argent d’un compte existant**
   - Saisie de l’ID du compte et du montant.
   - Ajout d’une opération de type "RETRAIT".

4. **Affichage d’un compte**
   - Saisie de l’ID du compte.
   - Affichage des informations du compte, du client associé et des opérations effectuées.

---

### Entités :

#### `Customer`

* `int id`
* `String firstName`
* `String lastName`
* `String phone`

#### `BankAccount`

* `int id`
* `int customerId`
* `Customer customer`
* `List Operation`
* `Double totalAmount`

#### `Operation`

* `int id`
* `double amount`
* `OperationStatus status` *(enum: DEPOSIT, WITHDRAWAL)*
* `int accountId`