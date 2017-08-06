package com.loserbird.dao;

import com.loserbird.model.Permission;

public interface PermissionDao {
	 public Permission createPermission(Permission permission);

	 public void deletePermission(Long permissionId);
}
