# ProductService
## Technologies
- SpringBoot 3.5.5
- Java 21
- Maven
- Docker
- Postgres
## Setup instructions
From the root directory:
```bash
    docker compose build;
    docker compose up -d;
```
Service is running on http://localhost:8080/.
## Seeded data
There is one seeded User with the role Admin, its credentials are:
- email: admin@gmail.com
- password: 123456789

Admins cart contains all the available products.

## Usage
Swagger is available for testing service endpoints. To access it go to: http://localhost:8080/swagger-ui.html
Authenticate by logging in or registering before accessing any of the other endpoints.
Both /login and /register endpoints return a JWT if they succeed.
Returned JWT needs to be pasted into the Authorize dialogs value field of the Swagger UI, for other endpoints to use.

## Architecture
Implemented layered architecture in the service, using Spring's DI engine to achieve loose coupling between layers (components).
Experimented with designing immutable DTOs using Java records, which made me use composition instead of inheritance.
## Challenges
Had problems with StackOverflowError when adding items to users cart because of bidirectional relationships and Lomboks @Data hashCode() and equals() implementations.
Solved that by making some of the relationships unidirectional, where it made sense.
And where i needed hashCode() and equals() I explicitly marked fields that will be included in these methods.
## Improvements
Better exception handling and clearer responses indicated better for user which exception occurred, if it can be sent back to the user.
Would have included unit test for services and controllers.
Better structured component for handling JWT functionalities.




