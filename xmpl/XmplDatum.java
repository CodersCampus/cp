package com.coderscampus.xmpl;

public class XmplDatum {
    private String yada;
    private int foo;
    private boolean bar;
    private String wah;

    public XmplDatum() {
    }

    public XmplDatum(String yada, int foo, boolean bar, String wah) {
        this.yada = yada;
        this.foo = foo;
        this.bar = bar;
        this.wah = wah;
    }

    public String toString() {
        return "XmplDatum{" +
                "yada='" + yada + '\'' +
                ", foo=" + foo +
                ", bar=" + bar +
                ", wah='" + wah + '\'' +
                '}';
    }

    public String getYada() {
        return yada;
    }

    public void setYada(String yada) {
        this.yada = yada;
    }

    public int getFoo() {
        return foo;
    }

    public void setFoo(int foo) {
        this.foo = foo;
    }

    public boolean isBar() {
        return bar;
    }

    public void setBar(boolean bar) {
        this.bar = bar;
    }

    public String getWah() {
        return wah;
    }

    public void setWah(String wah) {
        this.wah = wah;
    }
}
