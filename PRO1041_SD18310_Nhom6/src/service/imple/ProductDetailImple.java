/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.imple;

import java.util.ArrayList;
import model.entity.ProductDetail;
import repository.ProductDetailRepository;
import service.ProductDetailService;

/**
 *
 * @author TgNam
 */
public class ProductDetailImple implements ProductDetailService {

    ProductDetailRepository pdr = new ProductDetailRepository();

    @Override
    public ArrayList<ProductDetail> getList() {
        return pdr.getAll();
    }

    @Override
    public ArrayList<ProductDetail> getproduct_Exchange() {
        return pdr.getProductDetails_Exchange();
    }

    @Override
    public ArrayList<ProductDetail> getProductDetails_id(String idPR) {
        return pdr.getProductDetails_id(idPR);
    }

    @Override
    public ArrayList<ProductDetail> getProduct_Detail_Sell() {
        return pdr.getProductDetails_Sell();
    }

    @Override
    public ArrayList<ProductDetail> getProduct_Detail_Stop_selling() {
        return pdr.getProductDetails_id_Stop_Selling();
    }

    @Override
    public boolean them(ProductDetail prd) {
        if (pdr.Insert(prd)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean sua(String id, ProductDetail prd) {
        if (pdr.Update(id, prd)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean xoa(String id) {
        if (pdr.Delete(id)) {
            return true;
        } else {
            return false;
        }
    }

    // thêm sản phẩm vào table product detail của billJPanel 
    @Override
    public ArrayList<ProductDetail> get_ProductDetails_id_Bill(String id, String status) {
        return pdr.get_ProductDetails_id_Bill(id, status);
    }

    @Override
    public ArrayList<ProductDetail> getRestore_Product_Detail_stop_selling(int min, int max) {
        return pdr.getProductDetails_Stop_Selling(min, max);
    }

    @Override
    public boolean khoiPhuc(String id) {
        if (pdr.Restore(id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<ProductDetail> getProductDetail_Selling_Next(String idPR, int min, int max) {
        return this.pdr.getProductDetails_Selling_Next(idPR, min, max);
    }

}
