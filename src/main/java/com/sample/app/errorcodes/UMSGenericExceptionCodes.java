package com.sample.app.errorcodes;

public enum UMSGenericExceptionCodes {

	EMAIL_ALREADY_EXISTS("EG-001","Email already exists."),
	EMAIL_DOES_NOT_EXISTS("EG-003","Email Does not exists."),
	APPLICATION_ERROR_OCCURED("EG-002", "Application error occured"),

	;	
	
	
	private String errCode;
	private String errMsg;

	private UMSGenericExceptionCodes(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String errCode() {
		return this.errCode;
	}

	public String errMsg() {
		return this.errMsg;
	}

   public static UMSGenericExceptionCodes forName(String errorCode) {
      UMSGenericExceptionCodes errCode = null;
      for (UMSGenericExceptionCodes code : values()) {
         if (code.name().equals(errorCode)) {
            return code;
         }
      }
      return errCode;
   }
}
