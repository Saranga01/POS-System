package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDao;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao {

    @Override
    public boolean add(Item item) throws SQLException {
        return CrudUtil.executeUpdate("INSERT INTO Item VALUES(?,?,?,?,?,?)", item.getItemCode(), item.getDescription(),item.getPackSize(), item.getUnitPrice(), item.getQtyOnHand(), item.getDiscRate());

    }

    @Override
    public boolean delete(String s) throws SQLException {
        return  CrudUtil.executeUpdate("DELETE FROM Item WHERE ItemCode=?",s);

    }

    @Override
    public boolean update(Item item) throws SQLException {
        return CrudUtil.executeUpdate("UPDATE Item SET Description=?,PackSize=?,UnitPrice=?,QtyOnHand=?,DiscRate=? WHERE ItemCode=?", item.getDescription(),item.getPackSize(), item.getUnitPrice(), item.getQtyOnHand(), item.getDiscRate(),item.getItemCode());

    }

    @Override
    public Item search(String s) throws SQLException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Item WHERE ItemCode=?", s);

        resultSet.next();
        return new Item(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getDouble(4),
                resultSet.getInt(5),
                resultSet.getDouble(6)
        );
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException {
        ArrayList<Item> items=new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Item");
        while (resultSet.next()){
            items.add(
                    new Item(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4),
                            resultSet.getInt(5),
                            resultSet.getDouble(6)
                    )
            );
        }
        return items;
    }

    @Override
    public ObservableList<String> getItemCodes() throws SQLException {
        ObservableList<String> itemIds = FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Item");
        while (resultSet.next()) {
            itemIds.add(
                    resultSet.getString(1));
        }

        return itemIds;
    }

    @Override
    public boolean updateQty(String itemCode, int newQty) throws SQLException {
        return CrudUtil.executeUpdate("UPDATE Item SET QtyOnHand=? WHERE ItemCode=?", newQty,itemCode);
    }

    @Override
    public String getLastCodeId() throws SQLException {
        ResultSet rst = CrudUtil.executeQuery("SELECT ItemCode From `Item` ORDER BY ItemCode DESC LIMIT 1");
        return rst.next() ? String.format("I/%03d", (Integer.parseInt(rst.getString("ItemCode").replace("I/", "")) + 1)) : "I/001";
    }
}
