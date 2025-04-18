package at.fhv.sys.hotel.commands;

import jakarta.validation.constraints.NotNull;

public record DeleteCustomerCommand(
        @NotNull(message = "CustomerId can not be empty")
        String customerId
) {}
