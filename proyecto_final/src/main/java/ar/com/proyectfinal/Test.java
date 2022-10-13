package ar.com.proyectfinal;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("encoder:  " + encoder.encode("abel"));
        System.out.println("encoder:  " + encoder.encode("admin"));
        System.out.println("encoder:  " + encoder.encode("123456"));

        if (encoder.matches("abel", "$2a$10$IAz6WzJ314LH1NXq7Rf.dOYPP2uvzk08g.eAl9l4DRG4YsxavEV4W")) {
            System.out.println("encoder: true");
        }

    }
}


