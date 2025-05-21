# ConnectStayBookNew

Esta aplicación fue creada con [Bootify.io](https://bootify.io) y adaptada para usar MongoDB como base de datos principal.

---

## 🚀 Desarrollo

Configura la conexión a MongoDB en `application.yml` o crea tu propio archivo `application-local.yml` para sobrescribir los valores en entorno de desarrollo.

Ejemplo de configuración en `application.yml`:

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/connectstaybook
