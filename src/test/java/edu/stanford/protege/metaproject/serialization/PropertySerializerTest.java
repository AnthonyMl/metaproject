package edu.stanford.protege.metaproject.serialization;

import com.google.gson.Gson;
import edu.stanford.protege.metaproject.Utils;
import edu.stanford.protege.metaproject.api.*;
import edu.stanford.protege.metaproject.impl.NameImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class PropertySerializerTest {
    private static final String propStr = "testString1", diffPropStr = "testString2";
    private String jsonProp, jsonOtherProp, jsonDiffProp;
    private Name prop, otherProp, diffProp;
    private Gson gson;

    @Before
    public void setUp() {
        gson = new DefaultGsonSerializer().getDefaultSerializer();

        prop = Utils.getName(propStr);
        otherProp = Utils.getName(propStr);
        diffProp = Utils.getName(diffPropStr);

        jsonProp = gson.toJson(prop);
        jsonOtherProp = gson.toJson(otherProp);
        jsonDiffProp = gson.toJson(diffProp);
    }

    @Test
    public void testNotNullSerialization() {
        assertThat(jsonProp, is(not(equalTo(null))));
    }

    @Test
    public void testNotNullDeserialization() {
        assertThat(gson.fromJson(jsonProp, NameImpl.class), is(not(equalTo(null))));
    }

    @Test
    public void testRoundTrip() {
        assertThat(gson.fromJson(jsonProp, NameImpl.class), is(prop));
    }

    @Test
    public void testSerializationOfEqualObjects() {
        assertThat(prop, is(otherProp));
        assertThat(jsonProp, is(jsonOtherProp));
    }

    @Test
    public void testSerializationOfDifferentObjects() {
        assertThat(prop, is(not(diffProp)));
        assertThat(jsonProp, is(not(gson.toJson(diffProp))));
    }

    @Test
    public void testDeserializationOfEqualObjects() {
        assertThat(gson.fromJson(jsonProp, NameImpl.class), is(gson.fromJson(jsonOtherProp, NameImpl.class)));
    }

    @Test
    public void testDeserializationOfDifferentObjects() {
        assertThat(gson.fromJson(jsonProp, NameImpl.class), is(not(gson.fromJson(jsonDiffProp, NameImpl.class))));
    }

    @Test
    public void testGet() {
        assertThat(gson.fromJson(jsonProp, NameImpl.class).get(), is(propStr));
    }
}