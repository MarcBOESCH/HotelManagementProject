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

<h2>Behavior</h2>
After populating the databases as well as the EventStore:    
When trying to add a new customer, the command-side validates whether or not the id already exists in memory, if it does not exist, it sends the Event to be processed by the EventBus. When Processing the EventBus tells the query side to update the database and add the new customer.     
When trying to book a room for a certain time for the customer, the command side once again validates whether or not the room is free at the input time. If it is, the command side checks if the customer id exists, if it does, the EventStore saves the Event of the booking as well as tells the query side to update its database.
When someone now tries to book the room at the time when it is booked, the command side already validates it as being booked and throws an exception.
The booking can be cancelled after being created. Customers can be updated and/or deleted.
Bookings have to be paid separately, only booking them does not mark them as paid automatically.
If the command-side or query-side is terminated, the replayAllEvents method from the EventBus can restore the stored date in the other two services.
