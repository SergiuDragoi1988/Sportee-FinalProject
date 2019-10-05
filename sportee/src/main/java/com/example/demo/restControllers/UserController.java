package com.example.demo.restControllers;

import com.example.demo.data.DTO.UserDTO;
import com.example.demo.services.IUserService;
import com.example.demo.services.NotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Inject
    private IUserService userService;


    @GetMapping("user/all")
    public List<UserDTO> getAll() {
        return userService.getAllUsers();

    }

    @RequestMapping("user/{userId}/sendMail")
    public void sendMail(@PathVariable Integer userId, String subject, String content) {
        userService.sendMail(userId, subject, content);


    }

    @GetMapping("user/{id}/modify")
    public void modifyUser(@PathVariable Integer id, Optional<String> name, Optional<String> email) {
        userService.modifyUser(id, name, email);
    }

    @GetMapping("user/insert")
    public void insertUser(String name, String email, String password) {
        userService.insertUser(name, email, password);
    }

    @GetMapping("user/{id}/delete")
    public void deleteUser(@PathVariable Integer id) {
        userService.delete(id);
    }

    @GetMapping("user/{userId}/changeAddress")
    public void changeAddress(@PathVariable Integer userId, Optional<String> city, Optional<String> country, Optional<String> street) {
        userService.changeAddress(userId, city, country, street);
    }

    @GetMapping("user/{userId}/getTitle")
    public String getUserTitle(@PathVariable Integer userId) throws NotFoundException {
        return userService.getServiceTitle(userId);
    }

    @GetMapping("user/{userId}/getName")
    public String getUserName(@PathVariable Integer userId) throws NotFoundException
    {
        return userService.getUserName(userId);
    }
}
