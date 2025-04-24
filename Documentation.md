<h2>Architecture:</h2>    
We are using Quarkus as the framework for all three applications. The EventStore Database from the project skeleton is used.    
As per the task description, the command side uses in memory persistence for the customers and bookings, the query side uses an SQL database for ease of use.  
The services communicate using REST. For Persisting we use Quarkus Panache.




<h2>How to Run:</h2> 
Run docker-compose up in the terminal (make sure docker desktop is running if you use it);    
Make sure all three applications (hotel-command-side, hotel-query-side and eventbus) are running;  

To run commands, we used a simple swagger integration (accessible at http://localhost:8082/q/swagger-ui/#/);   

To see the EventStore go to http://localhost:2113/web/index.html#/streams
   
<h2>Workflow:</h2>
The main workflow of this application is as follows:    
Populate the database by running the Command in the SwaggerUI called "createInitialModels"; (at http://localhost:8082/q/swagger-ui/#/)   

Now you can create your own customers, book Rooms at available times for them, or use the existing customers.
You can also delete customers or bookings that already exist.

If for some reason the command-side is stopped, the in memory data can be restored by using the replayAllEvents method at http://localhost:8081/q/swagger-ui/#/ there is no automated call to this function, though.

The command-side methods "bookingCanceled", "roomBooked". "customerCreated", "customerUpdated" and "customerDeleted" are solely used for when the EventStore is replayed to get the in-memory persistence back.
