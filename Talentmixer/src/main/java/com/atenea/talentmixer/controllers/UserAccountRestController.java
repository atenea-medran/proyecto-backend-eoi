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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
