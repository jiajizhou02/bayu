package com.bayu.returnJson;

public class Permission {

    private String permissionId;



    public String getPermissionId() {
        return permissionId;
    }

    public Permission() {
    }

    public Permission(String permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId='" + permissionId + '\'' +
                '}';
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}



