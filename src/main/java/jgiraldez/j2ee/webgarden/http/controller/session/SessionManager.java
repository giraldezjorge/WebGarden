package jgiraldez.j2ee.webgarden.http.controller.session;

import jgiraldez.j2ee.webgarden.model.categoria.vo.CategoriaVO;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.delegate.
	ProductsManagementFacadeDelegate;
import jgiraldez.j2ee.webgarden.model.productsmanagementfacade.delegate.
	ProductsManagementFacadeDelegateFactory;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.delegate.
	UsersManagementFacadeDelegate;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.delegate.
	UsersManagementFacadeDelegateFactory;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.exceptions.
	IncorrectPasswordException;
import jgiraldez.j2ee.webgarden.model.usersmanagementfacade.vo.LoginResultVO;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import jgiraldez.j2ee.webgarden.util.exceptions.InternalErrorException;
import jgiraldez.j2ee.webgarden.util.exceptions.InstanceNotFoundException;
import jgiraldez.j2ee.webgarden.util.exceptions.DuplicateInstanceException;


public final class SessionManager {

    public final static String FIRST_NAME_SESSION_ATTRIBUTE = "firstName";
    public final static String NIF_SESSION_ATTRIBUTE = "NIF";
    public final static String TYPE_SESSION_ATTRIBUTE = "type";
    private final static String 
    	USERS_MANAGEMENT_FACADE_DELEGATE_SESSION_ATTRIBUTE = 
    		"usersManagementFacadeDelegate";
    private final static String 
    	PRODUCTS_MANAGEMENT_FACADE_DELEGATE_SESSION_ATTRIBUTE =
    		"productsManagementFacadeDelegate";

    public static final String LOGIN_NAME_COOKIE = "loginName";
    public static final String ENCRYPTED_PASSWORD_COOKIE = "encryptedPassword";
    public static final String LOGIN_BY_COOKIE = "loginBy";

    private static final int COOKIES_TIME_TO_LIVE_REMEMBER_MY_PASSWORD =
        30 * 24 * 3600; // 30 days
    private static final int COOKIES_TIME_TO_LIVE_REMOVE = 0;

    private SessionManager() {}
    
    public final static void login(HttpServletRequest request, 
    		HttpServletResponse response, String loginName, 
    		String clearPassword, boolean rememberMyPassword, String loginBy) 
    		throws IncorrectPasswordException, 
    		InstanceNotFoundException,InternalErrorException {

        /* 
         * Try to login, and if successful, update session with the necessary 
         * objects for an authenticated user.
         */ 
        LoginResultVO loginResultVO = 
        	doLogin(request, loginName, clearPassword, false, loginBy);
        
        /* Add cookies if requested. */
        if (rememberMyPassword) {
            leaveCookies(response, loginName, 
            			loginResultVO.getEncryptedPassword(), 
            			COOKIES_TIME_TO_LIVE_REMEMBER_MY_PASSWORD);
        }
        
    }

    /**
     * Destroyes session, and removes cookies if the user had selected
     * "remember my password.
     */
    public final static void logout(HttpServletRequest request,
        HttpServletResponse response) throws InternalErrorException {
    
        /* Remove cookies. */
        leaveCookies(response, "", "", COOKIES_TIME_TO_LIVE_REMOVE);
    
        /* Invalidate session. */
        HttpSession session = request.getSession(true);
        session.invalidate();
    
    }

    public final static UsersManagementFacadeDelegate 
    	getUsersManagementFacadeDelegate(HttpServletRequest request) 
    	throws InternalErrorException {

        HttpSession session = request.getSession(true);
        UsersManagementFacadeDelegate usersManagementFacadeDelegate = 
            (UsersManagementFacadeDelegate) session.getAttribute(
                USERS_MANAGEMENT_FACADE_DELEGATE_SESSION_ATTRIBUTE);
                
        if (usersManagementFacadeDelegate == null) {
            usersManagementFacadeDelegate = 
            		UsersManagementFacadeDelegateFactory.getDelegate();
            session.setAttribute(
            		USERS_MANAGEMENT_FACADE_DELEGATE_SESSION_ATTRIBUTE, 
            		usersManagementFacadeDelegate);
        }

        return usersManagementFacadeDelegate;
        
    }
    
    public final static ProductsManagementFacadeDelegate 
    	getProductsManagementFacadeDelegate(HttpServletRequest request) 
    	throws InternalErrorException {
    	
    	HttpSession session = request.getSession(true);
    	ProductsManagementFacadeDelegate productsManagementFacadeDelegate = 
    		(ProductsManagementFacadeDelegate) session.getAttribute(
    			PRODUCTS_MANAGEMENT_FACADE_DELEGATE_SESSION_ATTRIBUTE);
    	
    	if (productsManagementFacadeDelegate == null) {
    		
    		productsManagementFacadeDelegate = 
    				ProductsManagementFacadeDelegateFactory.getDelegate();
    		session.setAttribute(
    				PRODUCTS_MANAGEMENT_FACADE_DELEGATE_SESSION_ATTRIBUTE, 
    				productsManagementFacadeDelegate);
    		
    	}
    	
    	return productsManagementFacadeDelegate;
    	
    }
    
    /**
     * Guarantees that the session will have the necessary objects if the user
     * has been authenticated or had selected "remember my password" in the 
     * past.
     */
    public final static void touchSession(HttpServletRequest request) 
        throws InternalErrorException {
    
        /* Check if "firstName" is in the session. */
        String firstName = null;
        HttpSession session = request.getSession(false);
        
        if (session != null) {
        
            firstName = (String) session.getAttribute(
                FIRST_NAME_SESSION_ATTRIBUTE);
                
            if (firstName != null) {
                return;
            }
            
        }
        
        /* 
         * The user had not been authenticated or his/her session has expired. 
         * We need to check if the user has selected "remember my password" in 
         * the last login (login name and password will come as cookies). If 
         * so, we reconstruct user's session objects.
         */    
        updateSessionFromCookies(request);
    
    }

    public final static boolean isUserAuthenticated(
    		HttpServletRequest request) {
    
        HttpSession session = request.getSession(false);
        
        if (session == null) {
            return false;
        } else {
            return session.getAttribute(FIRST_NAME_SESSION_ATTRIBUTE) != null;
        }
    
    }
    
    public final static boolean isAdmin(HttpServletRequest request) {
    	
    	HttpSession session = request.getSession(false);
    	
    	if (session == null) {
    		return false;
    	} else {
    		return session.getAttribute(TYPE_SESSION_ATTRIBUTE).equals("admin"); 
    	}
    }
    
    public final static boolean isEmployee(HttpServletRequest request) {
    	
    	HttpSession session = request.getSession(false);
    	
    	if (session == null) {
    		return false;
    	} else {
    	
    		boolean employee;
    		if (session.getAttribute(TYPE_SESSION_ATTRIBUTE).equals("employee") 
    		|| session.getAttribute(TYPE_SESSION_ATTRIBUTE).equals("admin")) {
    			employee = true;
    		} else {
    			employee = false;
    		}
    		
    		return employee; 
    	}
    }
    
    private final static void updateSessionForAuthenticatedUser(
        HttpServletRequest request, String firstName, String NIF, String type) 
    	throws InternalErrorException {
        
        /* Insert objects in session. */
        HttpSession session = request.getSession(true);
        session.setAttribute(FIRST_NAME_SESSION_ATTRIBUTE, firstName);
        session.setAttribute(NIF_SESSION_ATTRIBUTE, NIF);
        session.setAttribute(TYPE_SESSION_ATTRIBUTE, type);
//        session.setAttribute(Globals.LOCALE_KEY, locale);
    }
    
    /**
     * Tries to login (inserting necessary objects in the session) by using 
     * cookies (if present).
     */
    private final static void updateSessionFromCookies(
        HttpServletRequest request) throws InternalErrorException {
        
        /* Are there cookies in the request ? */
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return;
        }

        /* 
         * Check if the login name, the encrypted password and the type come as 
         * cookies. 
         */
        String loginName = null;
        String encryptedPassword = null;
        String loginBy = null;
        int foundCookies = 0;
        
        for (int i=0; (i<cookies.length) && (foundCookies < 3); i++) {
            if (cookies[i].getName().equals(LOGIN_NAME_COOKIE)) {
                loginName = cookies[i].getValue();
                foundCookies++;
            }
            if (cookies[i].getName().equals(ENCRYPTED_PASSWORD_COOKIE)) {
                encryptedPassword = cookies[i].getValue();
                foundCookies++;
            }
            if (cookies[i].getName().equals(LOGIN_BY_COOKIE)) {
                loginBy = cookies[i].getValue();
                foundCookies++;
            }
        }

        if (foundCookies != 3) {
            return;
        }
        
        /* Try to login, and if successful, update session with the necessary 
         * objects for an authenticated user.
         */
        try {
            doLogin(request, loginName, encryptedPassword, true, loginBy);
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) { // Incorrect loginName or encryptedPassword
            return;
        }

    }
    
    private final static void leaveCookies(HttpServletResponse response,
        String loginName, String encryptedPassword, int timeToLive) {
        
         /* Create cookies. */
        Cookie loginNameCookie = new Cookie(LOGIN_NAME_COOKIE, loginName);
        Cookie encryptedPasswordCookie = new Cookie(ENCRYPTED_PASSWORD_COOKIE, 
            encryptedPassword);

        /* Set maximum age to cookies. */
        loginNameCookie.setMaxAge(timeToLive);
        encryptedPasswordCookie.setMaxAge(timeToLive);

        /* Add cookies to response. */
        response.addCookie(loginNameCookie);
        response.addCookie(encryptedPasswordCookie);

    }
    
    private final static LoginResultVO doLogin(HttpServletRequest request,
         String loginName, String password, boolean passwordIsEncrypted, 
         String loginBy) throws IncorrectPasswordException, 
         InstanceNotFoundException, InternalErrorException {
         
         /* Check "loginName" and "clearPassword". */
//        UsersManagementFacadeDelegate usersManagementFacadeDelegate = 
//    			getUsersManagementFacadeDelegate(request);
        UsersManagementFacadeDelegate usersManagementFacadeDelegate = 
        		UsersManagementFacadeDelegateFactory.getDelegate();
        LoginResultVO loginResultVO = usersManagementFacadeDelegate.login(
        				loginName, password, passwordIsEncrypted, loginBy);

        /* Insert necessary objects in the session. */
        updateSessionForAuthenticatedUser(request, loginResultVO.getFirstName(),
        		loginResultVO.getNIF(), loginResultVO.getType());
        System.out.println(loginResultVO.toString());
        
        /* Return "loginResultVO". */
        return loginResultVO;
         
    }
    
    public final static String getNIF(HttpServletRequest request) {
    	
    	HttpSession session = request.getSession(false);
    	
    	return (String) session.getAttribute(NIF_SESSION_ATTRIBUTE);
    }

}