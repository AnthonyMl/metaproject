package edu.stanford.protege.metaproject.api;

import edu.stanford.protege.metaproject.Utils;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class OntologyTermIdSuffixTest {
    private static final String
            idSuffixStr = "testIdSuffix",
            diffIdSuffixStr = "diffTestIdSuffix",
            toStringHead = "OntologyTermIdSuffix";

    private OntologyTermIdSuffix idSuffix, otherIdSuffix, diffIdSuffix;

    @Before
    public void setUp() {
        idSuffix = Utils.getOntologyTermIdSuffix(idSuffixStr);
        otherIdSuffix = Utils.getOntologyTermIdSuffix(idSuffixStr);
        diffIdSuffix = Utils.getOntologyTermIdSuffix(diffIdSuffixStr);
    }

    @Test
    public void testNotNull() {
        assertThat(idSuffix, is(not(equalTo(null))));
    }

    @Test
    public void testGet() {
        assertThat(idSuffix.get(), is(idSuffixStr));
    }

    @Test
    public void testEqualToSelf() {
        assertThat(idSuffix, is(equalTo(idSuffix)));
    }

    @Test
    public void testEquals() {
        assertThat(idSuffix, is(equalTo(otherIdSuffix)));
    }

    @Test
    public void testNotEquals() {
        assertThat(idSuffix, is(not(equalTo(diffIdSuffix))));
    }

    @Test
    public void testHashCode() {
        assertThat(idSuffix.hashCode(), is(otherIdSuffix.hashCode()));
    }

    @Test
    public void testToString() {
        assertThat(idSuffix.toString(), startsWith(toStringHead));
    }
}