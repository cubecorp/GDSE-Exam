/*
 *
 *  * ---------------------------------------------------------------------------------------------
 *  *  *  Copyright (c) GDSE-exam. All rights reserved.
 *  *  *  Licensed under the SriLankan Information License. See License.txt in the project root for license information.
 *  *  *--------------------------------------------------------------------------------------------
 *
 */

package lk.ijse.pos.view.tm;

/**
 * @author Mohamed Aazaf <aazafmax2@gmail.com> (www.aazafbiz.web.app)
 * @since 10/9/21
 */
public class CustomerTM {
    String id;
    String name;
    String address;
    double salary;

    public CustomerTM() {
    }

    public CustomerTM(String id, String name, String address, double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
