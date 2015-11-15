package unittests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import objects.BaseObject;
import objects.Group;

public class TestDeleteObject {

	BaseObject baseObject = new Group();
	private Map<String, Object> params = new HashMap<>();

	@Before
	public void beforeTest() {
		baseObject.dao = new DAOstub();
		params.put("groupname", "groupForTestingDeleting");
		params.put("year", 2);
		baseObject.setParams(params);
		baseObject.addObject();
	}

	@Test
	public void testDeleteObject() {
		assertEquals(1, baseObject.deleteObject());
	}

}
