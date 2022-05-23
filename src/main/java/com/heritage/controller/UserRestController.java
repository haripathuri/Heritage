package com.heritage.controller;

import com.heritage.annotations.APIUrl;
import com.heritage.pojo.User;
import com.heritage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author Hari Pathuri
 * 5/17/2022 5:32 PM
 */

@RestController
@APIUrl
@Validated
public class UserRestController {

    private final IUserService userService;

    @Autowired
    public UserRestController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "user/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(value = "user/byId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByUserId(@RequestParam @Min(value = 1, message = "Should not be less than 1") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping(value = "user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
        userService.createUser(user);
        return ResponseEntity.accepted().body("User Created");
    }

    @PutMapping(value = "user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody @Valid User user) {
        userService.updateUser(user);
        return ResponseEntity.accepted().body("User Updated");
    }

    @DeleteMapping(value = "user")
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
