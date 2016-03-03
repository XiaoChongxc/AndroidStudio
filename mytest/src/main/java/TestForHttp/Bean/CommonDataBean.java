package TestForHttp.Bean;

/**
 * 基础回传数据格式
 * <ul>
 * <li>success ，是否成功，Boolean</li>
 * <li>code ，错误代码，String</li>
 * <li>text ，错误信息，String</li>
 * </ul>
 * 
 * @date 2015年8月18日
 * @author qsxiaogang
 */
public abstract class CommonDataBean
{
	protected Boolean success;
	protected String code;
	protected String text;

	/**
	 * @return success ，是否成功，Boolean
	 */
	public Boolean getSuccess()
	{
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(Boolean success)
	{
		this.success = success;
	}

	/**
	 * @return code ，错误代码，String
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * @return text ，错误信息，String
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text)
	{
		this.text = text;
	}

}
