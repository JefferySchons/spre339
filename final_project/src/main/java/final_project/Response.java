package final_project;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response
{
	private int code;
	
	public Response()
	{
		
	}
	
	public Response(int code)
	{
		this.code = code;
	}
	
	@JsonProperty("status")
	public void setStatus(int code)
	{
		this.code = code;
	}
	
	@JsonProperty("status")
	public int getStatus()
	{
		return code;
	}
}
