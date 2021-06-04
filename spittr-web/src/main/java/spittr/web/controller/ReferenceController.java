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
        return "redirect:/reference/list";
    }

    @PostMapping(value = "/register")
    public String processRegistration(@Valid Reference reference, Errors errors, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            return "registerForm";
        }
        Reference savedReference = referenceService.save(reference);
        redirectAttributes.addAttribute("id", savedReference.getId());
        return "redirect:/reference/{id}";
    }

    @GetMapping("/{id}")
    public String showReference(@PathVariable Long id, Model model) {
        Reference reference = referenceService.findById(id);
        if (reference == null) {
            throw new ReferenceNotFoundException();
        }
        model.addAttribute(reference);
        return "reference";
    }

    @GetMapping(value = "/list")
    public String getReferences(Model model,
                                         @RequestParam(value = "max", defaultValue = "9223372036854775807") long max,
                                         @RequestParam(value = "count", defaultValue = "20") int count) {
        log.debug("getting all references");
        List<Reference> references = referenceService.findReferences(max, count);
        if (CollectionUtils.isEmpty(references)) {
            throw new ReferenceNotFoundException();
        }
        model.addAttribute("references", references);
        return "list";
    }

}