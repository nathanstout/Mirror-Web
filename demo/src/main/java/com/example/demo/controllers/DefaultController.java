package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.models.enums.Occasion;
import com.example.demo.models.enums.Type;

/**
 * Default controller that handles redirecting the logged-in user to one of the
 * appropriate landing screens based on their user roles. If a new role is added
 * to the system, add to the edu.ncsu.csc.itrust.roles.Role class.
 *
 * Other functionality should (generally) not be added to this class and instead
 * go in an appropriate controller for the user type. See the sub-packages for
 * location of each controller type.
 *
 * @author Kai Presler-Marshall
 *
 */

@Controller
public class DefaultController {

    @GetMapping ( "" )
    public RedirectView home () {
        return new RedirectView( "viewClothes?type=NotSpecified" );
    }

    @RequestMapping ( "viewClothes" )
    public String viewClothesPage ( final Model model, @RequestParam ( value = "type" ) final Type type ) {
        model.addAttribute( "type", type );
        return "viewClothes";
    }

    @GetMapping ( "outfits" )
    public String outfitsPage ( final Model model, @RequestParam ( value = "occasion" ) final Occasion occasion ) {
        model.addAttribute( "occasion", occasion );
        return "outfits";
    }

    @GetMapping ( { "calendar", "calendar.html" } )
    public String calendarPage ( final Model model ) {
        return "calendar";
    }
}
