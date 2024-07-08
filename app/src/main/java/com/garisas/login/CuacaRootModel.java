package com.garisas.login;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CuacaRootModel {
    @SerializedName("list")
    private List<CuacaListModel> listModelList;

    @SerializedName("city")
    private CityModel cityModel;
    public CuacaRootModel(){

    }
    public CityModel getCityModel() {
        return cityModel;
    }

    public void setCityModel(CityModel cityModel) {
        this.cityModel = cityModel;
    }

    public List<CuacaListModel> getListModelList() {
        return listModelList;
    }

    public void setListModelList(List<CuacaListModel> listModelList){
        this.listModelList = listModelList;
    }

}
