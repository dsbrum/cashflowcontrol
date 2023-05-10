package br.com.confidencial.CashFlowControl;

import org.springframework.boot.SpringApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Maintest {

    public static void main(String[] args) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "admin";
        String encodedPassword = passwordEncoder.encode(password);

        System.out.println();
        System.out.println("Password is         : " + password);
        System.out.println("Encoded Password is : " + encodedPassword);
        System.out.println();

        boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
        System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);
    }

}
