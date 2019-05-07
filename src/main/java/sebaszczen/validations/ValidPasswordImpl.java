package sebaszczen.validations;

import sebaszczen.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidPasswordImpl implements ConstraintValidator<ValidPassword,Object> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        boolean isValid=false;
        UserDto user = (UserDto) obj;
        boolean equals = user.getPassword().equals(user.getMatchingPassword());
        if (user.getPassword()!=null&&user.getMatchingPassword()!=null && equals){
            isValid = true;
        }
        return isValid;
    }
}
