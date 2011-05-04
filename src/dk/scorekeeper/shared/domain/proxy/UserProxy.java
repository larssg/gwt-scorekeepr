package dk.scorekeeper.shared.domain.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

import dk.scorekeeper.server.domain.User;
import dk.scorekeeper.server.service.locator.ObjectifyLocator;

@ProxyFor(value = User.class, locator = ObjectifyLocator.class)
public interface UserProxy extends DatastoreObjectProxy {
	String getEmail();
	String getFullName();
	String getPassword();
	String getUserName();
	void setEmail(String email);
	void setFullName(String fullName);
	void setPassword(String password);
	void setUserName(String userName);
}