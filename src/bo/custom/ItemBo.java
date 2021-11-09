package bo.custom;

import bo.SuperBo;
import dto.ItemDto;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.sql.SQLException;

public interface ItemBo extends SuperBo {
    public String generateItemCode() throws SQLException;
    public boolean saveItem(ItemDto itemDto) throws SQLException;
    public boolean updateItem(ItemDto itemDto) throws SQLException;
    public ItemDto searchItem(String Id) throws SQLException;
    public boolean deleteItem(String Id) throws SQLException;
    public ObservableList<ItemDto> viewAllItems() throws SQLException;
    public void setItemTableValue(TableColumn... tableValue);
    public ObservableList<String> getItemCodes() throws SQLException;
}
