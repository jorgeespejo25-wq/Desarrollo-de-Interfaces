/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Calculadora.imc.model;

/**
 *
 * @author Jorge Espejo Martínez
 */
public class CalculadoraIMC {

    public double calcular(double peso, double altura) {
        double imc = 0;
        imc = peso / (altura * altura);
        return (imc);
    }

    public String clasificar(double imc) {
        String traduct;
        if (imc <= 24.9) {
            if (imc < 18.5) {
                traduct = "Bajo Peso";
            } else {
                traduct = "Peso Normal";
            }
        } else {
            if (imc >= 30) {
                traduct = "Obesidad";
            } else {
                traduct = "Sobrepeso";
            }
        }
        return traduct;
    }

    public boolean sonValidos(double peso, double altura) {
        boolean validos;
        if (peso > 0 && altura > 0) {
            validos = true;
        } else {
            validos = false;
        }
        return validos;
    }
}
