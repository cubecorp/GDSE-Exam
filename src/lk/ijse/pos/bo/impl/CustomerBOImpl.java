/*
 *
 *  * ---------------------------------------------------------------------------------------------
 *  *  *  Copyright (c) GDSE-exam. All rights reserved.
 *  *  *  Licensed under the SriLankan Information License. See License.txt in the project root for license information.
 *  *  *--------------------------------------------------------------------------------------------
 *
 */

package lk.ijse.pos.bo.impl;

import lk.ijse.pos.bo.CustomerBO;
import lk.ijse.pos.dao.CustomerDAO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;
import java.util.ArrayList;

/**
 * @author Mohamed Aazaf <aazafmax2@gmail.com> (www.aazafbiz.web.app)
 * @since 10/9/21
 */
public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean addCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.add(new Customer(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary()));
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return customerDAO.delete(id);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.update(new Customer(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary()));
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws Exception {
        Customer search = customerDAO.search(id);
        return new CustomerDTO(search.getId(), search.getName(), search.getAddress(), search.getSalary());
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : all) {
            customerDTOS.add(new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getSalary()));
        }
        return customerDTOS;
    }

}
