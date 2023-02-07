package hu.nye.national_library_system;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/User/login")
public class Controller {

    @PostMapping
    public String test() {
        return "{\"token\":\"42d61305-fefb-4118-b858-38e20fe5bba0\",\"user\":{\"id\":54,\"name\":\"kiss.miklos.daniel@gmail.com\",\"username\":\"kiss.miklos.daniel@gmail.com\",\"registered\":\"2021-12-15T09:56:29.000Z\"},\"system\":{\"code\":\"uat\",\"label\":\"uat\"},\"module\":{\"code\":\"Nls\",\"label\":\"Nls\",\"permissions\":{\"Book\":[\"LIST\",\"LOAD\",\"CREATE\",\"MODIFY\",\"DELETE\",\"MENU\"],\"Library\":[\"LIST\",\"LOAD\",\"CREATE\",\"MODIFY\",\"DELETE\",\"MENU\"],\"LibraryBook\":[\"LIST\",\"LOAD\",\"CREATE\",\"MODIFY\",\"DELETE\",\"MENU\"]}},\"role\":{\"code\":\"ADMIN\",\"label\":\"Adminisztrátor\"},\"clientsession\":{\"token\":\"d1412416-2ea0-441e-9526-9e64a4f9d40b\",\"label\":\"d1412416-2ea0-441e-9526-9e64a4f9d40b\"},\"login\":{\"id\":\"af57c50c-c974-426d-8cf5-6bed1d5d43a7\",\"label\":\"af57c50c-c974-426d-8cf5-6bed1d5d43a7\"},\"created\":\"2023-02-04T15:06:05.000Z\",\"userKey\":\"dc56a3e0-257b-4731-bf3c-836d5da8d621\",\"groupKey\":\"11f46312-24a7-4a5f-b6fe-4e057e255dff\",\"apitoken\":\"c7348130-e2f3-4886-a92b-552bb9d0c783\"}";
    }
}

//TODO: nem id-t küldeni, hanem label + id
