package at.fhv.sys.hotel.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PayBookingCommand(

        @NotBlank
        String paymentId,

        @NotBlank
        String paymentMethod,

        @NotBlank
        String amount,

        @NotNull
        LocalDateTime paidAt
) {}
