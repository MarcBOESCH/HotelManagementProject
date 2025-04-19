package at.fhv.sys.hotel.commands;

import jakarta.validation.constraints.NotBlank;

public record CancelBookingCommand(
        @NotBlank
        String bookingId
) {}
