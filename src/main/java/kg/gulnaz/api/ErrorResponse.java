package kg.gulnaz.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ErrorResponse {
    @Getter
    @Setter
    private String error;
    @Getter
    @Setter
    private String message;
}
