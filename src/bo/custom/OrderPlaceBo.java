package bo.custom;

import bo.SuperBo;
import dto.CartTmDto;
import dto.OrderDetailDto;
import dto.OrdersDto;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderPlaceBo extends SuperBo {
    public String getOrderId() throws SQLException;
    public CartTmDto setCartTm(String itemCode,int reqQty) throws SQLException;
    public int checkAllReadyAddCart(ObservableList<CartTmDto> cartTm,String itemCode);
    public int mineQty(ObservableList<CartTmDto> cartTm,String itemCode);
    public double TotalCostGenerate(ObservableList<CartTmDto> cart);
    public boolean placeOrder(OrdersDto ordersDto, ArrayList<OrderDetailDto> orderDetails) throws SQLException;


}
