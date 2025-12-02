package duoc.projects.serena.controller;

import duoc.projects.serena.dto.userActiveSession.UserActiveSessionRequestDTO;
import duoc.projects.serena.dto.userActiveSession.UserActiveSessionResponseDTO;
import duoc.projects.serena.services.UserActiveSession.UserActiveSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user-active-sessions")
@CrossOrigin(origins = "*")
@Tag(name = "Sesiones de Usuario Activas", description = "Operaciones relacionadas con la gestión de sesiones de usuario activas")
public class UserActiveSessionController {

    private final UserActiveSessionService service;

    public UserActiveSessionController(UserActiveSessionService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todas las sesiones de usuario activas", description = "Obtiene la lista completa de sesiones de usuario activas almacenadas.")
    @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente")
    public ResponseEntity<List<UserActiveSessionResponseDTO>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una sesión de usuario activa por su ID", description = "Obtiene una sesión de usuario activa específica por su ID.")
    @ApiResponse(responseCode = "200", description = "Sesión de usuario activa encontrada")
    @ApiResponse(responseCode = "404", description = "Sesión de usuario activa no encontrada")
    public ResponseEntity<UserActiveSessionResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva sesión de usuario activa", description = "Crea una nueva sesión de usuario activa en la base de datos.")
    @ApiResponse(responseCode = "201", description = "Sesión de usuario activa creada exitosamente")
    public ResponseEntity<UserActiveSessionResponseDTO> create(@RequestBody UserActiveSessionRequestDTO dto) {
        UserActiveSessionResponseDTO created = service.create(dto);
        URI location = URI.create("/api/v1/user-active-sessions/" + created.getSessionId());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una sesión de usuario activa existente", description = "Actualiza una sesión de usuario activa existente en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Sesión de usuario activa actualizada exitosamente")
    @ApiResponse(responseCode = "404", description = "Sesión de usuario activa no encontrada")
    public ResponseEntity<UserActiveSessionResponseDTO> update(
            @PathVariable Integer id,
            @RequestBody UserActiveSessionRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una sesión de usuario activa por su ID", description = "Elimina una sesión de usuario activa de la base de datos por su ID.")
    @ApiResponse(responseCode = "204", description = "Sesión de usuario activa eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Sesión de usuario activa no encontrada")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
