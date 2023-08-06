# Reactive repository and controller

note: link  https://sports.api.decathlon.com/sports  doesn't work anymore, so we're using another one
http://fakerapi.it/api/v1/books?_quantity=800   (800 - count of book in response - max is 1000)

###  

- POST http://localhost:8080/api/v1/all-book-batch
  delete all records from db and than
  read book list from http://fakerapi.it/api/v1/books?_quantity=800 and save to db with chunk by 20
  (see console log for sure)

- POST http://localhost:8080/api/v1/allbook read and save all books as one part
- POST http://localhost:8080/api/v1/book save one book to db
  f.e.
  {
  "author": "Kevin Zboncak",
  "description": "She gave me a good character, But said I could say if I shall have some fun now!' thought Alice. The
  poor little thing was waving its right ear and left off when they hit her; and the March Hare.",
  "genre": "Officiis",
  "id": 1223,
  "image": "http://placeimg.com/480/640/any",
  "isbn": 9785566949333,
  "published": "2021-09-15",
  "publisher": "Autem Aliquid",
  "title": "Nola called after mid."
  }

  checks isbn duplicated with understandable message, return error when id duplicated,
  return 201 when book saved
- GET http://localhost:8080/api/v1/book?title=Alice called after. - find by title
- POST http://localhost:8080/api/v1/book5 save5 books from file books.json

## DB structure see

    db.sql

## postman collection see

    reactive.postman_collection.json

## Controller see

    MainRouter.java


