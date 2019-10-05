package com.example.demo.services;

import com.example.demo.data.DAO.Address;
import com.example.demo.data.DAO.AuthenticationInfo;
import com.example.demo.data.DAO.User;
import com.example.demo.data.DTO.UserDTO;
import com.example.demo.data.repositories.AddressRepository;
import com.example.demo.data.repositories.AuthenticationInfoRepository;
import com.example.demo.data.repositories.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("iUserService")
@AllArgsConstructor
public class UserService implements IUserService {

    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private JavaMailSender javaMailSender;
    private AuthenticationInfoRepository authenticationInfoRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<UserDTO>();
        Iterable<User> all = userRepository.findAll();
        all.forEach(u -> users.add(new UserDTO(u)));
        return users;
    }

    @Override
    public void sendMail(Integer userId, String subject, String content) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(u -> {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(u.getEmail());
            mail.setFrom("ralured1979@gmail.com");
            mail.setSubject(subject);
            mail.setText(content);
            javaMailSender.send(mail);
        });
    }

    @Override
    public void modifyUser(Integer id, Optional<String> name, Optional<String> email) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(u -> {
            name.ifPresent(n -> u.setName(n));
            email.ifPresent(e -> u.setEmail(e));
            userRepository.save(u);
        });
    }

    @Override
    public void insertUser(String name, String email, String password) {
        User u = User.builder().name(name).email(email).build();
        userRepository.save(u);
        AuthenticationInfo ai = AuthenticationInfo.builder().userName(name).password(password).build();
        authenticationInfoRepository.save(ai);


    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void changeAddress(Integer userId, Optional<String> city, Optional<String> country, Optional<String> street) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(u -> {
            Address address = getUserAddress(u);
            city.ifPresent(c -> address.setCity(c));
            country.ifPresent(c -> address.setCountry(c));
            street.ifPresent(s -> address.setStreet(s));
            Address updatedAddress = addressRepository.save(address);
            u.setAddress(updatedAddress);
            userRepository.save(u);
        });

    }

    @Override
    public String getServiceTitle(Integer id) throws NotFoundException {

        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User u = user.get();
            RestTemplate template = new RestTemplate();
            String url = "https://jsonplaceholder.typicode.com/todos/" + u.getId();
            String response = template.getForObject(url, String.class);

            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response);
                return root.path("title").asText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       throw new NotFoundException();
    }

    private Address getUserAddress(User u) {
        Address address = u.getAddress();
        if (address == null) {
            address = new Address();
            address.setUser(u);
        }
        return address;
    }

    public String getUserName (Integer userId) throws NotFoundException{
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User u = user.get();
            return u.getName();
        }
        throw new NotFoundException();
    }

}
