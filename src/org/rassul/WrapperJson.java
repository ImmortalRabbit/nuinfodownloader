package org.rassul;

import java.util.List;

/**
 * Created by rassul on 2/24/17.
 */
public class WrapperJson {
    private List<Row> rows;
    private List<String> departmentPath;
    private String departmentPathEN;
    private String organizationName;
    private int count;
    private int departmentId;
    private int organisationId;
    private String groupEmail;

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public List<String> getDepartmentPath() {
        return departmentPath;
    }

    public void setDepartmentPath(List<String> departmentPath) {
        this.departmentPath = departmentPath;
    }

    public String getDepartmentPathEN() {
        return departmentPathEN;
    }

    public void setDepartmentPathEN(String departmentPathEN) {
        this.departmentPathEN = departmentPathEN;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(int organisationId) {
        this.organisationId = organisationId;
    }

    public String getGroupEmail() {
        return groupEmail;
    }

    public void setGroupEmail(String groupEmail) {
        this.groupEmail = groupEmail;
    }
}
