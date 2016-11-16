**CPRE 339 Final Project Proposal**

Group members:
* Jeffery Schons
* Jacob Long
* Chidike Ubani

Idea is an ATM system. This would involve coding the server that handles all the
machines as well as coding a sample ATM Client. The server would handle the clients
each on different threads.

This project will meet the requirements because it will have multiple processes:
the server and multiple clients accessing the server. The server will act as a mediator
for the clients, processing their requests and transferring their money. The server will
interpret messages sent from the clients. There will also be a chain of responsibility on
the server, where data is passed in from the clients, processed by the server, then sent
to the database to be stored.