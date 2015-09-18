package com.easymarket.core;

/**
 * Temporary solution
 *
 * It means supplier category/type.
 *
 *  This enum is used by Supplier and SupplierDepartment.
 *
 */
public enum SupplierType {

    //TODO - We should change this enum to a database table(@table). We should allow admins to add supplier type in database.
    MARKET,
    BABY_STORE,
    PHARMACY;
}
