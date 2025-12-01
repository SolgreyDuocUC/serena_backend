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

    @Column(name = "nombreUsuario")
    private String userName;

    @Column(name = "emailUsuario", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "contraseniaUsuario", nullable = false)
    private String userPassword;

    @Column(name = "usuarioCondiciones")
    private Boolean userAceptConditions;

    @Column(name = "imagenUsuario")
    private String userImageUri;
}

