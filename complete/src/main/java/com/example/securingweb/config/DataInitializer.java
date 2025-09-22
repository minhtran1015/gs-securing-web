package com.example.securingweb.config;

import com.example.securingweb.model.User;
import com.example.securingweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create sample users if they don't exist
        createUserIfNotExists("user", "password", User.Role.USER);
        createUserIfNotExists("admin", "admin", User.Role.ADMIN);
        
        // Additional USER demo accounts
        createUserIfNotExists("john", "john123", User.Role.USER);
        createUserIfNotExists("mary", "mary123", User.Role.USER);
        createUserIfNotExists("demo", "demo", User.Role.USER);
        createUserIfNotExists("guest", "guest", User.Role.USER);
        
        // Additional ADMIN demo accounts
        createUserIfNotExists("superadmin", "super123", User.Role.ADMIN);
        createUserIfNotExists("manager", "manager123", User.Role.ADMIN);
        createUserIfNotExists("root", "root123", User.Role.ADMIN);
        
        System.out.println("\n=== DEMO ACCOUNTS CREATED ===");
        System.out.println("👤 USER Accounts:");
        System.out.println("  • user/password");
        System.out.println("  • john/john123");
        System.out.println("  • mary/mary123");
        System.out.println("  • demo/demo");
        System.out.println("  • guest/guest");
        System.out.println("\n🛡️ ADMIN Accounts:");
        System.out.println("  • admin/admin");
        System.out.println("  • superadmin/super123");
        System.out.println("  • manager/manager123");
        System.out.println("  • root/root123");
        System.out.println("=============================\n");
    }
    
    private void createUserIfNotExists(String username, String password, User.Role role) {
        if (!userRepository.existsByUsername(username)) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(role);
            user.setEnabled(true);
            userRepository.save(user);
            System.out.println("Created " + role + ": " + username + "/" + password);
        }
    }
}