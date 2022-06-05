package com.example.demo.controllers.api;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.forms.ClothingForm;
import com.example.demo.models.Clothing;
import com.example.demo.models.enums.Color;
import com.example.demo.models.enums.Type;
import com.example.demo.services.ClothingService;

@RestController
public class APIClothingController extends APIController {

    @Autowired
    private ClothingService service;

    private static String   UPLOAD_DIR = System.getProperty( "user.home" ) + "/test";

    @SuppressWarnings ( "unchecked" )
    @GetMapping ( BASE_PATH + "/clothes" )
    public List<Clothing> getClothes () {
        return (List<Clothing>) service.findAll();
    }

    @SuppressWarnings ( { "unchecked", "rawtypes" } )
    @GetMapping ( BASE_PATH + "/filter/{type}/{primaryColor}/{secondaryColor}" )
    public List<Clothing> filterClothes ( @PathVariable ( "type" ) final Type type,
            @PathVariable ( required = false ) final String primaryColor,
            @PathVariable ( required = false ) final String secondaryColor ) {

        Set<Clothing> allSet = null; // Handle case for filtering ALL clothes
        if ( type == Type.NotSpecified ) {
            allSet = new HashSet( service.findAll() );
        }
        else {
            allSet = new HashSet( service.findByType( type ) );
        }
        if ( !primaryColor.equals( "null" ) ) {
            final Color pColor = Color.valueOf( primaryColor );
            final Set<Clothing> typeSet = new HashSet( service.findByPrimaryColor( pColor ) );
            allSet.retainAll( typeSet );
        }
        if ( !secondaryColor.equals( "null" ) ) {
            final Color sColor = Color.valueOf( secondaryColor );
            final Set<Clothing> typeSet = new HashSet( service.findBySecondaryColor( sColor ) );
            allSet.retainAll( typeSet );
        }
        return new ArrayList<>( allSet );

    }

    @SuppressWarnings ( { "unchecked", "rawtypes" } )
    @GetMapping ( BASE_PATH + "/clothes/{type}" )
    public ResponseEntity getClothesByType ( @PathVariable ( "type" ) final Type type ) {
        try {
            List<Clothing> clothes = null;
            if ( type.equals( Type.NotSpecified ) ) {
                clothes = (List<Clothing>) service.findAll();
            }
            else {
                clothes = service.findByType( type );
            }
            if ( clothes == null ) {
                return new ResponseEntity( errorResponse( "No clothes of type " + type ), HttpStatus.NOT_FOUND );
            }
            return new ResponseEntity( clothes, HttpStatus.OK );
        }
        catch ( final Exception e ) {
            return new ResponseEntity(
                    errorResponse( "Could not retrieve clothes of type " + type + " because of " + e.getMessage() ),
                    HttpStatus.BAD_REQUEST );
        }
    }

    @SuppressWarnings ( { "unchecked", "rawtypes" } )
    @PostMapping ( BASE_PATH + "/clothes" )
    public ResponseEntity createClothing ( @ModelAttribute final ClothingForm clothingForm ) throws Exception {
        String result = null;
        try {

            result = this.saveUploadedFiles( clothingForm.getPhoto() );

        }
        // Here Catch IOException only.
        // Other Exceptions catch by RestGlobalExceptionHandler class.
        catch ( final IOException e ) {
            e.printStackTrace();
            return new ResponseEntity( errorResponse( "Error: " + e.getMessage() ), HttpStatus.BAD_REQUEST );
        }

        Clothing clothing = null;
        try {
            clothing = new Clothing( clothingForm );
            service.save( clothing );
            return new ResponseEntity( successResponse( "successfully created. uploaded to " + result ),
                    HttpStatus.OK );
        }
        catch ( final Exception e ) {
            System.out.println( e.getMessage() );
            return new ResponseEntity( errorResponse( "Error: " + e.getMessage() ), HttpStatus.BAD_REQUEST );
        }
    }

    // Save Files
    private String saveUploadedFiles ( final MultipartFile file ) throws IOException {

        // Make sure directory exists!
        final File uploadDir = new File( UPLOAD_DIR );
        uploadDir.mkdirs();

        final StringBuilder sb = new StringBuilder();

        if ( !file.isEmpty() ) {
            final String uploadFilePath = UPLOAD_DIR + "/" + file.getOriginalFilename();

            final byte[] bytes = file.getBytes();
            final Path path = Paths.get( uploadFilePath );
            Files.write( path, bytes );

            sb.append( uploadFilePath ).append( ", " );
        }

        return sb.toString();
    }

    private void deleteUploadedFiles ( final String filename ) throws IOException {
        final List<Clothing> clothes = service.findByPhoto( filename );
        // If photo is being used by a different object do not delete it
        if ( clothes.size() <= 1 ) {
            final String filePath = UPLOAD_DIR + "/" + filename;
            final Path path = Paths.get( filePath );
            Files.deleteIfExists( path );
        }
    }

    @GetMapping ( BASE_PATH + "/photos/{filename}" )
    public ResponseEntity<Resource> getFile ( @PathVariable final String filename ) throws MalformedURLException {
        final File file = new File( UPLOAD_DIR + "/" + filename );
        if ( !file.exists() ) {
            throw new RuntimeException( "File not found" );
        }
        final Resource resource = new UrlResource( file.toURI() );
        return ResponseEntity.ok()
                .header( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"" )
                .body( resource );
    }

    @SuppressWarnings ( { "rawtypes", "unchecked" } )
    @GetMapping ( BASE_PATH + "/clothing/{id}" )
    public ResponseEntity getClothing ( @PathVariable final Long id ) {
        final Clothing clothing = (Clothing) service.findById( id );
        if ( null == clothing ) {
            return new ResponseEntity( errorResponse( "Clothing not found" ), HttpStatus.NOT_FOUND );
        }

        return new ResponseEntity( clothing, HttpStatus.OK );
    }

    @SuppressWarnings ( { "rawtypes", "unchecked" } )
    @PutMapping ( BASE_PATH + "/clothes" )
    public ResponseEntity editClothing ( @ModelAttribute final ClothingForm clothingForm ) {
        System.out.println( clothingForm.toString() );
        final Clothing updated = new Clothing( clothingForm );
        final Clothing original = (Clothing) service.findById( clothingForm.getId() );

        String result = "";
        if ( updated.getPhoto() == null || updated.getPhoto().isEmpty() ) {
            updated.setPhoto( original.getPhoto() );
        }
        else if ( !updated.getPhoto().equals( original.getPhoto() ) ) {
            try {

                result = this.saveUploadedFiles( clothingForm.getPhoto() );
                this.deleteUploadedFiles( original.getPhoto() );
            }
            // Here Catch IOException only.
            // Other Exceptions catch by RestGlobalExceptionHandler class.
            catch ( final IOException e ) {
                e.printStackTrace();
                return new ResponseEntity( errorResponse( "Error: " + e.getMessage() ), HttpStatus.BAD_REQUEST );
            }
        }

        try {
            service.save( updated );
            return new ResponseEntity( successResponse( "successfully created. uploaded to " + result ),
                    HttpStatus.OK );
        }
        catch ( final Exception e ) {
            System.out.println( e.getMessage() );
            return new ResponseEntity( errorResponse( "Error: " + e.getMessage() ), HttpStatus.BAD_REQUEST );
        }
    }

    @SuppressWarnings ( { "rawtypes", "unchecked" } )
    @DeleteMapping ( BASE_PATH + "/clothes/{id}" )
    public ResponseEntity deleteClothing ( @PathVariable final Long id ) {
        final Clothing clothing = (Clothing) service.findById( id );
        if ( null == clothing ) {
            return new ResponseEntity( errorResponse( "No clothing found" ), HttpStatus.NOT_FOUND );
        }
        final String filename = clothing.getPhoto();
        try {
            this.deleteUploadedFiles( filename );
            service.delete( clothing );
        }
        // Here Catch IOException only.
        // Other Exceptions catch by RestGlobalExceptionHandler class.
        catch ( final IOException e ) {
            e.printStackTrace();
            return new ResponseEntity( errorResponse( "Error: " + e.getMessage() ), HttpStatus.BAD_REQUEST );
        }

        return new ResponseEntity( successResponse( "deleted successfully" ), HttpStatus.OK );
    }

}
