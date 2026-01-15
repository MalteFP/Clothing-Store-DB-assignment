package org.example;

abstract class Page {
    protected abstract void display();
    protected abstract Object decision();

    public Object run(){
        display();
        return decision();
    }
}
