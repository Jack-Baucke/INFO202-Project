"use strict";
class SaleItem {

    constructor(product, quantity) {
        // only set the fields if we have a valid product
        if (product) {
            this.product = product;
            this.quantityPurchased = quantity;
            this.salePrice = product.listPrice;
        }
    }

    getItemTotal() {
        return this.salePrice * this.quantityPurchased;
    }

}

class ShoppingCart {

    constructor() {
        this.items = new Array();
    }

    reconstruct(sessionData) {
        for (let item of sessionData.items) {
            this.addItem(Object.assign(new SaleItem(), item));
        }
    }

    getItems() {
        return this.items;
    }

    addItem(item) {
        this.items.push(item);
    }

    setCustomer(customer) {
        this.customer = customer;
    }

    getTotal() {
        let total = 0;
        for (let item of this.items) {
            total += item.getItemTotal();
        }
        return total;
    }

}

// create a module, and load the other pluggable modules
var module = angular.module('ShoppingApp', ['ngResource', 'ngStorage']);

module.factory('productDAO', function ($resource) {
    return $resource('/api/products/:id');
});

module.factory('categoryDAO', function ($resource) {
    return $resource('/api/categories/:cat');
});

module.factory('registerDAO', function ($resource) {
    return $resource('/api/register');
});

module.factory('signInDAO', function ($resource) {
    return $resource('/api/customers/:username');
});

module.factory('cart', function ($sessionStorage) {
    let cart = new ShoppingCart();

    // is the cart in the session storage?
    if ($sessionStorage.cart) {

        // reconstruct the cart from the session data
        cart.reconstruct($sessionStorage.cart);
    }

    return cart;
});

module.factory('saleDAO', function($resource) {
    return $resource('/api/sales');
});



module.controller('ProductController', function (productDAO, categoryDAO) {
    this.products = productDAO.query();
    this.categories = categoryDAO.query();
    // click handler for the category filter buttons
    this.selectCategory = function (selectedCat) {
        this.products = categoryDAO.query({"cat": selectedCat});
    };
    this.getAll = function () {
        this.products = productDAO.query();
    };
});



module.controller('CustomerController', function (registerDAO, signInDAO, $sessionStorage, $window) {

    this.signInMessage = "Please sign in to continure.";

    let ctrl = this;

    this.registerCustomer = function (customer) {
        registerDAO.save(null, customer);
        console.log(customer);
        $window.location.href = "index.html";
    };
    this.signIn = function (username, password) {
        // get customer from web service
        signInDAO.get({'username': username},
                // success
                        function (customer) {
                            // also store the retrieved customer
                            $sessionStorage.customer = customer;
                            // redirect to home
                            $window.location.href = '.';
                        },
                        // fail
                                function () {
                                    ctrl.signInMessage = 'Sign in failed. Please try again.';
                                }
                        );
                    };
            this.checkSignIn = function () {
                if ($sessionStorage.customer) {
                    this.signedIn = true;
                    this.welcome = "Welcome " + $sessionStorage.customer.firstName;
                } else {
                    this.signedIn = false;

                }
            };
            
            this.signOut = function() {
                delete $sessionStorage.customer;
                $window.location.href = "index.html";
            };

        });
    
module.controller('CartController', function (cart, saleDAO, $sessionStorage, $window) {
    this.items = cart.getItems();
    this.total = cart.getTotal();
    this.selectedProduct = $sessionStorage.selectedProduct;
    
    this.buy = function (selectedProduct) {
        $sessionStorage.selectedProduct = selectedProduct;
        $window.location.href = 'cart.html';
    };
    
    this.addToCart = function (quantity) {
        
        let selectedProduct = $sessionStorage.selectedProduct;
        
        $sessionStorage.quantity = quantity;
        let saleItem = new SaleItem($sessionStorage.selectedProduct, $sessionStorage.quantity);
        cart.addItem(saleItem);
        $sessionStorage.cart = cart;
        $window.location.href = 'products.html';
    };
    
    this.checkOutCart = function () {
        
        cart.setCustomer($sessionStorage.customer);
        saleDAO.save(cart);
        delete $sessionStorage.cart;
        $window.location.href = 'thanks.html';        
    };
    
});