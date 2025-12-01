package duoc.projects.serena.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "emotion")
@Data
public class Emotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emotion")
    private Long id;

    @Column(name = "name_emotion", nullable = false)
    private String name;

    @Column(name = "description_emotion", nullable = false)
    private String description;

    @Column(name = "color_emotion", nullable = false)
    private String color;       // aquí guardas el HEX "#FFFFFF"

    @Column(name = "text_color_emotion", nullable = false)
    private String textColor;   // otro HEX

    @Column(name = "icon_emotion", nullable = false)
    private String icon;        // URL o emoji
}

