import entities.Order;
import exceptions.DomainException;
import repository.OrderRepository;
import repository.OrderRepositoryImpl;
import services.OrderService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        OrderRepository orderRepository = new OrderRepositoryImpl();
        OrderService orderService = new OrderService(orderRepository);

        try {
            int menu;
            do {
                System.out.println("Select an option:");
                System.out.println(
                        "1 - Add order\n" +
                                "2 - Update order\n" +
                                "3 - List\n" +
                                "4 - Search by code\n" +
                                "5 - Search by prefix\n" +
                                "6 - Delete order\n" +
                                "7 - Update status\n" +
                                "0 - Exit"
                );
                menu = sc.nextInt();
                switch (menu) {
                    //create
                    case 1:
                        sc.nextLine();

                        Integer id = 0;
                        while (true) {
                            System.out.print("Enter order ID: ");
                            String idInput = sc.nextLine().replaceAll("//s+", "");
                            try {
                                id = Integer.parseInt(idInput);
                                if (id <= 0) {
                                    System.out.println("ID must be greater than 0. Try again.");
                                    continue;
                                }

                                break;

                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a numeric ID without spaces.");
                            }
                        }


                        System.out.print("Enter order code: ");
                        String code = sc.nextLine();
                        System.out.print("Enter order Color: ");
                        String color = sc.nextLine();

                        Order order = new Order(id, code, color);
                        orderService.createOrder(order);
                        break;

                    //update
                    case 2:
                        sc.nextLine();
                        System.out.print("Enter the order code: ");
                        String oldCode = sc.nextLine();
                        System.out.print("Enter the new code: ");
                        String newCode = sc.nextLine();

                        System.out.println(orderService.updateOrder(oldCode, newCode));

                        break;

                    //List without history
                    case 3:
                        List<Order> orders = orderService.listAll();
                        System.out.println(orderService.listFormattedOrders(orders, false));
                        break;

                    //find by code
                    case 4:
                        sc.nextLine();
                        System.out.print("Enter the order code: ");
                        String findOneCode = sc.nextLine();

                        System.out.println(orderService.findByCode(findOneCode));
                        break;

                    //find Prefix
                    case 5:
                        sc.nextLine();
                        System.out.print("Enter the order code: ");
                        String findCodePrefix = sc.nextLine();

                        List<Order> codePrefix = orderService.findByPrefix(findCodePrefix);
                        System.out.println(orderService.listFormattedOrders(codePrefix, true));

                        break;

                    //delete
                    case 6:
                        sc.nextLine();
                        System.out.print("Enter the order code: ");
                        String deleteOrder = sc.nextLine();

                        orderService.deleteOrder(deleteOrder);
                        System.out.println("Order: " + deleteOrder + " Deleted successfully.");
                        break;

                    //update status
                    case 7:
                        sc.nextLine();
                        System.out.print("Enter the order code: ");
                        String select = sc.nextLine();

                        System.out.println("Select the new status: ");
                        System.out.println("1 - CUT");
                        System.out.println("2 - EDGE");
                        System.out.println("3 - DOWEL");
                        System.out.println("4 - BUILD");
                        System.out.println("5 - WRAP");
                        System.out.println("6 - LOAD");
                        System.out.println("7 - DELIVERY");

                        int choose = sc.nextInt();

                        Order changingStatus = orderService.updateStatus(select, choose);
                        System.out.println("Status updated to: " + changingStatus.getOrderStatus());
                        break;

                    default:

                }

            } while (menu != 0);

            sc.close();

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numeric values only.");
        } catch (DomainException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status option");
        }
    }
}