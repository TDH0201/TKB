package com.tbk.ThoiKhoaBieu.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import com.tbk.ThoiKhoaBieu.Const.Constan;

import lombok.Data;

@Data
public class Individual {

	private List<TimetableClass> chromosome = new ArrayList<>();
	private double fitness = -1;
	private Random random = new Random();
	private TeacherDto chuNhiem;
	private List<TeacherDto> chuNhiemList = new ArrayList<>();

	private Map<Long, Integer> soTiet = new HashMap<>();

	public Individual(Timetable timetable) {

		for (ClassDto classDto : timetable.getClasses()) {

			List<SubjectDto> subjects = timetable.getSubjectByGrade(classDto.getGrade());
			List<TeacherDto> teacherDtoList = timetable.getTeacherByClass(classDto.getGrade());
			List<TeacherDto> giaoVienChuNhiem = teacherDtoList.stream().filter(dto -> {
				return dto.getIsTeacherClass();
			}).collect(Collectors.toList());

			// set chu nhiem
			while (true) {
				int teacherRamdom = random.nextInt(giaoVienChuNhiem.size());
				if (!chuNhiemList.contains(giaoVienChuNhiem.get(teacherRamdom))) {
					this.chuNhiem = giaoVienChuNhiem.get(teacherRamdom);
					chuNhiemList.add(giaoVienChuNhiem.get(teacherRamdom));
					break;
				}

			}

			// set so tiet
			for (SubjectDto subject : subjects) {
				soTiet.put(subject.getId(), subject.getNumberTime());
			}

			for (TimeSlot timeSlot : timetable.getTimeslots()) {

				TimetableClass timetableClass = new TimetableClass();

				if (timeSlot.getTimeslot().equals(Constan.timeSlotCC)) {
					for (SubjectDto subject : subjects) {
						if (subject.getName().equals(Constan.chao_Co)) {
							timetableClass.setSubjectName(subject.getName());
							timetableClass.setSubJectsId(subject.getId());
							timetableClass.setTimeSlot(timeSlot.getTimeslot());
							timetableClass.setTimSlotId(timeSlot.getTimeslotId());
							timetableClass.setTeacherId(chuNhiem.getId());
							timetableClass.setTeacherName(chuNhiem.getName());

							soTiet.remove(subject.getId());

							timetableClass.setRoomName(classDto.getClassname());
							timetableClass.setRoomId(classDto.getId());
							chromosome.add(timetableClass);
							break;
						}
					}
					continue;
				}

				if (timeSlot.getTimeslot().equals(Constan.timeSlotSinhHoatLop)) {
					for (SubjectDto subject : subjects) {
						if (subject.getName().equals(Constan.Shlop)) {
							timetableClass.setSubjectName(subject.getName());
							timetableClass.setSubJectsId(subject.getId());
							timetableClass.setTimeSlot(timeSlot.getTimeslot());
							timetableClass.setTimSlotId(timeSlot.getTimeslotId());
							timetableClass.setTeacherId(chuNhiem.getId());
							timetableClass.setTeacherName(chuNhiem.getName());

							soTiet.remove(subject.getId());

							timetableClass.setRoomName(classDto.getClassname());
							timetableClass.setRoomId(classDto.getId());
							chromosome.add(timetableClass);
							break;
						}
					}
					continue;
				}

				if (classDto.getClassname().charAt(0) == '1' || classDto.getClassname().charAt(0) == '2'
						|| classDto.getClassname().charAt(0) == '3') {
					if (timeSlot.getTimeslot().substring(3).equals("10:20 - 10:55")) {

						timetableClass.setRoomName(classDto.getClassname());
						timetableClass.setRoomId(classDto.getId());
						timetableClass.setTimeSlot(timeSlot.getTimeslot());
						timetableClass.setTimSlotId(timeSlot.getTimeslotId());
						chromosome.add(timetableClass);
						continue;
					}
				} else if (classDto.getClassname().charAt(0) == '4' || classDto.getClassname().charAt(0) == '5') {
					if (!timeSlot.getTimeslot().equals("T3 10:20 - 10:55")
							|| !timeSlot.getTimeslot().equals("T5 10:20 - 10:55")) {

						timetableClass.setRoomName(classDto.getClassname());
						timetableClass.setRoomId(classDto.getId());
						timetableClass.setTimeSlot(timeSlot.getTimeslot());
						timetableClass.setTimSlotId(timeSlot.getTimeslotId());
						chromosome.add(timetableClass);
						continue;
					}
				}

				if (!Constan.ngayNghi.contains(timeSlot.getTimeslot())) {
					// set subject
					while (true) {
						if (soTiet.size() > 0) {
							int radom1 = random.nextInt(subjects.size());
							if (soTiet.get(subjects.get(radom1).getId()) != null) {
								if (soTiet.get(subjects.get(radom1).getId()) > 0) {
									timetableClass.setSubjectName(subjects.get(radom1).getName());
									timetableClass.setSubJectsId(subjects.get(radom1).getId());

									int numberTime = soTiet.get(subjects.get(radom1).getId()) - 1;
									soTiet.put(subjects.get(radom1).getId(), numberTime);

									// set teacher
									for (TeacherDto teacherDto : teacherDtoList) {
										for (SubjectDto s : teacherDto.getSubjectIds()) {
											if (s.getName().equals(subjects.get(radom1).getName())) {
												if (Constan.monBatBuoc.contains(subjects.get(radom1).getName())) {
													timetableClass.setTeacherId(chuNhiem.getId());
													timetableClass.setTeacherName(chuNhiem.getName());
												} else {
													timetableClass.setTeacherName(teacherDto.getName());
													timetableClass.setTeacherId(teacherDto.getId());
												}
											}
										}
									}

									if (timetableClass.getTeacherName() != null
											&& timetableClass.getSubjectName() != null) {
										break;
									}

								} else {
									soTiet.remove(subjects.get(radom1).getId());
									continue;
								}
							} else {
								continue;
							}
						} else {
							break;
						}
					}
				}

				// set timeSlot
				timetableClass.setRoomName(classDto.getClassname());
				timetableClass.setRoomId(classDto.getId());
				timetableClass.setTimeSlot(timeSlot.getTimeslot());
				timetableClass.setTimSlotId(timeSlot.getTimeslotId());
				chromosome.add(timetableClass);

			}
			chuNhiem = null;
		}
	}
}
