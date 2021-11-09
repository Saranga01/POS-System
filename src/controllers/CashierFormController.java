package controllers;

import bo.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.ItemBo;
import bo.custom.OrderPlaceBo;
import com.jfoenix.controls.JFXComboBox;
import dto.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import validation.Validation;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

public class CashierFormController extends LogInFormController {
    public AnchorPane rootCashierForm;
    public Label lblWelcomeName;
    public TextField txtCustomerPostalCode;
    public TextField txtCustomerProvince;
    public Button btnAddCustomer;
    public TextField txtCustomerAddress;
    public TextField txtCustomerName;
    public TextField txtCustomerID;
    public TextField txtCustomerCity;
    public TextField txtCustomerTitle;
    public TextField txtEnterCustID;
    public TextField txtCustID;
    public TextField tctCustTitle;
    public TextField txtCustAddress;
    public TextField txtCustName;
    public TextField txtCustCity;
    public TextField txtCustProvince;
    public TextField txtCustPostalCOde;
    //public TableView<AddCustomer> tblViewCustomer;
    public TableColumn colmCustId;
    public TableColumn colmCustTitle;
    public TableColumn colmCustName;
    public TableColumn colmCustAddress;
    public TableColumn colmCustCity;
    public TableColumn colmCustProvince;
    public TableColumn colmCustPostalCode;
    public TableColumn colmItemCode;
    public TableColumn colomDescription;
    public TableColumn colmPackSize;
    public TableColumn colmUnitPrice;
    public TableColumn colmQtyOnHand;
    public TableColumn colmDiscRate;
    //public TableView<AddItem> tblViewItem;
    public Label lblWelComeName;
    public Label lblDate;
    public Label lblTime;
    public JFXComboBox<String> CombSelectItemCode;
    public Label lblItemDescription;
    public Label lblItemQtyOnHand;
    public Label lblItemUnitPrice;
    public Label lblItemDiscount;
    public Label lblAfterDiscountPrice;
    public TextField txtReqAmount;
    //public TableView<CartTM> tblOrderDetails;
    public TableColumn colmItemCodeOrder;
    public TableColumn colmItemReqAmountOrder;
    public TableColumn colmItemPriceOrder;
    public Label lblTotalPrice;
    public JFXComboBox<String> CombSelectCustomerID;
    public Label lblCustomerName;
    public Label lblCustomerAddress;
    public Label lblCostomerCity;
    public Label lblCustomerProvince;
    public Label lblCustomerPostalCode;
    static String SelectItemCode;
    public Label lblVerified;
    public ImageView imgCustomer;
    public Label lblVerification;
    public Label lblOrderId;
    public Label OrderIdText;
    public Label lblTotalIDManageOrder;
   // public TableView<OrderMangeTblTM> tblOrderDetailsMangeOrder;
    public TableColumn colmItemCodeMangeOrder;
    public TableColumn colmQtyOrderMange;
    public TableColumn colmItemCostOrderMange;
    public JFXComboBox CombSelectOrderIDMangeOrder;
    public Label lblCustIDandNameManageOrder;
    public Label OrderDateManageOrder;
    public Label lblTimeOrderMangeOrder;
    public Label lblOrderIDManageOrder;
    public AnchorPane rootManagement;
    public TableView tblOrderDetails;
    public TableView tblOrderDetailsMangeOrder;
    public TableView tblViewCustomer;
    public TableView tblViewItem;
    double AfterDiscountPrice;
    int selectedRow =-1;
    String selectItemCode;
    ItemDto itemDto;

    Pattern name=Pattern.compile("^[A-z. ]{3,70}$");
    Pattern address=Pattern.compile("^[A-z0-9/,. ]{4,150}$");
    Pattern postalCode=Pattern.compile("^[0-9]{3,10}$");
    Pattern qty=Pattern.compile("^[0-9]{1,}$");
    Pattern title=Pattern.compile("^[A-Z]{1}[a-z]{1,3}[.]$");


    CustomerBo customerBo= (CustomerBo) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.CUSTOMER);
    ItemBo itemBo= (ItemBo) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEM);
    OrderPlaceBo orderPlaceBo= (OrderPlaceBo) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PURCHASE_ORDER);
    Validation validation= (Validation) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.VALIDATION);

    public void initialize() {

        rootManagement.setVisible(false);
        lblVerified.setVisible(false);
        imgCustomer.setVisible(false);
        lblVerification.setVisible(false);
        OrderIdText.setVisible(false);
        lblOrderId.setVisible(false);


        loadCustomerIdsAndItemCodes();
        loadTimeAndDate();

        CombSelectCustomerID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                CustomerDto customerDto = customerBo.searchCustomer(newValue);
                setCustomerDetails(customerDto);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        CombSelectItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                itemDto = itemBo.searchItem(newValue);
                selectItemCode = newValue;
                setItemDetails(itemDto);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        colmItemCodeOrder.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colmItemReqAmountOrder.setCellValueFactory(new PropertyValueFactory<>("reqQty"));
        colmItemPriceOrder.setCellValueFactory(new PropertyValueFactory<>("price"));
        colmItemCodeOrder.setStyle("-fx-alignment: CENTER;");
        colmItemReqAmountOrder.setStyle("-fx-alignment: CENTER;");
        colmItemPriceOrder.setStyle("-fx-alignment: CENTER;");


        lblWelcomeName.setVisible(true);
        lblWelcomeName.setText("Hi " + UserName + " ....! You can do all the action that are valid for an cashier here.");
    }


    public void RootOrderDatailsOnClicked(MouseEvent mouseEvent) {
    }



    ObservableList<CartTmDto> setCartTmObs= FXCollections.observableArrayList();
    public void btnAddToCartOnAction(ActionEvent actionEvent) throws SQLException {

      if(validation.validationTxt(qty,txtReqAmount)==false || CombSelectItemCode.getSelectionModel().getSelectedIndex()<0){
          new Alert(Alert.AlertType.WARNING, "Wrong please check and try again...!", ButtonType.OK).showAndWait();
      }else {

          if (Integer.parseInt(txtReqAmount.getText()) < 1) {
              new Alert(Alert.AlertType.WARNING, "Invalid Qty..!", ButtonType.OK).showAndWait();

          } else {
              if (Integer.parseInt(lblItemQtyOnHand.getText()) >= Integer.parseInt(txtReqAmount.getText())) {

                  CartTmDto setCartTm = null;
                  try {
                      setCartTm = orderPlaceBo.setCartTm(selectItemCode, Integer.parseInt(txtReqAmount.getText()));
                      if (orderPlaceBo.checkAllReadyAddCart(setCartTmObs, selectItemCode) == -1) {
                          setCartTmObs.add(
                                  setCartTm
                          );

                      } else {

                          int selectRow = orderPlaceBo.checkAllReadyAddCart(setCartTmObs, selectItemCode);
                          int qty = setCartTmObs.get(selectRow).getReqQty() + setCartTm.getReqQty();
                          setCartTm = orderPlaceBo.setCartTm(selectItemCode, qty);
                          setCartTmObs.add(
                                  setCartTm
                          );
                          setCartTmObs.remove(selectRow);
                      }

                  } catch (SQLException throwables) {
                      throwables.printStackTrace();
                  }
                  setItemDetails(itemDto);
                  tblOrderDetails.setItems(setCartTmObs);
                  lblTotalPrice.setText(String.valueOf(orderPlaceBo.TotalCostGenerate(setCartTmObs)));
              } else {
                  new Alert(Alert.AlertType.WARNING, "Invalid Qty..!", ButtonType.OK).showAndWait();
              }
          }
      }
    }



    public void btnConfirmOrderOnAction(ActionEvent actionEvent) {

        if(CombSelectCustomerID.getSelectionModel().getSelectedIndex()<0){
            new Alert(Alert.AlertType.WARNING, "Please Select Customer...!", ButtonType.OK).showAndWait();
        }else if( CombSelectItemCode.getSelectionModel().getSelectedIndex()<0){
            new Alert(Alert.AlertType.WARNING, "Please Select Item...!", ButtonType.OK).showAndWait();
        }else if(setCartTmObs.size()==0){
            new Alert(Alert.AlertType.WARNING, "Please add item qty...!", ButtonType.OK).showAndWait();
        }else{
            ArrayList<OrderDetailDto> orderDetails = new ArrayList<>();
            try {
                for (CartTmDto orderDetail : setCartTmObs
                ) {
                    orderDetails.add(
                            new OrderDetailDto(
                                    lblOrderId.getText(),
                                    orderDetail.getItemCode(),
                                    orderDetail.getReqQty(),
                                    orderDetail.getPrice()
                            )
                    );
                }

                OrdersDto ordersDto = new OrdersDto(
                        lblOrderId.getText(),
                        CombSelectCustomerID.getValue(),
                        lblDate.getText(),
                        lblTime.getText(),
                        Double.parseDouble(lblTotalPrice.getText())
                );

                if (orderPlaceBo.placeOrder(ordersDto, orderDetails)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully Saved..!", ButtonType.OK).showAndWait();
                    setCartTmObs.clear();
                    txtReqAmount.clear();
                    lblTotalPrice.setText("0.00");
                } else {
                    new Alert(Alert.AlertType.WARNING, "Wrong Please Try Again..!", ButtonType.OK).showAndWait();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void ManageOrderChange(Event event) {
    }

    public void btnMoreOrdersOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOrderOnAction(ActionEvent actionEvent) {


    }

    public void DeleteOrderOnAction(ActionEvent actionEvent) {
    }

    public void AddCustomerChange(Event event) {
        try {
           txtCustomerID.setText(customerBo.generateCustomerId());
           txtCustomerID.setDisable(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnAddCustomer(ActionEvent actionEvent) {


        if(custValidate(txtCustomerTitle,txtCustomerName,txtCustomerAddress,txtCustomerCity,txtCustomerProvince,txtCustomerPostalCode)) {

            try {
                boolean b = customerBo.saveCustomer(new CustomerDto(
                        txtCustomerID.getText(),
                        txtCustomerTitle.getText(),
                        txtCustomerName.getText(),
                        txtCustomerAddress.getText(),
                        txtCustomerCity.getText(),
                        txtCustomerProvince.getText(),
                        txtCustomerPostalCode.getText()
                ));

                if (b == true) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully Saved..!").showAndWait();
                    loadCustomerIdsAndItemCodes();
                    clearAddCustomer();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Already Entered Customer ID").showAndWait();
                    clearAddCustomer();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "Wrong please check Details...!").showAndWait();
        }
    }

    public void clearAddCustomer(){
        txtCustomerID.clear();
        txtCustomerTitle.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerCity.clear();
        txtCustomerProvince.clear();
        txtCustomerPostalCode.clear();
    }
    public void clearUpdateText(){
        txtCustID.clear();
        tctCustTitle.clear();
        txtCustName.clear();
        txtCustAddress.clear();
        txtCustCity.clear();
        txtCustProvince.clear();
        txtCustPostalCOde.clear();
    }


    public void btnSearch(ActionEvent actionEvent) {
        try {
            CustomerDto customerDto = customerBo.searchCustomer(txtEnterCustID.getText());
            txtCustID.setText(customerDto.getCustomerId());
            tctCustTitle.setText(customerDto.getCustomerTitle());
            txtCustName.setText(customerDto.getCustomerName());
            txtCustAddress.setText(customerDto.getCustomerAddress());
            txtCustCity.setText(customerDto.getCustomerCity());
            txtCustProvince.setText(customerDto.getProvince());
            txtCustPostalCOde.setText(customerDto.getPostalCode());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean custValidate(TextField...txtField){
        if(validation.validationTxt(title,txtField[0])&
                validation.validationTxt(name,txtField[1])&
                validation.validationTxt(address,txtField[2])&
                validation.validationTxt(name,txtField[3])&
                validation.validationTxt(name,txtField[4])&
                validation.validationTxt(postalCode,txtField[5])) {

            return true;
        }
        return false;
    }

    public void btnUpdate(ActionEvent actionEvent) {

        if(custValidate(tctCustTitle,txtCustName,txtCustAddress,txtCustCity,txtCustProvince,txtCustPostalCOde)) {
            try {
                boolean b = customerBo.updateCustomer(new CustomerDto(
                        txtCustID.getText(),
                        tctCustTitle.getText(),
                        txtCustName.getText(),
                        txtCustAddress.getText(),
                        txtCustCity.getText(),
                        txtCustProvince.getText(),
                        txtCustPostalCOde.getText()
                ));

                if (b == true) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully Update..!").showAndWait();
                    clearAddCustomer();
                    clearUpdateText();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Already Entered Customer ID").showAndWait();
                    clearAddCustomer();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "Wrong please check Details...!").showAndWait();
        }
    }

    public void btnDelete(ActionEvent actionEvent) {
        try {

            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.INFORMATION, "Are You Sure Delete..!", ButtonType.YES, ButtonType.NO).showAndWait();
            if(ButtonType.YES.equals(buttonType.get())) {
                boolean b = customerBo.deleteCustomer(txtCustID.getText());


                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Delete Successfully..!", ButtonType.OK).showAndWait();
                    clearUpdateText();

                } else {
                    new Alert(Alert.AlertType.WARNING, "Please try Again..!", ButtonType.OK).showAndWait();
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

    public void btnLogOut(ActionEvent actionEvent) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do You Want to Logout ?", ButtonType.APPLY.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            Parent parent = FXMLLoader.load(this.getClass().getResource("../VIew/LogIn.fxml"));
            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) this.rootCashierForm.getScene().getWindow();

            primaryStage.setScene(scene);
            primaryStage.setTitle("Login Form");
            primaryStage.show();
            primaryStage.centerOnScreen();
        }
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.INFORMATION, "Are you sure clear ?", ButtonType.YES, ButtonType.NO).showAndWait();

        if(ButtonType.YES.equals(buttonType.get())) {

            if(setCartTmObs.size()==0){
                new Alert(Alert.AlertType.WARNING, "Empty Cart..!", ButtonType.OK).showAndWait();
            }else {
                int selectedIndex = tblOrderDetails.getSelectionModel().getSelectedIndex();
                int newQty = Integer.parseInt(lblItemQtyOnHand.getText()) + setCartTmObs.get(selectedIndex).getReqQty();
                lblItemQtyOnHand.setText(String.valueOf(newQty));
                double newPrice = Double.parseDouble(lblTotalPrice.getText()) - setCartTmObs.get(selectedIndex).getPrice();
                lblTotalPrice.setText(String.valueOf(newPrice));

                setCartTmObs.remove(selectedIndex);
            }
        }

    }

    public void btnClearOrderOnAction1(ActionEvent actionEvent) {

        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.INFORMATION, "Are you sure clear ?", ButtonType.YES, ButtonType.NO).showAndWait();

        if(ButtonType.YES.equals(buttonType.get())) {

            if(setCartTmObs.size()==0) {
                new Alert(Alert.AlertType.WARNING, "Empty Cart..!", ButtonType.OK).showAndWait();
            }else {
                for (CartTmDto cart : setCartTmObs
                ) {
                    if (selectItemCode.equals(cart.getItemCode())) {
                        int i = Integer.parseInt(lblItemQtyOnHand.getText()) + cart.getReqQty();
                        lblItemQtyOnHand.setText(String.valueOf(i));
                    }
                }
                txtReqAmount.clear();
                lblTotalPrice.setText("0.00");
                setCartTmObs.clear();
            }
        }
    }

    public void loadCustomerIdsAndItemCodes(){

        try {
            CombSelectCustomerID.getItems().clear();
            CombSelectItemCode.getItems().clear();
            CombSelectCustomerID.setItems(customerBo.getCustomerIds());
            CombSelectItemCode.setItems(itemBo.getItemCodes());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void setCustomerDetails(CustomerDto customerDto) throws SQLException {
        lblCustomerName.setText(customerDto.getCustomerName());
        lblVerified.setVisible(true);
        imgCustomer.setVisible(true);
        lblVerification.setVisible(true);
        OrderIdText.setVisible(true);
        lblOrderId.setVisible(true);
        lblCustomerAddress.setText(customerDto.getCustomerAddress());
        lblCostomerCity.setText(customerDto.getCustomerCity());
        lblCustomerProvince.setText(customerDto.getProvince());
        lblCustomerPostalCode.setText(customerDto.getPostalCode());
        lblOrderId.setText(orderPlaceBo.getOrderId());
    }



    public void setItemDetails(ItemDto itemDto) throws SQLException {

        lblItemDescription.setText(itemDto.getDescription());
        lblItemQtyOnHand.setText(String.valueOf(itemDto.getQtyOnHand()-orderPlaceBo.mineQty(setCartTmObs,selectItemCode)));
        lblItemDiscount.setText(String.valueOf(itemDto.getDiscountRate())+"%");
        lblItemUnitPrice.setText("Rs."+String.valueOf(itemDto.getUnitPrice())+"/=");
        double AfterDiscount;
        AfterDiscount=itemDto.getUnitPrice()*itemDto.getDiscountRate()/100;
        AfterDiscountPrice=itemDto.getUnitPrice()-AfterDiscount;
        lblAfterDiscountPrice.setText("Rs."+String.valueOf(AfterDiscountPrice)+"/=");

    }


    public void loadTimeAndDate(){
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
}