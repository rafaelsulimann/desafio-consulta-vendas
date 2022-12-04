package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleSumaryProjection;

public class SaleSumaryDTO {

    private String sellerName;
    private Double total;

    public SaleSumaryDTO(){
    }

    public SaleSumaryDTO(String sellerName, Double total){
        this.sellerName = sellerName;
        this.total = total;
    }

    public SaleSumaryDTO(SaleSumaryProjection projection){
        sellerName = projection.getSellerName();
        total = projection.getTotal();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "SaleSumaryDTO [sellerName=" + sellerName + ", total=" + total + "]";
    }        
    
}
