package com.example.company.domain.user.service;

import com.example.company.common.exception.ResourceNotFoundException;
import com.example.company.domain.user.model.User;
import com.example.company.domain.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer for User domain operations.
 */
@Service
@Transactional(readOnly = true)
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieve all users.
     *
     * @return list of all users
     */
    public List<User> getAllUsers() {
        logger.debug("Fetching all users");
        return userRepository.findAll();
    }

    /**
     * Retrieve a user by ID.
     *
     * @param id the user ID
     * @return the user
     * @throws ResourceNotFoundException if user not found
     */
    public User getUserById(Long id) {
        logger.debug("Fetching user with id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    /**
     * Create a new user.
     *
     * @param user the user to create
     * @return the created user
     */
    @Transactional
    public User createUser(User user) {
        logger.info("Creating new user with email: {}", user.getEmail());
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }
        return userRepository.save(user);
    }

    /**
     * Update an existing user.
     *
     * @param id   the user ID
     * @param user the updated user data
     * @return the updated user
     * @throws ResourceNotFoundException if user not found
     */
    @Transactional
    public User updateUser(Long id, User user) {
        logger.info("Updating user with id: {}", id);
        User existingUser = getUserById(id);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    /**
     * Delete a user by ID.
     *
     * @param id the user ID
     * @throws ResourceNotFoundException if user not found
     */
    @Transactional
    public void deleteUser(Long id) {
        logger.info("Deleting user with id: {}", id);
        User user = getUserById(id);
        userRepository.delete(user);
    }
}
