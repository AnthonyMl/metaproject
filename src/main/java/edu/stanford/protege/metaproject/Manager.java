package edu.stanford.protege.metaproject;

import edu.stanford.protege.metaproject.api.*;
import edu.stanford.protege.metaproject.api.exception.MetaprojectNotLoadedException;
import edu.stanford.protege.metaproject.api.exception.ObjectConversionException;
import edu.stanford.protege.metaproject.api.exception.ServerConfigurationNotLoadedException;
import edu.stanford.protege.metaproject.api.exception.UnknownAccessControlObjectIdException;
import edu.stanford.protege.metaproject.impl.FactoryImpl;
import edu.stanford.protege.metaproject.serialization.DefaultJsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class Manager {
    private static final DefaultJsonSerializer serializer = new DefaultJsonSerializer();
    private static ServerConfiguration serverConfiguration;
    private static Factory factory;

    /**
     * Get a factory for creating metaproject-related objects
     *
     * @return Factory
     */
    public static Factory getFactory() {
        if(factory == null) {
            factory = new FactoryImpl();
        }
        return factory;
    }

    /**
     * Set the server configuration
     *
     * @param serverConfig  Server configuration
     */
    public static void setServerConfiguration(ServerConfiguration serverConfig) {
        serverConfiguration = checkNotNull(serverConfig, "Server configuration must not be null");
    }

    /**
     * Load a server configuration from a file
     *
     * @param f Server configuration file
     * @return Server configuration
     * @throws FileNotFoundException    File specified was not found
     * @throws ObjectConversionException   JSON object could not be converted to a Java object
     */
    public static ServerConfiguration loadServerConfiguration(File f) throws FileNotFoundException, ObjectConversionException {
        checkNotNull(f, "Configuration file must not be null");
        serverConfiguration = serializer.parseServerConfiguration(f);
        return serverConfiguration;
    }

    /**
     * Load a client configuration from a file
     *
     * @param f Client configuration file
     * @return Client configuration
     * @throws FileNotFoundException    File specified was not found
     * @throws ObjectConversionException   JSON object could not be converted to a Java object
     */
    public static ClientConfiguration loadClientConfiguration(File f) throws FileNotFoundException, ObjectConversionException {
        checkNotNull(f, "Configuration file must not be null");
        return serializer.parseClientConfiguration(f);
    }

    /**
     * Get the server configuration loaded by this manager
     *
     * @return Server configuration
     * @throws ServerConfigurationNotLoadedException    Server configuration has not been loaded
     */
    public static ServerConfiguration getServerConfiguration() throws ServerConfigurationNotLoadedException {
        if(serverConfiguration == null) {
            throw new ServerConfigurationNotLoadedException("No server configuration has been loaded.");
        }
        return serverConfiguration;
    }

    /**
     * Get the metaproject loaded by this manager
     *
     * @return Metaproject
     * @throws MetaprojectNotLoadedException    Metaproject definition has not been loaded
     * @throws ServerConfigurationNotLoadedException    Server configuration has not been loaded
     */
    public static Metaproject getMetaproject() throws MetaprojectNotLoadedException, ServerConfigurationNotLoadedException {
        if (serverConfiguration == null) {
            throw new ServerConfigurationNotLoadedException("No server configuration has been loaded.");
        }
        if (serverConfiguration.getMetaproject() == null) {
            throw new MetaprojectNotLoadedException("No metaproject definition has been loaded.");
        }
        return serverConfiguration.getMetaproject();
    }

    /**
     * Verify whether access control object(s) are registered with the given registry
     *
     * @param registry  Registry for given access control object
     * @param objects   One or more access control object identifiers
     * @throws UnknownAccessControlObjectIdException Unknown access control object identifier exception
     */
    public static void checkExistence(Registry registry, AccessControlObjectId... objects) throws UnknownAccessControlObjectIdException {
        for (AccessControlObjectId obj : objects) {
            checkNotNull(obj);
            if (!registry.contains(obj)) {
                throw new UnknownAccessControlObjectIdException("The specified access control object identifier " + obj.get() + ", of type " + obj.getType() +
                        ", does not correspond to a known one. It may not have been registered with the appropriate manager.");
            }
        }
    }
}
