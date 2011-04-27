package dk.scorekeeper.shared.requestfactory;

import java.util.List;

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

import dk.scorekeeper.server.domain.User;
import dk.scorekeeper.shared.domain.proxy.UserProxy;

@Service(User.class)
public interface UserRequest extends RequestContext {
	Request<List<UserProxy>> findAllUsers();
	InstanceRequest<UserProxy, Void> persist();
}
