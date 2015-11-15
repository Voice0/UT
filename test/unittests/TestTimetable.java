package unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import objects.Timetable;

public class TestTimetable {

	Timetable timetable = new TimetableStub();

	@Test // get timetable for chosen teacher
	public void testTimetableForTeacher() {
		assertEquals(2, timetable.getTimetable("Teacher1", "teacher").size());
	}

	@Test // get timetable for chosen group
	public void testTimetableForGroup() {
		assertEquals(4, timetable.getTimetable("Group1", "group").size());
	}

}
