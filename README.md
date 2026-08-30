# API REST de Películas

API REST para gestionar películas, géneros, años y actores.

## Descripción

API REST desarrollada con Spring Boot que permite:
- Obtener todas las películas
- Obtener una película por ID
- Añadir una película
- Actualizar una película
- Eliminar una película
- Buscar películas por título o género

## Pre-requisitos

- Java 21
- Maven
- Spring Boot 4.1.1

## Instalación

```bash
git clone <repositorio>
cd api-rest-movies
mvn install
```

## Ejecución

```mvn spring-boot:run```

## Endpoints

Método	Endpoint	Descripción
GET	/api/v1/movies	Obtener todas las películas
GET	/api/v1/movies/{id}	Obtener película por ID
POST	/api/v1/movies	Añadir película
PUT	/api/v1/movies/{id}	Actualizar película
DELETE	/api/v1/movies/{id}	Eliminar película
GET	/api/v1/movies/search?title=Cure	Buscar por título
GET	/api/v1/movies/search?genre=Drama	Buscar por género

## Diagramas

### Diagrama de casos de uso
```mermaid
graph TD
    Usuario((Usuario)) -->|obtener todas| ObtenerPeliculas
    Usuario -->|obtener por ID| ObtenerPeliculaPorId
    Usuario -->|añadir| AnadirPelicula
    Usuario -->|actualizar| ActualizarPelicula
    Usuario -->|eliminar| EliminarPelicula
    Usuario -->|buscar por título| BuscarPorTitulo
    Usuario -->|buscar por género| BuscarPorGenero
```

### Diagrama de secuencia (GET /api/v1/movies)
```mermaid
sequenceDiagram
    Cliente->>Controlador: GET /api/v1/movies
    Controlador->>Servicio: getEntities()
    Servicio->>Repositorio: findAll()
    Repositorio-->>Servicio: List<MovieEntity>
    Servicio-->>Controlador: List<MovieDTOResponse>
    Controlador-->>Cliente: JSON
```

### Diagrama de clases
```mermaid
classDiagram
    class MovieEntity {
        +Long id
        +String title
        +String director
        +int length
        +Set<GenreEntity> genres
        +YearEntity year
        +Set<ActorEntity> actors
    }
    class GenreEntity {
        +Long id
        +String name
    }
    class YearEntity {
        +Long id
        +int year
    }
    class ActorEntity {
        +Long id
        +String name
    }
    MovieEntity "N" --> "N" GenreEntity : many-to-many
    MovieEntity "N" --> "1" YearEntity : many-to-one
    MovieEntity "N" --> "N" ActorEntity : many-to-many
```

### Diagrama de Chen (Entidad-Relación)
```mermaid
erDiagram
    MOVIES ||--o{ MOVIES_GENRES : has
    GENRES ||--o{ MOVIES_GENRES : has
    MOVIES }o--|| YEARS : belongs_to
    MOVIES ||--o{ MOVIES_ACTORS : has
    ACTORS ||--o{ MOVIES_ACTORS : has
```

### Diagrama de patas de gallo
```mermaid
erDiagram
    MOVIES ||--o{ MOVIES_GENRES : has
    GENRES ||--o{ MOVIES_GENRES : has
    MOVIES }o--|| YEARS : belongs_to
    MOVIES ||--o{ MOVIES_ACTORS : has
    ACTORS ||--o{ MOVIES_ACTORS : has
```
