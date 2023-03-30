package com.agileactors.training.util;

public class TrainerUtil {

    public static boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
}
