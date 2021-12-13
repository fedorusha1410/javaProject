package com.fedorusha.appsstore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<CellPassword,String> {


    @Override
    public void initialize(CellPassword paramA) {

    }

    @Override
    public boolean isValid(String pass, ConstraintValidatorContext context) {
        if(pass == null){
            return false;
        }
        if (pass.matches("/(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}/g}")) return true;
        //латинские буквы верхий регистр
        //латинские буквы нижний регистр
        //специсимвол хоть один
        //хотя бы одно число
        // строка состоит не менее, чем из 6 вышеупомянутых символов

        else return false;
    }
}
