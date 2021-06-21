package com.demo.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.project.entity.Task;
import com.demo.project.exception.NotFoundException;
import com.demo.project.repository.TaskRepository;
import com.demo.project.repository.UserRepository;

@RestController
@RequestMapping(value = "/task")
public class TaskController {
	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserRepository userRepository;

	@PostMapping("/users/{userId}/tasks")
	public Task addTask(@PathVariable Long userId, @RequestBody Task task) {
		return userRepository.findById(userId).map(user -> {
			task.setUser(user);
			return taskRepository.save(task);
		}).orElseThrow(() -> new NotFoundException("user not found"));
	}

	@GetMapping("/users/{userId}/tasks")
	public List<Task> getTaskByUserId(@PathVariable Long userId) {
		if (!userRepository.existsById(userId)) {
			throw new NotFoundException("User not found");
		}
		return taskRepository.findAllByUserId(userId);
	}
	
	@GetMapping("/users/{userId}/tasks/{task_id}")
	public List<Task> getTaskById(@PathVariable Long userId,@PathVariable Long task_id) {
		if ((!userRepository.existsById(userId) && !taskRepository.existsById(task_id)) || !userRepository.existsById(userId) || !taskRepository.existsById(task_id)) {
			throw new NotFoundException("Not found");
		}
		return taskRepository.findByTaskId(task_id);
	}
	

	@PutMapping("/users/{userId}/tasks/{task_id}")
	public Task updateTask(@PathVariable Long userId, @PathVariable Long task_id, @RequestBody Task taskUpdated) {
		if (!userRepository.existsById(userId)) {
			throw new NotFoundException("user not found");
		}
		return taskRepository.findById(task_id).map(task -> {
			task.settask_name(taskUpdated.gettask_name());
			task.settask_Description(taskUpdated.gettask_Description());
			return taskRepository.save(task);
		}).orElseThrow(() -> new NotFoundException("Task not found"));
	}
//
	@DeleteMapping("/users/{userId}/tasks/{task_id}")
	public String deleteTask(@PathVariable Long userId, @PathVariable Long task_id) {
		if (!userRepository.existsById(userId)) {
			throw new NotFoundException("user not found");
		}
		return taskRepository.findById(task_id).map(task -> {
			taskRepository.delete(task);
			return "Task deleted successfully";
		}).orElseThrow(() -> new NotFoundException("Task not found"));
	}
	
	
	
	
}
