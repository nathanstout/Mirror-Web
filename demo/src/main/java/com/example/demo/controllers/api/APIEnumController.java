package com.example.demo.controllers.api;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.enums.Color;
import com.example.demo.models.enums.Occasion;
import com.example.demo.models.enums.Type;

@RestController
public class APIEnumController extends APIController {
    @GetMapping ( BASE_PATH + "/type" )
    public List<Map<String, Object>> getTypes () {
        return Arrays.asList( Type.values() ).stream().map( t -> t.getInfo() ).collect( Collectors.toList() );
    }

    @GetMapping ( BASE_PATH + "/color" )
    public List<Map<String, Object>> getColors () {
        return Arrays.asList( Color.values() ).stream().map( c -> c.getInfo() ).collect( Collectors.toList() );
    }

    @GetMapping ( BASE_PATH + "/occasion" )
    public List<Map<String, Object>> getOccasions () {
        return Arrays.asList( Occasion.values() ).stream().map( o -> o.getInfo() ).collect( Collectors.toList() );
    }
}
