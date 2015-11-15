package unittests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import objects.BaseObject;
import objects.Group;

public class TestGetAllObjects {

	BaseObject baseObject = new Group();

	@Before
	public void beforeTest() {
		baseObject.dao = new DAOstub();
	}

	@Test
	public void testGetAll() {
		assertEquals(12, baseObject.getAll().size());
	}

}
