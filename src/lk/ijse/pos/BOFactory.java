/*
 *
 *  * ---------------------------------------------------------------------------------------------
 *  *  *  Copyright (c) GDSE-exam. All rights reserved.
 *  *  *  Licensed under the SriLankan Information License. See License.txt in the project root for license information.
 *  *  *--------------------------------------------------------------------------------------------
 *
 */

package lk.ijse.pos;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.bo.impl.ItemBOImpl;

/**
 * @author Mohamed Aazaf <aazafmax2@gmail.com> (www.aazafbiz.web.app)
 * @since 10/9/21
 */
public class BOFactory {
    private static lk.ijse.pos.bo.BOFactory boFactory;

    private BOFactory() {
    }

    public static lk.ijse.pos.bo.BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new lk.ijse.pos.bo.BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes {
        CUSTOMER, ITEM
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case ITEM:
                return new ItemBOImpl();
            default:
                return null;
        }
    }
}
