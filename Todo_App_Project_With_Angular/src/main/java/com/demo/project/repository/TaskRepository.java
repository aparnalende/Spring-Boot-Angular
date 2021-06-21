package com.demo.project.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.project.entity.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

//	public Task findByTaskId(Long taskId);
//	List<Task> findAllByUserId(Long userId);
	
	@Query("select t from Task t join t.user u where u.userId = ?1")
	List<Task> findAllByUserId(Long userId);
	List<Task> findByTaskId(Long task_id);
}
