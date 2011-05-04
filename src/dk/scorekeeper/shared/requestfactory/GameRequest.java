package dk.scorekeeper.shared.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

import dk.scorekeeper.server.service.GameDao;
import dk.scorekeeper.server.service.locator.DaoServiceLocator;
import dk.scorekeeper.shared.domain.proxy.GameProxy;

@Service(value = GameDao.class, locator = DaoServiceLocator.class)
public interface GameRequest extends RequestContext {
	Request<List<GameProxy>> listAll();
	Request<GameProxy> saveAndReturn(GameProxy game);
}
