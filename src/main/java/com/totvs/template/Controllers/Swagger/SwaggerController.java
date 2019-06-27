package com.totvs.template.Controllers.Swagger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "${endpoint.api}/swagger")
public class SwaggerController {

    @GetMapping
    public void swaggerUi(HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui.html");
    }
}
