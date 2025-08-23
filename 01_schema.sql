-- ===========================================
-- Sistema de Gestión Académica - Esquema base
-- DB destino: gestion_academica_db
-- ===========================================

-- 0) (Solo para desarrollo) limpiar si existía antes
DROP SCHEMA IF EXISTS academico CASCADE;

-- 1) Crear schema y usarlo
CREATE SCHEMA academico;
SET search_path TO academico, public;

-- 2) Tablas "maestras" (independientes)

-- PROFESOR
CREATE TABLE profesor (
  profesor_id VARCHAR(10) PRIMARY KEY,
  nombre_completo VARCHAR(120) NOT NULL,
  email VARCHAR(120) NOT NULL UNIQUE
);

-- ESTUDIANTE
CREATE TABLE estudiante (
  carnet VARCHAR(12) PRIMARY KEY,
  nombre VARCHAR(60) NOT NULL,
  apellido VARCHAR(60) NOT NULL,
  fecha_nacimiento DATE NOT NULL
);

-- CURSO (autorrelación para prerrequisito simple)
CREATE TABLE curso (
  curso_id VARCHAR(10) PRIMARY KEY,
  nombre VARCHAR(120) NOT NULL,
  creditos SMALLINT NOT NULL CHECK (creditos BETWEEN 1 AND 10),
  curso_prerrequisito_id VARCHAR(10) NULL,
  CONSTRAINT fk_curso_prerreq
    FOREIGN KEY (curso_prerrequisito_id)
    REFERENCES curso(curso_id)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);

-- TERM (Periodo Académico: año + semestre)
CREATE TABLE term (
  term_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  anio SMALLINT NOT NULL,
  semestre SMALLINT NOT NULL CHECK (semestre IN (1,2)),
  CONSTRAINT uq_term UNIQUE (anio, semestre)
);

-- 3) Tablas dependientes

-- CURSO_OFERTADO (un curso en un periodo, impartido por un profesor)
CREATE TABLE curso_ofertado (
  oferta_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  curso_id VARCHAR(10) NOT NULL,
  term_id INT NOT NULL,
  profesor_id VARCHAR(10) NOT NULL,
  CONSTRAINT fk_oferta_curso
    FOREIGN KEY (curso_id) REFERENCES curso(curso_id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_oferta_term
    FOREIGN KEY (term_id) REFERENCES term(term_id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_oferta_profesor
    FOREIGN KEY (profesor_id) REFERENCES profesor(profesor_id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT uq_oferta UNIQUE (curso_id, term_id)  -- una sola oferta por curso/periodo
);

-- INSCRIPCION (asociativa Estudiante ↔ Curso_Ofertado, guarda nota y fecha)
CREATE TABLE inscripcion (
  inscripcion_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  carnet VARCHAR(12) NOT NULL,
  oferta_id INT NOT NULL,
  nota_final DECIMAL(5,2) NULL CHECK (nota_final BETWEEN 0 AND 100),
  fecha_evaluacion DATE NULL,
  CONSTRAINT fk_insc_est
    FOREIGN KEY (carnet) REFERENCES estudiante(carnet)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_insc_ofer
    FOREIGN KEY (oferta_id) REFERENCES curso_ofertado(oferta_id)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT uq_insc UNIQUE (carnet, oferta_id), -- no doble inscripción
  -- Si hay nota, debe haber fecha; si no hay nota, fecha debe ser NULL
  CONSTRAINT ck_nota_fecha CHECK (
    (nota_final IS NULL AND fecha_evaluacion IS NULL)
    OR (nota_final IS NOT NULL AND fecha_evaluacion IS NOT NULL)
  )
);

-- 4) Índices para acelerar consultas
CREATE INDEX idx_curso_ofertado_term ON curso_ofertado(term_id);
CREATE INDEX idx_inscripcion_carnet ON inscripcion(carnet);
CREATE INDEX idx_inscripcion_oferta ON inscripcion(oferta_id);
