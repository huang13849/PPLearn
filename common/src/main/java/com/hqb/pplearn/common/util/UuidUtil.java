package com.hqb.pplearn.common.util;

import java.util.UUID;

public class UuidUtil {

	public static String getNewUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
