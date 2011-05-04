package dk.scorekeeper.shared.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

import dk.scorekeeper.server.service.UserDao;
import dk.scorekeeper.server.service.locator.DaoServiceLocator;
import dk.scorekeeper.shared.domain.proxy.UserProxy;

@Service(value = UserDao.class, locator = DaoServiceLocator.class)
public interface UserRequest extends RequestContext {
	Request<List<UserProxy>> listAll();
	Request<UserProxy> saveAndReturn(UserProxy user);
}
