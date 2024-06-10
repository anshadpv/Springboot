package com.login.loginDemo.controller;

import com.login.loginDemo.model.AuthReq;
import com.login.loginDemo.model.Bpin;
import com.login.loginDemo.model.Mpin;
import com.login.loginDemo.model.UserInfo;
import com.login.loginDemo.repository.BpinRepository;
import com.login.loginDemo.repository.MpinRepository;
import com.login.loginDemo.repository.UserRepository;
import com.login.loginDemo.service.JwtService;
import com.login.loginDemo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class userController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MpinRepository mpinRepository;

    @Autowired
    private BpinRepository bpinRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static String generateRandomUDID() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[16];
        secureRandom.nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome,this endpoint is not authenticated.";
    }


    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return userInfoService.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('USER')")
    public String  userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String  adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken_mpin")
    public ResponseEntity<String> authenticateAndGetTokenMpin(@RequestBody Mpin mpin){
        Optional<Mpin> mpin1 = mpinRepository.findByUsername(mpin.getUsername());

        if (mpin1.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found");
        }

        if (!mpin1.get().getMpin().equals(mpin.getMpin())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid MPIN.");
        }

        String token = jwtService.generateToken(mpin.getUsername());
        return ResponseEntity.ok(token);

    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthReq authReq) {
        //Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
        Optional<UserInfo> user1 = userRepository.findByName(authReq.getUsername());

        if (user1.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found.");
        }

        UserInfo existingUser = user1.get();
        if (!passwordEncoder.matches(authReq.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid password");
        }

        String token = jwtService.generateToken(authReq.getUsername());
        return ResponseEntity.ok(token);

    }


    @PostMapping("/generate_udid")
    public ResponseEntity<String> authenticateAndGetTokenBpin(@RequestBody Bpin bpin) {
        Optional<Bpin> udid=bpinRepository.findByUsername(bpin.getUsername());
        if(udid.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found.");
        }
        if(!udid.get().getBpin().equals(bpin.getBpin()))
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Bpin.");
        }

        String randomUdid = generateRandomUDID();
        Bpin bpin2 = new Bpin(bpin.getUsername(), randomUdid, bpin.getBpin());
        bpinRepository.save(bpin2);
        return ResponseEntity.ok(randomUdid);
    }

    @PostMapping("/authenticate/udid")
    public ResponseEntity<String> authenticateUdid(@RequestBody Bpin bpin) {
        String username = bpin.getUsername();
        Optional<Bpin> bpinOptional=bpinRepository.findByUsername(bpin.getUsername());
        if(bpinOptional.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found.");
        }
        if(!bpinOptional.get().getUdid().equals(bpin.getUdid()))
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Udid.");
        }

        String token = jwtService.generateToken(username);
        return ResponseEntity.ok(token);
    }

}

