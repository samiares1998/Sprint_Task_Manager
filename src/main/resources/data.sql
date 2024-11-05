-- 1. Crear tabla de Usuarios
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(20) DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Crear tabla de Tareas
CREATE TABLE tasks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    status ENUM('PENDING', 'IN_PROGRESS', 'COMPLETED') DEFAULT 'PENDING',
    priority ENUM('HIGH', 'LOW') DEFAULT 'LOW',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 3. Insertar datos iniciales (opcional)
INSERT INTO users (username, password, email, role) VALUES
('admin@example.com', 'admin_password_encrypted', 'admin@example.com', 'ADMIN'),
('user1', 'user1_password_encrypted', 'user1@example.com', 'USER');

-- 4. Insertar ejemplos de tareas (opcional)
INSERT INTO tasks (user_id, title, description, status) VALUES
(1, 'Setup Project', 'Setup Spring Boot project for the API', 'COMPLETED'),
(2, 'Add Authentication', 'Implement JWT for authentication', 'IN_PROGRESS');
