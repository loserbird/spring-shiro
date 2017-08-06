package com.loserbird.service;

import com.loserbird.model.Permission;

public interface PermissionService {
	 public Permission createPermission(Permission permission);
	    public void deletePermission(Long permissionId);
}
