package bo.custom.impl;

import bo.custom.ItemBo;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;
import dto.ItemDto;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo{

    ItemDao itemDao=new ItemDaoImpl();

    @Override
    public String generateItemCode() throws SQLException {
        int tempId=Integer.parseInt(itemDao.getLastCodeId().split("/")[1]);
        if(tempId<10){
            return  "I/00"+tempId;
        }else if(tempId<100){
            return "I/0"+tempId;
        }else {
            return "I"+tempId;
        }
    }

    @Override
    public boolean saveItem(ItemDto itemDto) throws SQLException {

        return itemDao.add(
                new Item(
                        itemDto.getItemCode(),
                        itemDto.getDescription(),
                        itemDto.getPackSize(),
                        itemDto.getUnitPrice(),
                        itemDto.getQtyOnHand(),
                        itemDto.getDiscountRate()
                )
        );


    }

    @Override
    public boolean updateItem(ItemDto itemDto) throws SQLException {
        return itemDao.update(new Item(
                itemDto.getItemCode(),
                itemDto.getDescription(),
                itemDto.getPackSize(),
                itemDto.getUnitPrice(),
                itemDto.getQtyOnHand(),
                itemDto.getDiscountRate()
        ));
    }

    @Override
    public ItemDto searchItem(String Id) throws SQLException {
        Item item = itemDao.search(Id);

        ItemDto itemDto=new ItemDto(
          item.getItemCode(),
          item.getDescription(),
          item.getPackSize(),
          item.getUnitPrice(),
          item.getQtyOnHand(),
          item.getDiscRate()
        );
        return itemDto;
    }

    @Override
    public boolean deleteItem(String Id) throws SQLException {
        return itemDao.delete(Id);
    }

    @Override
    public ObservableList<ItemDto> viewAllItems() throws SQLException {
        ArrayList<Item> getAllItems = itemDao.getAll();
        ObservableList<ItemDto> setAllItems= FXCollections.observableArrayList();

        for (Item allItems:getAllItems
        ) {
            setAllItems.add(
                    new ItemDto(allItems.getItemCode(),allItems.getDescription(),allItems.getPackSize(),allItems.getUnitPrice(),allItems.getQtyOnHand(),allItems.getDiscRate())
            );
        }

        return setAllItems;
    }

    @Override
    public void setItemTableValue(TableColumn... tableValue) {
        String [] ItemProperty=new String[7];
        ItemProperty[0]="itemCode";
        ItemProperty[1]="description";
        ItemProperty[2]="packSize";
        ItemProperty[3]="unitPrice";
        ItemProperty[4]="qtyOnHand";
        ItemProperty[5]="discountRate";

        for(int i=0;i<tableValue.length;i++){
            tableValue[i].setCellValueFactory(new PropertyValueFactory<>(ItemProperty[i]));
            tableValue[i].setStyle("-fx-alignment: CENTER;");
        }

    }

    @Override
    public ObservableList<String> getItemCodes() throws SQLException {
        return itemDao.getItemCodes();
    }
}
