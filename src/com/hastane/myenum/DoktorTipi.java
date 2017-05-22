package com.hastane.myenum;

public enum DoktorTipi {
	
	STAJYER, KALICI, ZIYARETEDEN,DIGER;

	public static DoktorTipi getDoktorTipi(String scins) {

		for (DoktorTipi uzman : DoktorTipi.values()) {
			if (uzman.name().equals(scins)) {
				return uzman;
			}
			if ("STAJYER".equalsIgnoreCase(scins)) {
				return DoktorTipi.STAJYER;
			} else if ("KALICI".equalsIgnoreCase(scins)) {
				return DoktorTipi.KALICI;
			} else {
				return DoktorTipi.ZIYARETEDEN;
			}
		}

		return DIGER;

	}

}
