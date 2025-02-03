package cl.oscar_pino.apiSecurityJWT.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {
	
	@GetMapping("/saludo")
	public ResponseEntity<?> home(){
		
		return ResponseEntity.ok().body("holas apis");
	}

}
