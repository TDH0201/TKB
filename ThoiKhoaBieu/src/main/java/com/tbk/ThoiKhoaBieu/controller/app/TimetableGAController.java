package com.tbk.ThoiKhoaBieu.controller.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbk.ThoiKhoaBieu.model.GeneticAlgorithm;
import com.tbk.ThoiKhoaBieu.model.Population;
import com.tbk.ThoiKhoaBieu.model.Timetable;
import com.tbk.ThoiKhoaBieu.model.TimetableClass;
import com.tbk.ThoiKhoaBieu.service.IClassService;
import com.tbk.ThoiKhoaBieu.service.IRoomService;
import com.tbk.ThoiKhoaBieu.service.ISubjectService;
import com.tbk.ThoiKhoaBieu.service.ITeacherService;
import com.tbk.ThoiKhoaBieu.service.ITimeSlotService;

@RestController
@RequestMapping("/ga")
public class TimetableGAController {

	@Autowired
	IClassService classService;

	@Autowired
	IRoomService roomService;

	@Autowired
	ITeacherService teacherService;

	@Autowired
	ISubjectService subjectsService;

	@Autowired
	ITimeSlotService timeSlotService;

	private Timetable timetable = new Timetable();

	private GeneticAlgorithm ga;

	private Population population;

	@GetMapping("/init")
	public List<TimetableClass> initalTimetable() {

		timetable.setClasses(classService.findAll());
		timetable.setRooms(roomService.findAll());
		timetable.setTeachers(teacherService.findAll());
		timetable.setSubjectDtos(subjectsService.findAll());
		timetable.setTimeslots(timeSlotService.findAll());

		ga = new GeneticAlgorithm(2, 0.01, 0.9, 2, 5);

		this.population = ga.initPopulation(this.timetable);
		this.timetable.createClasses(population.getPopulation().get(0));
		
		return this.timetable.getTimeTableList();
	}
}
