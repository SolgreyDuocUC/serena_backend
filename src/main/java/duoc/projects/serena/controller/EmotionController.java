package duoc.projects.serena.controller;

import duoc.projects.serena.dto.emotion.EmotionRequestDTO;
import duoc.projects.serena.dto.emotion.EmotionResponseDTO;
import duoc.projects.serena.services.Emotion.EmotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/emotions")
@CrossOrigin(origins = "*")
@Tag(name = "Emociones", description = "Operaciones relacionadas con la gestión de emociones")
public class EmotionController {

    private final EmotionService emotionService;

    public EmotionController(EmotionService emotionService) {
        this.emotionService = emotionService;
    }

    @GetMapping
    @Operation(summary = "Listar emociones", description = "Obtiene la lista completa de emociones almacenadas.")
    @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente")
    public ResponseEntity<List<EmotionResponseDTO>> listarEmotions() {
        List<EmotionResponseDTO> emotions = emotionService.findAll();
        return ResponseEntity.ok(emotions);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una emoción por su ID", description = "Obtiene una emoción específica por su ID.")
    @ApiResponse(responseCode = "200", description = "Emoción encontrada")
    @ApiResponse(responseCode = "404", description = "Emoción no encontrada")
    public ResponseEntity<EmotionResponseDTO> obtenerEmotion(@PathVariable Long id) {
        EmotionResponseDTO emotion = emotionService.findById(id);
        return ResponseEntity.ok(emotion);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva emoción", description = "Crea una nueva emoción en la base de datos.")
    @ApiResponse(responseCode = "201", description = "Emoción creada exitosamente")
    public ResponseEntity<EmotionResponseDTO> crearEmotion(@RequestBody EmotionRequestDTO request) {
        EmotionResponseDTO creado = emotionService.create(request);
        URI location = URI.create("/api/v1/serena/emotions/" + creado.getId());
        return ResponseEntity.created(location).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una emoción existente", description = "Actualiza una emoción existente en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Emoción actualizada exitosamente")
    @ApiResponse(responseCode = "404", description = "Emoción no encontrada")
    public ResponseEntity<EmotionResponseDTO> actualizarEmotion(
            @PathVariable Long id,
            @RequestBody EmotionRequestDTO request) {

        EmotionResponseDTO actualizado = emotionService.update(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una emoción por su ID", description = "Elimina una emoción de la base de datos por su ID.")
    @ApiResponse(responseCode = "204", description = "Emoción eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Emoción no encontrada")
    public ResponseEntity<Void> eliminarEmotion(@PathVariable Long id) {
        emotionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
