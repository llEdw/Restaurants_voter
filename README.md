Restaurant Voting System Project 
=================================

This is REST API implementation of voting system for deciding where to have lunch.

### Technology stack: 

* Spring Boot
* Spring MVC
* Spring Security
* JPA(Hibernate)
* REST(Jackson)
* Maven
* Lombok
* H2DB


### Started task:
* There are 2 types of users: admin and regular users
* Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
* Menu changes each day (admins do the updates)
* Users can vote on which restaurant they want to have lunch at
* Only one vote counted per user
* If user votes again the same day:
    - If it is before 11:00 we assume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed
* Each restaurant provides a new menu each day.

## Rest architecture:

#### AccountController
- /api/account                              - **GET, DELETE, PUT**
- /api/account/register                     - **POST**
- /api/account/users                        - **GET, POST**
- /api/account/users/by-email?email=*       - **GET**
- /api/account/users/by-lastname?ln=*       - **GET**
- /api/account/users/id                     - **GET, DELETE, PUT**

#### RestaurantController
- /api/restaurants                          - **GET, POST**
- /api/restaurants/id                       - **GET, DELETE, PUT**
- /api/restaurants/by?name=*                - **GET**

#### DishController
- /api/dishes                - **GET**
- /api/dishes/by?name=*      - **GET**
- /api/dishes/filter?a=*&b=* - **GET**
- /api/restaurants/id/dishes                - **GET, POST**
- /api/restaurants/id/dishes/id             - **GET, DELETE, PUT**

#### VoteController
- /api/votes                                - **GET, POST**




### Domain model 




![image](https://user-images.githubusercontent.com/69795454/119143951-df36e980-ba50-11eb-9f4c-186d9ee20ab6.png)
