package com.atenea.talentmixer.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.atenea.talentmixer.models.dto.ProjectDto;
import com.atenea.talentmixer.models.entities.Project;
import com.atenea.talentmixer.models.services.IprojectService;

@CrossOrigin(origins= {"*"})
@RestController
@RequestMapping("/projects")
public class ProjectRestController {
	
	@Autowired
	private IprojectService projectService;
	
	@GetMapping("/dto")
	public List<ProjectDto> indexDto(){
		List<ProjectDto> listaDto = new ArrayList<>();
		projectService.findAll().forEach(project->{
			listaDto.add(new ProjectDto(project.getId(),project.getTitle(),project.getSummary(),project.getDescription(),project.getCreatedAt(),project.getImage()));
		});		
		return listaDto;
	}
	
	@GetMapping("")
	public ResponseEntity<?> index(){
		
		List<Project> respuesta = new ArrayList<Project>();
		Map<String,Object> response = new HashMap<>();
		
		try {
			respuesta = projectService.findAll()
					.stream()
		            .map(p -> {
		                Project project = new Project(p);
		                if(project.getImage()!=null)
		                	project.setImage(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + project.getImage());
		                return project;
		            })
		            .collect(Collectors.toList());
		} catch (DataAccessException e) {  
			response.put("mensaje", "Error al conectar con la base de datos");
			response.put("error", e.getMessage().concat(":")
					.concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<Project>>(respuesta,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")  
	public ResponseEntity<?> show(@PathVariable int id){
		
		Project project = null;
		Map<String,Object> response = new HashMap<>();
		try {
			project = projectService.findById(id);
		} catch (DataAccessException e) {  
			response.put("mensaje", "Error al conectar con la base de datos");
			response.put("error", e.getMessage().concat(":")
					.concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(project==null) {
			response.put("mensaje", "El proyecto con ID: ".concat(id+"").concat(" no existe"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
        if(project.getImage()!=null) 
        	project.setImage(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + project.getImage());

		return new ResponseEntity<Project>(project,HttpStatus.OK);		
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		Project project = null;
		Map<String,Object> response = new HashMap<>();
		try {
			project = projectService.findById(id);
			projectService.delete(id);
		} catch (DataAccessException e) {  
			response.put("mensaje", "Error al eliminar el id");
			response.put("error", e.getMessage().concat(":")
					.concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		if(project==null) {  
			response.put("mensaje", "El proyecto con ID: ".concat(id+"").concat(" no existe"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "El proyecto se ha borrado correctamente");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody Project project){
		
		Project newProject = null;
		Map<String,Object> response = new HashMap<>();
					
		try {
			newProject = projectService.save(project);
			if(project.getImage()!=null)
				newProject.setImage(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + newProject.getImage());
				
		} catch (DataAccessException e) { 
			response.put("mensaje", "Error al conectar con la base de datos");
			response.put("error", e.getMessage().concat(":")
					.concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El proyecto se ha insertado correctamente");
		response.put("project", newProject);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Project project, @PathVariable int id){
		
		Project currentProject = null;
		Project updatedProject = null;
		Map<String,Object> response = new HashMap<>();
			
		try {
			currentProject = projectService.findById(id); 
		} catch (DataAccessException e) {  
			response.put("mensaje", "Error al conectar con la base de datos");
			response.put("error", e.getMessage().concat(":")
					.concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(currentProject==null) {
			response.put("mensaje", "El proyecto con ID: ".concat(id+"").concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			currentProject.setTitle(project.getTitle());
			currentProject.setSummary(project.getSummary());
			currentProject.setDescription(project.getDescription());
			currentProject.setCreatedAt(project.getCreatedAt());
			if(project.getImage() != null)
				currentProject.setImage(project.getImage());
			updatedProject = projectService.save(currentProject);
			if(project.getImage() != null)
				updatedProject.setImage(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + updatedProject.getImage());

		} catch (DataAccessException e) { 
			response.put("mensaje", "Error al conectar con la base de datos");
			response.put("error", e.getMessage().concat(":")
					.concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El proyecto se ha modificado correctamente");
		response.put("project", updatedProject);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

	}
}