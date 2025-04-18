package at.fhv.sys.hotel.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Optional;

public record UpdateCustomerCommand(
        Optional<@Size(min = 2, max= 100) String> name,
        Optional<@Email String> email,
        Optional<@Size(min = 5, max = 200) String> address,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        Optional<@Past LocalDate> birthdate
) {}
