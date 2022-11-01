package com.tbk.ThoiKhoaBieu.service;

import java.util.List;

import com.tbk.ThoiKhoaBieu.entity.TeacherEntity;
import com.tbk.ThoiKhoaBieu.model.TeacherDto;


public interface ITeacherService {
   List<TeacherDto> findAll ();
   TeacherDto save(TeacherDto teacher);
}
