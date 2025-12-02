-- Script para insertar emociones básicas en la base de datos
-- Ejecutar en DBeaver conectado a la base de datos PostgreSQL

INSERT INTO emotion (name_emotion, description_emotion, color_emotion, text_color_emotion, icon_emotion) VALUES
('Felicidad', 'Sentimiento de alegría y bienestar', '#FFD700', '#000000', '😊'),
('Tristeza', 'Sentimiento de melancolía', '#4A90E2', '#FFFFFF', '😢'),
('Ansiedad', 'Sentimiento de preocupación o nerviosismo', '#FF6B6B', '#FFFFFF', '😰'),
('Calma', 'Estado de tranquilidad y paz', '#7DD3C0', '#000000', '😌'),
('Enojo', 'Sentimiento de ira o molestia', '#E74C3C', '#FFFFFF', '😠'),
('Miedo', 'Sentimiento de temor o amenaza', '#9B59B6', '#FFFFFF', '😨'),
('Sorpresa', 'Reacción ante algo inesperado', '#F39C12', '#000000', '😲'),
('Amor', 'Sentimiento de afecto profundo', '#E91E63', '#FFFFFF', '❤️'),
('Aburrimiento', 'Falta de interés o motivación', '#95A5A6', '#FFFFFF', '😐'),
('Gratitud', 'Sentimiento de agradecimiento', '#27AE60', '#FFFFFF', '🙏');

-- Verificar que se insertaron correctamente
SELECT * FROM emotion ORDER BY id_emotion;


