package com.example.iasll.bac;


public class Products {
    private String _course;
    private String _foodname;
    private double _price;

    public Products(String course, String name, double price) {
        this._course = course;
        this._foodname = name;
        this._price=price;
    }

    public double get_price() {
        return _price;
    }

    public void set_price(double _price) {
        this._price = _price;
    }

    public String get_course() {
        return _course;
    }

    public void set_course(String _course) {
        this._course = _course;
    }

    public String get_foodname() {
        return _foodname;
    }

    public void set_foodname(String _foodname) {
        this._foodname = _foodname;
    }
}

/*public class Products {
    private String _weight;
    private String _drinks;
    private double _bac;

    public Products(String weight, String beverage, double bac) {
        this._weight = weight;
        this._drinks = beverage;
        this._bac=bac;
    }

    public double get_bac() {
        return _bac;
    }

    public void set_bac(double _bac) {
        this._bac = _bac;
    }

    public String get_weight() {
        return _weight;
    }

    public void set_weight(String _course) {
        this._weight = _weight;
    }

    public String get_drinks() {
        return _drinks;
    }

    public void set_drinks(String _drinks) {
        this._drinks = _drinks;
    }
}
*/

