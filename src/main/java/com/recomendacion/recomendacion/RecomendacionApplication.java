package com.recomendacion.recomendacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
public class RecomendacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecomendacionApplication.class, args);
	}

	@RestController
	@RequestMapping("/api/v1")
	public class RecomendacionController {

		@PostMapping("/recomend")
		public ResponseEntity<?> obtenerRecomendacion(@RequestBody Request requestBody) {
			String userId = requestBody.getUsuario();
			String subcategoria = requestBody.getSubcategoria();

			RestTemplate restTemplate = new RestTemplate();

			// Consumir el endpoint de usuarios
			String usuariosEndpoint = "http://127.0.0.1:81/api/v1/usuarios/" + userId;
			ResponseEntity<Object[]> usuariosResponse = restTemplate.getForEntity(usuariosEndpoint, Object[].class);

			// Consumir el endpoint de subcategoría
			String subcategoriaEndpoint = "http://127.0.0.1:81/api/v1/subcategoria/" + subcategoria;
			ResponseEntity<Object[]> subcategoriaResponse = restTemplate.getForEntity(subcategoriaEndpoint, Object[].class);

			// Ejemplo de cómo podrías construir la respuesta
			Map<String, Object> response = new HashMap<>();
			response.put("usuario", usuariosResponse.getBody());
			response.put("recomendacion", subcategoriaResponse.getBody());

			return ResponseEntity.ok(response);
		}

		public static class Request {
			private String usuario;
			private String subcategoria;

			public String getUsuario() {
				return usuario;
			}

			public String getSubcategoria() {
				return subcategoria;
			}

		}
	}


}
