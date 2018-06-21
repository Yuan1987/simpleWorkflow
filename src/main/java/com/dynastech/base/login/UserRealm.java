package com.dynastech.base.login;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.dynastech.base.common.service.ICommonService;
import com.dynastech.system.entity.Resource;
import com.dynastech.system.entity.User;
import com.dynastech.system.service.IResourceManagerService;
import com.dynastech.system.service.IUserManagerService;


/**
 * @author yuan
 */
public class UserRealm extends AuthorizingRealm {  
	
	@Autowired
	private IUserManagerService userManagerService;
	
	@Autowired
	private IResourceManagerService resourceManagerService;
	
	@Autowired
	ICommonService commonService;
	
	
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
    	 String username = (String) principals.getPrimaryPrincipal();
         SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
         
         /*Set<Role> roles = userService.findRoles(username);
         Set<String> roleNames = new HashSet<String>();
         for (Role role : roles) {
             roleNames.add(role.getRole());
         }
         authorizationInfo.setRoles(roleNames);*/
         
         User user=commonService.getCurrentUser();
         
         List<Resource> list = resourceManagerService.findResourceByUserId(user.getId(), null);
         
         Set<String> permissionNames = new HashSet<String>();
         for (Resource re : list) {
             permissionNames.add(re.getPermission());
         }
         authorizationInfo.setStringPermissions(permissionNames);

         return authorizationInfo; 
    }  
  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(  
            AuthenticationToken authcToken) throws AuthenticationException{
    	
        String username = (String)authcToken.getPrincipal();
        //得到密码  
       // String password = new String((char[])authcToken.getCredentials()); 
        
       /* String userName = "zhangsan"; // 用户名称  
        String password = "123456"; // 密码  
        String host = "ucpro.net"; // AD服务器  
        String port = "80"; // 端口  
        String domain = "@ucpro.net"; // 邮箱的后缀名  
        String url = new String("ldap://" + host + ":" + port);  
        String user = userName.indexOf(domain) > 0 ? userName : userName + domain;  
        Hashtable<String, String> env = new Hashtable<String, String>();  
        DirContext ctx;  
        env.put(Context.SECURITY_AUTHENTICATION, "simple");  
        env.put(Context.SECURITY_PRINCIPAL, user);   
        env.put(Context.SECURITY_CREDENTIALS, password);  
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");  
        env.put(Context.PROVIDER_URL, url);  
  
        try {  
            ctx = new InitialDirContext(env);  
            ctx.close();  
            System.out.println("验证成功！");  
        } catch (NamingException e) {  
            e.printStackTrace();
            System.out.println("验证失败！");  
        }  */
        
        User user2 = userManagerService.selectByUserName(username);
    	if (user2 == null) {
			throw new UnknownAccountException();
		}else{
			//user2.setPassword(password);
		}
    	//后期不会使用该方式 ，故现为明文密码
        return new SimpleAuthenticationInfo(username,user2.getPassword(), getName());  
    }
  
}  