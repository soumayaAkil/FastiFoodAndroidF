package com.example.testing.Api.Api_Panier;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testing.Models.Cart;

import java.util.List;

@Dao
public interface CartDao {
    @Insert
    public void addToCart(Cart cart);

    @Query("SELECT * FROM MyCart")
    public List<Cart> getData();

    @Query("SELECT EXISTS (SELECT 1 FROM mycart WHERE id_prod=:id)")
    public int isAddToCart(int id);

    @Query("select COUNT (*) from MyCart")
    int countCart();

    @Query("DELETE FROM MyCart WHERE id_prod=:id ")
    int deleteItem(int id);
    //incremente quantity

    @Query("UPDATE MyCart SET quantite=:quantite+1 WHERE id_prod = :id")
    public int incrquantity(int quantite, int id);

    //decremente quantity
    @Query("UPDATE MyCart SET quantite=:quantite-1 WHERE id_prod = :id")
    public int decrquantity(int quantite, int id);
    //get quantity
    @Query("SELECT quantite FROM MYCART WHERE id_prod = :id")
    public int getqntapr(int id);
}
