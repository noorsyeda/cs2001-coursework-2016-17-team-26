import java.io.InputStream;

class Test
{
	public static void  main(String args[])
	{
		try
		{
			  Process p=Runtime.getRuntime().exec("ps");

			  InputStream is = p.getInputStream(); //or p.getErrorStream() on error
			  int c;
                          StringBuilder commandResponse = new StringBuilder();

		          while( (c = is.read()) != -1) 
			  {
                          	commandResponse.append((char)c);
        		  }
		          System.out.println(commandResponse);   //Print command response
			  is.close();
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}
	}
}
