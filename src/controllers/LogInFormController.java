package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LogInFormController {

    public AnchorPane rootLogInPage;
    public Label lblCreateNewAccount;
    public TextField txtUserName;
    public TextField txtPassword;
    public Label lblLoginNameError;
    public Label lblLoginPasswordError;
    static String UserName;

    public void initialize(){
        lblLoginNameError.setVisible(false);
        lblLoginPasswordError.setVisible(false);
    }

    public void btnLogInOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        String adminUserName = "Admin";
        String adminPassword = "Admin123";
        String cashierUserName = "Cashier";
        String cashierPassword = "Cashier123";


        if (adminUserName.equals(txtUserName.getText())) {
            if (adminPassword.equals(txtPassword.getText())) {

                Parent parent = FXMLLoader.load(this.getClass().getResource("../view/AdminForm.fxml"));
                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) this.rootLogInPage.getScene().getWindow();

                primaryStage.setScene(scene);
                primaryStage.setTitle("Admin Form");
                primaryStage.show();
                primaryStage.centerOnScreen();
                return;


            }


            }else if (cashierUserName.equals(txtUserName.getText())) {
            if (cashierPassword.equals(txtPassword.getText())) {

                Parent parent = FXMLLoader.load(this.getClass().getResource("../view/CashierForm.fxml"));
                Scene scene = new Scene(parent);
                Stage primaryStage = (Stage) this.rootLogInPage.getScene().getWindow();

                primaryStage.setScene(scene);
                primaryStage.setTitle("Cashier Form");
                primaryStage.show();
                primaryStage.centerOnScreen();
                return;

            }

        }else {
            new Alert(Alert.AlertType.WARNING, "Please Enter Correct User Name and Password...!", ButtonType.OK).showAndWait();
        }
    }

}

