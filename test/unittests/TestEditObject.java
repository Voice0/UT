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

public class TestEditObject {

	BaseObject baseObject = new Group();
	private Map<String, Object> params = new HashMap<>();

	@Before
	public void beforeTest() {
		baseObject.dao = new DAOstub();
		params.put("groupname", "groupForTestingEditing");
		params.put("year", 2);
		params.put("newgroupname", "groupAfterRename");
		baseObject.setParams(params);
		baseObject.addObject();
	}

	@Test
	public void testEditObject() {
		assertEquals(1, baseObject.editObject());
	}

	@After
	public void afterTest() {
		params.put("groupname", "groupAfterRename");
		baseObject.deleteObject();
	}

}
