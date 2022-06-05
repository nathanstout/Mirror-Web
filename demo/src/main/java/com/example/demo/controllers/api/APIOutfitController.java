package com.example.demo.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Outfit;
import com.example.demo.models.enums.Occasion;
import com.example.demo.services.OutfitService;

@RestController
public class APIOutfitController extends APIController {
    @Autowired
    private OutfitService service;

    @SuppressWarnings ( "unchecked" )
    @GetMapping ( BASE_PATH + "/outfits" )
    public List<Outfit> getOutfits () {
        return (List<Outfit>) service.findAll();
    }

    @SuppressWarnings ( { "unchecked", "rawtypes" } )
    @GetMapping ( BASE_PATH + "/outfits/{occasion}" )
    public ResponseEntity getOutfitsByOccasion ( @PathVariable ( "occasion" ) final Occasion occasion ) {
        try {
            List<Outfit> outfits = null;
            if ( occasion.equals( Occasion.NotSpecified ) ) {
                outfits = (List<Outfit>) service.findAll();
            }
            else {
                outfits = service.findByOccasion( occasion );
            }
            if ( outfits == null ) {
                return new ResponseEntity( errorResponse( "No outfits of occasion " + occasion ),
                        HttpStatus.NOT_FOUND );
            }
            return new ResponseEntity( outfits, HttpStatus.OK );
        }
        catch ( final Exception e ) {
            return new ResponseEntity(
                    errorResponse(
                            "Could not retrieve outfits of occasion " + occasion + " because of " + e.getMessage() ),
                    HttpStatus.BAD_REQUEST );
        }
    }

    @SuppressWarnings ( { "unchecked", "rawtypes" } )
    @PostMapping ( BASE_PATH + "/outfits" )
    public ResponseEntity createOutfit ( @RequestBody final Outfit outfit ) throws Exception {
        service.save( outfit );
        return new ResponseEntity( successResponse( "Outfit successfully created." ), HttpStatus.OK );
    }
}
