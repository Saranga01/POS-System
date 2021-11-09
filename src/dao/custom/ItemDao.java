package dao.custom;

import dao.CrudDao;
import entity.Item;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ItemDao extends CrudDao<Item,String> {
    ObservableList<String> getItemCodes() throws SQLException;
    public boolean updateQty(String itemCode,int newQty) throws SQLException;
    public String getLastCodeId() throws SQLException;
}
