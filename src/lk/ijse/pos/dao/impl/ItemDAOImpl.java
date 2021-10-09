/*
 *
 *  * ---------------------------------------------------------------------------------------------
 *  *  *  Copyright (c) GDSE-exam. All rights reserved.
 *  *  *  Licensed under the SriLankan Information License. See License.txt in the project root for license information.
 *  *  *--------------------------------------------------------------------------------------------
 *
 */

package lk.ijse.pos.dao.impl;

import lk.ijse.pos.dao.CrudUtils;
import lk.ijse.pos.dao.ItemDAO;
import lk.ijse.pos.entity.Item;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author Mohamed Aazaf <aazafmax2@gmail.com> (www.aazafbiz.web.app)
 * @since 10/9/21
 */
public class ItemDAOImpl implements ItemDAO {


    @Override
    public boolean add(Item item) throws Exception {
        return CrudUtils.executeUpdate("INSERT INTO item VALUES(?,?,?,?)",
                item.getCode(),
                item.getDescription(),
                item.getUnitPrice(),
                item.getQtyOnHand());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtils.executeUpdate("DELETE FROM Item where code = ? ", s);
    }

    @Override
    public boolean update(Item item) throws Exception {
        return CrudUtils.executeUpdate("UPDATE Item set description=?, unitPrice=?, qtyOnHand=? WHERE code=?",
                item.getDescription(),
                item.getUnitPrice(),
                item.getQtyOnHand(),
                item.getCode());
    }

    @Override
    public Item search(String s) throws Exception {
        ResultSet rst = CrudUtils.executeQuery("SELECT * FROM Item WHERE CODE=?",s);
        if (rst.next()){
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getInt(4));
        }
        return null;
    }

    @Override
    public ArrayList<Item> getAll() throws Exception {
        ResultSet rst = CrudUtils.executeQuery("SELECT * FROM Item");
        ArrayList<Item> allItems = new ArrayList<>();
        while (rst.next()){
            Item item = new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getInt(4));
            allItems.add(item);
        }
        return allItems;
    }
}
