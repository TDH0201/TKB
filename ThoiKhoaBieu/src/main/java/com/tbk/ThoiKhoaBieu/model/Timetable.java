package com.tbk.ThoiKhoaBieu.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Timetable {
	private List<RoomDto> rooms = new ArrayList<>();
	private List<TeacherDto> teachers = new ArrayList<>();
	private List<SubjectDto> subjectDtos = new ArrayList<>();
	private List<ClassDto> classes = new ArrayList<>();
	private List<TimeSlot> timeslots = new ArrayList<>();
	private List<TimetableClass> timeTableList = new ArrayList<>();

	private int numClasses = 0;

	public List<SubjectDto> getSubjectByGrade(Integer grade) {

		List<SubjectDto> subjects = new ArrayList<>();
		for (SubjectDto subjectDto : this.subjectDtos) {
			if(subjectDto.getGrade().equals(String.valueOf(grade))) {
				subjects.add(subjectDto);
			}
		}
		
		return subjects;
	}

	public List<TeacherDto> getTeacherByClass(Integer grade) {

		List<TeacherDto> teacherList = new ArrayList<>();
		for (TeacherDto teacher : this.teachers) {
			if (teacher.getSubjectIds().size() > 0) {
				if(teacher.getSubjectIds().get(0).getGrade().equals(String.valueOf(grade))) {
					teacherList.add(teacher);
				}
			}
		}
		
		return teacherList;
	}

	public void createClasses(Individual individual) {
		this.timeTableList = individual.getChromosome();
	}
	
	public Object clone()throws CloneNotSupportedException{  
		return super.clone();  
		}  
}