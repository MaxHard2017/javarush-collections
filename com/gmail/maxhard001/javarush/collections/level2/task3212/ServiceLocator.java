package com.gmail.maxhard001.javarush.collections.level2.task3212;

import com.gmail.maxhard001.javarush.collections.level2.task3212.service.Service;
import com.gmail.maxhard001.javarush.collections.level2.task3212.context.InitialContext;

public class ServiceLocator {
    private static Cache cache;

    static {
        cache = new Cache();
    }

    /**
     * First, check for a service object in the cache
     * If a service object is not in the cache, perform a lookup using
     * the JNDI initial context and get the service object. Add it to
     * the cache for future use.
     *
     * @param jndiName The name of the service object in the context
     * @return Object mapped to the name in context
     */
    public static Service getService(String jndiName) {
        
        Service servSesult = cache.getService(jndiName);
        
        if (servSesult == null) {
            InitialContext context = new InitialContext();
            servSesult = (Service) (context.lookup(jndiName));
            cache.addService(servSesult);
        }
        
        return servSesult;
    }
}
