package edu.stanford.protege.metaproject.impl;

import edu.stanford.protege.metaproject.TestUtils;
import edu.stanford.protege.metaproject.api.UserId;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Rafael Gonçalves <br>
 * Center for Biomedical Informatics Research <br>
 * Stanford University
 */
public class UserIdTest {
    private static final String
            userIdStr = "testUserId1",
            diffIdStr = "testUserId2",
            toStringHead = UserId.class.getSimpleName();

    private UserId userId, otherUserId, diffUserId;

    @Before
    public void setUp() {
        userId = TestUtils.getUserId(userIdStr);
        otherUserId = TestUtils.getUserId(userIdStr);
        diffUserId = TestUtils.getUserId(diffIdStr);
    }

    @Test
    public void testNotNull() {
        assertThat(userId, is(not(equalTo(null))));
    }

    @Test
    public void testGet() {
        assertThat(userId.get(), is(userIdStr));
    }

    @Test
    public void testEqualToSelf() {
        assertThat(userId, is(userId));
    }

    @Test
    public void testEquals() {
        assertThat(userId, is(otherUserId));
    }

    @Test
    public void testNotEquals() {
        assertThat(userId, is(not(diffUserId)));
    }

    @Test
    public void testHashCode() {
        assertThat(userId.hashCode(), is(otherUserId.hashCode()));
    }

    @Test
    public void testToString() {
        assertThat(userId.toString(), startsWith(toStringHead));
    }
}