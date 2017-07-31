package app.gamestore.dto.bindingDtos.user;

import app.gamestore.dto.bindingDtos.game.BoughtGame;
import app.gamestore.dto.bindingDtos.game.ShoppingCartGame;
import app.gamestore.entities.enums.Role;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ShoppingCartUser {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private Role role;
    private Set<ShoppingCartGame> shoppingCart;
    private Set<BoughtGame> boughtGames;

    public ShoppingCartUser() {
        this.shoppingCart = new HashSet<>();
        this.boughtGames = new HashSet<>();
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

    public Set<ShoppingCartGame> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Set<ShoppingCartGame> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Set<BoughtGame> getBoughtGames() {
        return boughtGames;
    }

    public void setBoughtGames(Set<BoughtGame> boughtGames) {
        this.boughtGames = boughtGames;
    }

    public boolean addGameToCart(ShoppingCartGame game) {

        boolean isFound = false;

        for (BoughtGame boughtGame : boughtGames) {
            if (Objects.equals(boughtGame.getId(), game.getId()) && Objects.equals(boughtGame.getTitle(), game.getTitle())) {
                isFound = true;
            }
        }

        if(!isFound) {
            this.shoppingCart.add(game);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeGameFromCart(ShoppingCartGame game) {
       return shoppingCart.remove(game);
    }
}
