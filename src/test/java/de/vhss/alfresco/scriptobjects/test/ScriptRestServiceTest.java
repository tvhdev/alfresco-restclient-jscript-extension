package de.vhss.alfresco.scriptobjects.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tradeshift.test.remote.Remote;
import com.tradeshift.test.remote.RemoteTestRunner;

import de.vhss.alfresco.scriptobjects.ScriptRestService;

/**
 * A simple class demonstrating how to run out-of-container tests 
 * loading Alfresco application context.
 * 
 * This class uses the RemoteTestRunner to try and connect to 
 * localhost:4578 and send the test name and method to be executed on 
 * a running Alfresco. One or more hostnames can be configured in the @Remote
 * annotation.
 * 
 * If there is no available remote server to run the test, it falls 
 * back on local running of JUnits.
 * 
 * For proper functioning the test class file must match exactly 
 * the one deployed in the webapp (either via JRebel or static deployment)
 * otherwise "incompatible magic value XXXXX" class error loading issues will arise.  
 * 
 * @author Gabriele Columbro 
 * @author Maurizio Pillitu
 *
 */
@RunWith(RemoteTestRunner.class)
@Remote(runnerClass=SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:alfresco/application-context.xml")
public class ScriptRestServiceTest {
    
    private static final String ADMIN_USER_NAME = "admin";

    static Logger log = Logger.getLogger(ScriptRestServiceTest.class);

    @Autowired
    protected ScriptRestService scriptRestService;
    
    @Test
    public void testWiring() {
        assertNotNull(scriptRestService);
    }
    
    @Test
    public void testGet() {
    	AuthenticationUtil.setFullyAuthenticatedUser(ADMIN_USER_NAME);
    	String ret = scriptRestService.get("http://www.heise.de");
    	assertNotEquals(ret, "error");
    }
}
