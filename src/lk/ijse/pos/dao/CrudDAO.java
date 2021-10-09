/*
 *
 *  * ---------------------------------------------------------------------------------------------
 *  *  *  Copyright (c) GDSE-exam. All rights reserved.
 *  *  *  Licensed under the SriLankan Information License. See License.txt in the project root for license information.
 *  *  *--------------------------------------------------------------------------------------------
 *
 */

package lk.ijse.pos.dao;

import java.util.ArrayList;

/**
 * @author Mohamed Aazaf <aazafmax2@gmail.com> (www.aazafbiz.web.app)
 * @since 10/9/21
 */
public interface CrudDAO<T, ID> extends SuperDAO{

    boolean add(T t) throws Exception;

    boolean delete(ID id) throws Exception;

    boolean update(T t) throws Exception;

    T search(ID id) throws Exception;

    ArrayList<T> getAll() throws Exception;

}
