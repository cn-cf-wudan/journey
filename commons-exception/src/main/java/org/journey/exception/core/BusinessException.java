package org.journey.exception.core;

/**
* @ClassName: BusinessException
* @ClassNameExplain: 业务异常
* @Description: 异常的统一处理
* @author wudan-mac
* @date 2016年4月1日 上午11:04:49
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1;

	private int code;

	private String errorMsg;
	
	/**
	* @Description  构造自己的异常
	* @param code 错误码 
	* @param errorMsg 错误描述
	* @author wudan-mac
	 */
	public BusinessException(int code,String errorMsg){
		super(errorMsg);
		this.code = code;
		this.errorMsg = errorMsg;
	}
	
	/**
	* @Description  业务异常
	* @author wudan-mac
	 */
	public BusinessException(int code,String errorMsg,Throwable throwable){
		super(errorMsg,throwable);
		this.code = code;
		this.errorMsg = errorMsg;
	}
	
	public int getCode(){
		return code;
	}
	
	public String getErrorMsg(){
		return errorMsg;
	}

}
