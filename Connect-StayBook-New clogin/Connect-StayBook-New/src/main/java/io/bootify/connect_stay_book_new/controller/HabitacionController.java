package io.bootify.connect_stay_book_new.controller;

import io.bootify.connect_stay_book_new.domain.Hotel;
import io.bootify.connect_stay_book_new.model.HabitacionDTO;
import io.bootify.connect_stay_book_new.repos.HotelRepository;
import io.bootify.connect_stay_book_new.service.HabitacionService;
import io.bootify.connect_stay_book_new.util.CustomCollectors;
import io.bootify.connect_stay_book_new.util.ReferencedWarning;
import io.bootify.connect_stay_book_new.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/habitacions")
public class HabitacionController {

    private final HabitacionService habitacionService;
    private final HotelRepository hotelRepository;

    public HabitacionController(final HabitacionService habitacionService,
                                final HotelRepository hotelRepository) {
        this.habitacionService = habitacionService;
        this.hotelRepository = hotelRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("hotelValues", hotelRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Hotel::getId, Hotel::getId)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("habitacions", habitacionService.findAll());
        return "habitacion/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("habitacion") final HabitacionDTO habitacionDTO) {
        return "habitacion/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("habitacion") @Valid final HabitacionDTO habitacionDTO,
                      final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "habitacion/add";
        }
        habitacionService.create(habitacionDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("habitacion.create.success"));
        return "redirect:/habitacions";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final String id, final Model model) {
        model.addAttribute("habitacion", habitacionService.get(id));
        return "habitacion/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final String id,
                       @ModelAttribute("habitacion") @Valid final HabitacionDTO habitacionDTO,
                       final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "habitacion/edit";
        }
        habitacionService.update(id, habitacionDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("habitacion.update.success"));
        return "redirect:/habitacions";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final String id,
                         final RedirectAttributes redirectAttributes) {
        final ReferencedWarning referencedWarning = habitacionService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR,
                    WebUtils.getMessage(referencedWarning.getKey(), referencedWarning.getParams().toArray()));
        } else {
            habitacionService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("habitacion.delete.success"));
        }
        return "redirect:/habitacions";
    }
}