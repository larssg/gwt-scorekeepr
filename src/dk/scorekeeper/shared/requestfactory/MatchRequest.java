package dk.scorekeeper.shared.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

import dk.scorekeeper.server.domain.Match;
import dk.scorekeeper.shared.domain.proxy.MatchProxy;

@Service(Match.class)
public interface MatchRequest extends RequestContext {
	Request<List<MatchProxy>> listAll();
	InstanceRequest<MatchProxy, Void> put();
}
