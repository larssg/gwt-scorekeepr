package dk.scorekeeper.shared.domain.proxy;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

import dk.scorekeeper.server.domain.User;

@ProxyFor(User.class)
public interface UserProxy extends EntityProxy {
	String getEmail();
	String getFullName();
	Long getId();
	String getUserName();
	void setEmail(String email);
	void setFullName(String fullName);
	void setUserName(String userName);
}