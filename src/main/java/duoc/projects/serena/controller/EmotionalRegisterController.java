package duoc.projects.serena.controller;

import duoc.projects.serena.dto.emotionalRegister.EmotionalRegisterRequestDTO;
import duoc.projects.serena.dto.emotionalRegister.EmotionalRegisterResponseDTO;
import duoc.projects.serena.services.EmotionalRegister.EmotionalRegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/emotional-registers")
@CrossOrigin(origins = "*")
@Tag(name = "Registros Emocionales", description = "Operaciones relacionadas con la gestión de registros emocionales")
public class EmotionalRegisterController {

    private final EmotionalRegisterService service;

    public EmotionalRegisterController(EmotionalRegisterService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todos los registros emocionales", description = "Obtiene la lista completa de registros emocionales almacenados.")
    @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente")
    public ResponseEntity<List<EmotionalRegisterResponseDTO>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un registro emocional por su ID", description = "Obtiene un registro emocional específico por su ID.")
    @ApiResponse(responseCode = "200", description = "Registro emocional encontrado")
    @ApiResponse(responseCode = "404", description = "Registro emocional no encontrado")
    public ResponseEntity<EmotionalRegisterResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo registro emocional", description = "Crea un nuevo registro emocional en la base de datos.")
    @ApiResponse(responseCode = "201", description = "Registro emocional creado exitosamente")
    public ResponseEntity<EmotionalRegisterResponseDTO> create(@RequestBody EmotionalRegisterRequestDTO dto) {
        EmotionalRegisterResponseDTO created = service.create(dto);
        URI location = URI.create("/api/v1/emotional-registers/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un registro emocional existente", description = "Actualiza un registro emocional existente en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Registro emocional actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Registro emocional no encontrado")
    public ResponseEntity<EmotionalRegisterResponseDTO> update(
            @PathVariable Long id,
            @RequestBody EmotionalRegisterRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un registro emocional por su ID", description = "Elimina un registro emocional de la base de datos por su ID.")
    @ApiResponse(responseCode = "204", description = "Registro emocional eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Registro emocional no encontrado")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
