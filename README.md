# Temp Capstone Backend

## Spring Boot backend currently deployed here:

### https://springboot-postgres-test.herokuapp.com/


### Test Endpoint

##### IMPORTANT: Heroku will go to 'sleep' after 30 minutes of inactivity, first API request to wake will take roughly 20 seconds.

#### Endpoint
```
https://springboot-postgres-test.herokuapp.com/test
```

#### Input
```
GET REQUEST
```

#### Example Response

**200** - Test Success (Backend is active)

```
{
    "success": true,
    "message": "This is a test endpoint",
    "payload": "Test Success!"
}
```
**Other Code** - Backend is offline