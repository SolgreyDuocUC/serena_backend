package duoc.projects.serena.controller;

import duoc.projects.serena.dto.user.UserCreateRequestDTO;
import duoc.projects.serena.dto.user.UserDTO;
import duoc.projects.serena.services.User.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Operaciones relacionadas con la gestión de usuarios")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Listar todos los usuarios", description = "Obtiene la lista completa de usuarios almacenados.")
    @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario por su ID", description = "Obtiene un usuario específico por su ID.")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Obtener un usuario por su email", description = "Obtiene un usuario específico por su dirección de correo electrónico.")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<UserDTO> findByEmail(@PathVariable String email) {
        UserDTO user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario en la base de datos.")
    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente")
    public ResponseEntity<UserDTO> create(@RequestBody UserCreateRequestDTO dto) {
        UserDTO created = userService.createUser(dto);
        URI location = URI.create("/api/users/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario existente", description = "Actualiza un usuario existente en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserCreateRequestDTO dto) {
        UserDTO updated = userService.updateUser(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario por su ID", description = "Elimina un usuario de la base de datos por su ID.")
    @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
