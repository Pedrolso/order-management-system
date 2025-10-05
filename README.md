# Order Management System (Java)

## Description
A simple console-based order management system. Allows you to create, update, list, search, delete orders, and change the status of each order. Order codes are normalized (lowercase, no dashes), and IDs must be positive numbers.

This project was developed following good Java programming practices, without using any frameworks or databases, focusing on clean code, validations, and proper design of entities and services.

## Features
- Create, update, and delete orders  
- Search orders by full code or prefix  
- Track order status using Enum (MACHINE, CUT, DOWEL, EDGE, BUILD, WRAP, LOAD, DELIVERY)  
- Maintain status change history with timestamps  
- Input validation for ID, code, and color  
- Console-based user interface

## Usage
- Run the program and interact with the console menu to manage orders.  
- Available actions: Add, Update, List, Search (by code or prefix), Delete, and Update Status.  
- Order IDs must be numeric and greater than 0.  
- Order codes are automatically formatted: lowercase letters, no dashes, and no leading spaces.  

## Menu Example
Select an option:
- 1 - Add order
- 2 - Update order
- 3 - List
- 4 - Search by code
- 5 - Search by prefix
- 6 - Delete order
- 7 - Update status
- 0 - Exit
 
## Technologies
- Java 17+  
- Collections (HashMap, List)  
- Enums  
- Exception handling  

## Contributing
Feel free to fork this repository and submit pull requests to improve features or fix bugs.  

## License
MIT License
