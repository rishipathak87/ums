package com.sample.app.errorcodes;


public enum UMSRequestExceptionCodes {
	
	INVALID_PASSWORD("ER-001","Please enter a valid password with 6 digits."),
	INVALID_EMAIL("ER-002","Please enter a Valid email"),
	INVALID_GENDER("ER-003","Please select a valid gender"),
	INVALID_NAME("ER-004","Please select a valid name"),
	INVALID_DOB("ER-005","please Enter Date in valid format"),
	INVALID_REQUEST("ER-006","Invalid Request"),
	
	;
	
	private String errCode;
	private String errMsg;

	private UMSRequestExceptionCodes(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String errCode() {
		return this.errCode;
	}

	public String errMsg() {
		return this.errMsg;
	}

   public static UMSRequestExceptionCodes forName(String errorCode) {
      UMSRequestExceptionCodes errCode = null;
      for (UMSRequestExceptionCodes code : values()) {
         if (code.name().equals(errorCode)) {
            return code;
         }
      }
      return errCode;
   }
}
