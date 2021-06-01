package by.brgtu.david.krasko.diplom.model.dto;

public class CustomerDto {
    private String firstName;
    private String secondName;

    public String getFirstName() {
        return firstName;
    }

    public CustomerDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getSecondName() {
        return secondName;
    }

    public CustomerDto setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }
}
