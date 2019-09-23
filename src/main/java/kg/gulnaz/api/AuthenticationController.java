package kg.gulnaz.api;

import kg.gulnaz.model.RegistrationDetails;
import kg.gulnaz.model.User;
import kg.gulnaz.service.UserService;
import kg.gulnaz.service.UserWithLoginAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> register(@RequestBody RegistrationDetails registrationDetails) {
        User user = userService.register(registrationDetails.getUsername(), registrationDetails.getPassword());
        return ResponseEntity.ok().body(user);
    }

    //*
    @ExceptionHandler(UserWithLoginAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handle(UserWithLoginAlreadyExists ex) {
        ErrorResponse body = new ErrorResponse();
        body.setError("CONFLICTING");
        body.setMessage(ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}
