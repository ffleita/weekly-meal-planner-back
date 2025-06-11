# weekly-meal-planner-back
## Comandos para levantar la app en un contenedor de Docker
1. docker build -t mi-app-spring .
2. docker run -p 8080:8080 mi-app-spring
## Documentacion de los endpoint
Levantar el servidor local y acceder a la documentacion de Swagger para ver la documentacion
correspondiente a cada uno de los endpoints desarrollados para esta aplicacion.\
[Swagger](http:localhost:8080/swagger-ui/index.html)
## Como probar los contratos
Se configuro SpringSecurity, por lo cual para hacer uso de los contratos se deben seguir estos pasos:
1. Autenticarse a traves del endpoint **_/auth/login_** con el usuario: **_admin_**, password: **_nimda_**.
2. Copiar el token de acceso.
3. Hacer click en el boton **_Authorize_** y completar el formulario con el token previamente copiado.
4. Ya estas listo para probar los demas contratos.
## Tecnologias necesarias
- Docker