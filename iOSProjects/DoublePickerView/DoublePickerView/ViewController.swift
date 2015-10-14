//
//  ViewController.swift
//  DoublePickerView
//
//  Created by Xavi Moll Villalonga on 14/10/15.
//  Copyright © 2015 Xavi Moll Villalonga. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDelegate {
    
    @IBOutlet weak var imageIzquierda: UIImageView!
    @IBOutlet weak var imageDerecha: UIImageView!
    @IBOutlet weak var textLabel: UILabel!
    @IBOutlet weak var pickerIzquierda: UIPickerView!
    @IBOutlet weak var pickerDerecha: UIPickerView!
    
    var pickerViewIzquierda = ["Puar", "Pikachu", "Agumon", "Oolong", "Charmander"];
    var pickerViewDerecha = ["Dragon Ball", "Pokemon", "Digimon"];
    var valIzquierda = "";
    var valDerecha = "";

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //Métodos para configurar el PickerView
    func numberOfComponentsInPickerView(pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if (pickerView == pickerIzquierda) {
            return pickerViewIzquierda.count;
        } else {
            return pickerViewDerecha.count;
        }
    }
    
    func pickerView(pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if (pickerView == pickerIzquierda) {
            return pickerViewIzquierda[row];
        } else {
            return pickerViewDerecha[row];
        }
        
    }
    
    //Método para indicar qué hacer cuando un elemento del PickerView ha sido seleccionado
    func pickerView(pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        textLabel.text = "";
        if (pickerView == pickerIzquierda){
            imageIzquierda.image = UIImage(named: pickerViewIzquierda[row])
            valIzquierda = pickerViewIzquierda[row];
        }
        if (pickerView == pickerDerecha) {
            imageDerecha.image = UIImage(named: pickerViewDerecha[row])
            valDerecha = pickerViewDerecha[row]
        }
        
        if(valDerecha == "Pokemon" && (valIzquierda == "Pikachu" || valIzquierda == "Charmander")){
            textLabel.text = valIzquierda + " es un " + valDerecha
        }
        if(valDerecha == "Digimon" && valIzquierda == "Agumon"){
            textLabel.text = valIzquierda + " es un " + valDerecha
        }
        if(valDerecha == "Dragon Ball" && (valIzquierda == "Oolong" || valIzquierda == "Puar")){
            textLabel.text = valIzquierda + " es un " + valDerecha
        }
    }
    
}

