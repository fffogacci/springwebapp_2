package it.javaproject.springwebapp.controller;

import it.javaproject.springwebapp.model.Ordine;
import it.javaproject.springwebapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import it.javaproject.springwebapp.model.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class ShopController {

    private List<Item> listaOggetti = new ArrayList<>();
    private HashMap<String, User> listaUtenti = new HashMap<>();
    private User loggedUser;
    private List<Item> shoppingTmp = new ArrayList<>();

    private Ordine ordine;
    private List<Ordine> listaOrdini= new ArrayList<>();

    private ArrayList creaLista(){
        Item item1 = new Item(0, "iphone", "SE", 500.00F);
        Item item2 = new Item(1, "televisione", "42 pollici", 800.00F);
        Item item3 = new Item(2, "lavatrice", "8 kg", 400.00F);

        ArrayList<Item> a = new ArrayList<>();
        a.add(item1);
        a.add(item2);
        a.add(item3);

        System.out.println(a.toString());

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
        listaOggetti= creaLista();
        model.addAttribute("item", new Item());
        model.addAttribute("items", listaOggetti);
        return "carrello";
    }

    @GetMapping("/gotoShopping")
    public String gtShop(@ModelAttribute Item item, Model model){
        /*
        item solo con code
        cerco in lista item completo
        e lo agg in carrello, var globale controller shoppingchart
         */
        Item tmp= listaOggetti.get(item.getCode());
        shoppingTmp.add(tmp);
        System.out.println("gtShop(): " + tmp);
        model.addAttribute("item", new Item());
        model.addAttribute("items", listaOggetti);
        model.addAttribute("shoppingTmp", shoppingTmp);
        return "carrello";
    }





    @GetMapping("/gestioneCarrello")
    public String gestCarrello(Model model){
        //parsing:DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        ordine= new Ordine(date, shoppingTmp);
        listaOrdini.add(ordine);
        model.addAttribute("ordini", listaOrdini);

        shoppingTmp.clear();

        return "ordini";
    }



    @GetMapping("/dopoOrdine")
    public String dopoOrd( Model model) {
       // model.addAttribute("user", );
        model.addAttribute("user", loggedUser);
        return "homepage";
    }

    @GetMapping("/goToOrdini")
    public String gtor(Model model){
        model.addAttribute("ordini", listaOrdini);
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
