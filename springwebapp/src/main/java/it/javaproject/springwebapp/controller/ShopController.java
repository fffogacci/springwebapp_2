package it.javaproject.springwebapp.controller;

import it.javaproject.springwebapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import it.javaproject.springwebapp.model.Item;

import java.util.*;

@Controller
public class ShopController {

    private List<Item> listaOggetti = creaLista();
    private HashMap<String, User> listaUtenti = new HashMap<>();
    private User loggedUser;

    private ArrayList creaLista(){
        Item item1 = new Item(01, "iphone", "SE", 500.00F);
        Item item2 = new Item(02, "televisione", "42 pollici", 800.00F);
        Item item3 = new Item(03, "lavatrice", "8 kg", 400.00F);

        ArrayList<Item> a = new ArrayList<>();
        a.add(item1);
        a.add(item2);
        a.add(item3);

        return a;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/formRegistrazione")
    public String registrazione(Model model) {
        model.addAttribute("user", new User());
        return "registrazione";
    }

    @PostMapping("/goToHomepage")
    public String homepage(@ModelAttribute User user, Model model) {
        listaUtenti.remove(loggedUser.getUsername());
        listaUtenti.put(user.getUsername(), user);
        model.addAttribute("user", user);
        return "homepage";
    }


    @GetMapping("/goToProfilo")
    public String profilo(Model model) {
        System.out.println("go to profilo "+loggedUser);
        model.addAttribute("user", loggedUser);
        return "profilo";
    }

    @GetMapping("/goToCarrello")
    public String carrello(Model model) {
        model.addAttribute("items", listaOggetti);
        return "carrello";
    }

    @GetMapping("/goToOrdini")
    public String ordini( Model model) {
       // model.addAttribute("user", );
        return "ordini";
    }



    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        if(listaUtenti.containsKey(user.getUsername())){
            System.out.println("login  "+listaUtenti.get(user.getUsername()));
            model.addAttribute("user", listaUtenti.get(user.getUsername()));
            loggedUser = user;
            return "homepage";
        } else{
            return "error";
        }
    }

    //metodo registrazione
    @PostMapping("/registrazione")
    public String registrazione(@ModelAttribute User user, Model model){
        String userName = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        if(!userName.isEmpty() && !password.isEmpty() && !email.isEmpty() && !listaUtenti.containsKey(user.getUsername())){
            listaUtenti.put(user.getUsername(), user);
            loggedUser = user;
            return "homepage";
        }else{
            return "error";
        }
    }


    private List<User> getFakeUsers() {
        User user1 = new User("daniele", "daniele", getAvatar(), "Via tiburtina", "dani@gmail.com");
        User user2 = new User("francesca", "daniele", getAvatar(), "Via aurelia", "franci@gmail.com");
        User user3 = new User("marco", "marco", getAvatar(), "Via aurelia", "marco@gmail.com");
        return Arrays.asList(user1, user2, user3);
    }

    private String getAvatar(){
        return "https://robohash.org/"+ new Random().nextInt() +".png?size=100x100";
    }

}
