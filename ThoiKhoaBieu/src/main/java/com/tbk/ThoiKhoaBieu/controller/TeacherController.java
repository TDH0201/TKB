package com.tbk.ThoiKhoaBieu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbk.ThoiKhoaBieu.model.TeacherDto;
import com.tbk.ThoiKhoaBieu.service.ITeacherService;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

	@Autowired
	ITeacherService teacherService;
	
	@GetMapping("")
	public List<TeacherDto> getAllTeacher() {
		return teacherService.findAll();
	}
	
	@PostMapping("/add")
	public TeacherDto save(@RequestBody TeacherDto teacherDto) {
		return teacherService.save(teacherDto);
	}
}
