package online.ivandjoh.demogradle.service.Impl;

import online.ivandjoh.demogradle.Entity.User;
import online.ivandjoh.demogradle.repository.UserRepository;
import online.ivandjoh.demogradle.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();

            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<User> getUserById(Long id) {
        try {
            User user = userRepository.findById(id).orElse(null);

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, User user) {
        try {
            User userToUpdate = userRepository.findById(id).orElse(null);

            if (userToUpdate == null) {
                return ResponseEntity.notFound().build();
            } else {

                userRepository.save(userToUpdate);

                return ResponseEntity.ok(userToUpdate);
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> createUser(User user) {
        try {
            User newUser = userRepository.save(user);
            logger.info("Creating user: {}", newUser);
//            return ResponseEntity.ok(newUser);
            return null;
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        User deleteUser = null;
        try {
            deleteUser = userRepository.findById(id).orElse(null);
            if (deleteUser != null) {
                userRepository.delete(deleteUser);
            } else {
                throw new Exception("User not found!!");
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
