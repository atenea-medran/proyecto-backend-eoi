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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.atenea.talentmixer.models.entities.Project;
import com.atenea.talentmixer.models.entities.UserAccount;
import com.atenea.talentmixer.models.services.IuserAccountService;

@CrossOrigin(origins= {"*"})
@RestController
@RequestMapping("/users")
public class UserAccountRestController {
	
	@Autowired
	private IuserAccountService userAccountService;
	
	@GetMapping("")
	public ResponseEntity<?> index(){
		
		List<UserAccount> respuesta = new ArrayList<UserAccount>();
		Map<String,Object> response = new HashMap<>();
		
		try {
			respuesta = userAccountService.findAll()
					.stream()
		            .map(u -> {
		                UserAccount userAccount = new UserAccount(u);
		                return userAccount;
		            })
		            .collect(Collectors.toList());
		} catch (DataAccessException e) {  // Error al acceder a la base de datos
			response.put("mensaje", "Error al conectar con la base de datos");
			response.put("error", e.getMessage().concat(":")
					.concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<UserAccount>>(respuesta,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")  
	public ResponseEntity<?> show(@PathVariable int id){
		
		UserAccount userAccount = null;
		Map<String,Object> response = new HashMap<>();
		try {
			userAccount = userAccountService.findById(id);
		} catch (DataAccessException e) {  // fallo en la petición a la base de datos
			response.put("mensaje", "Error al conectar con la base de datos");
			response.put("error", e.getMessage().concat(":")
					.concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(userAccount==null) {  // no existe el id de cliente
			response.put("mensaje", "El proyecto con ID: ".concat(id+"").concat(" no existe"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UserAccount>(userAccount,HttpStatus.OK);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		UserAccount userAccount = null;
		Map<String,Object> response = new HashMap<>();
		try {
			userAccount = userAccountService.findById(id);
			userAccountService.delete(id);
		} catch (DataAccessException e) {  // Error al acceder a la base de datos
			response.put("mensaje", "Error al eliminar el id");
			response.put("error", e.getMessage().concat(":")
					.concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		if(userAccount==null) {  // no existe el id de cliente
			response.put("mensaje", "El proyecto con ID: ".concat(id+"").concat(" no existe"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "El proyecto se ha borrado correctamente");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody UserAccount userAccount){
		
		UserAccount newUserAccount = null;
		Map<String,Object> response = new HashMap<>();
					
		try {
			newUserAccount = userAccountService.save(userAccount);

		} catch (DataAccessException e) {  // Error al acceder a la base de datos
			response.put("mensaje", "Error al conectar con la base de datos");
			response.put("error", e.getMessage().concat(":")
					.concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El usuario se ha insertado correctamente");
		response.put("user", newUserAccount);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}

}
