<h2>Architecture:</h2>    
We are using Quarkus as the framework for all three applications. The EventStore Database from the project skeleton is used.    
As per the task description, the command side uses in memory persistance for the customers and bookings, the query side uses an SQL database for ease of use.    




<h2>How to Run:</h2> 
Run docker-compose up in the terminal (make sure docker desktop is running if you use it);    
Run all three applications (hotel-command-side, hotel-query-side and eventbus) are running;  

To run commands, we used a simple swagger integration (accessible at http://localhost:8082/q/swagger-ui/#/);   

To see the EventStore go to http://localhost:2113/web/index.html#/streams
   
<h2>Workflow:</h2>
The main workflow of this application is as follows:    
Populate the database by running the Command in the SwaggerUI called "createInitialModels";    

Now you can create your own customers, book Rooms at available times for them, or use the existing customers.
You can also delete customers or bookings that already exist.

If for some reason the command is stopped, the in memory data can be restored by using the Replay