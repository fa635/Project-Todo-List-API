# Project-Todo-API

## Description
**Project-Todo-API** is a RESTful API built with Spring Boot that allows users to manage their to-do items. Users can create, update, delete, and retrieve todos, as well as filter and sort them by due date, priority, completion status, and category.

The app uses an embedded H2 database for storage and exposes endpoints for full CRUD operations and custom queries. Validation is enforced using Jakarta Bean Validation.

## Features
- Add todos with title, due date, category, priority, and optional description  
- Update todos by ID  
- Delete todos by ID  
- Get all todos  
- Sort todos by due date (asc/desc)  
- Sort todos by priority (asc/desc)  
- Filter todos by category and due date  
- Get only incomplete todos by due date or priority  

## Prerequisites
- Java 21  
- Maven  

## Installation

Clone the repository:
```bash
git clone https://github.com/fa635/Project-Todo-List-API.git
cd Project-Todo-List-API
```

Build the project:
```bash
./mvnw clean install
```

Run the API:
```bash
./mvnw spring-boot:run
```

## Usage
The API will be running at:  
**http://localhost:8080/todo**

### Sample Requests

#### Create a Todo
```bash
curl -X POST http://localhost:8080/todo \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Complete homework",
    "description": "Math and Science",
    "dueDate": "2025-04-20T18:00:00",
    "completed": false,
    "todoPriority": "HIGH",
    "category": "STUDY"
}'
```

#### Get All Todos
```bash
curl http://localhost:8080/todo
```

#### Update a Todo
```bash
curl -X PUT http://localhost:8080/todo/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Update homework",
    "description": "Math only",
    "dueDate": "2025-04-22T20:00:00",
    "completed": true,
    "todoPriority": "MEDIUM",
    "category": "STUDY"
}'
```

#### Delete a Todo
```bash
curl -X DELETE http://localhost:8080/todo/1
```

## Sort and Filter Examples

#### Get all todos sorted by due date descending:
```bash
curl "http://localhost:8080/todo/due-date?ascendingOrder=false"
```

#### Get incomplete todos sorted by priority:
```bash
curl "http://localhost:8080/todo/incomplete-by-priority?ascendingOrder=true"
```

#### Filter by category and due date:
```bash
curl "http://localhost:8080/todo/category-and-due-date?category=school&ascendingOrder=true"
```

## Notes
- Data is stored in an H2 file-based database (`./data/todos.db`)  
- You can access the H2 console at: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
  - **Username:** `sa`  
  - **Password:** *(empty)*  
- The database schema auto-updates on startup (`spring.jpa.hibernate.ddl-auto=update`)

## Future Enhancements
- User authentication  
- Swagger/OpenAPI documentation  
- Frontend client (React/Angular)  
- Notification system for upcoming todos  

## License
This project is open-source and free to use.
