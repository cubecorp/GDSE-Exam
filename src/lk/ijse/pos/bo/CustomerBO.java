/*
 *
 *  * ---------------------------------------------------------------------------------------------
 *  *  *  Copyright (c) GDSE-exam. All rights reserved.
 *  *  *  Licensed under the SriLankan Information License. See License.txt in the project root for license information.
 *  *  *--------------------------------------------------------------------------------------------
 *
 */

package lk.ijse.pos.bo;

import lk.ijse.pos.dto.CustomerDTO;

import java.util.ArrayList;

/**
 * @author Mohamed Aazaf <aazafmax2@gmail.com> (www.aazafbiz.web.app)
 * @since 10/9/21
 */
public interface CustomerBO extends SuperBO{
    boolean addCustomer(CustomerDTO customer) throws Exception;

    boolean deleteCustomer(String id) throws Exception;

    boolean updateCustomer(CustomerDTO customer) throws Exception;

    CustomerDTO searchCustomer(String id) throws Exception;

    ArrayList<CustomerDTO> getAllCustomers() throws Exception;
}
