package at.fhv.sys.hotel.commands;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateRoomCommand(
        @NotBlank
        String roomNumber,

        @NotNull
        @Min(1)
        Integer capacity,

        @NotBlank
        String description,

        @NotNull
        Boolean hasFullyLoadedBeerRefrigerator,

        @NotNull
        @Min(0)
        Double price
) {
}
