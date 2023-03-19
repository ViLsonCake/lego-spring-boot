package com.project.SpringBootLegoScrap.entity.entityConsts;

public class EntityConst {
    // Tables
    public static final String  USER_TABLE = "user";
    public static final String  LEGO_TABLE = "lego_set";

    // Messages
    public static final String USERNAME_EMPTY_MESSAGE = "Username can't be empty";
    public static final String USERNAME_SIZE_MESSAGE = "Name should be between 2 and 20 characters";
    public static final String PASSWORD_EMPTY_MESSAGE = "Password can't be empty";
    public static final String PASSWORD_PATTERN_VALID_MESSAGE = "Password is not valid";
    public static final String EMAIL_EMPTY_MESSAGE = "Email can't be empty";
    public static final String EMAIL_VALID_MESSAGE = "Email is not valid";

    // Patterns
    public static final String REGEX_PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!_^&+=])(?=\\S+$).{8,16}$";
    public static final String REGEX_AGES_PATTERN = "\\d{1,2}\\+";
    public static final String REGEX_PRICE_PATTERN = "\\$.+";

}
