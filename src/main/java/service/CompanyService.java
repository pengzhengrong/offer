package service;

import java.util.List;
import java.util.Map;

import entrty.CompanyPo;
import entrty.JobPo;
import entrty.UserPo;

public interface CompanyService {
	
	public int checkUser( String userName , String pwd );
	
	public int publishJob( JobPo po , int companyId );
	
	public int update(CompanyPo po);
	
	public int changePwd( String newPwd , int companyId);
	
	public CompanyPo getCompany( String userName);
	
	public int getId( String userName );
	
}
