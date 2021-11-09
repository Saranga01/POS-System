package validation.impl;

import javafx.scene.control.TextField;
import validation.Validation;

import java.util.regex.Pattern;

public class ValidationImpl implements Validation {

    @Override
    public boolean validationTxt(Pattern pattern, TextField textField) {
        if(pattern.matcher(textField.getText()).matches()){
            textField.setStyle("-fx-text-fill: black");
            return true;
        }else{
            textField.setStyle("-fx-text-fill: red");
            return false;
        }
    }
}
