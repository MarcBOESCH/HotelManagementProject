package at.fhv.sys.hotel.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookRoomCommand(
        @NotBlank(message = "BookingId can not be empty")
        String bookingId,

        @NotBlank(message = "CustomerId can not be empty")
        String customerId,
        @NotBlank(message = "RoomNumber can not be empty")
        String roomNumber,

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate fromDate,

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate toDate
) {}
