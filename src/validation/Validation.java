package validation;

import bo.SuperBo;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

public interface Validation extends SuperBo {
    public boolean validationTxt(Pattern pattern, TextField textField);
}
