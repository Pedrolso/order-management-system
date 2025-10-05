package entities.enums;

public enum OrderStatus {
    MACHINE(0),
    CUT(1),
    DOWEL(2),
    EDGE(3),
    BUILD(4),
    WRAP(5),
    LOAD(6),
    DELIVERY(7);

    //---------------------------------------------------//
    private final int option;

    OrderStatus(int option) {
        this.option = option;
    }

    public int getOption() {
        return option;
    }

    public static OrderStatus fromOption(int option) {
        for (OrderStatus status : values()) {
            if (status.option == option) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status option: " + option);
    }

}
