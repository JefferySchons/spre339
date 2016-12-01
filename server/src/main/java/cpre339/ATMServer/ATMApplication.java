package cpre339.ATMServer;

import java.util.List;

import cpre339.ATMServer.json.User;
import cpre339.ATMServer.resource.AccountResource;
import cpre339.ATMServer.resource.UserResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ATMApplication extends Application<ATMConfiguration>
{
	public static void main(String[] args) throws Exception
	{
		new ATMApplication().run(args);
	}
	
	@Override
	public String getName()
	{
		return "atm";
	}
	
	@Override
	public void initialize(Bootstrap<ATMConfiguration> bootstrap)
	{
		
	}
	
	@Override
	public void run(ATMConfiguration config, Environment env)
	{
		final UserResource user = new UserResource();
		final AccountResource account = new AccountResource();
		
		env.jersey().register(user);
		env.jersey().register(account);
	}
}
