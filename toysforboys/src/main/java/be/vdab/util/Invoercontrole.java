package be.vdab.util;

import java.math.BigDecimal;
import java.util.Objects;

public class Invoercontrole {
	public final static Long positiveLong(long param, String error) throws IllegalArgumentException {
		if (param < 0) {
			throw new IllegalArgumentException(error);
		}
		return param;
	}

	
	public final static String noEmptyOrNullString(String param, String error) throws IllegalArgumentException {
		if (param == null || param.equals("")) {
			throw new IllegalArgumentException(error);
		}
		return param;
	}


	public final static BigDecimal positiveBigDecimal(BigDecimal param, String error)
			throws IllegalArgumentException, NullPointerException {
		Objects.requireNonNull(param, "parameter cannot be null");
		if (param.signum() == -1) {
			throw new IllegalArgumentException(error);
		}
		return param;
	}
}
