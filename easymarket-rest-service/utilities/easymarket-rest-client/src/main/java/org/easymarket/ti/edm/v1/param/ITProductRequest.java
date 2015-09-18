package org.easymarket.ti.edm.v1.param;

/**
 * Model used to ask for supplier around.
 */
public class ITProductRequest extends ITNearByRequest {

    private String departmentId;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

}
