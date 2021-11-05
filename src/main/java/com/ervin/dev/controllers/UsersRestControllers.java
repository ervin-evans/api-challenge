package com.ervin.dev.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ervin.dev.models.entity.User;
import com.ervin.dev.services.IUserService;

@RestController
@RequestMapping("/api")
public class UsersRestControllers {

	@Autowired
	private IUserService userService;

	@GetMapping("/users")
	public List<User> index() {
		return userService.findAll();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		User user = null;
		Map<String, Object> response = new HashMap<>();
		try {
			user = userService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (user == null) {
			response.put("mensaje",
					"El usuario con el id: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<?> Create(@RequestBody User user) {
		User newUser = null;
		Map<String, Object> response = new HashMap<>();
		try {
			newUser = userService.save(user);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "¡El usuario ha sido creado con éxito!");
		response.put("usuario", newUser);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/users/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public User update(@RequestBody User user, @PathVariable Long id) {
		User actualUser = userService.findById(id);
		actualUser.setName(user.getName());
		actualUser.setSurname(user.getName());
		actualUser.setEmail(user.getEmail());
		actualUser.setUsername(user.getUsername());
		actualUser.setPass(user.getPass());
		actualUser.setRegistrationDate(new Date());
		return userService.save(actualUser);
	}

	@DeleteMapping("/users/{id}")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}

}
