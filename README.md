# Library-System-Website
A API to manage a library’s book inventory. There are two main components of this API: management endpoints (to be used by the Librarians) and user facing endpoints for end-user actions. 

Books are referenced up by their ISBN. The library can have more than 1 copy of any given book (multiple copies of the same ISBN).

API Endpoints to be built:
Librarian Endpoints: 
•	An endpoint to add a book (by ISBN) to the library. 
•	An endpoint to remove a book (by its internal Id) from the library
•	An endpoint that generates a list of all overdue books.
User Endpoints: 
•	An endpoint to check out a book (assume a 2 week checkout period from time of call). A User can check out any book except when:  
-	They currently have 3 checked out books.  
-	They are overdue on returning any book. 
•	An endpoint to return a checked out book.
•	An endpoint that returns all currently checked out books for that user.
