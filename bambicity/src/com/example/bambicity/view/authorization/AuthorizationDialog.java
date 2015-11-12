package com.example.bambicity.view.authorization;

import java.util.ArrayList;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;

import com.example.bambicity.APILayers.Authorization.AuthorizationManager;
import com.example.bambicity.APILayers.Authorization.AuthorizationManagerConfig;
import com.example.bambicity.model.UserInfoModel;
import com.example.bambicity.view.main.MainActivity;

public class AuthorizationDialog {
	private MainActivity mainActivity;
	private String choiseUserAccount;
	private AuthorizationManager authorizationManager;
	private AuthorizationManagerConfig authorizationManagetConfig;
	
	public AuthorizationDialog(MainActivity mainActivity)
	{
		this.mainActivity = mainActivity;
		this.authorizationManagetConfig = new AuthorizationManagerConfig();
		this.authorizationManager = new AuthorizationManager(authorizationManagetConfig);
	}
	
	public void start() throws Exception
	{
		final CharSequence [] csUserAccounts = getUserAccounts();
		choiseUserAccount = csUserAccounts[0].toString();
		Builder ad = new AlertDialog.Builder(mainActivity).setSingleChoiceItems(csUserAccounts, 0, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				choiseUserAccount = csUserAccounts[which].toString();
			}
		}).setCancelable(false)	
        .setPositiveButton("Вход",
        	new DialogInterface.OnClickListener()
        	{
            	@Override
				public void onClick(DialogInterface dialog, int whichButton) 
            	{  
            		UserInfoModel.getInstance().getProgressDialog().show();
            		
            		UserInfoModel.getInstance().setUserAccount(choiseUserAccount);
            		authorizationManagetConfig.setMainActivity(mainActivity);
      	 			authorizationManager.send();
            	}
            })
        .setNegativeButton("Выход",
        	new DialogInterface.OnClickListener()
        	{
                @Override
				public void onClick(DialogInterface dialog, int whichButton)
                {
                	android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(0);
                }
            });
			ad.setTitle("Выберете аккаунт:").show();
	}
	
	
	private CharSequence [] getUserAccounts() throws Exception
	{
		AccountManager manager = (AccountManager) mainActivity.getSystemService(Context.ACCOUNT_SERVICE);
		ArrayList<String> userAccounts = initUserAccounts(manager);
		return userAccounts.toArray(new CharSequence[userAccounts.size()]);
	}
	
	public ArrayList <String> initUserAccounts(AccountManager manager) throws Exception
	{
		Account [] accounts = manager.getAccountsByType("com.google");
		if (accounts == null) 
            throw new Exception("Accounts not found");
		
		ArrayList <String> userAccounts = new ArrayList <String>();
		for(Account account : accounts)
		{
			userAccounts.add(account.name);
		}
		return userAccounts;
	}
}
