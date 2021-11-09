package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao<T,Id> {

    boolean add(T t) throws SQLException;

    boolean delete(Id id) throws SQLException;

    boolean update(T t) throws SQLException;

    T search(Id id) throws SQLException;

    ArrayList<T> getAll() throws SQLException;
}
