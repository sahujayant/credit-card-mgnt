package com.example.card.credit.validation;

import javax.validation.ConstraintValidator;
    import javax.validation.ConstraintValidatorContext;

/**
 * Validator to validate if input credit card number satisfy lunn 10 validation.
 */
public class LuhnValidator implements ConstraintValidator<LuhnValidation, String> {

    public boolean isValid(String number, ConstraintValidatorContext cxt) {
        return isLuhnNumber(number);
    }

    public Boolean isLuhnNumber(String number) {
        //Remove special characters from input number.
        number = number.replace("-", "");
        number = number.replace(" ", "");
        int s1 = 0, s2 = 0;

        try {
            String reverse = new StringBuffer(number).reverse().toString();
            for (int i = 0; i < reverse.length(); i++) {
                int digit = Character.digit(reverse.charAt(i), 10);
                if (i % 2 == 0) {
                    //this is for odd digits, they are 1-indexed in the algorithm
                    s1 += digit;
                } else {
                    //add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
                    s2 += 2 * digit;
                    if (digit >= 5) {
                        s2 -= 9;
                    }
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return (s1 + s2) % 10 == 0;
    }
}
