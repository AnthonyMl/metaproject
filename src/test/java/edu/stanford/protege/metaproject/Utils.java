package edu.stanford.protege.metaproject;

import edu.stanford.protege.metaproject.api.*;
import edu.stanford.protege.metaproject.api.impl.*;
import org.semanticweb.owlapi.model.IRI;

import java.util.*;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class Utils {
    private static final int DEFAULT_SET_SIZE = 3;
    private static final OperationType DEFAULT_OPERATION_TYPE = OperationType.READ;
    private static final OperationPrerequisite.Modifier DEFAULT_MODIFIER = OperationPrerequisite.Modifier.PRESENT;
    private static final String IRI_PREFIX = "http://protege.stanford.edu/test/";
    private static final Random random = new Random();
    
    /*   access control object identifiers   */

    public static UserId getUserId() {
        return getUserId("userId" + newUUID());
    }

    public static UserId getUserId(String userId) {
        return new UserIdImpl(userId);
    }

    public static RoleId getRoleId() {
        return getRoleId("roleId" + newUUID());
    }

    public static RoleId getRoleId(String roleId) {
        return new RoleIdImpl(roleId);
    }

    public static ProjectId getProjectId() {
        return getProjectId("projectId" + newUUID());
    }

    public static ProjectId getProjectId(String projectId) {
        return new ProjectIdImpl(projectId);
    }

    public static OperationId getOperationId() {
        return getOperationId("operationId" + newUUID());
    }

    public static OperationId getOperationId(String operationId) {
        return new OperationIdImpl(operationId);
    }


    /*   entity IRIs   */

    public static EntityIriPrefix getEntityIriPrefix() {
        return getEntityIriPrefix(IRI_PREFIX);
    }

    public static EntityIriPrefix getEntityIriPrefix(String prefix) {
        return new EntityIriPrefixImpl(prefix);
    }

    public static EntityNamePrefix getEntityNamePrefix() {
        return getEntityNamePrefix("prefix" + newUUID());
    }

    public static EntityNamePrefix getEntityNamePrefix(String prefix) {
        return new EntityNamePrefixImpl(prefix);
    }

    public static EntityNameSuffix getEntityNameSuffix() {
        return getEntityNameSuffix("" + random.nextInt(Integer.SIZE - 1));
    }

    public static EntityNameSuffix getEntityNameSuffix(String suffix) {
        return new EntityNameSuffixImpl(suffix);
    }

    public static EntityName getEntityName() {
        return getEntityName(Utils.getEntityNamePrefix(), Utils.getEntityNameSuffix());
    }

    public static EntityName getEntityName(EntityNamePrefix prefix, EntityNameSuffix suffix) {
        return new EntityNameImpl(prefix, suffix);
    }

    public static EntityIri getEntityIri() {
        return getEntityIri(Utils.getEntityIriPrefix(), Utils.getEntityName());
    }

    public static EntityIri getEntityIri(EntityIriPrefix prefix, EntityName suffix) {
        return new EntityIriImpl(prefix, suffix);
    }


    /*   basic elements   */

    public static Name getName() {
        return getName("name " + newUUID());
    }

    public static Name getName(String name) {
        return new NameImpl(name);
    }

    public static Description getDescription() {
        return getDescription("description " + newUUID());
    }

    public static Description getDescription(String description) {
        return new DescriptionImpl(description);
    }

    public static Address getAddress() {
        return getAddress("http://protege.stanford.edu/" + newUUID());
    }

    public static Address getAddress(String address) {
        return new AddressImpl(address);
    }

    public static EmailAddress getEmailAddress() {
        return getEmailAddress(newUUID() + "@email.com");
    }

    public static EmailAddress getEmailAddress(String address) {
        return new EmailAddressImpl(address);
    }

    public static OperationPrerequisite getOperationPrerequisite() {
        return getOperationPrerequisite(DEFAULT_MODIFIER);
    }

    public static OperationPrerequisite getOperationPrerequisite(OperationPrerequisite.Modifier modifier) {
        return getOperationPrerequisite(getIRI(), modifier);
    }

    public static OperationPrerequisite getOperationPrerequisite(IRI iri, OperationPrerequisite.Modifier modifier) {
        return new OperationPrerequisiteImpl(iri, modifier);
    }

    public static IRI getIRI() {
        return getIRI(IRI_PREFIX + newUUID());
    }

    public static IRI getIRI(String iri) {
        return IRI.create(iri);
    }

    public static String newUUID() {
        return UUID.randomUUID().toString();
    }


    /*   authentication   */

    public static SaltedPasswordDigest getSaltedPassword() {
        return getSaltedPassword("testPassword" + random.nextInt(), getSalt());
    }

    public static SaltedPasswordDigest getSaltedPassword(String password, Salt salt) {
        return Utils.getPasswordMaster().createHash(new PlainPasswordImpl(password), salt);
    }

    public static PlainPassword getPlainPassword() {
        return getPlainPassword("testPassword" + newUUID());
    }

    public static PlainPassword getPlainPassword(String password) {
        return new PlainPasswordImpl(password);
    }

    public static Salt getSalt() {
        return getSaltGenerator().generate();
    }

    public static Salt getSalt(String s) {
        return new SaltImpl(s);
    }

    public static AuthenticationDetails getAuthenticationDetails() {
        return getAuthenticationDetails(getUserId(), getSaltedPassword());
    }

    public static AuthenticationDetails getAuthenticationDetails(UserId userId, SaltedPasswordDigest password) {
        return new AuthenticationDetailsImpl(userId, password);
    }

    public static SaltGenerator getSaltGenerator() {
        return new SaltGeneratorImpl();
    }

    public static SaltGenerator getSaltGenerator(int byteSize) {
        return new SaltGeneratorImpl(byteSize);
    }

    public static PasswordHasher getPasswordMaster() {
        return new Pbkdf2PasswordHasher.Builder().createPasswordHasher();
    }

    public static PasswordHasher getPasswordMaster(int hashByteSize, int nrPBKDF2Iterations, SaltGenerator saltGenerator) {
        return new Pbkdf2PasswordHasher.Builder()
                .setHashByteSize(hashByteSize)
                .setNumberOfIterations(nrPBKDF2Iterations)
                .setSaltGenerator(saltGenerator)
                .createPasswordHasher();
    }


    /*   access control policy managers   */

    public static PolicyManager getPolicyManager() {
        return new PolicyManagerImpl();
    }

    public static PolicyManager getPolicyManager(Map<UserId, Set<RoleId>> map) {
        return new PolicyManagerImpl(map);
    }

    public static UserManager getUserManager() {
        return new UserManagerImpl();
    }

    public static UserManager getUserManager(Set<User> users) {
        return new UserManagerImpl(users);
    }

    public static RoleManager getRoleManager() {
        return new RoleManagerImpl();
    }

    public static RoleManager getRoleManager(Set<Role> roles) {
        return new RoleManagerImpl(roles);
    }

    public static OperationManager getOperationManager() {
        return new OperationManagerImpl();
    }

    public static OperationManager getOperationManager(Set<Operation> operations) {
        return new OperationManagerImpl(operations);
    }

    public static ProjectManager getProjectManager() {
        return new ProjectManagerImpl();
    }

    public static ProjectManager getProjectManager(Set<Project> projects) {
        return new ProjectManagerImpl(projects);
    }

    public static AuthenticationManager getAuthenticationManager() {
        return new AuthenticationManagerImpl(Utils.getAuthenticationDetailsSet());
    }

    public static AuthenticationManager getAuthenticationManager(Set<AuthenticationDetails> authDetails) {
        return new AuthenticationManagerImpl(authDetails);
    }


    /*   policy and server/client configurations   */

    public static Server getServer(ServerConfiguration configuration, EntityIriGenerator idGenerator) {
        return new ServerImpl(configuration, idGenerator);
    }

    public static Client getClient(ClientConfiguration configuration) {
        return new ClientImpl(configuration);
    }

    public static ServerConfiguration getServerConfiguration() {
        return getServerConfiguration(Utils.getHost(), Utils.getAccessControlPolicy(), Utils.getAuthenticationManager(), Utils.getStringPropertyMap(), Utils.getEntityIriStatus());
    }

    public static ServerConfiguration getServerConfiguration(Host host, AccessControlPolicy accessControlPolicy, AuthenticationManager authenticationManager, Map<String, String> properties, EntityIriStatus idStatus) {
        return new ServerConfigurationImpl.Builder()
                .setHost(host)
                .setAccessControlPolicy(accessControlPolicy)
                .setAuthenticationManager(authenticationManager)
                .setPropertyMap(properties)
                .setEntityIriStatus(idStatus)
                .createServerConfiguration();
    }

    public static ClientConfiguration getClientConfiguration() {
        return getClientConfiguration(Utils.getAccessControlPolicy(), random.nextInt(), Utils.getGUIRestrictionSet(), Utils.getStringPropertyMap());
    }

    public static ClientConfiguration getClientConfiguration(AccessControlPolicy accessControlPolicy, int syncDelay, Set<GuiRestriction> disabledUIComponents, Map<String,String> propertyMap) {
        return new ClientConfigurationImpl(accessControlPolicy, syncDelay, disabledUIComponents, propertyMap);
    }

    public static Host getHost() {
        return getHost(Utils.getAddress(), random.nextInt());
    }

    public static Host getHost(Address address, int port) {
        return new HostImpl(address, port);
    }

    public static AccessControlPolicy getPolicySample() {
        return new TestPolicy().getPolicy();
    }

    public static AccessControlPolicy getAccessControlPolicy() {
        return getAccessControlPolicy(Utils.getPolicyManager(Utils.getUserRoleMap()), Utils.getUserManager(Utils.getUserSet()),
                Utils.getRoleManager(Utils.getRoleSet()), Utils.getOperationManager(Utils.getOperationSet()), Utils.getProjectManager(Utils.getProjectSet()));
    }

    public static AccessControlPolicy getAccessControlPolicy(PolicyManager policyManager, UserManager userManager, RoleManager roleManager, OperationManager operationManager, ProjectManager projectManager) {
        return new AccessControlPolicyImpl.Builder()
                .setPolicyManager(policyManager)
                .setUserManager(userManager)
                .setRoleManager(roleManager)
                .setOperationManager(operationManager)
                .setProjectManager(projectManager)
                .createAccessControlPolicy();
    }

    public static Map<UserId,Set<RoleId>> getUserRoleMap() {
        Map<UserId,Set<RoleId>> map = new HashMap<>();
        for(int i = 0; i < DEFAULT_SET_SIZE; i++) {
            map.put(Utils.getUserId(), Utils.getRoleIdSet());
        }
        return map;
    }

    public static Map<String,String> getStringPropertyMap() {
        Map<String,String> map = new HashMap<>();
        map.put("prop1", "value1");
        map.put("prop2", "value2");
        return map;
    }


    /*   access control policy objects   */

    public static Project getProject() {
        return getProject(getProjectId(), getName(), getDescription(), getAddress(), getUserId(), getUserIdSet());
    }

    public static Project getProject(ProjectId id, Name name, Description description, Address address, UserId owner, Set<UserId> administrators) {
        return new ProjectImpl(id, name, description, address, owner, administrators);
    }

    public static Operation getOperation() {
        return getOperation(getOperationId(), getName(), getDescription(), DEFAULT_OPERATION_TYPE, Optional.of(getOperationPrerequisiteSet(getOperationPrerequisite())));
    }

    public static Operation getOperation(OperationId id, Name operationName, Description description, OperationType type, Optional<Set<OperationPrerequisite>> prerequisites) {
        return new OperationImpl(id, operationName, description, type, prerequisites);
    }

    public static Role getRole() {
        return getRole(getRoleId(), getName(), getDescription(), getProjectIdSet(DEFAULT_SET_SIZE), getOperationIdSet(DEFAULT_SET_SIZE));
    }

    public static Role getRole(ProjectId project, OperationId operation) {
        Set<ProjectId> projects = new HashSet<>();
        projects.add(project);

        Set<OperationId> operations = new HashSet<>();
        operations.add(operation);

        return getRole(getRoleId(), getName(), getDescription(), projects, operations);
    }

    public static Role getRole(RoleId id, Name name, Description description, Set<ProjectId> projects, Set<OperationId> operations) {
        return new RoleImpl(id, name, description, projects, operations);
    }

    public static User getUser() {
        return getUser(getUserId(), getName(), getEmailAddress());
    }

    public static User getUser(UserId userId, Name name, EmailAddress emailAddress) {
        return new UserImpl(userId, name, emailAddress);
    }


    /*   sets of access control policy objects   */

    public static Set<User> getUserSet() {
        return getUserSet(DEFAULT_SET_SIZE);
    }

    public static Set<User> getUserSet(int size) {
        Set<User> users = new HashSet<>();
        for(int i = 0; i < size; i++) {
            users.add(getUser());
        }
        return users;
    }

    public static Set<User> getUserSet(User... users) {
        Set<User> userSet = new HashSet<>();
        Collections.addAll(userSet, users);
        return userSet;
    }

    public static Set<Role> getRoleSet() {
        return getRoleSet(DEFAULT_SET_SIZE);
    }

    public static Set<Role> getRoleSet(int size) {
        Set<Role> roles = new HashSet<>();
        for(int i = 0; i < size; i++) {
            roles.add(getRole());
        }
        return roles;
    }

    public static Set<Role> getRoleSet(Role... roles) {
        Set<Role> roleSet = new HashSet<>();
        Collections.addAll(roleSet, roles);
        return roleSet;
    }

    public static Set<Operation> getOperationSet() {
        return getOperationSet(DEFAULT_SET_SIZE);
    }

    public static Set<Operation> getOperationSet(int size) {
        Set<Operation> operations = new HashSet<>();
        for(int i = 0; i < size; i++) {
            operations.add(getOperation());
        }
        return operations;
    }

    public static Set<Operation> getOperationSet(Operation... operations) {
        Set<Operation> operationSet = new HashSet<>();
        Collections.addAll(operationSet, operations);
        return operationSet;
    }

    public static Set<Project> getProjectSet() {
        return getProjectSet(DEFAULT_SET_SIZE);
    }

    public static Set<Project> getProjectSet(int size) {
        Set<Project> projects = new HashSet<>();
        for(int i = 0; i < size; i++) {
            projects.add(getProject());
        }
        return projects;
    }

    public static Set<Project> getProjectSet(Project... projects) {
        Set<Project> projectSet = new HashSet<>();
        Collections.addAll(projectSet, projects);
        return projectSet;
    }


    /*   sets of identifiers   */

    public static Set<UserId> getUserIdSet() {
        return getUserIdSet(DEFAULT_SET_SIZE);
    }

    public static Set<UserId> getUserIdSet(int size) {
        Set<UserId> userIdSet = new HashSet<>();
        for(int i = 0; i < size; i++) {
            userIdSet.add(getUserId());
        }
        return userIdSet;
    }

    public static Set<UserId> getUserIdSet(String... userIds) {
        Set<UserId> userIdSet = new HashSet<>();
        for(String userId : userIds) {
            userIdSet.add(getUserId(userId));
        }
        return userIdSet;
    }

    public static Set<UserId> getUserIdSet(UserId... userIds) {
        Set<UserId> userIdSet = new HashSet<>();
        Collections.addAll(userIdSet, userIds);
        return userIdSet;
    }

    public static Set<OperationId> getOperationIdSet() {
        return getOperationIdSet(DEFAULT_SET_SIZE);
    }

    public static Set<OperationId> getOperationIdSet(int size) {
        Set<OperationId> operationIdSet = new HashSet<>();
        for(int i = 0; i < size; i++) {
            operationIdSet.add(getOperationId());
        }
        return operationIdSet;
    }

    public static Set<OperationId> getOperationIdSet(String... operationIds) {
        Set<OperationId> operationIdSet = new HashSet<>();
        for(String operationId : operationIds) {
            operationIdSet.add(getOperationId(operationId));
        }
        return operationIdSet;
    }

    public static Set<ProjectId> getProjectIdSet() {
        return getProjectIdSet(DEFAULT_SET_SIZE);
    }

    public static Set<ProjectId> getProjectIdSet(int size) {
        Set<ProjectId> projectIdSet = new HashSet<>();
        for(int i = 0; i < size; i++) {
            projectIdSet.add(getProjectId());
        }
        return projectIdSet;
    }

    public static Set<ProjectId> getProjectIdSet(String... projectIds) {
        Set<ProjectId> projectIdSet = new HashSet<>();
        for(String projectId : projectIds) {
            projectIdSet.add(getProjectId(projectId));
        }
        return projectIdSet;
    }

    public static Set<RoleId> getRoleIdSet(RoleId... roleIds) {
        Set<RoleId> roles = new HashSet<>();
        Collections.addAll(roles, roleIds);
        return roles;
    }

    public static Set<RoleId> getRoleIdSet() {
        return getRoleIdSet(DEFAULT_SET_SIZE);
    }

    public static Set<RoleId> getRoleIdSet(int size) {
        Set<RoleId> roleSet = new HashSet<>();
        for(int i = 0; i < size; i++) {
            roleSet.add(getRoleId());
        }
        return roleSet;
    }


    /*   sets of other things   */

    public static Set<OperationPrerequisite> getOperationPrerequisiteSet(OperationPrerequisite... operationPrerequisites) {
        Set<OperationPrerequisite> operationPrerequisiteSet = new HashSet<>();
        Collections.addAll(operationPrerequisiteSet, operationPrerequisites);
        return operationPrerequisiteSet;
    }

    public static Set<AuthenticationDetails> getAuthenticationDetailsSet() {
        return getAuthenticationDetailsSet(DEFAULT_SET_SIZE);
    }

    public static Set<AuthenticationDetails> getAuthenticationDetailsSet(int size) {
        Set<AuthenticationDetails> set = new HashSet<>();
        for(int i = 0; i < size; i++) {
            set.add(getAuthenticationDetails());
        }
        return set;
    }

    public static Set<AuthenticationDetails> getAuthenticationDetailsSet(AuthenticationDetails... details) {
        Set<AuthenticationDetails> set = new HashSet<>();
        Collections.addAll(set, details);
        return set;
    }

    public static Set<GuiRestriction> getGUIRestrictionSet() {
        Set<GuiRestriction> set = new HashSet<>();
        for(int i = 0; i < DEFAULT_SET_SIZE; i++) {
            set.add(Utils.getGUIRestriction());
        }
        return set;
    }

    public static GuiRestriction getGUIRestriction() {
        return getGUIRestriction("button" + newUUID(), GuiRestriction.Visibility.VISIBLE);
    }

    public static GuiRestriction getGUIRestriction(String componentName, GuiRestriction.Visibility visibility) {
        return new GuiRestrictionImpl(componentName, visibility);
    }


    /*   entity IRI generation   */

    public static UuidPrefixedNameEntityIriGenerator getUuidPrefixedNameEntityIriGenerator(EntityIriPrefix iriPrefix,
            EntityNamePrefix classIdPrefix, EntityNamePrefix objectPropertyIdPrefix, EntityNamePrefix dataPropertyIdPrefix,
            EntityNamePrefix annotationPropertyIdPrefix, EntityNamePrefix individualIdPrefix)
    {
        return new UuidPrefixedNameEntityIriGenerator.Builder()
                .setEntityIriPrefix(iriPrefix)
                .setClassNamePrefix(classIdPrefix)
                .setObjectPropertyNamePrefix(objectPropertyIdPrefix)
                .setDataPropertyNamePrefix(dataPropertyIdPrefix)
                .setAnnotationPropertyNamePrefix(annotationPropertyIdPrefix)
                .setIndividualNamePrefix(individualIdPrefix)
                .createPrefixedUUIDGenerator();
    }

    public static SequentialPrefixedNameEntityIriGenerator getSequentialPrefixedNameEntityIriGenerator(EntityIriPrefix iriPrefix,
            EntityNamePrefix classIdPrefix, EntityNamePrefix objectPropertyIdPrefix, EntityNamePrefix dataPropertyIdPrefix,
            EntityNamePrefix annotationPropertyIdPrefix, EntityNamePrefix individualIdPrefix,
            EntityNameSuffix classIdSuffix, EntityNameSuffix objPropSuffix, EntityNameSuffix dataPropSuffix,
            EntityNameSuffix annPropSuffix, EntityNameSuffix indSuffix)
    {
        return new SequentialPrefixedNameEntityIriGenerator.Builder()
                .setEntityIriPrefix(iriPrefix)
                .setClassNamePrefix(classIdPrefix)
                .setObjectPropertyNamePrefix(objectPropertyIdPrefix)
                .setDataPropertyNamePrefix(dataPropertyIdPrefix)
                .setAnnotationPropertyNamePrefix(annotationPropertyIdPrefix)
                .setIndividualNamePrefix(individualIdPrefix)
                .setClassNameSuffix(classIdSuffix)
                .setObjectPropertyNameSuffix(objPropSuffix)
                .setDataPropertyNameSuffix(dataPropSuffix)
                .setAnnotationPropertyNameSuffix(annPropSuffix)
                .setIndividualNameSuffix(indSuffix)
                .createSequentialPrefixedNameEntityIriGenerator();
    }

    public static UuidEntityIriGenerator getUuidEntityIriGenerator(EntityIriPrefix iriPrefix) {
        return new UuidEntityIriGenerator(iriPrefix);
    }

    public static EntityIriStatus getEntityIriStatus() {
        return getEntityIriStatus(Utils.getEntityIriPrefix(),
                Utils.getEntityNamePrefix(), Utils.getEntityNamePrefix(), Utils.getEntityNamePrefix(), Utils.getEntityNamePrefix(), Utils.getEntityNamePrefix(),
                Utils.getEntityNameSuffix(Integer.toString(random.nextInt())), Utils.getEntityNameSuffix(Integer.toString(random.nextInt())),
                Utils.getEntityNameSuffix(Integer.toString(random.nextInt())), Utils.getEntityNameSuffix(Integer.toString(random.nextInt())),
                Utils.getEntityNameSuffix(Integer.toString(random.nextInt())));
    }

    public static EntityIriStatus getEntityIriStatus(EntityIriPrefix iriPrefix, EntityNameSuffix classId, EntityNameSuffix objPropId,
                                                     EntityNameSuffix dataPropId, EntityNameSuffix annPropId, EntityNameSuffix indId)
    {
        return new EntityIriStatusImpl.Builder()
                .setEntityIriPrefix(iriPrefix)
                .setClassNamePrefix(Utils.getEntityNamePrefix("class"))
                .setObjectPropertyNamePrefix(Utils.getEntityNamePrefix("objprop"))
                .setDataPropertyNamePrefix(Utils.getEntityNamePrefix("dataprop"))
                .setClassNameSuffix(classId)
                .setObjectPropertyNameSuffix(objPropId)
                .setDataPropertyNameSuffix(dataPropId)
                .setAnnotationPropertyNameSuffix(annPropId)
                .setIndividualNameSuffix(indId)
                .createEntityIriStatus();
    }

    public static EntityIriStatus getEntityIriStatus(EntityIriPrefix iriPrefix, EntityNamePrefix classPrefix, EntityNamePrefix objPropPrefix, EntityNamePrefix dataPropPrefix,
                                                     EntityNamePrefix annPropPrefix, EntityNamePrefix indPrefix, EntityNameSuffix classId,
                                                     EntityNameSuffix objPropId, EntityNameSuffix dataPropId, EntityNameSuffix annPropId, EntityNameSuffix indId)
    {
        return new EntityIriStatusImpl.Builder()
                .setEntityIriPrefix(iriPrefix)
                .setClassNamePrefix(classPrefix)
                .setObjectPropertyNamePrefix(objPropPrefix)
                .setDataPropertyNamePrefix(dataPropPrefix)
                .setAnnotationPropertyNamePrefix(annPropPrefix)
                .setIndividualNamePrefix(indPrefix)
                .setClassNameSuffix(classId)
                .setObjectPropertyNameSuffix(objPropId)
                .setDataPropertyNameSuffix(dataPropId)
                .setAnnotationPropertyNameSuffix(annPropId)
                .setIndividualNameSuffix(indId)
                .createEntityIriStatus();
    }
}
