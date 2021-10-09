/*
 *
 *  * ---------------------------------------------------------------------------------------------
 *  *  *  Copyright (c) GDSE-exam. All rights reserved.
 *  *  *  Licensed under the SriLankan Information License. See License.txt in the project root for license information.
 *  *  *--------------------------------------------------------------------------------------------
 *
 */

package lk.ijse.pos.bo;

import lk.ijse.pos.dto.ItemDTO;

import java.util.ArrayList;

/**
 * @author Mohamed Aazaf <aazafmax2@gmail.com> (www.aazafbiz.web.app)
 * @since 10/9/21
 */
public interface ItemBO extends SuperBO{
    boolean addItem(ItemDTO itemDTO) throws Exception;

    boolean deleteItem(String code) throws Exception;

    boolean updateItem(ItemDTO itemDTO) throws Exception;

    ItemDTO searchItem(String code)throws Exception;

    ArrayList<ItemDTO> getAllItems()throws Exception;
}
