package com.csinfotech.simplewebproject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/task")
public class TaskController {
	@Autowired
	private TaskService taskService;
	
	@GetMapping(value="/create")
	public String createTask(Model model){
		TaskEntity taskEntity=new TaskEntity();
		model.addAttribute("taskEntity",taskEntity);
		model.addAttribute("title","CreateTask");
		return "/taskCreate";
		
	}
	@PostMapping(value="/save")
	public String postTask(Model model,TaskEntity taskEntity){
		taskService.saveTask(taskEntity);
		return "redirect:/task/list";
		
	}
	@GetMapping(value="/list")
	public String taskList(Model model){
		List<TaskEntity> taskEntities=taskService.getAllTask();
		model.addAttribute("taskEntities",taskEntities);
		model.addAttribute("title","Tasklist");
		return "/taskList";
	}
	@GetMapping(value="/update/{id}")
	public String updateTask(Model model, @PathVariable int id){
		model.addAttribute("taskEntity",taskService.getTaskById(id));
		model.addAttribute("title","Update Task");
		return "/taskCreate";
	}
	@GetMapping(value="/delete/{id}")
	public String deleteTask(Model model,@PathVariable int id){
	 	taskService.deleteTask(id);
	 	return "redirect:/task/list";
	}
	
	
	
	
	
	

}
