package com.im31.sta.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.im31.sta.entity.TaskSchedule;

public interface TaskScheduleRepository extends JpaRepository<TaskSchedule, Long> {
	List<TaskSchedule> findByTaskId(Long taskId);
}
