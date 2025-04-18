package at.fhv.sys.hotel.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;


public record CreateCustomerCommand(
        @NotBlank(message = "Id can not be empty")
        String userId,

        @NotBlank(message = "Name can not be empty")
        @Size(min = 2, max= 100, message = "Name must be at least 2 and max 100 characters long")
        String name,

        @NotBlank(message = "Email can not be empty")
        @Email(message = "Email must have a valid format")
        String email,

        @NotBlank(message = "Address can not be empty")
        @Size(min = 5, max = 200, message = "Address must be at least 5 and max 200 characters long")
        String address,

        @NotNull(message = "Birthdate can not be empty")
        @Past(message = "Birthdate has to be in the past")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate birthdate
) {}
