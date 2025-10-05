package entities;

import entities.enums.OrderStatus;
import exceptions.DomainException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EnumMap;

public class Order {

    private Integer id;
    private String code;
    private String color;
    private OrderStatus orderStatus;
    private final EnumMap<OrderStatus, LocalDateTime> statusHistory = new EnumMap<>(OrderStatus.class);

    //It's in the toString method to display the correct format.
    private static final DateTimeFormatter DISPLAY = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    //-------------------------------------------------------------------------//

    //The constructor creates the object, sets the enum to MACHINE, and assigns the system's local date and time.
    public Order(Integer id, String code, String color) {
        if (id == null || id <= 0) {
            throw new DomainException("ID must be greater than zero.");
        }

        if (Character.isWhitespace(code.charAt(0))) {
            throw new DomainException("Code cannot start with a blank space.");
        }

        this.id = id;
        this.code = fixingFormat(code);

        if (color == null || color.isBlank()) {
            throw new DomainException("Color cannot be empty or blank.");
        }

        this.color = color;

        //Inicial status
        this.orderStatus = OrderStatus.MACHINE;
        // time resgister
        this.statusHistory.put(this.orderStatus, LocalDateTime.now());
    }

    //Updates the order's enum and refreshes the LocalDateTime of the update.
    public void setStatus(OrderStatus newStatus) {
        this.orderStatus = newStatus;
        this.statusHistory.put(newStatus, LocalDateTime.now());
    }

//--------------------------------------------------------------------------------//

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = fixingFormat(code);
    }

    public String getColor() {

        return color;
    }

    public void setColor(String color) {

        this.color = color;
    }

    public OrderStatus getOrderStatus() {

        return orderStatus;
    }

    public EnumMap<OrderStatus, LocalDateTime> getStatusHistory() {

        return statusHistory;
    }

    //-------------------------------METHODS----------------------------------------//

    public static String fixingFormat(String input) {


        if (input == null || input.isBlank()) {
            throw new DomainException("Code cannot be empty.");
        }

        String normal = input.toLowerCase().replace("-", "").replace(" ", "");

        char first = normal.charAt(0);
        if (!Character.isLetterOrDigit(first)) {
            throw new DomainException("Code cannot start with a symbol.");
        }
        if (Character.isDigit(first) && input.trim().startsWith("-")) {
            throw new DomainException("Code cannot be a negative number.");
        }
        if (Character.isDigit(first)) {
            throw new DomainException("Code cannot start with a number.");
        }

        return normal;
    }

    //List without history "case 3"
    public String listCase3() {
        return "Order { ID: " + id
                + ", Code: " + code
                + ", Color: " + color
                + ", Order Status: " + orderStatus + " }";
    }

    //----------------------------------------------------------------------------//

    @Override
    public String toString() {
        StringBuilder historyStr = new StringBuilder();
        for (var entry : statusHistory.entrySet()) {
            historyStr.append(entry.getKey())
                    .append(" = ")
                    .append(entry.getValue().format(DISPLAY))
                    .append(" | ");
        }


        return "Order { ID: " + id
                + ", Code: " + code
                + ", Color: " + color
                + ", Order Status: " + orderStatus
                + ", History: { " + historyStr.toString()
                + "} }";
    }

}
