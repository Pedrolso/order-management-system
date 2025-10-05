# Order Management System (Java)

## Description
Java CRUD project for managing orders with input validation, status tracking via Enum, search by code or prefix, status updates, and history logging. Console-based interface for creating, updating, listing, and deleting orders.

## Features
- Create, update, and delete orders  
- Search orders by full code or prefix  
- Track order status using Enum (MACHINE, CUT, DOWEL, EDGE, BUILD, WRAP, LOAD, DELIVERY)  
- Maintain status change history with timestamps  
- Input validation for ID, code, and color  
- Console-based user interface

## Uso
- Execute o programa e interaja com o menu do console para gerenciar pedidos.  
- Ações disponíveis: Adicionar, Atualizar, Listar, Buscar (por código ou prefixo), Excluir e Atualizar Status.  
- IDs dos pedidos devem ser numéricos e maiores que 0.  
- Os códigos dos pedidos são formatados automaticamente: letras minúsculas, sem traços e sem espaços no início. 

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
