package org.seng2050.lab08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {

    @Autowired
    private Cart cart;

    @GetMapping("/cart")
    public ModelAndView viewStore() {
        return new ModelAndView("cart");
    }

    @PostMapping("/cart")
    public String postMethodName(
        @RequestParam("id") String id,
        @RequestParam("quantity") int quantity
    ) {

        this.cart.addItem(id, quantity);
        
        return "redirect:/cart";
    }
}
