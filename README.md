# Capstone Backend

### Deployed here: https://springboot-postgres-test.herokuapp.com/

Please replace all endpoint address with the above URL.


##### IMPORTANT: Heroku will go to 'sleep' after 30 minutes of inactivity, first API request to wake will take roughly 20 seconds.

---
---

### [GET] Connection Test & Wake Heroku

#### Endpoint
```
https://springboot-postgres-test.herokuapp.com/test
```

#### Input
```
GET REQUEST
```

#### Response

**200** - Test Success (Backend is active)

```
{
    "success": true,
    "message": "This is a test endpoint",
    "payload": "Test Success!"
}
```
**Other Code** - Backend is offline

---

### [POST] User Log-In

If successful, frontend will receive a JWT that MUST be used with all other endpoints.

#### Endpoint
```
https://springboot-postgres-test.herokuapp.com/authenticate
```

#### Input
```
{
    "username": "",
    "password": ""
}
```

#### Example Response

**200** - Successful authentication

```
{
    "success": true,
    "message": "Token generated!",
    "payload": [GENERATED JWT]
}
```
**401** - Authentication failed

```
{
    "success": false,
    "message": "The username and password provided are incorrect!",
    "payload": null
}
```
---
---