/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Calculadora.imc.controller;

import com.Calculadora.imc.model.CalculadoraIMC;
import com.Calculadora.imc.view.VistaCalculadora;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Jorge Espejo Martínez
 */
public class IMCController implements ActionListener {

    private JTextField txtPeso;
    private JTextField txtAltura;
    private JLabel lblResultado;
    private JLabel lblClasificacion;
    //Instancia del modelo
    private final CalculadoraIMC calculadora = new CalculadoraIMC();

    //Constructor recibe la vista y enlaza los componentes
    public IMCController(VistaCalculadora vista) {
        this.txtPeso = vista.getPeso();
        this.txtAltura = vista.getAltura();
        this.lblResultado = vista.getResultado();
        this.lblClasificacion = vista.getClasificacion();
        //Controlador escuchador
        vista.getCalcular().addActionListener(this); //this = IMCController
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        calcularIMC();
    }

    //Botón
    public void calcularIMC() {
        double peso;
        double altura;
        double imc;
        String pesoStr;
        String alturaStr;
        String clasificacion;
        String imcFormat;

        //Recoge texto de campos txt
        pesoStr = txtPeso.getText();
        alturaStr = txtAltura.getText();
        //RAE: en españa se utiliza la ",", pero por influencia internacional se admite el "."
        pesoStr = pesoStr.replace(',', '.');
        alturaStr = alturaStr.replace(',', '.');
        try {
            //Parsear los textos a double
            peso = Double.parseDouble(pesoStr);
            altura = Double.parseDouble(alturaStr);
            //Validacion de datos
            if (!calculadora.sonValidos(peso, altura)) {
                lblResultado.setText("Error");
                lblClasificacion.setText("Peso y altura deben ser mayores que 0");
                return;
            }
            //Llamada a calcular --> CalculadoraIMC
            imc = calculadora.calcular(peso, altura);
            //Llamar a clasificar --> CalculadoraIMC
            clasificacion = calculadora.clasificar(imc);
            //Remplazar punto por coma
            imcFormat = String.format("%.2f", imc);
            imcFormat = imcFormat.replace('.', ',');
            //Actualizar la Vista con los resultados
            lblResultado.setText(imcFormat);
            lblClasificacion.setText(clasificacion);
            //Colores IMC --> ISO 3864
            if (clasificacion.equals("Peso Normal")) {
                lblClasificacion.setForeground(Color.decode("#009B77"));
            } else {
                if (clasificacion.equals("Bajo Peso") || clasificacion.equals("Sobrepeso")) {
                    lblClasificacion.setForeground(Color.decode("#FF7900"));
                } else {
                    lblClasificacion.setForeground(Color.decode("#FF0000"));
                }
            }
        } catch (NumberFormatException nfe) {
            lblResultado.setText("Error");
            lblClasificacion.setText("Datos inválidos");
        }
    }
}
