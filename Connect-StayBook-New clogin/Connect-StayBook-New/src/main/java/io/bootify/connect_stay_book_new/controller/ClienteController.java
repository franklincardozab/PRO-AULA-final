package io.bootify.connect_stay_book_new.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bootify.connect_stay_book_new.model.ClienteDTO;
import io.bootify.connect_stay_book_new.service.ClienteService;
import io.bootify.connect_stay_book_new.util.JsonStringFormatter;
import io.bootify.connect_stay_book_new.util.WebUtils;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ObjectMapper objectMapper;

    public ClienteController(final ClienteService clienteService, final ObjectMapper objectMapper) {
        this.clienteService = clienteService;
        this.objectMapper = objectMapper;
    }

    @InitBinder
    public void jsonFormatting(final WebDataBinder binder) {
        binder.addCustomFormatter(new JsonStringFormatter<List<String>>(objectMapper) {
        }, "historialReservas");
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("clientes", clienteService.findAll());
        return "cliente/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("cliente") final ClienteDTO clienteDTO) {
        return "cliente/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("cliente") @Valid final ClienteDTO clienteDTO,
                      final BindingResult bindingResult,
                      final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "cliente/add";
        }
        clienteService.create(clienteDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("cliente.create.success"));
        return "redirect:/clientes";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final String id, final Model model) {
        model.addAttribute("cliente", clienteService.get(id));
        return "cliente/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final String id,
                    @ModelAttribute("cliente") @Valid final ClienteDTO clienteDTO,
                       final BindingResult bindingResult,
                       final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "cliente/edit";
        }
        clienteService.update(id, clienteDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("cliente.update.success"));
        return "redirect:/clientes";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final String id,
                        final RedirectAttributes redirectAttributes) {
        clienteService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("cliente.delete.success"));
        return "redirect:/clientes";
    }
}