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
import lk.ijse.pos.dao.CustomerDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author Mohamed Aazaf <aazafmax2@gmail.com> (www.aazafbiz.web.app)
 * @since 10/9/21
 */
public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer customer) throws Exception {
        return CrudUtils.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?)",
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getSalary());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtils.executeUpdate("DELETE FROM Customer WHERE id=?", s);
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        return CrudUtils.executeUpdate("UPDATE Customer SET name=?, address=?, salary=? WHERE id=?",
                customer.getName(),
                customer.getAddress(),
                customer.getSalary(),
                customer.getId());
    }

    @Override
    public Customer search(String s) throws Exception {
        ResultSet rst = CrudUtils.executeQuery("SELECT * FROM Customer WHERE id=?", s);
        if (rst.next()) {
            return new Customer(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getDouble(4));
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        ResultSet rst = CrudUtils.executeQuery("SELECT * FROM Customer");
        ArrayList<Customer> allCustomers = new ArrayList<>();
        while (rst.next()) {
            Customer customer = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4));
            allCustomers.add(customer);
        }
        return allCustomers;
    }
}
