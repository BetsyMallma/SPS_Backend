# SPS - Backend (Spring Boot + PostgreSQL)

API REST para gestionar géneros, directores y títulos (películas/series).
Es la versión en Java del backend que antes estaba en FastAPI, con la misma
estructura del proyecto del profesor (`modelo`, `dao`, `servicio`, `routers`,
`schemas`, `excepciones`, `config`).

## Cómo ejecutarlo

1. **Crear la base de datos** (en pgAdmin, Query Tool sobre la BD `postgres`):
   ```sql
   CREATE DATABASE sps_db;
   ```
2. **Revisar el archivo `.env`** (junto a `pom.xml`). Ya viene con `DB_PASSWORD=admin123`;
   cámbialo si tu contraseña de PostgreSQL es otra.
3. **Spring Tools:** File → Import → Maven → Existing Maven Projects → elegir esta carpeta.
4. Clic derecho en `SpsApplication.java` → **Run As → Spring Boot App**.
5. Abrir Swagger: **http://localhost:8000/docs**

Al primer arranque se crean las tablas y se carga el catálogo de ejemplo
(8 géneros, 8 directores, 25 títulos). Si ya hay datos, no se vuelve a cargar.

> El servidor usa el puerto **8000**, igual que el Frontend. Si tienes corriendo
> otro proyecto en ese puerto (por ejemplo el del profesor), deténlo antes.

## Endpoints

| Recurso | Ruta | Operaciones |
|---|---|---|
| Géneros | `/generos/` | GET, POST, GET `/{id}`, PUT `/{id}`, DELETE `/{id}` |
| Directores | `/directores/` | GET, POST, GET `/{id}`, PUT `/{id}`, DELETE `/{id}` |
| Títulos | `/titulos/` | GET, POST, GET `/{id}`, PUT `/{id}`, DELETE `/{id}` |
| Títulos | `/titulos/{id}/visto` | PUT |
| Títulos | `/titulos/{id}/pendiente` | PUT |
| Títulos | `/titulos/{id}/calificacion` | PUT, body: `{"nota": 8.5}` |

Las rutas y los nombres de los campos JSON (`id_genero`, `id_director`, ...) son
los mismos que en la versión anterior, por eso el Frontend funciona sin cambios.

## Reglas de negocio (iguales a la versión anterior)

- No se repite un género (por nombre) ni un director (nombre + apellido).
- No se repite un título con el mismo director y año.
- El año debe estar entre 1888 y el año actual + 5.
- La calificación debe estar entre 1 y 10.
- El tipo debe ser `PELICULA` o `SERIE`; el estado, `VISTO` o `PENDIENTE` (por defecto `PENDIENTE`).
- No se puede eliminar un género o director que tenga títulos asociados (error 409).
- Los errores se devuelven como `{"detail": "mensaje"}`.
