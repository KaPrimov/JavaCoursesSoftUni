package app.gamestore.dto.bindingDtos.user;

import app.gamestore.dto.viewDtos.game.BoughGameView;
import app.gamestore.entities.enums.Role;

import java.util.HashSet;

public class BoughtGamesUser {

    private Long id;
    private String email;
    private String password;
    private String fullName;
    private Role role;
    private HashSet<BoughGameView> boughtGames;
    private HashSet<BoughGameView> shoppingCart;

    public BoughtGamesUser() {
    }

    public HashSet<BoughGameView> getBoughtGames() {
        return boughtGames;
    }

    public void setBoughtGames(HashSet<BoughGameView> boughtGames) {
        this.boughtGames = boughtGames;
    }

    public HashSet<BoughGameView> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(HashSet<BoughGameView> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void buyAll() {
        boughtGames.addAll(shoppingCart);
        shoppingCart.clear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Successfully bought games:");
        sb.append(System.lineSeparator());
        for (BoughGameView boughtGame : boughtGames) {
            sb.append(boughtGame.toString()).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
