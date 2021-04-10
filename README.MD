# Usersphonebook

A simple server for working with users and their phone books. Server realized only in-memory storage.

#### java -jar target/springboot-0.0.1-SNAPSHOT.jar

Examples:

Task|Request
----|-------
to get all users|>curl -X GET http://localhost:8080/api/user/
to create a new user with name 'name'|>curl -X PUT http://localhost:8080/api/user/name
to get a user by id(id starts at 1)|>curl -X GET http://localhost:8080/api/user/id
to remove a user by id|>curl -X DELETE http://localhost:8080/api/user/id
to change username by id|>curl -X POST http://localhost:8080/api/user/id/new-name
to get a user by username|>curl -X GET http://localhost:8080/api/user/by-name/name
to create a new phone entry by user id with name 'name' and phone number 'phone-number'|>curl -X PUT -F 'name=name' -F 'phone=phone-number' http://localhost:8080/api/contacts/id
to get a phone entry by user id and entry id(entry id starts at 1)|>curl -X GET http://localhost:8080/api/contacts/id/entry-id
to remove a phone entry by user id and entry id|>curl -X DELETE http://localhost:8080/api/contacts/id/entry-id
to change a phone entry by user id and entry id|>curl -X POST -F 'name=name' -F 'phone=phone-number' http://localhost:8080/api/contacts/id/entry-id
to get all phone entries by user id|>curl -X GET http://localhost:8080/api/contacts/id
to get a phone entry by user id and phone number|>curl -X GET http://localhost:8080/api/contacts/id/by-phone-number/phone-number