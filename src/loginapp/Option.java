package loginapp;

public enum Option {
    MANAGER("Manager"),
    EMPLOYEE("Employee");

    private String option;

    Option(String option) {
        this.option = option;
    }

    public String toString() {
        return option;
    }
}
