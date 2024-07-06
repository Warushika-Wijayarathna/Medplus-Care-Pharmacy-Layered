package lk.ijse.medpluscarepharmacylayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemDTO {
    private String  itemId;
    private String description;
    private int qty;
    private double wholeSalePrice;
    private double retailPrice;
    private double discount;
    private LocalDate expDate;


    public ItemDTO(String id, String desc, int qty, Double price, Double discount, double wholeSalePrice, LocalDate exp ){
        this.itemId=id;
        this.description=desc;
        this.qty=qty;
        this.wholeSalePrice=wholeSalePrice;
        this.retailPrice=price;
        this.discount=discount;
        this.expDate=exp;

    }

    public ItemDTO(String id, String desc, double price, double discount) {
        this.itemId=id;
        this.description=desc;
        this.retailPrice=price;
        this.discount=discount;
    }

    public ItemDTO(String desc, int qty, double wholeSalePrice, double retailPrice, double discount, LocalDate expDate) {
        this.description=desc;
        this.qty=qty;
        this.wholeSalePrice=wholeSalePrice;
        this.retailPrice=retailPrice;
        this.discount=discount;
        this.expDate=expDate;
    }
}