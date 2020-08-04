package com.softserve.ukrainer.utils;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Service;

@Service
public class UkrainerUtils {

    public static final String ALLOWED_SPL_CHARACTERS = "!@#$%^&*()_+";

    public static final String ERROR_CODE = "ERRONEOUS_SPECIAL_CHARS";

    public String generatePassword() {

        PasswordGenerator generator = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterRule splCharRule = new CharacterRule(new CustomCharacterData());
        splCharRule.setNumberOfCharacters(2);

        return generator.generatePassword(10, splCharRule, lowerCaseRule,
            upperCaseRule, digitRule);
    }

    private static class CustomCharacterData implements CharacterData {

        @Override
        public String getErrorCode() {
            return ERROR_CODE;
        }

        @Override
        public String getCharacters() {
            return ALLOWED_SPL_CHARACTERS;
        }
    }
}
