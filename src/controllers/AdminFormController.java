package controllers;

import bo.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.ItemBo;
import dto.ItemDto;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

public class AdminFormController extends LogInFormController{
    public AnchorPane rootAdminForm;
    public Label lblWellComeName;
    public TextArea txtItemDescription;
    public TextField txtDiscount;
    public TextField txtQtyOnHand;
    public TextField txtUnitPrice;
    public TextField txtPackSize;
    public TextField txtItemCode;
    public TextField txtEnterItemCode;
    public TextField txtItemCode1;
    public TextField txtItemPackSize1;
    public TextField txtItemUnitPrice1;
    public TextField txtItemQtyOnHand1;
    public TextField txtItemDiscount1;
    public TextArea txtDescription1;
    //public TableView<AddCustomer> tblViewCustomer;
    public TableColumn colmCustId;
    public TableColumn colmCustTitle;
    public TableColumn colmCustName;
    public TableColumn colmCustAddress;
    public TableColumn colmCustCity;
    public TableColumn colmCustProvince;
    public TableColumn colmCustPostalCode;
    //public TableView<AddItem> tblViewItem;
    public TableColumn colmItemCode;
    public TableColumn colomDescription;
    public TableColumn colmPackSize;
    public TableColumn colmUnitPrice;
    public TableColumn colmQtyOnHand;
    public TableColumn colmDiscRate;
    public Label lblWelComeName;
    public Label lblDate;
    public Label lblTime;
    public TableView tblViewCustomer;
    public TableView tblViewItem;

    CustomerBo customerBo= (CustomerBo) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.CUSTOMER);
    ItemBo itemBo= (ItemBo) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEM);

    Pattern TName=Pattern.compile("^[A-z. ]{3,70}$");
    Pattern TAddress=Pattern.compile("^[A-z0-9/,. ]{4,150}$");
    Pattern TEmail=Pattern.compile("^[A-z0-9]{3,}[@][a-z]{2,}[.][a-z]{2,5}$");
    Pattern TNic=Pattern.compile("^[0-9]{6,20}[Vv]?$");
    Pattern TMobile=Pattern.compile("^[0-9]{9,10}$");


    public void initialize(){
        LoadTimeAndDate();
        setItemCode();
        lblWellComeName.setVisible(true);
        lblWellComeName.setText("Hi "+UserName+" ....! You can do all the action that are valid for an admin here.");
    }

    public void LoadTimeAndDate(){
        Date date=new Date();
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));
        Timeline time=new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e->{
            LocalTime CurrentTime=LocalTime.now();
            if(CurrentTime.getHour()<10) {
                if(CurrentTime.getSecond()<10){
                    lblTime.setText("0" + CurrentTime.getHour() + ":" + CurrentTime.getMinute() + ":" +"0"+ CurrentTime.getSecond());
                }else {
                    lblTime.setText("0" + CurrentTime.getHour() + ":" + CurrentTime.getMinute() + ":" + CurrentTime.getSecond());
                }
            }else{
                if(CurrentTime.getSecond()<10){
                    lblTime.setText(CurrentTime.getHour() + ":" + CurrentTime.getMinute() + ":" +"0"+ CurrentTime.getSecond());
                }else{
                    lblTime.setText(CurrentTime.getHour() + ":" + CurrentTime.getMinute() + ":" + CurrentTime.getSecond());
                }

            }
        }),

                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }


    public void btnAddItem(ActionEvent actionEvent) {
        try {

            boolean b = itemBo.saveItem(
                    new ItemDto(
                            txtItemCode.getText(),
                            txtItemDescription.getText(),
                            txtPackSize.getText(),
                            Double.parseDouble(txtUnitPrice.getText()),
                            Integer.parseInt(txtQtyOnHand.getText()),
                            Double.parseDouble(txtDiscount.getText())
                    )
            );
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION,"Item Save Successfully..!",ButtonType.OK).showAndWait();
                clearAddItem();
                setItemCode();
            }else{
                new Alert(Alert.AlertType.WARNING,"Please try Again..!",ButtonType.OK).showAndWait();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void clearAddItem(){
        txtItemCode.clear();
        txtItemDescription.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtDiscount.clear();
    }

    public void clearUpdateItem(){
        txtItemDiscount1.clear();
        txtItemQtyOnHand1.clear();
        txtItemUnitPrice1.clear();
        txtItemPackSize1.clear();
        txtDescription1.clear();
        txtItemCode1.clear();
    }

    public void setItemCode(){
        try {
            txtItemCode.setText(itemBo.generateItemCode());
            txtItemCode.setDisable(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnItemSearchOnAction(ActionEvent actionEvent) {
        try {
            ItemDto itemDto = itemBo.searchItem(txtEnterItemCode.getText());

            txtItemCode1.setText(itemDto.getItemCode());
            txtDescription1.setText(itemDto.getDescription());
            txtItemPackSize1.setText(itemDto.getPackSize());
            txtItemUnitPrice1.setText(String.valueOf(itemDto.getUnitPrice()));
            txtItemQtyOnHand1.setText(String.valueOf(itemDto.getQtyOnHand()));
            txtItemDiscount1.setText(String.valueOf(itemDto.getDiscountRate()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void btnItemUpdateItemOnAction(ActionEvent actionEvent) {
        try {
            boolean b = itemBo.updateItem(new ItemDto(
                    txtItemCode1.getText(),
                    txtDescription1.getText(),
                    txtItemPackSize1.getText(),
                    Double.parseDouble(txtItemUnitPrice1.getText()),
                    Integer.parseInt(txtItemQtyOnHand1.getText()),
                    Double.parseDouble(txtItemDiscount1.getText())
            ));

            if(b==true){
                new Alert(Alert.AlertType.CONFIRMATION,"Update Successfully...!").showAndWait();
                clearUpdateItem();
                setItemCode();
            }else{
                new Alert(Alert.AlertType.WARNING,"Wrong Please try Again...!").showAndWait();
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void btnItemDeleteOnAction(ActionEvent actionEvent) {
        try {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Delete Item ?", ButtonType.YES, ButtonType.NO).showAndWait();

            if(ButtonType.YES.equals(buttonType.get())){
                if(itemBo.deleteItem(txtItemCode1.getText())){
                    new Alert(Alert.AlertType.CONFIRMATION," Delete Successfully... ").showAndWait();
                    clearUpdateItem();
                    setItemCode();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Wrong Please try Again...!").showAndWait();
                }

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void ViewCustomerChange(Event event) {
        try {
            tblViewCustomer.setItems(customerBo.viewAllCustomers());
            customerBo.setCustomerTableValue(colmCustId,colmCustTitle,colmCustName,colmCustAddress,colmCustCity,colmCustProvince,colmCustPostalCode);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ViewItemChange(Event event) {
        try {
            tblViewItem.setItems(itemBo.viewAllItems());

            itemBo.setItemTableValue(colmItemCode,colomDescription,colmPackSize,colmUnitPrice,colmQtyOnHand,colmDiscRate);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ViewReportChange(Event event) {
    }

    public void btnLogOut(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do You Want to Logout ?", ButtonType.APPLY.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            Parent parent = FXMLLoader.load(this.getClass().getResource("../VIew/LogIn.fxml"));
            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) this.rootAdminForm.getScene().getWindow();

            primaryStage.setScene(scene);
            primaryStage.setTitle("Login Form");
            primaryStage.show();
            primaryStage.centerOnScreen();
        }
    }

    public void UpdateChange(Event event) {
    }
}
