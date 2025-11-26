🧬 Proyecto Integrador — Detector de Mutantes
UTN – Facultad Regional Mendoza

📌 Descripción del Proyecto

Este proyecto implementa una API REST capaz de analizar secuencias de ADN para determinar si un humano es mutante, siguiendo el examen técnico de MercadoLibre.
El sistema detecta patrones de 4 letras consecutivas (A, T, C, G) en horizontal, vertical y diagonales.

Además, almacena cada análisis en una base de datos H2, usando SHA-256 para evitar duplicados y respetar normas de integridad.

Incluye:

🧪 Tests unitarios y de integración (MockMvc)

📊 Coverage JaCoCo > 80%

📘 Swagger UI documentando los endpoints

🐳 Dockerfile listo para contenerización

✔ Validaciones personalizadas

✔ Manejo global de excepciones

✔ Diagramas UML de secuencia


  🚀 Cómo Ejecutar el Proyecto
  
▶ 1. Ejecutar con Spring Boot
./gradlew bootRun

La API corre en:
👉 http://localhost:8080

▶ 2. Ejecutar con Docker

Construir la imagen:

./gradlew clean build -x test
docker build -t mutantes-app .

Levantar el contenedor:

docker run -p 8080:8080 mutantes-app

📘 Swagger – Documentación Automática

Swagger UI disponible en:

👉 http://localhost:8080/swagger-ui/index.html

🗄️ Base de Datos – H2 (In-Memory)

Este proyecto utiliza una base de datos H2 en memoria (in-memory) para almacenar los resultados del análisis de ADN.

🔍 Acceso al H2 Console

Una vez ejecutada la aplicación (local o en Docker):

👉 http://localhost:8080/h2-console

Usar estos valores:

Campo	Valor
JDBC URL	jdbc:h2:mem:mutantesdb
User	sa
Password	(vacío)

📌 IMPORTANTE: Si la levantaste por Docker, asegurate de ejecutar:

docker run -p 8080:8080 mutantes-app


o no levantará la consola.

🧬 Endpoints de la API
✔ POST /mutant

Analiza una secuencia de ADN.

Request:
{
  "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

Respuestas:

200 OK → Es mutante

403 Forbidden → Es humano

400 Bad Request → Falla de validación

✔ GET /stats

Retorna estadísticas globales:

{
  "countMutantDna": 40,
  "countHumanDna": 100,
  "ratio": 0.4
}

🔐 Hashing SHA-256 para evitar duplicados

Cada análisis no guarda el ADN original, solo:

hash SHA-256

si es mutante o no

Ejemplo de hash almacenado:

54d9bdcb9879e883ec1bc15b61d757d154ba695477b17b13021e353194cd2b22

Esto mejora la performance, evita reprocesamiento y respeta seguridad de datos.

✔ Validaciones Custom (ConstraintValidator)

El ADN se valida antes de procesarse:

Debe ser matriz NxN

Solo letras A, T, C, G

Sin filas nulas

Longitud mínima > 0

⚠️ Manejo Global de Excepciones

Centralizado en:
GlobalExceptionHandler.java

Ejemplo de error:

{
  "error": "Secuencia de ADN inválida"
}

👨‍💻 Tests Unitarios + MockMvc

Incluye tests para:

Componente	Archivo
Detector de mutantes	MutantDetectorTest.java
MutantService	MutantServiceTest.java
StatsService	StatsServiceTest.java
Controlador REST	MutantControllerTest.java

Los tests usan:
Mockito
MockMvc
JUnit 5

📊 Cobertura de Código (JaCoCo)

Generar reporte:

./gradlew clean test
./gradlew jacocoTestReport

Abrir el reporte:

👉 build/reports/jacoco/test/html/index.html

✔ Coverage Global > 80%
✔ Servicios y controlador cubiertos

🛠 Tecnologías Utilizadas

Java 21
Spring Boot 3.4
H2 Database
JPA / Hibernate
Mockito
JUnit 5
Swagger / OpenAPI
Docker
JaCoCo
Lombok

📦 Cómo Clonar y Probar

git clone https://github.com/Tomiaranda/Mutantes-utn-aranda.git

cd Mutantes-utn-aranda

./gradlew bootRun

👤 Autor

Tomás Aranda

Legajo:50766

📧 arandatomi10@gmail.com

🐙 GitHub: https://github.com/Tomiaranda

🎓 Proyecto Integrador — Desarrollo de Software

UTN – Facultad Regional Mendoza
