package com.csinfotech.simplewebproject;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
	@Autowired
	TaskRepository taskRepository;

	public void saveTask(TaskEntity taskEntity) {
		// TODO Auto-generated method stub
		TaskEntity taskEntity2=new TaskEntity();
		try {
			if(taskEntity.getId()==null){
				taskEntity.setDateCreated(new Date());
				taskRepository.save(taskEntity);
				
			}else{
				taskEntity2=taskRepository.getOne(taskEntity.getId());
				//taskEntity2.setDateCreated(taskEntity.getDateCreated());
				taskEntity2.setDateUpdated(new Date());
				taskEntity2.setDescription(taskEntity.getDescription());
				taskEntity2.setName(taskEntity.getName());
				taskRepository.save(taskEntity2);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public List<TaskEntity> getAllTask() {
		List<TaskEntity> entities=taskRepository.findAll();
		return entities;
		
	}
	public TaskEntity getTaskById(int id){
		return taskRepository.getOne(id);
	}
	public void deleteTask(int id){
		taskRepository.delete(getTaskById(id));
		
	}

}
