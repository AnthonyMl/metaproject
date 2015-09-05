package edu.stanford.protege.metaproject.api.impl;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import edu.stanford.protege.metaproject.api.*;
import edu.stanford.protege.metaproject.api.exception.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A representation of an access control policy where users are associated with roles. Roles have
 * a defined set of operations the user is allowed to perform within a set of projects
 *
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class MetaprojectImpl implements Metaproject, Serializable {
    private static final long serialVersionUID = 231539690194538811L;
    private PolicyManager policyManager;
    private RoleManager roleManager;
    private OperationManager operationManager;
    private UserManager userManager;
    private ProjectManager projectManager;

    /**
     * Package-private constructor; use builder
     *
     * @param policyManager   Policy manager
     */
    MetaprojectImpl(PolicyManager policyManager, RoleManager roleManager, OperationManager operationManager, UserManager userManager, ProjectManager projectManager) {
        this.policyManager = checkNotNull(policyManager);
        this.roleManager = checkNotNull(roleManager);
        this.operationManager = checkNotNull(operationManager);
        this.userManager = checkNotNull(userManager);
        this.projectManager = checkNotNull(projectManager);
    }

    @Override
    public boolean isOperationAllowed(OperationId operationId, ProjectId projectId, UserId userId) throws PolicyException {
        checkExistence(operationManager, operationId);
        checkExistence(projectManager, projectId);

        Set<RoleId> roles = policyManager.getRoles(userId);
        for(RoleId role : roles) {
            try {
                Role r = roleManager.getRole(role);
                if(r.getProjects().contains(projectId) && r.getOperations().contains(operationId)) {
                    return true;
                }
            } catch (RoleNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Set<Operation> getOperationsInProject(UserId userId, ProjectId projectId) throws UserNotInPolicyException {
        Set<Operation> operations = new HashSet<>();
        Set<Role> roles = getRoles(userId);
        roles.stream().filter(r -> r.getProjects().contains(projectId)).forEach(r -> {
            for (OperationId opId : r.getOperations()) {
                try {
                    operations.add(operationManager.getOperation(opId));
                } catch (OperationNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        return operations;
    }

    @Override
    public Set<Project> getProjects(UserId userId) throws UserNotInPolicyException {
        Set<Project> projects = new HashSet<>();
        Set<Role> roles = getRoles(userId);
        for(Role role : roles) {
            for(ProjectId p : role.getProjects()) {
                try {
                    projects.add(projectManager.getProject(p));
                } catch (ProjectNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return projects;
    }

    @Override
    public Set<Role> getRoles(UserId userId) throws UserNotInPolicyException {
        Set<Role> roles = new HashSet<>();
        for(RoleId roleId : policyManager.getRoles(userId)) {
            System.out.println("role id being fetched: " + roleId.get());
            try {
                roles.add(roleManager.getRole(roleId));
            } catch (RoleNotFoundException e) {
                e.printStackTrace();
            }
        }
        return roles;
    }

    @Override
    public Set<User> getUsers(ProjectId projectId) {
        Set<User> users = new HashSet<>();
        for (UserId userId : policyManager.getUserRoleMappings().keySet()) {
            try {
                getRoles(userId).stream().filter(role -> role.getProjects().contains(projectId)).forEach(role -> {
                    try {
                        users.add(userManager.getUser(userId));
                    } catch (UserNotFoundException e) {
                        e.printStackTrace();
                    }
                });
            } catch (UserNotInPolicyException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public PolicyManager getPolicyManager() {
        return policyManager;
    }

    @Override
    public RoleManager getRoleManager() {
        return roleManager;
    }

    @Override
    public OperationManager getOperationManager() {
        return operationManager;
    }

    @Override
    public UserManager getUserManager() {
        return userManager;
    }

    @Override
    public ProjectManager getProjectManager() {
        return projectManager;
    }

    /**
     * Verify whether access control object(s) are registered with the given manager
     *
     * @param manager   Manager for given access control object
     * @param objects   One or more access control object identifiers
     * @throws PolicyException  Policy exception
     */
    private void checkExistence(Manager manager, AccessControlObjectId... objects) throws PolicyException {
        for(AccessControlObjectId obj : objects) {
            checkNotNull(obj);
            if(!manager.contains(obj)) {
                throw new PolicyException("The specified access control object does not correspond to a known one. " +
                        "It may not have been registered with the appropriate manager");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetaprojectImpl policy = (MetaprojectImpl) o;
        return Objects.equal(policyManager, policy.policyManager) &&
                Objects.equal(roleManager, policy.roleManager) &&
                Objects.equal(operationManager, policy.operationManager) &&
                Objects.equal(userManager, policy.userManager) &&
                Objects.equal(projectManager, policy.projectManager);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(policyManager, roleManager, operationManager, userManager, projectManager);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("policyManager", policyManager)
                .add("roleManager", roleManager)
                .add("operationManager", operationManager)
                .add("userManager", userManager)
                .add("projectManager", projectManager)
                .toString();
    }

    /**
     * Builder for access control policies
     */
    public static class Builder {
        private PolicyManager policyManager = new PolicyManagerImpl();
        private RoleManager roleManager = new RoleManagerImpl();
        private OperationManager operationManager = new OperationManagerImpl();
        private UserManager userManager = new UserManagerImpl();
        private ProjectManager projectManager = new ProjectManagerImpl();

        public Builder setPolicyManager(PolicyManager policyManager) {
            this.policyManager = policyManager;
            return this;
        }

        public Builder setRoleManager(RoleManager roleManager) {
            this.roleManager = roleManager;
            return this;
        }

        public Builder setOperationManager(OperationManager operationManager) {
            this.operationManager = operationManager;
            return this;
        }

        public Builder setUserManager(UserManager userManager) {
            this.userManager = userManager;
            return this;
        }

        public Builder setProjectManager(ProjectManager projectManager) {
            this.projectManager = projectManager;
            return this;
        }

        public MetaprojectImpl createAccessControlPolicy() {
            return new MetaprojectImpl(policyManager, roleManager, operationManager, userManager, projectManager);
        }
    }
}
