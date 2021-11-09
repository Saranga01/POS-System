package bo.custom.impl;

import bo.custom.OrderPlaceBo;
import dao.custom.ItemDao;
import dao.custom.OrderDao;
import dao.custom.impl.ItemDaoImpl;
import dao.custom.impl.OrderDaoImpl;
import db.DbConnection;
import dto.CartTmDto;
import dto.OrderDetailDto;
import dto.OrdersDto;
import entity.Item;
import entity.OrderDetail;
import entity.Orders;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderPlaceBoImpl implements OrderPlaceBo {

    OrderDao orderDao=new OrderDaoImpl();
    ItemDao itemDao=new ItemDaoImpl();

    @Override
    public String getOrderId() throws SQLException {

            int tempId=Integer.parseInt(orderDao.getOrderId().split("/")[1]);
            if(tempId<10){
                return  "O/00"+tempId;
            }else if(tempId<100){
                return "O/0"+tempId;
            }else {
                return "O"+tempId;
            }

    }

    @Override
    public CartTmDto setCartTm(String itemCode,int reqQty) throws SQLException {


        ResultSet resultSet = orderDao.getItemPrice(itemCode);
        resultSet.next();
        double itemPrice = resultSet.getDouble(4);
        double discount=resultSet.getDouble(6);
        double discountPrice= itemPrice/100*discount;
        double afterDiscountPrice=itemPrice-discountPrice;
        double reqQtyPrice=afterDiscountPrice*reqQty;

      
        CartTmDto setCartTm = new CartTmDto(itemCode, reqQty, reqQtyPrice);

        return setCartTm;

    }

    @Override
    public int checkAllReadyAddCart(ObservableList<CartTmDto> cartTm,String itemCode) {

        int i=-1;
        for (CartTmDto cart:cartTm
             ) {
             i++;
             if(cart.getItemCode().equals(itemCode)){
                return i;
             }
        }
        return -1;
    }


    @Override
    public int mineQty(ObservableList<CartTmDto> cartTm, String itemCode) {
        int qty=0;
        for (CartTmDto cart:cartTm
             ) {
            if(cart.getItemCode().equals(itemCode)){
                qty=cart.getReqQty();
            }

        }
        return qty;
    }



    @Override
    public double TotalCostGenerate(ObservableList<CartTmDto> cart) {
        double totalCost=0.0;
        for (CartTmDto cartTm:cart
             ) {
            totalCost+=cartTm.getPrice();
        }
        return totalCost;
    }

    @Override
    public boolean placeOrder(OrdersDto ordersDto, ArrayList<OrderDetailDto> orderDetails) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        boolean saveOrder=false;
        boolean saveOrderDetail=false;


        try {
            connection.setAutoCommit(false);
            saveOrder = orderDao.add(new Orders(ordersDto.getOrderId(), ordersDto.getCustId(), ordersDto.getOrderDate(), ordersDto.getOrderTime(), ordersDto.getTotalCost()));

            for (OrderDetailDto orderDetail:orderDetails) {
                saveOrderDetail = orderDao.orderDetailsSave(new OrderDetail(orderDetail.getOrderId(), orderDetail.getItemCode(), orderDetail.getOrderQTY(), orderDetail.getItemCost()));

            }

            if(saveOrder){
                if(saveOrderDetail){
                    connection.commit();

                    for (OrderDetailDto updateItem:orderDetails
                         ) {
                        Item rsl = itemDao.search(updateItem.getItemCode());
                        int newQty = rsl.getQtyOnHand() - updateItem.getOrderQTY();
                        itemDao.updateQty(updateItem.getItemCode(),newQty);

                    }
                    
                    return true;

                }else{
                    connection.rollback();
                    return false;
                }


            }else{
                connection.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

            connection.setAutoCommit(true);
        }

        return false;
    }

}
