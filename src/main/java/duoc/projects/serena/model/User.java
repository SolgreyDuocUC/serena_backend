package duoc.projects.serena.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_usuario")
    private String userName;

    @Column(name = "email_usuario", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "contrasenia_usuario", nullable = false)
    private String userPassword;

    @Column(name = "usuario_condiciones")
    private Boolean userAceptConditions;

    @Column(name = "imagen_usuario")
    private String userImageUri;

    // Métodos requeridos por Spring Security ---
    public String getUsername() {
        return this.userEmail; // Spring usa email para login
    }

    public String getPassword() {
        return this.userPassword;
    }

    // Este método devuelve el nombre del usuario
    public String getName() {
        return this.userName;
    }
}
