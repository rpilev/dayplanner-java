<h1>Dayplanner</h1>

This is a Java Spring Boot back-end for a day planner app.

To run start: `com.petproject.PetprojectApplication`

<h2>Features</h2>

<h3>CRUD API</h3>
<p>Implements PagingAndSortingRepository from Spring Data</p>

<b>GET /api/task</b>

*Parameters:*

    * page Number
    * size Number
    * sort String
    * direction String

*Example response:*

    {
        "content": [
            ...
            {
                "id": 1,
                "name": "Example name",
                "description": "Example description",
                "done": false
            },
            ...
        ],
        "pageable": {
            "size": 5,
            "page": 0,
            "totalElements": 30
        },
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        }
    }

<b>POST /api/task</b>

*Example request body:*

    {
    	"name": "Task to add",
    	"description": "Adding a task that is done",
    	"isDone": true
    }

*Example response:*

    {
        "id": 0,
        "name": "Task to add",
        "description": "Adding a task that is done",
        "done": true
    }

<b>PUT /api/task/{id}</b>

`example id = 1`

*Example request body:*

    {
      "name": "New name",
      "description": "New description",
    }

*Example response:*

    {
        "id": 1,
        "name": "New name",
        "description": "New description",
        "done": true
    }
    

<b>GET /api/task/{id}</b>

`example id = 1`

*Example response:*


    {
        "id": 1,
        "name": "Example name",
        "description": "Example description",
        "done": false
    }
    
<b>DELETE /api/task/{id}</b>

`example id = 1`

*Example response:*

    200 OK
    

<h2>Configuration</h2>
   
*Example application.properties file*
   
    server.port = 8080
    spring.application.name = PetProject
    
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/sys
    spring.datasource.username=
    spring.datasource.password=
    
    spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
    
    server.servlet.contextPath=/api
    
    logging.level.org.hibernate.SQL=DEBUG
    logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
    logging.level.org.springframework.jdbc.core.JdbcTemplate=DEBUG
    logging.level.org.springframework.jdbc.core.StatementCreatorUtils=TRACE


<h2>TODO</h2>

* Fix isDone -> done issue
* Find a better database system solution for developing
