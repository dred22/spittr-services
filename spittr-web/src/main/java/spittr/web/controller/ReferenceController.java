package spittr.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spittr.domain.model.Reference;
import spittr.web.exeption.ReferenceNotFoundException;
import spittr.web.service.ReferenceService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/reference")
@Slf4j
public class ReferenceController {

    private ReferenceService referenceService;

    @Autowired
    ReferenceController(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    @GetMapping(value = "/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute(new Reference());
        return "registerForm";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteReference(@PathVariable Long id) {
        referenceService.deleteById(id);
        return "redirect:/references";
    }

    @PostMapping(value = "/register")
    public String processRegistration(@Valid Reference reference, Errors errors, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            return "registerForm";
        }
        Reference savedReference = referenceService.save(reference);
        redirectAttributes.addAttribute("username", reference.getUserName());
        return "redirect:/reference/{username}";
    }

    @GetMapping
    public String showReference(
            @PathVariable String username, Model model) {
        Reference reference = referenceService.findByUsername(username);
        if (reference == null) {
            throw new ReferenceNotFoundException();
        }
        model.addAttribute(reference);
        return "reference";
    }

    @GetMapping(value = "/references")
    public List<Reference> getReferences(Model model,
                                         @RequestParam(value = "max", defaultValue = "9223372036854775807") long max,
                                         @RequestParam(value = "count", defaultValue = "20") int count) {
        log.debug("getting all references");
        List<Reference> references = referenceService.findReferences(max, count);
        if (CollectionUtils.isEmpty(references)) {
            throw new ReferenceNotFoundException();
        }
        //when instead of view name a value is returned, Spring looks for a view with the same name,
        // and if it's a case the model data value name will be composed of his type(s), in this case, it'll be 'referenceList'
        return references;
    }

}